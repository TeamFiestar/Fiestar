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


      } else {
         // "아니오"를 선택한 경우 아무 작업도 하지 않음
      }
   });
}


      for(let selectEach of selectEachList){
         selectEach.checked = selectAll.checked;
      }
      
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

         // 장바구니 총 가격을 다시 계산
         checkedPrice();
      }
   })

   .catch(error => console.error('Error:', error));

}



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
   const amountPrice = btn.parentElement.nextElementSibling

   

   // amountPrice.innerText = Number(itemCount.innerText) * Number(defaultPrice.innerText)

    amountPrice.innerText = (Number(itemCount.innerText) * Number(defaultPrice.innerText));
   
   // amountPrice = Number(itemCount.innerText) * Number(defaultPrice.innerText);
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
      
      // totalPrice.innerText = sum;

      totalPrice.innerText = ` '₩' + sum.tolocaleString()`;

      // ` ` 문자열 사이 변수를 사용해야 할 때 유용하게 사용 가능


      

   // totalPrice.textContent = "$" + sum.toLocaleString();
}


for(let i=0; i<plusList.length; i++) {
   plusList[i].addEventListener("click", e => {
      // let amountPrice = e.target.nextElementSibling.nextElementSibling.nextElementSibling;
      let cartNo = e.target.nextElementSibling.nextElementSibling;
      let itemCount = e.target.previousElementSibling;
      itemCount.innerText = Number(itemCount.innerText) + 1;  // 변경된 값
      
      if(itemCount.innerText > 10) {
         itemCount.innerText = 10;
         alert("1인당 최대 10개까지 구매 가능합니다.")
      }

      clacPrice(e.target);
      checkedPrice();
      let eachPrice = e.target.parentElement.nextElementSibling.innerText;


      // eachPrice.innerText = Number(eachPrice.innerText);
      // defaultPrice.innerText = Number(defaultPrice.innerText);


      // eachPrice.textContent = 
      
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


// // 체크 상태가 변했을 때 checkedPrice(); 수행
// document.addEventListener("change", e => {
//    console.log(checkedItems)
//    checkedPrice();
   
// });


// 각각의 selectEach 체크박스 상태 변경 시
// for (let selectEach of selectEachList) {
//    selectEach.addEventListener("change", e => {
//       checkedPrice(); // 총 가격 업데이트
//    });
// }

// selectAll 체크박스 상태 변경 시
selectAll.addEventListener("change", () => {
   for (let selectEach of selectEachList) {
      selectEach.checked = selectAll.checked;
   }
   checkedPrice(); // 총 가격 업데이트
});

// // 각각의 selectEach 체크박스 상태 변경 시
// for (let selectEach of selectEachList) {
//    selectEach.addEventListener("change", () => {
//       checkedPrice(); // 총 가격 업데이트
//    });
// }


// 각각의 selectEach 체크박스 상태 변경 시
for (let selectEach of selectEachList) {
   selectEach.addEventListener("change", e => {
      checkedPrice(); // 총 가격 업데이트
   });
}



document.addEventListener("change", e => {
   
   checkedPrice();
   
});



document.addEventListener('DOMContentLoaded', () => {

   document.addEventListener('DOMContentLoaded', () => {
      const priceElement = document.getElementById('price');
      const price = priceElement.innerText;
      priceElement.innerText = '₩' + Number(price).toLocaleString('ko-KR');
    });
   checkedPrice() ;
});



const itemList = document.getElementsByClassName("item-list");
console.log(itemList);
const form = document.getElementById("checkoutFrm");

// && checkList.length
form.addEventListener("submit", e => {
   
   if(itemList.length == 0 || totalPrice.innerText == 0) {
      alert("선택된 상품이 없습니다.")         
      e.preventDefault();
      return;
   }

});


// 결제 버튼 클릭 시 -> 아이템 리스트에서 체크 선택된 아이템 리스트 안에 포함된 장바구니 번호(폼 태그에 담김) 
// checkout 뷰로 전달

// const itemList = document.getElementsByClassName("item-list");
// const form = document.getElementById("checkoutFrm");
// const checkedItems = document.querySelectorAll(".selectEach:checked");

// // checkedItems의 default 밸류
// console.log(checkedItems)

// // for(let selectEach of selectEachList) {
// //    console.log(selectEach)
// // }

// form.addEventListener("submit", e => {

//    // const itemList = document.getElementsByClassName("item-list");

//    // 체크된 아이템의 밸류값
//    const checkedItems = document.querySelectorAll(".selectEach:checked");

//    // let checkOutCartNo = checkedItems.values

//    const form = document.getElementById("checkoutFrm");

//    // let checkedCartNo = checkedItems.values
 
//    console.log(checkedCartNo)

   // array.forEach(checkedCartNo => console.log(checkedCartNo) {
      
   // });


   // checkedCartNo.forEach((checkedCartNo) => {
   //    copyItems.push(item);
   //  });

   
//    if(itemList.length == 0 || totalPrice.innerText == 0) {
//       alert("선택된 상품이 없습니다.")         
//       e.preventDefault();
//       return;
//    }


// });







//  ---------------------------------------------------------------------


// function sendCheckoutRequest(checkedItems) {
//          fetch('checkout', { 
//              method: 'POST',
//              headers: {
//                  'Content-Type': 'application/json',
//              },
//              body: checkedCartNo
//          })
//          .then(response => { response.json(); })
//          .then(data => {
//              console.log('Checkout Success:', data);
//              // 성공적으로 처리된 후의 로직 (예: 페이지 리다이렉션)
//          })
//          .catch(error => {
//              console.error('Checkout Error:', error);
//          });
//      }


// ---------------------------------------------------------------------------
