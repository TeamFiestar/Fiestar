/* 프로필 이미지 미리보기, 제거 */
const memberProfile = document.querySelector("#profileImg");
const imageInput = document.querySelector("#imageInput");

let statusCheck = -1;
let backupInput;

if (imageInput != null) {
  const changeImageFn = e => {

    console.log(e.target);
    console.log(e.target.files);

  };

}