const plus = document.querySelector(".plus");
const minus = document.querySelector(".minus");
const productPrice = document.querySelector(".product-price");
const selectedprice = document.querySelector(".selected-price");
const result = document.querySelector(".result");
const totalProduct = document.querySelector("#totalProduct");
const totalPrice = document.querySelector("#totalPrice");
let i = 1;

plus.addEventListener("click", () => {
  i++
  result.textContent = i;
  let totalcostNum = i*productPrice.getAttribute("value");
  selectedprice.textContent = "₩" + totalcostNum.toLocaleString();
  totalProduct.textContent = "총 상품금액 (" + i +")개";
  totalPrice.textContent = "₩" + totalcostNum.toLocaleString();

});

minus.addEventListener("click", () => {
  if(i>1) {
  i--
  result.textContent = i;
  let totalcostNum = i*productPrice.getAttribute("value");
  selectedprice.textContent = "₩" + totalcostNum.toLocaleString();
  totalProduct.textContent = "총 상품금액 (" + i +")개";
  totalPrice.textContent = "₩" + totalcostNum.toLocaleString();

  } 

});


let day = new Date();
let postDay = new Date();
postDay.setDate(postDay.getDate() + 7);

// let year = today.getFullYear();
// let month = today.getMonth() +1;
// let date = today.getDate();

document.getElementById("delivery-date").innerText = day.getFullYear() + "." + parseInt(day.getMonth()+1) + "." + day.getDate() 
+ "  ~  " + postDay.getFullYear() + "." + parseInt(postDay.getMonth()+1) + "." + postDay.getDate();


//----------------------------------------------------------------------

const optionSelect = document.querySelector("#optionSelect");
const container = document.querySelector(".product-purchase-list");

const optionValue = document.querySelector("select[name=key] option:checked").innerText;

console.log(optionValue);
optionSelect.addEventListener("change", () => {

  const productName = document.createElement("p");

  productName.classList.add("product-purchase-name");

  productName.innerHTML = "[아티스트명] 상품명";

  const optionArea = document.createElement("div");

  optionArea.classList.add("option-area");

  const span = document.createElement("span");

  const button1 = document.createElement("button");

  button1.classList.add("minus");

  button1.innerHTML = "-";

  const result = document.createElement("h4");

  result.classList.add("result");

  result.innerText = "1";

  const button2 = document.createElement("button");
  button2.classList.add("plus");
  button2.innerHTML ="+";

  const optionName = document.createElement("h4");
  optionName.classList.add("optionName");
  optionName.optionValue;

  const selectedPrice = document.createElement("span");
  selectedPrice.classList.add("selected-price");

  const deleteList = document.createElement("span");
  deleteList.classList.add("remove-row");

  deleteList.innerHTML ="&times;";

  deleteList.addEventListener("click", e => {
    const parent = e.target.parentElement;

    parent.remove();
  });


  span.append(button1, result, button2, optionName, deleteList);
  container.append(productName, span, selectedPrice)







});
