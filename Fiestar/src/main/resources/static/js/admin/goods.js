const plus = document.querySelector("input-goodsPlus");
const optionArea = document.getElementById("option-area");
const inputArea = document.getElementById("input-area");

function optionPlus() {
  const div = document.createElement("div");

  const input = document.createElement("input");
  input.classList.add("input-goodsOption");
  input.setAttribute("type", "text");

  const img = document.createElement("img");
  img.classList.add("input-goodsMinus");
  img.setAttribute("src", "../../static/img/🦆 icon _minus_.png");

  div.append(img, input);

  inputArea.append(div);
}

plus.addEventListener("click", () => {
  optionPlus();
});

// function goods() {
//   const div = document.createElement("div");
//   div.classList.add("goods-add");

//   const h1 = document.createElement("h1");
//   h1.innerText = "굿즈 등록";

//   const br1 = document.createElement("br");

//   const form = document.createElement("form");
//   form.setAttribute("action", "#");

//   const div2 = document.createElement("div");
//   div2.classList.add("goods-title");
//   div.innerHTML = "상품명";

//   const input1 = document.createElement("input");
//   input1.classList.add("input-goodsTitle");
//   input1.setAttribute("type", "text");

//   div2.append(input1);

//   // 상품 옵션
//   const div3 = document.createElement("div");
//   div3.classList.add("goods-option");
//   div3.innerText = "상품 옵션";

//   const br2 = document.createElement("br");
//   const div4 = document.createElement("div");

//   const img = document.createElement("img");
//   img.classList.add("input-goodsPlus");
//   img.setAttribute("src", "../../static/img/다운로드.png");

//   const input2 = document.createElement("input");
//   input2.classList.add("input-goodsOption");
//   input2.setAttribute("type", "text");

//   div4.append(img, input2);
//   div3.append(br2, div4);

//   // 상품 설명
//   const div5 = document.createElement("div");
//   div5.classList.add("goods-content");
//   div5.innerText = "상품 설명";

//   const textarea = document.createElement("textarea");
//   textarea.classList.add("input-goodsContent");

//   const div6 = document.createElement("div");
//   div6.classList.add("goodsImg");

//   const label = document.createElement("label");
//   label.setAttribute("for", "img1");

//   const img2 = document.createElement("img");
//   img2.classList.add("goods-contentImg");
//   img2.setAttribute("src", "#");

//   label.append(img2);

//   const input = document.createElement("input");
//   input;
// }
