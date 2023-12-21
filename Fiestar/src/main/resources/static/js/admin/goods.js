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



















// //미리보기 관련 요소 모두 얻어오기

// //img태그 5개
// const previewList1 = document.getElementsByClassName("img1");
// const previewList2 = document.getElementsByClassName("img2");

// //input type = "file"  태그 5개
// const inputImageList = document.getElementsByClassName("img2");




// //inputImageList 크기 만큼 백업용 배열을 생성
// //->파일선택 => 취소 시 사용할 input type = "file"요소 저장
// const backupInputList = new Array(inputImageList.length);


// /* 이미지 선택 시 수행할 함수 */
// const changeImageFn = (imageInput , order)=>{
  
//   //imageInput : 파일이 선택/취소된 input태그
//   //order : input 태그 순서(썸테일 0, 나머지 1~4)

//   //업로드 파일 최대 크기
//   const maxSize = 1024 * 1024 * 10;

//   //업로드한 파일 정보가 담긴 객체
//   const uploadFile = imageInput.files[0];


//   console.log(uploadFile);





//   // ------------- 선택된 파일의 크기가 지정된 크기를 초과하는 경우 ---------------
//   if(uploadFile.size > maxSize){
//     alert("10MB 이하의 이미지를 선택해주세요");

//     //이미지 없다가 추가하는 경우
//     if(backupInputList == undefined){
//       imageInput.value = ''; //file 타입 input태그 value를 빈칸으로 만듦
//                              //->선택된 파일을 제거
//     }
//     //기존에 이미지 선택 -> 다시 새 이미지 선택
//     else{
//        //1) backup한 요소를 복제
//       const temp = backupInputList[order].cloneNode(true);

//       //2) 화면에 원본 input을 temp로 바꾸기
//       imageInput.after(temp);  //원본 다음에 temp 추가
//       imageInput.remove(); //원본을 화면에서 제거
//       imageInput = temp; //temp를 imageInput 변수에 대입

//       //복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
//       imageInput.addEventListener("change", ()=>{
//         changeImageFn(imageInput , order);
//       });
//     }

//     return;
//   }




//   // ----------- 선택된 이미지 파일을 읽어와 미리 보기 만들기 --------------
//   const reader = new FileReader();

//   //매개변수에 작성된 파일을 읽어서 파일을 나타내는 URL 형태로 변경
//   //->FileReader.result 필드에 저장되어 있음
//   reader.readAsDataURL(uploadFile)

//   //파일을 다 읽은 경우
//   reader.onload= e=> {
//     const url = e.target.result; //이미지가 변환된 DataUrl

//     //order 번째 .preview에 이미지 추가
//     previewList[order].src = url;

//     //파일이 업로드된 input태그를 복제해서 backupInputList에 추가
//     backupInputList[order] = imageInput.cloneNode(true);
//   };
// }



//---------------------------------------------------------------------
/* 제출 시 유효성 검사 */
const goodsFrm = document.getElementById("goodsFrm");
goodsFrm.addEventListener("submit", e=>{
  const title = document.querySelector("[name='productName']");
  const price = document.querySelector("[name='productPrice']");
 

  //제목 미입력
  if(title.value.trim().length == 0){
    alert("제목을 입력해주세요");
    
    e.preventDefault(); //form 제출x
    title.value = "";
    title.focus();
  }

  //가격 미입력
  if(price.value.trim().length == 0){
    alert("가격을 입력해주세요");
    
    e.preventDefault(); //form 제출x
    price.value = "";
    price.focus();

  }
});
