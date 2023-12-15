function changeStatus(purchaseNo, thisStatus){

  console.log(purchaseNo);
  console.log(thisStatus.value);
  fetch("/artistAdmin/updatePurchaseStatus",{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify({purchaseNo : purchaseNo, purchaseStatus : thisStatus.value})
  })
  .then(resp => resp.json())
  .then(result =>{
    console.log(result);
  })
}


const modal = document.querySelector(".orderModal");
const modal_background = document.querySelector(".orderModal");
// const btnOpenPopup = document.querySelector(".btn-open-popup");
const modalCloseButton = document.querySelector(".modalCloseButton");

// btnOpenPopup.addEventListener("click", () => {
//   open();
// });

//Hide modal
modalCloseButton.addEventListener("click", () => {
  close();
});

window.addEventListener("click", (e) => {
  e.target === modal_background ? close() : false;
});

function close() {
  modal.classList.remove("show");
}
function open() {
  modal.classList.add("show");
}

function selectPurchaseDetail(purchaseNo){

  fetch('/artistAdmin/selectPurchaseDetails?purchaseNo=' + purchaseNo)
  .then( resp => resp.json())
  .then( purchaseDetail =>{

    console.log(purchaseNo);

    console.log(purchaseDetail);

    console.log(purchaseDetail.productList); 

    const orderNicknameInfo = document.querySelector('.order-nickname-info');
    const orderAddressInfo = document.querySelector('.order-address-info');
    const orderPriceInfo = document.querySelector('.order-price-info');
    const orderStatusInfo = document.querySelector('.order-status-info'); 

    let address = purchaseDetail.memberAddress;
    address = address.split('^^^').join(' ');

    orderNicknameInfo.textContent = purchaseDetail.memberNickname;
    orderAddressInfo.textContent = address;
    orderPriceInfo.textContent = purchaseDetail.purchasePrice;
    if(purchaseDetail.purchaseStatus == 1) orderStatusInfo.textContent = '주문 완료';
    if(purchaseDetail.purchaseStatus == 2) orderStatusInfo.textContent = '배송 준비';
    if(purchaseDetail.purchaseStatus == 3) orderStatusInfo.textContent = '배송 중';
    open();

    


  })
  .catch(err => console.log(err));

}