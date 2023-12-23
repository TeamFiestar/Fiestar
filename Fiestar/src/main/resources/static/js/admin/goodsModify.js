const plus = document.querySelector(".plusBtn");
const minus = document.querySelector(".input-goodsMinus");
const optionArea = document.getElementById("option-area");
const inputArea = document.getElementById("input-area");
const deleteImg1 = document.getElementById("deleteImg1");
const deleteImg2 = document.getElementById("deleteImg2");

let optionListIndex = 0;

function optionPlus() {
  const div = document.createElement("div");
  div.classList.add("input-goodsDiv");

  const input = document.createElement("input");
  input.classList.add("input-goodsOption");
  input.placeholder = "옵션 없을 시 '없음' 입력" ;
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

function handleOnInput(el, maxlength) {
  if(el.value.length > maxlength)  {
    el.value 
      = el.value.substr(0, maxlength);
  }
} 



function optionMinus(minus) {
  const deleteDiv = document.querySelector(".input-goodsDiv");
  deleteDiv.remove();
}


//---------------------------------------------------------------------
/* 제출 시 유효성 검사 */
const goodsFrm = document.getElementById("goodsFrm");
goodsFrm.addEventListener("submit", e=>{
  const title = document.querySelector("[name='productName']");
  const price = document.querySelector("[name='productPrice']");
  const option = document.getElementById("optionBtn");
 
 

 //제목 미입력
 if(title.value.trim().length == 0){
  alert("제목을 입력해주세요");
  
  e.preventDefault(); 
  title.value = "";
  title.focus();
}

  //가격 미입력
  if(price.value.trim().length == 0){
    alert("가격을 입력해주세요");
    
    e.preventDefault(); 
    price.value = "";
    price.focus();
  }


});



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



 /* x버튼 클릭 시 */
 deleteImg1.addEventListener("click",()=>{

  //미리보기 삭제
  deleteImg1.removeAttribute("src");  //src속성을 제거(img태그)

  //input태그 파일 제거
  deleteImg1.value = '';

  //같은 위치 backup제거
  deleteImg1 = undefined;

});



