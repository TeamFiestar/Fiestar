

const modalBtn = document.querySelector(".feedWriteBtn");

modalBtn.addEventListener('click', e =>{

  const content = document.querySelector("#feedWrite2");

  // 제목 미입력 상태
 
  // 내용 미입력
  if(content.value.trim().length == 0 ) {
    alert("내용을 입력 해주세요.");

    e.preventDefault(); // form 제출 X
    content.value = "";
    content.focus();

    return;

  }
});

const previewList = document.getElementsByClassName("preview");

const inputImageList = document.getElementsByClassName("inputImage");

const deleteImageList = document.getElementsByClassName("delete-image");

const changeImageFn = (imageInput, order) => {

  // imageInput : 파일이 선택/취소된 input 태그
  // order : input 태그 순서(썸네일 0, 나머지 1~4)

  // 업로드 파일 최대 크기(10MB)
  const maxSize = 1024 * 1024 * 10;

  // 업로드한 파일 정보가 담긴 객체
  const uploadFile = imageInput.files[0];


  // ---------- 파일을 한 번 선택한 후 취소했을 때 ----------
  if (uploadFile == undefined) {
    console.log("파일 선택이 취소됨");

    // 1) backup한 order번째 요소를 복제
    const temp = backupInputList[order].cloneNode(true);

    // 2) 화면에 원본 input을 temp로 바꾸기
    imageInput.after(temp); // 원본 다음에 temp 추가
    imageInput.remove(); // 원본을 화면에서 제거
    imageInput = temp; // temp를 imageInput 변수에 대입

    // 복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
    imageInput.addEventListener("change", () => {
      changeImageFn(imageInput, order);
    });

    return;
  }


  // ---------- 선택된 파일의 크기가 지정된 크기를 초과하는 경우 ----------

  if (uploadFile.size > maxSize) {
    alert("10MB 이하의 이미지를 선택 해주세요");

    // 없다 -> 추가한 경우
    if (backupInputList[order] == undefined) {
      imageInput.value = ''; // file 타입 input 태그 value를 빈칸으로 만듦
      // == 선택된 파일을 제거
    }

    // 기존에 이미지 선택 -> 다시 새 이미지 선택
    else {

      // 1) backup한 order번째 요소를 복제
      const temp = backupInputList[order].cloneNode(true);

      // 2) 화면에 원본 input을 temp로 바꾸기
      imageInput.after(temp); // 원본 다음에 temp 추가
      imageInput.remove(); // 원본을 화면에서 제거
      imageInput = temp; // temp를 imageInput 변수에 대입

      // 복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
      imageInput.addEventListener("change", () => {
        changeImageFn(imageInput, order);
      });
    }

    return;
  }

  // ---------- 선택된 이미지 파일을 읽어와 미리 보기 만들기 ----------
  // JS에서 파일을 읽는 객체
  const reader = new FileReader();

  // 매개변수에 작성된 파일을 읽어서 파일을 나타내는 URL 형태로 변경
  reader.readAsDataURL(uploadFile);

  // 파일을 다 읽은 경우
  reader.onload = e => {
    const url = e.target.result; // 이미지가 변환된 DataUrl

    // order 번째 .preview에 이미지 추가
    previewList[order].src = url;

    // 파일이 업로드된 input 태그를 복제해서 backupInputList에 추가
    backupInputList[order] = imageInput.cloneNode(true);
  };
}





for (let i = 0; i < inputImageList.length; i++) {

  /* 이미지 선택 또는 취소 시 */
  inputImageList[i].addEventListener("change", e => {
    changeImageFn(e.target, i);
    //   inputImage,  order
  });


  /* x버튼 클릭 시 */
  deleteImageList[i].addEventListener('click', () => {

    // 미리보기 삭제
    previewList[i].removeAttribute("src"); // src 속성 제거

    // input 태그 파일 제거
    inputImageList[i].value = '';

    // 같은 위치 backup 요소 제거
    backupInputList[i] = undefined;

  });

} 
