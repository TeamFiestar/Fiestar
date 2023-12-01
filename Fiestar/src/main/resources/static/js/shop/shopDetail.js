
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

  

  const productName = document.createElement("span");
  productName.classList.add("product-purchase-name");
  productName.innerHTML = "[아티스트명] 상품명";


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

  //--------------------------------------------------
  const span = document.createElement("span");

  const button1 = document.createElement("button");

  button1.classList.add("minus");
  button1.setAttribute('onclick', 'minusBtn(this)');

  button1.innerHTML = "-";

  const result = document.createElement("h4");

  result.classList.add("result");

  result.innerText = "1";

  const button2 = document.createElement("button");
  button2.classList.add("plus");
  button2.setAttribute('onclick', 'plusBtn(this)');
  button2.innerHTML ="+";


  // -------------------------------------------------
  const optionName = document.createElement("h4");
  optionName.classList.add("optionName");
  const optionValue2 = document.querySelector("select[name=key]").value;
  optionName.innerHTML= "[옵션] " + optionValue2;

  let selectedPrice = document.createElement("span");
  selectedPrice.classList.add("selected-price");

  selectedPrice.innerHTML = "₩" + productPrice.getAttribute("value").toLocaleString();

  // let totalcostNum = i*productPrice.getAttribute("value");
  // selectedprice.innerHTML = "₩" + totalcostNum.toLocaleString();

  
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



function plusBtn(e){
  let i = e.previousSibling.innerText;
  i++;
  console.log(e.previousSibling);
  e.previousSibling.textContent = i;
  let totalcostNum = i*productPrice.getAttribute("value");
  e.parentElement.nextElementSibling.textContent = "₩" + totalcostNum.toLocaleString();
  totalNoProduct( );
  totalCost();

};

// plus.addEventListener("click", () => {
//   i++
//   result.textContent = i;
//   let totalcostNum = i*productPrice.getAttribute("value");
//   selectedprice.textContent = "₩" + totalcostNum.toLocaleString();
//   totalProduct.textContent = "총 상품금액 (" + i +")개";
//   totalPrice.textContent = "₩" + totalcostNum.toLocaleString();

// });

function minusBtn(e) {
  let i = e.nextSibling.innerText;
  if(i>1) {
  
  i--;
  e.nextSibling.textContent = i;
  let totalcostNum = i*productPrice.getAttribute("value");
  e.parentElement.nextElementSibling.textContent = "₩" + totalcostNum.toLocaleString();
  totalPrice.textContent = "₩" + totalcostNum.toLocaleString();
  totalNoProduct( );
  totalCost()
  } 

};


function totalNoProduct( ) {

  const results = document.querySelectorAll(".result");

  let sum = 0;

  for(i of results) {
    console.log(i);
    sum += (Number)(i.innerText);

  }

  totalProduct.textContent = "총 상품금액 (" + sum +")개";
  totalProduct.setAttribute('value',sum);
  console.log(totalProduct.getAttribute('value'));
}


function totalCost() {

  selectedPrice = 110000;

  let total = 0;

  total = selectedPrice * totalProduct.value;


  totalPrice.textContent = "₩" + total.toLocaleString();


}

/* minus.addEventListener("click", () => {
  if(i>1) {
  i--
  result.textContent = i;
  let totalcostNum = i*productPrice.getAttribute("value");
  selectedprice.textContent = "₩" + totalcostNum.toLocaleString();
  totalProduct.textContent = "총 상품금액 (" + i +")개";
  totalPrice.textContent = "₩" + totalcostNum.toLocaleString();

  } 

}); */



