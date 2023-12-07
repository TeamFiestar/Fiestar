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

// (현재 가격 / 상품 수량) * 상품 수량 

// 디폴트 가격 * 수량 

// 디폴트 = 가격 * 수량 현재 가격으로 표시

// 현재 가격에서 이벤트 발생 시 

// plus 버튼
const plusList = document.querySelectorAll(".plus"); 

// minus 버튼
const minusList = document.querySelectorAll(".minus");

// 개별 가격 리스트
 const eachPriceList = document.querySelectorAll(".amount-price");

// const eachCountList = document.querySelectorAll(".item-count");



console.log(eachPriceList);

//   for(let i = 0; i<eachPriceList; i++){

//   }

// const price = docoment.querySelector(".amount-price");

// cartDefaultPrice = eachPriceList[i].value * itemCount 
//  상품 개별 가격 = cartDefaultPrice 



console.log(plusList);

for(let i=0; i<plusList.length; i++) {

   plusList[i].addEventListener("click", e => {

      
      // e -> 발생한 이벤트 정보를 담고 있는 객체 ,  e.target -> 이벤트가 일어난 요소 
      let itemCount = e.target.previousElementSibling;
      console.log(itemCount.innerText);
      itemCount.innerText = Number(itemCount.innerText) + 1;

      let defaultPrice = e.target.nextElementSibling;
      console.log(defaultPrice.innerText);
      defaultPrice.innerText = Number(defaultPrice.innerText + eachPriceList[i].innerText);



      // -------------------------------------------------------------------------------- //

      // 담겨있는 상품 가격[eachPriceList[i].innerText], 담겨있는 상품 수량  

      // i번째 배열의 상품 가격 * 수량 = 상품 가격  

      console.log(eachCountList);
      for(let i=0; i<eachCountList.length; i++){
         // let eachCount = eachCountList[i].innerText;
         // eachCount.innerText = Number(eachCountList[i].innerText);
         
      }

      for(let i = 0; i<eachPriceList.length; i++){
      
      // let defaultPrice = Number(eachPriceList[i].innerText) * Number(eachCountList[i].innerText);

      let eachPrice = eachPrice

      eachPrice.innerText = Number(eachCountList[i].innerText * eachPriceList[i].innerText);
       


      //  나올 가격 = Number(itemCount * eachPriceList[i].innerText);

      }  Number(eachPriceList[0].innerText)
      // let EachPrice = defaultPrice * itemCount;

      // for(let i = 0; i<eachPriceList.length; i++){

      //    let defaultPrice = eachPriceList[i].innerText;
      //    let eachPrice = Number(itemCount.innerText * defaultPrice.innerText);
      // }
      
//  개별 가격 = e.target.nextElementSibling * i번째 eachPriceList

      // let  =  * Number(itemCount.inner)

       // -------------------------------------------------------------------------------- //

   });
}


for(let i=0; i<minusList.length; i++) {

   minusList[i].addEventListener("click", e => {

      console.log
      // e -> 발생한 이벤트 정보를 담고 있는 객체 ,  e.target -> 이벤트가 일어난 요소 
      let itemCount = e.target.nextElementSibling;

      if(itemCount.innerText > 1 ) {
         itemCount.innerText = Number(itemCount.innerText) - 1;
         
      }

     else{

     }

   });
}

// 수량에 따라 가격 조정 

// const eachPriceList = document.querySelectorAll(".amount-price");

// for(let i=0; i<eachPriceList; i++){

//    eachPriceList[i].addEventListener("change", e => {

//       console.log

//       let itemCount =  
//    });
// }





// for(let i=0; i<minusList.length; i--) {

//    if( i> 0 ) {
//       itemCount.innerText = 1;
//    }


// else{

//    minusList[i].addEventListener("click", e => {
//       // e -> 발생한 이벤트 정보를 담고 있는 객체 ,  e.target -> 이벤트가 일어난 요소 
//       let itemCount = e.target.nextElementSibling;
//       itemCount.innerText = Number(itemCount.innerText) - 1;
   
//    });
// }
// }





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



