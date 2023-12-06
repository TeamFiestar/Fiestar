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

/////////////////////////////////////////////////////////////////////////////////////////////////////


const minusList = document.getElementsByClassName("input-minus");
const plusList = document.getElementsByClassName("input-plus");
const itemCountList = document.getElementsByClassName("item-amount");







for(let i = 0; i < minusList.length; i++){

   minusList[i].addEventListener("click", e => {
      minusItemCountFn(e.target, i);

   });
}





let plus = document.getElementsByClassName("input-plus");
let itemCount = document.getElementsByClassName("item-amount");  
let amountPrice = document.getElementsByClassName("amount-price");





      let i=0; i<input.value; i++


   }

   console.log(minus.innerText);
   if(i > 0) {
      i--
      itemCount.innnerText = i;
      let amountPriceNum = i*132;
      amountPrice.innerText = "$" + amountPriceNum.toLocaleString();
      
   } else {
      amountPrice.innerText = "$" + 0
   }

});


plus.addEventListener("click", () => {
   i++
   itemCount.innerText = i;
   let amountPriceNum = i*132;
   amountPrice.innerText = "$" + amountPriceNum.toLocaleString();

});



// const minus = document.getElementsByClassName("input-minus");
// const plus = document.getElementsByClassName("input-plus");




// let minus = document.getElementsByClassName("input-minus");
// let plus = document.getElementsByClassName("input-plus");
// let itemCount = document.getElementsByClassName("item-amount");  
// let amountPrice = document.getElementsByClassName("amount-price");


// minus.addEventListener("click", () => {
//    if(i > 0) {
//       i--
//       itemCount.textContent = i;
//       let amountPriceNum = i*132;
//       amountPrice.textContent = "$" + amountPriceNum.toLocaleString();
      
//    } else {
//       amountPrice.textContent = "$" + 0
//    }

// });


// plus.addEventListener("click", () => {
//    i++
//    itemCount.textContent = i;
//    let amountPriceNum = i*132;
//    amountPrice.textContent = "$" + amountPriceNum.toLocaleString();

// });


// 수량, 상품 하나 가격, 총 가격



// const productPrice = document.querySelector(".product-price");
// const selectedPrice = document.querySelector(".selected-price");

// const totalProduct = document.querySelector("#totalProduct");
// const totalPrice = document.querySelector("#totalPrice");


// const selectEach = document.getElementById("selectEach");

// const xBtn = document.getElementById("xBtn");

// const minus = document.getElementById("minus");

// const plus = document.getElementById("plus");

// selectAll 클릭 시 버튼 색상 변경

// selectAll.addEventListener("click", e => {






