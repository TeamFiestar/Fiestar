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