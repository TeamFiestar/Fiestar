// 해당 상품 전체 선택 눌렀을 때 -> 

// 수량 선택 했을 때 숫자 

// X 버튼 눌렀을 때 

// value : input 태그만 가질 수 있는 입력값.
// innerHtml : 시작태그, 종료태그  사이에 작성된 글 + HTML 요소
// innerText : 시작태그, 종료태그  사이에 작성된 글 


const selectAll = document.getElementById("selectAll");
const selectEachList = document.getElementsByClassName("selectEach");
const totalPrice = document.getElementById("totalPrice");



// const xBtnList = document.querySelectorAll(".x-btn");
// console.log(xBtnList);

// for(i = 0; i < xBtnList.length; i++) {
//    xBtnList[i].addEventListener("click", e => {   
//       // e.target.parentElement.parentElement.children[1].remove();
//       e.target.parentElement.parentElement.parentElement.remove();
//       let cartNo = e.target.parentElement.previousElementSibling.previousElementSibling;
//       console.log(cartNo.innerText);     
//       cartNo.innerText = Number(cartNo.innerText);
//       checkedPrice();   

// for (i = 0; i < xBtnList.length; i++) {
//    xBtnList[i].addEventListener("click", e => {
//       // 클릭된 삭제 버튼을 기반으로 해당 상품 행을 삭제합니다.
//       let x = e.target.parentElement.parentElement.parentElement.remove();

//       // 장바구니 번호를 가져오고 숫자로 변환합니다.
//       let cartNo = e.target.parentElement.previousElementSibling.previousElementSibling;

//       let deletedCartNo = Number(cartNo.innerText); 

//       // 장바구니 번호를 가지고 서버에 삭제 요청을 보냅니다.
//       sendDeleteRequest(deletedCartNo);

//       // 장바구니 총 가격을 다시 계산합니다.
//       checkedPrice();
//    });
// }


const xBtnList = document.querySelectorAll(".x-btn");

for (let i = 0; i < xBtnList.length; i++) {
   xBtnList[i].addEventListener("click", e => {
      // 확인 메시지 표시
      const confirmation = confirm("선택하신 상품을 삭제하겠습니까?");

      if (confirmation) {
         // "예"를 선택한 경우 상품 삭제 처리
         const cartRow = e.target.parentElement.parentElement.parentElement;
         let cartNo = e.target.parentElement.previousElementSibling.previousElementSibling;
         let deletedCartNo = Number(cartNo.innerText);

         // 서버에 삭제 요청 보내기
         sendDeleteRequest(deletedCartNo);

         // 화면에서 상품 행 삭제
         cartRow.remove();

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

function sendDeleteRequest(cartNo) {
   fetch('cartPage', { // 서버의 엔드포인트 URL로 변경해야 합니다.
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      },
      body: JSON.stringify({ cartNo: cartNo })
   })
   .then(response => {
      if (response.ok) {
         // 성공적으로 삭제되면 아무것도 반환하지 않습니다.
         return;
      } else {
         throw new Error('상품 삭제에 실패했습니다.');
      }
   })
   .catch(error => console.error('Error:', error));
}


// selectEachList에서 checked 된 수가 0 -> 제출 금지

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

   amountPrice.innerText = Number(itemCount.innerText) * Number(defaultPrice.innerText);
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

   totalPrice.innerText = sum;
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



// // 문서가 로딩 완료 되었을 때 checkedPrice() 수행
// document.addEventListener('DOMContentLoaded', () => {
//    checkedPrice() ;
// });

document.addEventListener('DOMContentLoaded', () => {
   // 모든 장바구니 항목을 불러온다.
   checkedPrice() ;
});

   // --------------------------------------------------------

   // const itemList = document.querySelectorAll('.item-list');

   
   // itemList.forEach(item => {
      //     const eachCartNo = item.querySelector('.cartNo');
      
      //     // cartNoSpan이 존재하면 그 내용을 출력하거나 다른 작업을 수행합니다.
      //     if (eachCartNo) {
         //         console.log(eachCartNo.innerText); // cartNo 값을 콘솔에 출
         //     }
         // });
   // --------------------------------------------------------





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

// 체크 버튼이 클릭 되면 체크된 장바구니 번호 & 상품 가격을 
// 체크 버튼이 클릭되면 가격, 총 가격 넣기