// 해당 상품 전체 선택 눌렀을 때 -> 

// 수량 선택 했을 때 숫자 

// X 버튼 눌렀을 때 

const selectAll = document.getElementById("selectAll");
const selectEachList = document.getElementsByClassName("selectEach");

selectAll.addEventListener("change", () => {

   for(let selectEach of selectEachList){
      selectEach.checked = selectAll.checked;
   }

});

// const selectEach = document.getElementById("selectEach");

// const xBtn = document.getElementById("xBtn");

// const minus = document.getElementById("minus");

// const plus = document.getElementById("plus");

// selectAll 클릭 시 버튼 색상 변경

// selectAll.addEventListener("click", e => {






