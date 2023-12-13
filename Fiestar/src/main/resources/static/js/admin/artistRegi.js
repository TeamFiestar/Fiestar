// const backImg = document.querySelector(".backImg");
// const profile = document.querySelector(".profile");
// const image = document.querySelector(".image");

// const previewList = document.getElementsByClassName("preview");

const inputImgList = document.getElementsByClassName("input-artistGroupImg");
const deleteImgList = document.getElementsByClassName("delete-image");

const backupList = new Array(inputImgList.length);

const deleteOrderSet = new Set();

const changeImage = (imgInput, order) => {
  const maxSize = 1024 * 1024 * 10;

  const uploadFIle = imgInput.files[0];

  if (uploadFIle == undefined) {
    console.log("파일 취소");
    const temp = backupList[order].cloneNode(true);

    imgInput.after(temp);
    imgInput.remove();
    imgInput = temp;

    imgInput.addEventListener("change", () => {
      changeImage(imgInput, order);
    });
    return;
  }

  if (uploadFIle.size > maxSize) {
    alert("10MB 이하로 선택해주세요.");

    if (backupList[order] == undefined) {
      imgInput.value = "";
    } else {
      const temp = backupList[order].cloneNode(true);

      imgInput.after(temp);
      imgInput.remove();
      imgInput = temp;

      imgInput.addEventListener("change", () => {
        changeImage(imgInput, order);
      });
    }
    return;
  }

  const reader = new FileReader();

  reader.readAsDataURL(uploadFIle);

  reader.onload = (e) => {
    const url = e.target.result;

    previewList[order].src = url;

    backupList[order] = imgInput.cloneNode(true);

    deleteOrderSet.delete(order);
  };
};

for (let i = 0; i < inputImgList.length; i++) {
  inputImgList[i].addEventListener("change", (e) => {
    changeImage(e.target, i);
  });

  deleteImgList[i].addEventListener("click", () => {
    previewList[i].removeAttribute("src"); // "src" 속성 삭제

    // input 태그 파일 제거
    inputImgList[i].value = "";

    // 같은 위치 backup 요소 제거
    backupList[i] = undefined;

    deleteOrderSet.add(i);
  });
}

const artistGroupFrm = document.querySelector("#artistGroupFrm");

artistGroupFrm.addEventListener("submit", (e) => {
  const title = document.querySelector("[name=artistGroupTitle]");
  const img = document.querySelector(".input-artistGroupImg");

  if (title.value.trim().length == 0) {
    alert("그룹명을 입력해주세요.");
    e.preventDefault();
    title.value = "";
    title.focus();
    return;
  }
  var imgCheck = img.value;
  if (!imgCheck) {
    alert("파일을 확인 해주세요");
    e.preventDefault();
    return;
  }
});

function groupDelete() {
  fetch("/admin/groupDelete", {
    method: "delete",
  });
}
