
// -----------------------------------------------------------------------

// 배송 일자 

let day = new Date();
let postDay = new Date();
postDay.setDate(postDay.getDate() + 7);


document.getElementById("delivery-date").innerText = day.getFullYear() + "." + parseInt(day.getMonth()+1) + "." + day.getDate() 
+ "  ~  " + postDay.getFullYear() + "." + parseInt(postDay.getMonth()+1) + "." + postDay.getDate();


//----------------------------------------------------------------------





// 옵션 선택하여 상품 추가

const optionSelect = document.querySelector("#optionSelect");
const container = document.querySelector(".product-purchase-list");
const optionValue2 = document.querySelector("select[name=key]");
const optionValue = document.querySelector("select[name=key] option:checked").selected;



optionSelect.addEventListener("change", () => {
  
  
  /* 같은 옵션 선택 시 밑으로 내려가지 않고 누적 작성 예정 */
  const nowSelectOption = optionSelect.selectedIndex; // 현재 선택된 select 몇번째 인덱스인지 얻어옴
  
  const selectOptionArea = document.querySelectorAll('.option-area');
  
  for(let i = 0 ; i < selectOptionArea.length ; i++){
    console.log(selectOptionArea[i].getAttribute('value'));
    if(selectOptionArea[i].getAttribute('value') == nowSelectOption){
      console.log(selectOptionArea[i].firstElementChild.children[2]); 
      
      plusBtn(selectOptionArea[i].firstElementChild.children[2]);
      optionSelect.selectedIndex = 0; //처음 옵션으로 되돌리기
   
      return;
    }
  
  }
  
  
  
  /* ************************************************** */
  
  
  
  const productName = document.createElement("span");
  productName.classList.add("product-purchase-name");
  productName.innerHTML = `[${artistGroupTitle}] ${nameProduct}`;
  
  
  const deleteList = document.createElement("span");
  deleteList.classList.add("remove-row");
  
  deleteList.innerHTML ="&times;";
  
  deleteList.addEventListener("click", e => {
    const parent = e.target.parentElement;
    const parent2 = e.target.parentElement.nextElementSibling;
    
    parent.remove();
    parent2.remove();
    totalNoProduct( );
    totalCost()
  });
  
  
  // -------------------------------------------------
  
  
  const optionArea = document.createElement("div");
  optionArea.classList.add("option-area");
  optionArea.setAttribute('value',nowSelectOption);
  
  //--------------------------------------------------
  const span = document.createElement("span");
  
  const button1 = document.createElement("button");
  
  button1.classList.add("minus");
  button1.setAttribute('onclick', 'minusBtn(this)');
  button1.setAttribute('type', 'button');
  
  button1.innerHTML = "-";
  
  const result = document.createElement("h4");
  
  result.classList.add("result");
  
  result.innerText = "1";
  
  const button2 = document.createElement("button");
  button2.classList.add("plus");
  button2.setAttribute('onclick', 'plusBtn(this)');
  button2.setAttribute('type', 'button');
  button2.innerHTML ="+";
  
  
  // -------------------------------------------------
  const optionName = document.createElement("h4");
  optionName.classList.add("optionName");
  const optionValue2 = document.querySelector("select[name=key]").value;
  optionName.innerHTML= "[옵션]" + optionValue2;
  optionSelect.selectedIndex = 0; //처음 옵션으로 되돌리기
  
  let selectedPrice = document.createElement("span");
  selectedPrice.classList.add("selected-price");
  
  let productPrice2 = productPrice.getAttribute("value");
  
  // selectedPrice.innerText = "₩" + productPrice2.toLocaleString();
  
  
  productName.append(deleteList);
  span.append(button1, result, button2, optionName);
  optionArea.append(span, selectedPrice);
  container.append(productName, optionArea);
  
  
  totalNoProduct( );
  totalCost()
  
});


const plus = document.querySelector(".plus");
const minus = document.querySelector(".minus");
const productPrice = document.querySelector(".product-price");
const selectedPrice = document.querySelector(".selected-price");

const totalProduct = document.querySelector("#totalProduct");
const totalPrice = document.querySelector("#totalPrice");
const count = document.getElementById("productCount");
const totalP = document.getElementById("total");






function plusBtn(e){
  let i = e.previousSibling.innerText;
  i++;
  // console.log(e.previousSibling);
  e.previousSibling.textContent = i;
  // let totalcostNum = i*productPrice.getAttribute("value");
  // e.parentElement.nextElementSibling.textContent = "₩" + totalcostNum.toLocaleString();
  totalNoProduct( );
  totalCost();
};



function minusBtn(e) {
  let i = e.nextSibling.innerText;
  if(i>1) {
  
  i--;
  e.nextSibling.textContent = i;
  // let totalcostNum = i*productPrice.getAttribute("value");
  // e.parentElement.nextElementSibling.textContent = "₩" + totalcostNum.toLocaleString();
  totalNoProduct( );
  totalCost();
  } 

};




function totalNoProduct( ) {

  const results = document.querySelectorAll(".result");

  let sum = 0;

  for(i of results) {
    console.log(i);
    sum += (Number)(i.innerText);
  }
  
  totalProduct.textContent = "총 상품금액 (" + sum +")";
  totalProduct.setAttribute('value',sum);

  const productValue = totalProduct.getAttribute('value');
  count.value = productValue;
}


function totalCost() {
  
  let total = 0;
  total = price * totalProduct.getAttribute('value');
  totalPrice.textContent = total;
  const totalPriceValue = total;
  totalP.value = totalPriceValue;
  console.log(totalP.getAttribute('value'));
  console.log(total);
}

totalCost();






const updateBtn = document.getElementById("updateBtn");
if(updateBtn != null){
updateBtn.addEventListener("click", ()=>{
  location.href = `/artistAdmin/${artistGroupTitle}/${productNo}/goodsModify`;
});
}


const deleteBtn = document.getElementById("deleteBtn");
if(deleteBtn != null){
  deleteBtn.addEventListener("click", ()=>{
  location.href = `/artistAdmin/${productNo}/goodsDelete`;
});

}



const form = document.getElementById("in-cart");

function send(action){

  if(!loginMember || totalProduct.getAttribute('value') == 0){
    
    if(totalProduct.getAttribute('value') == 0){
      alert("옵션을 선택해주세요");
    }else{
    alert("로그인 후 이용해 주십시오");
    }
  return;
}


  let url = null;

  if(action == 'addCart'){
    url = `/shop/shopDetail/${productNo}`;
    form.action = url;
    form.submit();


  }else if(action == 'purchase'){
    console.log({"productCount" : count.value, "totalPrice" : totalP.value});

    url = `/shop/shopDetail/directBuy/${productNo}`;
    fetch(url,{
      method : "POST",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify({"productCount" : count.value, "totalPrice" : totalP.value})
    })
    .then(resp => resp.text())
    .then(cartNo => {
  
      if(cartNo > 0){
        document.getElementById("selectEach").value = cartNo;
        form.action = "/checkout";
        form.submit();
      }
    })
  }
};



document.getElementById('in-cart').addEventListener('submit', (e) =>{
  if(!loginMember){
    e.preventDefault();
    alert("로그인 후 이용해 주십시오");
  }else if(totalProduct.getAttribute('value')){
    e.preventDefault();
    alert("옵션을 선택해주세요");
  }
})



form.addEventListener("submit", e=>{
    e.preventDefault();
});




