// 해당 상품 전체 선택 눌렀을 때 -> 

// 수량 선택 했을 때 숫자 

// X 버튼 눌렀을 때 

// value : input 태그만 가질 수 있는 입력값.
// innerHtml : 시작태그, 종료태그  사이에 작성된 글 + HTML 요소
// innerText : 시작태그, 종료태그  사이에 작성된 글 


const selectAll = document.getElementById("selectAll");

const selectEachList = document.getElementsByClassName("selectEach");

const totalPrice = document.getElementById("totalPrice");

const xBtnList = document.querySelectorAll(".x-btn");


// ---------------------- 삭제 ------------------------------

for (let i = 0; i < xBtnList.length; i++) {
   xBtnList[i].addEventListener("click", e => {
      // 확인 메시지 표시
      const confirmation = confirm("선택하신 상품을 삭제하겠습니까?");

      if (confirmation) {
         // "예"를 선택한 경우 상품 삭제 처리
         const row = e.target.parentElement.parentElement.parentElement;
         let cartNo = e.target.parentElement.previousElementSibling.previousElementSibling.children[4].innerText;

         console.log(cartNo);

         // 필요한 데이터 

         // 서버에 삭제 요청 보내기
         sendDeleteRequest(cartNo, row);

         // 장바구니 총 가격을 다시 계산
         checkedPrice();
         
      } else {
         // "아니오"를 선택한 경우 아무 작업도 하지 않음
      }
   });
}


      // for(let selectEach of selectEachList){
      //    selectEach.checked = selectAll.checked;
      // }
      
   // 이벤트가 발생 했을 때 -> checkedPrice(); 함수 호출

function sendDeleteRequest(cartNo, row) {
   fetch('cartPage', { 
      method: 'delete',
      headers: {
         'Content-Type': 'application/json',
      },

      body : cartNo
      // body: JSON.stringify({ cartNo: cartNo })
      // body : cartNo: Number(cartNo.innerText)
   })
   .then(response => response.text() )

   .then(result => {
      console.log(result);
      if(result > 0){
         row.remove();
      }
   })

   .catch(error => console.error('Error:', error));

}

// ----------------------------------------------------------

// function sendDeleteRequest(cartNo) {
//    fetch('cartPage', { 
//       method: 'delete',
//       headers: {
//          'Content-Type': 'application/json',
//       },
//       body: JSON.stringify({ cartNo: Number(cartNo.innerText) })

//    })
//    .then(response => {
//       if (response.ok) {
   
//          return;

//       } else {
//          throw new Error('상품 삭제에 실패했습니다.');
//       }
//    })
//    .catch(error => console.error('Error:', error));
// }

// ---------------------- 삭제 ------------------------------



selectAll.addEventListener("change", () => {

   // 향상된 for 문 selectEachList -> selectEach를 하나씩 꺼낸다.

   for(let selectEach of selectEachList){
      selectEach.checked = selectAll.checked;
   }
});


// plus 버튼
const plusList = document.querySelectorAll(".plus"); 

// minus 버튼
const minusList = document.querySelectorAll(".minus");

const cartNo = document.querySelectorAll(".cartNo");

const clacPrice = (btn) => {

   const itemCount = btn.parentElement.children[1];
   const defaultPrice = btn.parentElement.children[3];
   const amountPrice = btn.parentElement.nextElementSibling;

   // amountPrice = Number(itemCount.innerText) * Number(defaultPrice.innerText);
   

   amountPrice.innerText = Number(itemCount.innerText) * Number(defaultPrice.innerText)

   // amountPrice.textContent = amountPrice.innerText.toLocaleString();
   
}


// 체크된 상품의 가격 

const checkedPrice = () => {
   const checkList = document.querySelectorAll(".selectEach:checked");

   let sum = 0;
   // 체크리스트에서 체크 박스 하나씩 꺼내온다.

   for(let checkbox of checkList){
      const amountPrice = checkbox.parentElement.parentElement.parentElement.nextElementSibling.children[1].innerText;
      sum += Number(amountPrice);
   }

   totalPrice.textContent = "$" + sum.toLocaleString();
   // totalPrice.innerText = sum;
}





for(let i=0; i<plusList.length; i++) {
   plusList[i].addEventListener("click", e => {
      // let amountPrice = e.target.nextElementSibling.nextElementSibling.nextElementSibling;
      let cartNo = e.target.nextElementSibling.nextElementSibling;
      let itemCount = e.target.previousElementSibling;
      itemCount.innerText = Number(itemCount.innerText) + 1;  // 변경된 값
      
      clacPrice(e.target);
      checkedPrice();
      let eachPrice = e.target.parentElement.nextElementSibling.innerText;

      eachPrice.textContent = 
      
      console.log(eachPrice);
      // let newQuantity = itemCount.innerText; 
    

      sendUpdateRequest(cartNo, itemCount, eachPrice); // isSelected를 true로 가정

   });
}


for(let i=0; i<minusList.length; i++) {
   minusList[i].addEventListener("click", e => {
      // e -> 발생한 이벤트 정보를 담고 있는 객체 ,  e.target -> 이벤트가 일어난 요소
      
      let itemCount = e.target.nextElementSibling;
      let cartNo = e.target.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;
      if(itemCount.innerText > 1 ) {
         itemCount.innerText = Number(itemCount.innerText) - 1;
      
         clacPrice(e.target);
         checkedPrice();

      let eachPrice = e.target.parentElement.nextElementSibling.innerText;

      console.log(eachPrice);

         sendUpdateRequest(cartNo, itemCount, eachPrice);
      }
   });
}

function sendUpdateRequest(cartNo, itemCount, eachPrice) {
   fetch('cartPage', { 
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      },
      body: JSON.stringify({ cartNo: Number(cartNo.innerText), productCount: Number(itemCount.innerText),
          eachPrice: Number(eachPrice)})
   })
   .then(response => response.json())
   .then(productCount => console.log(itemCount.innerText),
   cartNo => console.log(cartNo.innerText),
    eachPrice => console.log(eachPrice))
   .catch(error => console.error('Error:', error));
}

// 체크 상태가 변했을 때 checkedPrice(); 수행
document.addEventListener("change", e => {
   
   checkedPrice();
   
});

// // selectAll 체크박스 상태 변경 시
// selectAll.addEventListener("change", () => {
//    for (let selectEach of selectEachList) {
//       selectEach.checked = selectAll.checked;
//    }
//    checkedPrice(); // 총 가격 업데이트
// });

// // 각각의 selectEach 체크박스 상태 변경 시
// for (let selectEach of selectEachList) {
//    selectEach.addEventListener("change", () => {
//       checkedPrice(); // 총 가격 업데이트
//    });
// }



document.addEventListener('DOMContentLoaded', () => {

   checkedPrice() ;
});



// ---------------------- 제출 버튼 --------------------------------

// 버튼 클릭 -> 체크된 장바구니 번호 넘기기 

const itemList = document.getElementsByClassName("item-list");
const form = document.getElementById("checkoutFrm");

// && checkList.length
form.addEventListener("submit", e => {
   if(itemList.length == 0 || totalPrice.innerText == 0) {
      alert("선택된 상품이 없습니다.")         
      e.preventDefault();
      return;
   }

});


// for (let i = 0; i < xBtnList.length; i++) {
//    xBtnList[i].addEventListener("click", e => {
//       // 확인 메시지 표시
//       const confirmation = confirm("선택하신 상품을 삭제하겠습니까?");

//       if (confirmation) {
//          // "예"를 선택한 경우 상품 삭제 처리
//          const row = e.target.parentElement.parentElement.parentElement;
//          let cartNo = e.target.parentElement.previousElementSibling.previousElementSibling.children[4].innerText;

//          console.log(cartNo);

//          // 필요한 데이터 

//          // 서버에 삭제 요청 보내기
//          sendDeleteRequest(cartNo, row);

//          // 장바구니 총 가격을 다시 계산
//          checkedPrice();
         
//       } else {
//          // "아니오"를 선택한 경우 아무 작업도 하지 않음
//       }
//    });
// }

// function sendCart(cartNo, row) {
//    fetch('cartPage', { 
//       method: 'delete',
//       headers: {
//          'Content-Type': 'application/json',
//       },

//       body : cartNo
//       // body: JSON.stringify({ cartNo: cartNo })
//       // body : cartNo: Number(cartNo.innerText)
//    })
//    .then(response => response.text() )

//    .then(result => {
//       console.log(result);
//       if(result > 0){
//          row.remove();
//       }
//    })

//    .catch(error => console.error('Error:', error));

// }


















// 체크 버튼이 클릭 되면    체크된 장바구니 번호 & 상품 가격을 

// 결제 버튼 누르면 체크된 장바구니만 담아서 checkout - result에 

//  결제 버튼 클릭 -> 체크된 장바구니 번호 & 


// 총 결제 금액을 업데이트하는 함수

// function updateTotalPrice() {
//    const checkList = document.querySelectorAll(".selectEach:checked");
//    let sum = 0;

//    for (let checkbox of checkList) {
//       const amountPrice = checkbox.parentElement.parentElement.parentElement.nextElementSibling.children[1].innerText;
//       sum += Number(amountPrice);
//    }

//    // 총 결제 금액 업데이트를 서버로 전송
//    fetch('/updateTotalPrice', {
//       method: 'POST',
//       headers: {
//          'Content-Type': 'application/json'
//       },
//       body: JSON.stringify({ totalPrice: sum })
//    })
//    .then(response => {
//       if (response.ok) {
//          // 서버로 업데이트 요청 성공 시에만 총 결제 금액 화면에 업데이트
//          totalPrice.innerText = sum;
//       } else {
//          throw new Error('총 결제 금액 업데이트에 실패했습니다.');
//       }
//    })
//    .catch(error => console.error('Error:', error));
// }

// // 체크 박스 상태 변경 시 호출
// document.addEventListener("change", e => {
//    if (e.target.classList.contains("selectEach")) {
//       updateTotalPrice();
//    }
// });

// // 수량 변경 시 호출
// for (let i = 0; i < minusList.length; i++) {
//    minusList[i].addEventListener("click", e => {
//       // ... 수량 감소 로직 ...
//       updateTotalPrice();
//    });
// }

// // 전체 선택 체크 박스 상태 변경 시 호출
// selectAll.addEventListener("change", () => {
//    // 향상된 for 문 selectEachList -> selectEach를 하나씩 꺼낸다.
//    for(let selectEach of selectEachList){
//       selectEach.checked = selectAll.checked;
//    }
// });

// // plus 버튼 클릭 시 호출
// for (let i = 0; i < plusList.length; i++) {
//    plusList[i].addEventListener("click", e => {
      
//       updateTotalPrice();
//    });
// }