const plus = document.querySelector(".plusBtn");
const minus = document.querySelector(".input-goodsMinus");
const optionArea = document.getElementById("option-area");
const inputArea = document.getElementById("input-area");

let optionListIndex = 0;

function optionPlus() {
  const div = document.createElement("div");
  div.classList.add("input-goodsDiv");

  const input = document.createElement("input");
  input.classList.add("input-goodsOption");
  input.setAttribute("name", `productOptionList[${optionListIndex++}].productOptionName`);
  input.setAttribute("type", "text");

  const img2 = document.createElement("img");
  img2.classList.add("input-goodsMinus");
  img2.setAttribute("src", "/img/icon _minus_.png");
  img2.setAttribute("onclick", "optionMinus(this)");

  div.append(input, img2);

  inputArea.append(div);
}

function optionMinus(minus) {
  const deleteDiv = document.querySelector(".input-goodsDiv");
  deleteDiv.remove();
}

