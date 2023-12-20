// // 변경 버튼 클릭 -> 인풋에 채워진 텍스트 모두 지우기

// const nameChangeBtn = document.getElementById("nameChangeBtn");

// nameChangeBtn.addEventListener("click", e => {

//     let name = e.target.nextElementSibling[0];

//     if(!nameInput.trim().length == 0){
//         nameInPut = ("");
//     }

// });
const nameChangeBtn = document.getElementById("nameChangeBtn");

nameChangeBtn.addEventListener("click", e => {

    let nameInput = document.getElementById("nameInput");
    let address1 = document.getElementById(id = "sample6_address");
    let address2 = document.getElementById("sample6_detailAddress");
    let address3 = document.getElementById("sample6_postcode");

    if (nameInput.value.trim().length || address1.value.trim().length || address2.trim().length || address3.trim().length !== 0) {
        nameInput.value = ("");
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

purchaseBtn.addEventListener("click", e => {

    /* 유효성 검사 */

    let address1 = document.getElementById("id=sample6_address");
    let address2 = document.getElementById("sample6_detailAddress");
    let address3 = document.getElementById("sample6_postcode");
    let PhoneNumber = document.getElementById("ordererPhoneNumber");

    if (nameInput.value.trim().length == 0 || address1.value.trim().length == 0 || address2.trim().length == 0 ||
        address3.trim().length == 0 || PhoneNumber.value.trim().length == 0) {

        alert("주문자 정보를 입력해주세요.")
        e.preventDefault();
        return;
    }

    requestPay();


});


function requestPay() {
    var IMP = window.IMP;
    IMP.init("imp66683387");

    let selectedMethod = document.querySelector('input[name="payMethod"]:checked').value;
    let pgValue, methodValue;

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
        amount: 50000,

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
