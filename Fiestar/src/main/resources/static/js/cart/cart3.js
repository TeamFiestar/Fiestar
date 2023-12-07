// 해당 상품 전체 선택 눌렀을 때 -> 

// 수량 선택 했을 때 숫자 

// X 버튼 눌렀을 때 

// value : input 태그만 가질 수 있는 입력값.
// innerHtml : 시작태그, 종료태그  사이에 작성된 글 + HTML 요소
// innerText : 시작태그, 종료태그  사이에 작성된 글 






const selectAll = document.getElementById("selectAll");
const selectEachList = document.getElementsByClassName("selectEach");
const totalPrice = document.getElementById("totalPrice");



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

const checkedPrice = () => {
   const checkList = document.querySelectorAll(".selectEach:checked");

   let sum = 0;


   // 
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
   
});


// 문서가 로딩 완료 되었을 때 checkedPrice() 수행
document.addEventListener('DOMContentLoaded', () => {
   checkedPrice() ;
});





// function howMany(selectObject) {
//    var numberSelected = 0;
//    for (var i = 0; i < selectObject.options.length; i++) {
//      if (selectObject.options[i].selected) {
//        numberSelected++;
//      }
//    }
//    return numberSelected;
//  }

//  var btn = document.getElementById("btn");
//  btn.addEventListener("click", function () {
//    alert(
//      "Number of options selected: " + howMany(document.selectForm.musicTypes),
