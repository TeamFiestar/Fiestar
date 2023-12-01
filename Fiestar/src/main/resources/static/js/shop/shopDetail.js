
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




});


const plus = document.querySelector(".plus");
const minus = document.querySelector(".minus");
const productPrice = document.querySelector(".product-price");
const selectedprice = document.querySelector(".selected-price");
const result = document.querySelector(".result");


function plusBtn(e){
  let i = e.previousSibling.innerText;
  i++;
  console.log(e.previousSibling);
  e.previousSibling.textContent = i;
  let totalcostNum = i*productPrice.getAttribute("value");
  e.parentElement.nextElementSibling.textContent = "₩" + totalcostNum.toLocaleString();


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
  totalProduct.textContent = "총 상품금액 (" + i +")개";
  totalPrice.textContent = "₩" + totalcostNum.toLocaleString();

  } 

};

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


const totalProduct = document.querySelectorAll("#totalProduct");
const totalPrice = document.querySelectorAll("#totalPrice");

totalProduct.textContent = "총 상품금액 (" + i +")개";
totalPrice.textContent = "₩" + totalcostNum.toLocaleString();