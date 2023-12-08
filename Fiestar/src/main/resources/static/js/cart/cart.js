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
console.log(xBtnList);

for(i = 0; i < xBtnList.length; i++) {
   xBtnList[i].addEventListener("click", e => {
      
      // e.target.parentElement.parentElement.children[1].remove();
      e.target.parentElement.parentElement.parentElement.remove();
      checkedPrice();   
      // for(let selectEach of selectEachList){
      //    selectEach.checked = selectAll.checked;
      // }
      
   // 이벤트가 발생 했을 때 -> checkedPrice(); 함수 호출
   


   });



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
      // e -> 발생한 이벤트 정보를 담고 있는 객체 ,  e.target -> 이벤트가 일어난 요소 
      let itemCount = e.target.previousElementSibling;
      itemCount.innerText = Number(itemCount.innerText) + 1;

      clacPrice(e.target)
      checkedPrice();

   });
}

for(let i=0; i<minusList.length; i++) {
   minusList[i].addEventListener("click", e => {
      // e -> 발생한 이벤트 정보를 담고 있는 객체 ,  e.target -> 이벤트가 일어난 요소 
      let itemCount = e.target.nextElementSibling;

      if(itemCount.innerText > 1 ) {
         itemCount.innerText = Number(itemCount.innerText) - 1;
         
         clacPrice(e.target);
         checkedPrice();
      }
   });
}

// 체크 상태가 변했을 때 checkedPrice(); 수행
document.addEventListener("change", e => {
   
   checkedPrice();
   
});



// 문서가 로딩 완료 되었을 때 checkedPrice() 수행
document.addEventListener('DOMContentLoaded', () => {
   checkedPrice() ;
});

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
   

// console.log(itemList.length);
// document.addEventListener("click", e => {
   // for문으로 아이템 리스트 조회 만약 itemList.lengh == 0 -> 제출 금지
   

// const itemList =  document.getElementsByClassName("item-list1");

// console.log(itemList.length);
// for(let i = 0; i < itemList.length; i++){
//    if(itemList.length < 1) {
//       alert("1개 이상 상품을 담아주세요")
//    }

// }


// const xBtnList = document.querySelectorAll(".x-btn");
// console.log(xBtnList);

// for(i = 0; i < xBtnList.length; i++) {
//    xBtnList[i].addEventListener("click", e => {

//       e.target.parentElement.parentElement.children[1].remove();
//       e.target.parentElement.parentElement.parentElement.remove();
      
//    });


// }




// ----------------------------------------------------------------------------

// 한 행 삭제 

// const xBtn = document.querySelector(".x-btn");

// xBtn.addEventListener("click", e => {

//    e.target.parentElement.parentElement.parentElement.remove();
   
// });


