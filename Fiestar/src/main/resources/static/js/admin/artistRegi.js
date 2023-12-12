const backImg = document.querySelector(".backImg");
const profile = document.querySelector(".profile");
const image = document.querySelector(".image");

const inputImg = document.querySelector(".input-artistGroupImg");
const deleteImg = document.querySelector(".delete-image");

const backupList = new Array(inputImg.length);

const changeImage = (imgInput, order) => {
  const maxSize = 1024 * 1024 * 10;

  const uploadFIle = imgInput.files[0];

  if (uploadFIle == undefined) {
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
  }

  const reader = new FileReader();

  reader.readAsDataURL(uploadFIle);

  reader.onload = (e) => {
    const url = e.target.result;

    backImg.src = url;
    profile.src = url;
    image.src = url;

    backupList[order] = imgInput.cloneNode(true);
  };
};

for (let i = 0; i < inputImg.length; i++) {
  inputImg[i].addEventListener("change", (e) => {
    changeImage(e.target, i);
  });

  deleteImg[i].addEventListener("click", () => {
    if (i == 0) {
      backImg.removeAttribute("src");
      backImg.value = "";
      backImg = undefined;
    } else if (i == 1) {
      profile.removeAttribute("src");
      profile.value = "";
      profile = undefined;
    } else {
      image.removeAttribute("src");
      image.value = "";
      image = undefined;
    }
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
