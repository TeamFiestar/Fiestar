// 



document.querySelectorAll(".default-price").forEach(item => {
    item.innerText = "₩" + Number(item.innerText).toLocaleString();
 })


 document.querySelectorAll(".tP").forEach(tP => {
    tP.innerText = "₩" + Number(tP.innerText).toLocaleString();
 })


 document.querySelectorAll(".totalProduct-price").forEach(totalProduct => {
    totalProduct.innerText = "₩" + Number(totalProduct.innerText).toLocaleString();
 })


 document.querySelectorAll(".purchasePrice").forEach(purchasePrice => {
    purchasePrice.innerText = "₩" + Number(purchasePrice.innerText).toLocaleString();
 })







const nameChangeBtn = document.getElementById("nameChangeBtn");

nameChangeBtn.addEventListener("click", e => {

    let nameInput = document.getElementById("nameInput");
    let PhoneNumber = document.getElementById("ordererPhoneNumber");
    let address1 = document.getElementById(id = "sample6_address");
    let address2 = document.getElementById("sample6_detailAddress");
    let address3 = document.getElementById("sample6_postcode");

    if (nameInput.value.trim().length || address1.value.trim().length || address2.trim().length || address3.trim().length
        || PhoneNumber.trim().length !== 0) {
 
        nameInput.value = ("");
        PhoneNumber.value = ("");
        address1.value = ("");
        address2.value = ("");
        address3.value = ("");
    }
});

const addressChangeBtn = document.getElementById("addressChange");

addressChangeBtn.addEventListener("click", e => {


    let address1 = document.getElementById("id=sample6_address");
    let address2 = document.getElementById("sample6_detailAddress");
    let address3 = document.getElementById("sample6_postcode");

    
    if (address1.value.trim().length || address2.trim().length || address3.trim().length !== 0) {
        address1.value = ("");
        address2.value = ("");
        address3.value = ("");
    }
});

// let payMethods = document.getElementsByClassName("payMethod");
//     let method;

//     for (let i = 0; i < payMethods.length; i++) {
//         if (payMethods[i].checked) {
//             method = payMethods[i].value;
//             console.log(method);
//         }
//     }


//     let payMethods = document.getElementsByClassName("payMethod");

// for (let i = 0; i < payMethods.length; i++) {
//     payMethods[i].addEventListener("change", function(getMethod) {
//         if (this.checked) {
//             let method = this.value;
//             console.log(method);
//         }
//     });
// }

let payMethods = document.getElementsByClassName("payMethod");

const updateCheckedMethod = () => {
    for (let i = 0; i < payMethods.length; i++) {
        if (payMethods[i].checked) {
            let checkedMethod = payMethods[i].value;
            methods = checkedMethod.value;
            console.log(checkedMethod);


        }
    }
};


for (let i = 0; i < payMethods.length; i++) {
    payMethods[i].addEventListener("change", updateCheckedMethod);
}

const form = document.getElementById("purchaseForm");
const purchaseBtn = document.getElementById("purchaseBtn");
const payMethod = document.getElementsByClassName("paymethod");

function addBlurEventListener(inputField, validationFunction) {
    let isFieldEdited = false;  // 필드가 수정되었는지 추적하는 변수

    // 'input' 이벤트 리스너: 사용자가 필드를 수정하면 isFieldEdited를 true로 설정
    inputField.addEventListener('input', () => {
        isFieldEdited = true;
    });

    // 'blur' 이벤트 리스너: 필드에서 포커스가 벗어났을 때 유효성 검사 수행
    inputField.addEventListener('blur', () => {
        if (isFieldEdited) {  // 필드가 수정된 경우에만 유효성 검사 수행
            validationFunction(inputField);
        }
    });
}

// 입력 필드의 유효성 상태를 업데이트하는 함수
function updateFieldValidity(field, isValid) {
    if (isValid) {
        field.classList.remove('invalid');
        field.style.border = "";  // 빨간 테두리 제거

    } else {
        field.classList.add('invalid');
        // field.style.border = "1px solid red";  // 빨간 테두리 추가

        if (document.activeElement !== field) {
            field.focus(); // 유효성 검사에 실패한 필드에 포커스 (포커스가 이미 있는 경우 제외)
        }
    }
}

function validateOrderInfo() {

    let isValid = true;

    // 주문자명 검사 (한글 2~10글자)
    const nameInput = document.getElementById("nameInput");
    const namePattern = /^[가-힣]{2,10}$/;
    if (!namePattern.test(nameInput.value.trim())) {
        alert("주문자명은 한글 2~10글자로 입력해주세요.");
        updateFieldValidity(nameInput, false);
        isValid = false;
        return false;


    } else {
        updateFieldValidity(nameInput, true);
    }

    // 전화번호 검사 (예: 01012345678)
    const phoneNumber = document.getElementById("ordererPhoneNumber");
    const phonePattern = /^\d{2,3}\d{3,4}\d{4}$/;
    if (!phonePattern.test(phoneNumber.value.trim())) {
        alert("전화번호 형식이 올바르지 않습니다. 예: 01012345678");
        updateFieldValidity(phoneNumber, false);
        isValid = false;
        return false;

    } else {
        updateFieldValidity(phoneNumber, true);
    }

    // 주소 검사 (빈 값이 아닌지)
    const address1 = document.getElementById("sample6_address");
    const address2 = document.getElementById("sample6_detailAddress");
    const address3 = document.getElementById("sample6_postcode");
    if (address1.value.trim().length === 0 || address2.value.trim().length === 0 || address3.value.trim().length === 0) {
        
        updateFieldValidity(address1, false);
        updateFieldValidity(address2, false);
        updateFieldValidity(address3, false);
        alert("주소가 올바르지 않습니다.");
        isValid = false;
        return false;

    } else {
        updateFieldValidity(address1, true);
        updateFieldValidity(address2, true);
        updateFieldValidity(address3, true);
    }



    let isMethodSelected = false;

    for (let i = 0; i < payMethods.length; i++) {
        if (payMethods[i].checked) {
            isMethodSelected = true;
            break;
        }
    }

    if (!isMethodSelected) {
        alert("결제 수단을 선택해주세요.");
        return false;
    }


   
    return isValid;
}



// 주문 정보 유효성 검사 함수

// 각 입력 필드에 'keyup' 이벤트 리스너 추가
// addKeyUpEventListener(document.getElementById("nameInput"), () => validateOrderInfo());
// addKeyUpEventListener(document.getElementById("ordererPhoneNumber"), () => validateOrderInfo());
// addKeyUpEventListener(document.getElementById("sample6_address"), () => validateOrderInfo());
// addKeyUpEventListener(document.getElementById("sample6_detailAddress"), () => validateOrderInfo());
// addKeyUpEventListener(document.getElementById("sample6_postcode"), () => validateOrderInfo());

// 결제 버튼 클릭 시 유효성 검사 후 결제 진행
// const purchaseBtn = document.getElementById("purchaseBtn");

// 문제점
// 유효성 검사를 한 번 실행하고 다음 입력 때 유효하지 않은 값을 입력하면 계속 alert


purchaseBtn.addEventListener("click", (e) => {



    if (!validateOrderInfo()) {




        // 유효성 검사 실패 시, 결제 중단

        const payMethods = document.getElementsByClassName("payMethod");

        
        e.preventDefault();


    } else {
       
        // 유효성 검사 통과 시, 결제 진행
        requestPay();
    }
});



// // 주문 정보 유효성 검사 함수
// function validateOrderInfo() {
//     // ...[기존 코드]...

//     // 결제 수단 검사
//     const payMethods = document.getElementsByClassName("payMethod");
//     let isMethodSelected = false;
//     for (let i = 0; i < payMethods.length; i++) {
//         if (payMethods[i].checked) {
//             isMethodSelected = true;
//             break;
//         }
//     }

//     if (!isMethodSelected) {
//         alert("결제 수단을 선택해주세요.");
//         return false;
//     }

//     return true; // 모든 검사 통과
// }






















// 결제 함수

function requestPay() {
    var IMP = window.IMP;
    IMP.init("imp66683387");

    let selectedMethod = document.querySelector('input[name="payMethod"]:checked').value;
    let pgValue, methodValue;
    // let amountPrice = document.getElementById("alll").value;

    if (selectedMethod == "1") { // 토스페이 선택 
        pgValue = 'tosspay.tosstest';
        methodValue = 'trans';

    } else if (selectedMethod == "2") { // 카카오 페이 선택 
        pgValue = 'kakaopay.TC0ONETIME';
        methodValue = 'card';
    }

    IMP.request_pay({
        pg: pgValue,
        pay_method: methodValue,
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: '주문명:FiestarShop',
        amount: 1,

    }, function (rsp) {
        if (rsp.success) {
            console.log(rsp);
            alert("주문이 완료 되었습니다.")

            form.submit(); // 폼태그 제출
            
            // window.location.href = "https://localhost/checkoutResult";
        } else {
            // 결제 실패 시 로직
            console.log(rsp);
            alert("결제에 실패했습니다.");
        }
    });
}


// 주소 API

function sample6_execDaumPostcode() {
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
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}
