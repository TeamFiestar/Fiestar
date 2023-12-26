

// ------------------------------------------------------

/* 회원 가입 유효성 검사 */
// .comfirm : 초록색 / / .error : 빨간색 / / 아무것도 없음 : 검정색

/* 모든 입력이 유효성 검사가 진행 되었는지 체크할 객체를 생성 */
const checkObj = {
    "memberEmail" : false,
    "authKey" : true,
    "memberPw" : false,
    "memberPwConfirm" : false,
    "memberNickname" : false,
    "memberAddress" : false
};

/* 이메일 유효성 검사 */
// 1) 이메일 유효성 검사에 사용할 요소 모두 얻어오기
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");

// 2) 이메일이 엽력될 때 마다 유효성 검사 실행
memberEmail.addEventListener("input", () => {

    // 3) 입력된 이메일이 없을경우
    if (memberEmail.value.trim().length == 0){


        memberEmail.value = '';
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요";
        emailMessage.classList.remove("confirm", "error");

        // checkObj의 memberEmail 값을 false로 변경
        // == 이메일이 유효하지 않음을 의미

        checkObj.memberEmail = false;
        return
    }

    // 정규식 검사
    const regEx = /^[A-Za-z\d\-\_]{4,}@[가-힣\w\-\_]+(\.\w+){1,3}$/;

    /*================ 이메일 중복검사 (비동기) ================*/





    // 입력 받은 이메일이 정규식과 일치하는 경우
    if(regEx.test(memberEmail.value) ){
        fetch("/member/checkEmail?email="+memberEmail.value)
        .then( response => response.text() )
        .then( result => {
    
            if(result == 0){ // 중복 X
                emailMessage.innerText = "사용 가능한 이메일 입니다"
                emailMessage.classList.add("confirm"); // 초록색 글씨
                emailMessage.classList.remove("error"); // 빨간글씨 제거
                checkObj.memberEmail = true;
            }else{ // 중복 O
                emailMessage.innerText = "이미 사용중인 이메일 입니다"
                emailMessage.classList.add("error"); // 초록색 글씨
                emailMessage.classList.remove("confirm"); // 빨간글씨 제거
                checkObj.memberEmail = false;
            }
    
        })
        
    .catch( e => console.log(e) )
    }

    // 입력 받은 이메일이 정규식과 일치하지 않는경우
    else{
        emailMessage.innerText = "알맞은 이메일 형식으로 작성해주세요"
        emailMessage.classList.add("error"); // 초록색 글씨
        emailMessage.classList.remove("confirm"); // 빨간글씨 제거
        checkObj.memberEmail = false;
    }
    
});




//========================== 이메일 인증 =============================

/* 인증번호 메일로 보내기 + DB INSERT */
const sendAuthKeyBtn = document.getElementById("sendAuthKeyBtn");
const authKeyMessage = document.getElementById("authKeyMessage");
const timeText = document.getElementById("time-text");

// 인증번호 보내기 버튼을 클릭하면
// authKeyMessage에 5분 타이머를 클릭
let authTimer;
let authMin = 4;
let authSec = 59;

// 인증번호를 보낸 이메일을 저장할 변수
let tempEmail;

// 인증번호 받기 버튼 클릭 시
sendAuthKeyBtn.addEventListener("click", function(){
    authMin = 4;
    authSec = 59;

    checkObj.authKey = false;

    if(checkObj.memberEmail){ // 중복이 아닌 이메일인 경우


        /* fetch() API - POST방식, 단일 데이터 요청 */
        fetch("/email/signup", {
            method : "POST",
            headers : {"Content-Type" : "application/text"},
            body : memberEmail.value // 전달되는 데이터가 한 개
        })
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                console.log("인증 번호가 발송되었습니다.")
                tempEmail = memberEmail.value;
            }else{
                console.log("인증번호 발송 실패")
            }
        })
        .catch(err => {
            console.log("이메일 발송 중 에러 발생");
            console.log(err);
        });
        

        alert("인증번호가 발송 되었습니다.");
        timeText.innerText='남은 시간 :';

        
        authKeyMessage.innerText = "05:00";
        authKeyMessage.classList.remove("confirm");

        authTimer = window.setInterval(()=>{

            authKeyMessage.innerText = "0" + authMin + ":" + (authSec<10 ? "0" + authSec : authSec);
            
            // 남은 시간이 0분 0초인 경우
            if(authMin == 0 && authSec == 0){
                checkObj.authKey = false;
                clearInterval(authTimer);
                return;
            }

            // 0초인 경우
            if(authSec == 0){
                authSec = 60;
                authMin--;
            }


            authSec--; // 1초 감소

        }, 1000)

    } else{
        alert("중복되지 않은 이메일을 작성해주세요.");
        memberEmail.focus();
    }

});  

/* 인증번호 확인 */
const authKey = document.getElementById("authKey");
const checkAuthKeyBtn = document.getElementById("checkAuthKeyBtn");

checkAuthKeyBtn.addEventListener("click", function(){


    if(authMin > 0 || authSec > 0){ // 시간 제한이 지나지 않은 경우에만 인증번호 검사 진행
        /* fetch API */
        const obj = {"inputKey":authKey.value, "email":tempEmail}

        console.log(authKey.value);
        console.log(tempEmail);

        fetch("/email/checkAuthKey",  {
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(obj)
        })
        .then(resp => resp.text())
        .then(result => {
          console.log(result);
            if(result > 0){
                clearInterval(authTimer);
                authKeyMessage.innerText = "인증되었습니다.";
                timeText.innerText = "";
                authKeyMessage.classList.add("confirm");
                checkObj.authKey = true;

                authKey.disabled = true;
            } else{
                alert("인증번호가 일치하지 않습니다.")
                checkObj.authKey = false;
            }
        })
        .catch(err => console.log(err));


    } else{
        alert("인증 시간이 만료되었습니다. 다시 시도해주세요.")
    }

});


//====================================================================


/*  비밀번호/비밀번호 확인 유효성 검사 */
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");
const pwConfirmMessage = document.getElementById("pwConfirmMessage");

// 비밀번호 입력 시 유효성 검사
memberPw.addEventListener("input", () => {

    // 비밀번호가 입력되지 않은 경우
    if(memberPw.value.trim().length == 0){
        memberPw.value = ""; // 띄어쓰지 못넣게 하기

        pwMessage.innerText = "영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요.";
        pwMessage.classList.remove("confirm", "error"); // 검정 글씨

        checkObj.memberPw = false; // 빈칸 == 유효 X
        return;
    }


    // 정규 표현식을 이용한 비밀번호 유효성 검사

    // 영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이
    const regEx = /^[a-zA-Z0-9\!\@\#\-\_]{6,20}$/;

    // 입력한 비밀번호가 유효한 경우
    if(regEx.test(memberPw.value)){
        checkObj.memberPw = true; 
        
        // 비밀번호가 유효하게 작성된 상태에서
        // 비밀번호 확인이 입력되지 않았을 때
        if(memberPwConfirm.value.trim().length == 0){

            pwMessage.innerText = "유효한 비밀번호 형식입니다";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
        }
        
        // 비밀번호가 유효하게 작성된 상태에서
        // 비밀번호 확인이 입력되어 있을 때
        else{
            pwMessage.innerText = "유효한 비밀번호 형식입니다";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
            // 비밀번호 == 비밀번호 확인  (같을 경우)
            if(memberPw.value == memberPwConfirm.value){
              pwConfirmMessage.innerText = "비밀번호가 일치합니다";
              pwConfirmMessage.classList.add("confirm");
              pwConfirmMessage.classList.remove("error");
                checkObj.memberPwConfirm = true;
                
            } else{ // 다를 경우
              pwConfirmMessage.innerText = "비밀번호가 일치하지 않습니다";
              pwConfirmMessage.classList.add("error");
              pwConfirmMessage.classList.remove("confirm");
                checkObj.memberPwConfirm = false;
            }
        }

        
    } else{ // 유효하지 않은 경우
        
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
        checkObj.memberPw = false; 
    }
});


// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener('input', ()=>{

    if(checkObj.memberPw){ // 비밀번호가 유효하게 작성된 경우에

        // 비밀번호 == 비밀번호 확인  (같을 경우)
        if(memberPw.value == memberPwConfirm.value){
          pwConfirmMessage.innerText = "비밀번호가 일치합니다";
          pwConfirmMessage.classList.add("confirm");
          pwConfirmMessage.classList.remove("error");
            checkObj.memberPwConfirm = true;
            
        } else{ // 다를 경우
          pwConfirmMessage.innerText = "비밀번호가 일치하지 않습니다";
          pwConfirmMessage.classList.add("error");
          pwConfirmMessage.classList.remove("confirm");
            checkObj.memberPwConfirm = false;
        }

    } else { // 비밀번호가 유효하지 않은 경우
        checkObj.memberPwConfirm = false;
    }
});



/* 닉네임 유효성 검사 */
const memberNickname =document.getElementById("memberNickname");
const nickMessage = document.getElementById("nickMessage");

memberNickname.addEventListener("input", () => {

    if (memberNickname.value.trim().length == 0){


        // memberNickname = '';
        nickMessage.innerText = "한글,영어,숫자로만 2~10글자";
        nickMessage.classList.remove("confirm", "error");

        // checkObj의 memberEmail 값을 false로 변경
        // == 이메일이 유효하지 않음을 의미

        checkObj.memberNickname = false;
        return
    }

    // 정규식 검사
    const regEx = /^[가-힣\w\d]{2,10}$/;

    // 입력 받은 이메일이 정규식과 일치하는 경우
    if(regEx.test(memberNickname.value) ){

        fetch("/member/checkNickname?nickname="+memberNickname.value)
        .then( response => response.text() )
        .then( result => {
    
            if(result == 0){ // 중복 X
                nickMessage.innerText = "사용 가능한 닉네임 입니다"
                nickMessage.classList.add("confirm"); // 초록색 글씨
                nickMessage.classList.remove("error"); // 빨간글씨 제거
                checkObj.memberNickname = true;
            }else{ // 중복 O
                nickMessage.innerText = "이미 사용중인 닉네임 입니다"
                nickMessage.classList.add("error"); // 초록색 글씨
                nickMessage.classList.remove("confirm"); // 빨간글씨 제거
                checkObj.memberNickname = false;
            }
        })
    

    }

    // 입력 받은 이메일이 정규식과 일치하지 않는경우
    else{
        nickMessage.innerText = "알맞은 닉네임 형식으로 작성해주세요"
        nickMessage.classList.add("error"); // 초록색 글씨
        nickMessage.classList.remove("confirm"); // 빨간글씨 제거
        checkObj.memberNickname = false;
    }

});



/* 주소 유효성 검사 */
const memberAddress = document.getElementById("memberAddress");
const memberAddressDetail = document.getElementById("memberAddressDetail");
const addrMessage = document.getElementById("addrMessage");

memberAddress.addEventListener('input', () => {

    if(memberAddress.value.length == 0 && memberAddressDetail.value.length == 0){
        checkObj.memberAddress = false;
    }
    else{
        checkObj.memberAddress = true;
    }

});




/* 회원 가입 버튼이 클릭 되었을 때 */
document.getElementById("signUpFrm").addEventListener("submit", e => {
    /* checkObj의 모든 값을 검사해서
       하나라도 false이면 가입 시도 X */

    // 객체 전용 향상된 for문 ( for ... in )

    for(let key in checkObj){

        // 객체에서 얻어온 값이 false 인 경우
        // 유효하지 않은 것이 있을 경우
        if(!checkObj[key]){
            let str
            switch(key){
                case "memberEmail" : str = "이메일이 유효하지 않습니다"; break;

                case "memberPw" : str = "비밀번호가 유효하지 않습니다"; break;

                case "memberPwConfirm" : str = "비밀번호가 일치하지 않습니다"; break;

                case "memberNickname" : str = "닉네임이 유효하지 않습니다"; break;

                case "memberAddress" : str = "주소가 입력되지 않았습니다"; break;

                case "authKey" : str = "인증번호가 유효하지 않습니다"; break;
            }

            alert(str);

            // key == input id 속성 값
            // 유효하지 않은 input 태그로 focus 맞춤
            document.getElementById(key).focus();
            e.preventDefault(); // form 제출 X
            return;
        }
    }
});



function selectAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수


            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            // if(data.userSelectedType === 'R'){
            //     // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            //     // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            //     if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
            //         extraAddr += data.bname;
            //     }
            //     // 건물명이 있고, 공동주택일 경우 추가한다.
            //     if(data.buildingName !== '' && data.apartment === 'Y'){
            //         extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            //     }
            //     // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            //     if(extraAddr !== ''){
            //         extraAddr = ' (' + extraAddr + ')';
            //     }
            //     // 조합된 참고항목을 해당 필드에 넣는다.
            //     document.getElementById("sample6_extraAddress").value = extraAddr;

            // } else {
            //     document.getElementById("sample6_extraAddress").value = '';
            // }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("memberAddress").value = addr;
            checkObj.memberAddress = true;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("memberAddressDetail").focus();
        }
    }).open();
}
