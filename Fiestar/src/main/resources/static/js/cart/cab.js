
const selectAll = document.getElementById("selectAll");
const selectEachList = document.getElementsByClassName("selectEach");
const totalPrice = document.getElementById("totalPrice");
const xBtnList = document.querySelectorAll(".x-btn");

for (let i = 0; i < xBtnList.length; i++) {
   xBtnList[i].addEventListener("click", e => {
      const confirmation = confirm("선택하신 상품을 삭제하겠습니까?");
      if (confirmation) {
         const row = e.target.parentElement.parentElement.parentElement;
         let cartNo = e.target.parentElement.previousElementSibling.previousElementSibling.children[4].innerText;
         sendDeleteRequest(cartNo, row);
      }
   });
}

function sendDeleteRequest(cartNo, row) {
   fetch('cartPage', { 
      method: 'delete',
      headers: {
         'Content-Type': 'application/json',
      },
      body: JSON.stringify({ cartNo: cartNo })
   })
   .then(response => response.text())
   .then(result => {
      if(result > 0){
         row.remove();
         checkedPrice();
      }
   })
   .catch(error => console.error('Error:', error));
}

selectAll.addEventListener("change", () => {
   for(let selectEach of selectEachList){
      selectEach.checked = selectAll.checked;
   }
});

const plusList = document.querySelectorAll(".plus"); 
const minusList = document.querySelectorAll(".minus");

const clacPrice = (btn) => {
   const itemCount = btn.parentElement.children[1];
   const defaultPrice = btn.parentElement.children[3];
   const amountPrice = btn.parentElement.nextElementSibling;
   amountPrice.innerText = '₩' + (Number(itemCount.innerText) * Number(defaultPrice.innerText.replace('₩', '').replace(/,/g, ''))).toLocaleString();
}

const checkedPrice = () => {
   const checkList = document.querySelectorAll(".selectEach:checked");
   let sum = 0;
   for(let checkbox of checkList){
      const amountPrice = checkbox.parentElement.parentElement.parentElement.nextElementSibling.children[1].innerText.replace('₩', '').replace(/,/g, '');
      sum += Number(amountPrice);
   }
   totalPrice.innerText = '₩' + sum.toLocaleString();
}

for(let i=0; i<plusList.length; i++) {
   plusList[i].addEventListener("click", e => {
      let cartNo = e.target.nextElementSibling.nextElementSibling;
      let itemCount = e.target.previousElementSibling;
      itemCount.innerText = Number(itemCount.innerText) + 1;  
      if(itemCount.innerText > 10) {
         itemCount.innerText = 10;
         alert("1인당 최대 10개까지 구매 가능합니다.")
      }
      clacPrice(e.target);
      checkedPrice();
      let eachPrice = e.target.parentElement.nextElementSibling.innerText;
      eachPrice.innerText = '₩' + Number(eachPrice.innerText).toLocaleString();
      sendUpdateRequest(cartNo, itemCount, eachPrice);
   });
}

for(let i=0; i<minusList.length; i++) {
   minusList[i].addEventListener("click", e => {
      let itemCount = e.target.nextElementSibling;
      let cartNo = e.target.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;
      if(itemCount.innerText > 1 ) {
         itemCount.innerText = Number(itemCount.innerText) - 1;
         clacPrice(e.target);
         checkedPrice();
         let eachPrice = e.target.parentElement.nextElementSibling.innerText;
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
      body: JSON.stringify({
         cartNo: Number(cartNo.innerText),
         productCount: Number(itemCount.innerText),
         eachPrice: Number(eachPrice.replace('₩', '').replace(/,/g, ''))
      })
   })
   .then(response => response.json())
   .then(data => console.log('Update Success:', data))
   .catch(error => console.error('Error:', error));
}

selectAll.addEventListener("change", () => {
   for (let selectEach of selectEachList) {
      selectEach.checked = selectAll.checked;
   }
   checkedPrice();
});

for (let selectEach of selectEachList) {
   selectEach.addEventListener("change", e => {
      checkedPrice();
   });
}

document.addEventListener("change", e => {
   checkedPrice();
});

document.addEventListener('DOMContentLoaded', () => {
   const defaultPrices = document.querySelectorAll(".defaultPrice"); // defaultPrice 클래스를 가진 모든 요소 선택
   defaultPrices.forEach(price => {
      price.innerText = '₩' + Number(price.innerText.replace('₩', '').replace(/,/g, '')).toLocaleString();
   });
   checkedPrice();
});

const itemList = document.getElementsByClassName("item-list");
const form = document.getElementById("checkoutFrm");

form.addEventListener("submit", e => {
   if(itemList.length == 0 || totalPrice.innerText == '₩' + 0) {
      alert("선택된 상품이 없습니다.")         
      e.preventDefault();
      return;
   }
});



// ...[나머지 코드]...
