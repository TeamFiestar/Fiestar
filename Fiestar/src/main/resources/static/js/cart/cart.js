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

const plus = document.querySelector(".plus");
const minus = document.querySelector(".minus");
const each = document.querySelector(".item-count");
const price = docoment.querySelector(".amount-price");


let a = 1;
let b = 132;

plus.addEventListener("click", () => {
   a++;
   each.innerText = a;
   price.innerText = a*b;
   console.log("")

});

minus.addEventListener("click", () => {
   if(a > 1){
      a--;
      each.innerText = a;

   }

});









// const minusList = document.getElementsByClassName("input-minus");
// const plusList = document.getElementsByClassName("input-plus");

// - or + 버튼 동작이 발생하면 수량의 개수를 변경

// const itemCountList = document.getElementsByClassName("item-amount");


// let minus = document.getElementsByClassName("input-minus")






// for(let i = 0; i < minusList.length; i++){

//    minusList[i].addEventListener("click", e => {
//       minusItemCountFn(e.target, i);

//    });
// }





// let plus = document.getElementsByClassName("input-plus");
// let itemCount = document.getElementsByClassName("item-amount");  
// let amountPrice = document.getElementsByClassName("amount-price");





//       let i=0; i<input.value; i++


//    }

//    console.log(minus.innerText);
//    if(i > 0) {
//       i--
//       itemCount.innnerText = i;
//       let amountPriceNum = i*132;
//       amountPrice.innerText = "$" + amountPriceNum.toLocaleString();
      
//    } else {
//       amountPrice.innerText = "$" + 0
//    }

// });


// plus.addEventListener("click", () => {
//    i++
//    itemCount.innerText = i;
//    let amountPriceNum = i*132;
//    amountPrice.innerText = "$" + amountPriceNum.toLocaleString();

// });



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



