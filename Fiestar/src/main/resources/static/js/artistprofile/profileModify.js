const inputImgList = document.getElementsByClassName("imageInput");
// const deleteImgList = document.getElementsByClassName("delete-image");

const previewList = document.getElementsByClassName("preview");

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

  // deleteImgList[i].addEventListener("click", () => {
  //   previewList[i].removeAttribute("src"); // "src" 속성 삭제

  //   // input 태그 파일 제거
  //   inputImgList[i].value = "";

  //   // 같은 위치 backup 요소 제거
  //   backupList[i] = undefined;

  //   deleteOrderSet.add(i);
  // });
}

function profilePlus() {
  // const div = document.createElement("div");
  // div.classList.add("profileImg");
  // const div2 = document.createElement("div");
  // div2.classList.add("profileImage");
  // const label1 = document.createElement("label");
  // label1.setAttribute("for","profileInput");
  // const div3 = document.createElement("div");
  // div3.classList.add("btn-upload3");
  // const img = document.createElement("img");
  // img.setAttribute("src","")
  // img.classList.add("preview");

  // div3.append(img);
  // label1.append(div3);
  // const input = document.createElement("input");
  // input.setAttribute("type","file");
  // input.setAttribute("name","artistProfile");
  // input.setAttribute("id","profileInput");
  // input.classList.add("imageInput");

  // div2.append(label1, input);
  const profileALl = document.querySelector(".profileALl");

  const profileContainer = document.createElement("div");
  profileContainer.classList.add("profileImg");

  const profileImageDiv = document.createElement("div");
  profileImageDiv.classList.add("profileImage");

  const fileInputLabel = document.createElement("label");
  fileInputLabel.setAttribute("for", "profileInput");

  const btnUploadDiv = document.createElement("div");
  btnUploadDiv.classList.add("btn-upload3");

  const imgPreview = document.createElement("img");
  imgPreview.setAttribute("src", "");
  imgPreview.classList.add("preview");

  btnUploadDiv.appendChild(imgPreview);

  fileInputLabel.appendChild(btnUploadDiv);

  const fileInput = document.createElement("input");
  fileInput.setAttribute("type", "file");
  fileInput.setAttribute("name", "artistProfile");
  fileInput.setAttribute("id", "profileInput");
  fileInput.classList.add("imageInput");
  fileInput.style.display = "none";

  profileImageDiv.appendChild(fileInputLabel);
  profileImageDiv.appendChild(fileInput);

  const profileNameDiv = document.createElement("div");
  profileNameDiv.classList.add("profileName");

  const artistNameInput = document.createElement("input");
  artistNameInput.setAttribute("type", "text");
  artistNameInput.setAttribute("placeholder", "이름을 작성해주세요");
  artistNameInput.setAttribute("name", "artistName");

  profileNameDiv.appendChild(artistNameInput);

  profileContainer.appendChild(profileImageDiv);
  profileContainer.appendChild(profileNameDiv);

  profileALl.appendChild(profileContainer);
}

const profileFrm = document.querySelector("#profileFrm");

profileFrm.addEventListener("submit", (e) => {
  const introduce = document.querySelector("[name=artistGroupIntroduce]");
  const img = document.querySelector(".imageInput");
  const artistName = document.querySelector("[name=artistName]");

  if (introduce.value.trim().length == 0) {
    alert("그룹명을 입력해주세요.");
    e.preventDefault();
    introduce.value = "";
    introduce.focus();
    return;
  }
  if (artistName.value.trim().length == 0) {
    alert("아티스트 이름을 입력해주세요.");
    e.preventDefault();
    artistName.value = "";
    artistName.focus();
    return;
  }
  var imgCheck = img.value;
  if (!imgCheck) {
    alert("파일을 확인 해주세요");
    e.preventDefault();
    return;
  }
});

if (message != null) {
  alert(message);
}
