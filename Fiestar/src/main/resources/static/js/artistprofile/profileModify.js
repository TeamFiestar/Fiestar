const inputImgList = document.getElementsByClassName("imageInput");
// const deleteImgList = document.getElementsByClassName("delete-image");

const previewList = document.querySelectorAll(".preview");

const backupList = new Array(inputImgList.length);

const deleteOrderSet = new Set();

let i = 0;

const changeImage = (imgInput, order) => {
  const maxSize = 1024 * 1024 * 10;

  const uploadFIle = imgInput.files[0];
  console.log(order);

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
  const profileALl = document.querySelector(".profileALl");

  const profileContainer = document.createElement("div");
  profileContainer.classList.add("profileImg");

  const profileImageDiv = document.createElement("div");
  profileImageDiv.classList.add("profileImage");

  const fileInputLabel = document.createElement("label");
  fileInputLabel.setAttribute("for", "profileInput" + i);

  const btnUploadDiv = document.createElement("div");
  btnUploadDiv.classList.add("btn-upload3");

  const imgPreview = document.createElement("img");
  imgPreview.setAttribute("src", "");
  imgPreview.classList.add("artistPreview");

  btnUploadDiv.appendChild(imgPreview);

  fileInputLabel.appendChild(btnUploadDiv);

  const fileInput = document.createElement("input");
  fileInput.setAttribute("type", "file");
  fileInput.setAttribute("name", "artistProfileImg");
  fileInput.setAttribute("id", "profileInput" + i);
  fileInput.setAttribute("accept", "image/*");
  fileInput.setAttribute("order", "i");
  fileInput.classList.add("imageInput");
  fileInput.style.display = "none";

  i++;

  fileInput.addEventListener("change", (event) => {
    const selectedFile = event.target.files[0];
    if (selectedFile) {
      console.log(event.target);
      const reader = new FileReader();
      const targetFileInput = event.target;

      const targetPreview = targetFileInput.previousElementSibling.children[0].children[0];
      // console.log(targetPreview);

      reader.onload = (e) => {
        targetPreview.setAttribute("src", e.target.result);
      };

      reader.readAsDataURL(selectedFile);
    }
  });

  profileImageDiv.appendChild(fileInputLabel);
  profileImageDiv.appendChild(fileInput);

  const profileNameDiv = document.createElement("div");
  profileNameDiv.classList.add("profileName");

  const profileNameDiv2 = document.createElement("div");
  profileNameDiv2.classList.add("profileName");

  const artistNameInput = document.createElement("input");
  artistNameInput.setAttribute("type", "text");
  artistNameInput.setAttribute("placeholder", "이름을 작성해주세요");
  artistNameInput.setAttribute("name", "Name");

  const artistNameInput2 = document.createElement("input");
  artistNameInput2.setAttribute("type", "text");
  artistNameInput2.setAttribute("placeholder", "이메일을 작성해주세요");
  artistNameInput2.setAttribute("name", "email");

  profileNameDiv.appendChild(artistNameInput);
  profileNameDiv2.appendChild(artistNameInput2);

  profileContainer.appendChild(profileImageDiv);
  profileContainer.appendChild(profileNameDiv);
  profileContainer.appendChild(profileNameDiv2);

  profileALl.appendChild(profileContainer);
  const imgPreviews = document.querySelectorAll(".artistPreview");
}

const profileFrm = document.querySelector("#profileFrm");

profileFrm.addEventListener("submit", (e) => {
  const introduce = document.querySelector("[name=artistGroupIntroduce]");
  const img = document.querySelector(".imageInput");
  const artistName = document.querySelector("[name=artistName]");
  const email = document.querySelector("[name=email]");

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
  if (email.value.trim().length == 0) {
    alert("이메일을 입력해주세요.");
    e.preventDefault();
    email.value = "";
    email.focus();
    return;
  }
});

if (message != null) {
  alert(message);
}
