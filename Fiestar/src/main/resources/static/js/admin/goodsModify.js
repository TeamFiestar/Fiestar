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
  input.setAttribute("className", "inputOption");

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

//------------------------------------------------------------------------------------------


/* 이미지 프리뷰 */
function thumbnail(input) {
  if(input.files && input.files[0]) {
    const reader = new FileReader();
    reader.onload = function(e) {
      //img태그 얻어옴
      document.getElementById('thumnail').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('thumnail').src = "";
  }
}

function content(input) {
  if(input.files && input.files[0]) {
    const reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('goods-contentImg').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('goods-contentImg').src = "";
  }
}


//---------------------------------------------------------------------
/* 제출 시 유효성 검사 */
const contentImg = document.getElementById("contentImg");
const thumbnailImg = document.getElementById("thumbnailImg");
const goodsFrm = document.getElementById("goodsFrm");
goodsFrm.addEventListener("submit", e=>{
  
  const option = document.querySelector("[className='inputOption']");


  if(contentImg.file.length === 0){
    alert("사진을 입력해주세요");
  }
  if(thumbnailImg.file.length === 0){
    alert("사진을 입력해주세요");
  }


  //제목 미입력
  if(title.value.trim().length == 0){
    alert("제목을 입력해주세요");
    
    e.preventDefault(); 
    title.value = "";
    title.focus();
  }


  //옵션 미입력
  if(option.value.trim().length == 0){
    alert("옵션을 입력해주세요");
    
    e.preventDefault(); 
    option.value = "";
    option.focus();

  }
});