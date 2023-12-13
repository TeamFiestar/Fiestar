
let likeCheck;
let likeCount2;
const dataObj = {};
const likeImg = document.querySelector('#likeImg');

function openModal(boardNo) {
  // 새로운 URL 생성
  var newUrl = "/" + artistGroupTitle + "/feed/" + boardNo;
  console.log(artistGroupTitle);
  console.log(boardNo);
  console.log(newUrl);

  // 상태 객체 (필요에 따라 사용)
  var stateObj = { artistGroupTitle: artistGroupTitle, boardNo: boardNo };

  // 새로운 URL로 이동 (페이지의 내용을 로드하지 않음)
  history.pushState(stateObj, "", newUrl);

  // 페이지의 내용을 동적으로 업데이트하는 함수 호출 (예시로 updatePageContent 함수 사용)
  updatePageContent(boardNo);

  const modal = document.getElementById('feedDetail');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";

}


function closeModal(stateObj){

  var newUrl = "/" + artistGroupTitle + "/feed";
  history.pushState(stateObj, "", newUrl);

  updatePageContent();
  const modal = document.getElementById('feedDetail');
  modal.classList.remove("show");
  document.body.style.overflow = "";
  
}


function updatePageContent(boardNo) {


  // 여기에 페이지 내용을 동적으로 업데이트하는 로직을 추가
  fetch("/AJAXboardDetail?boardNo="+ boardNo)
  .then(resp => resp.json())
  .then(board => {
    console.log(board);
    const boardNickname = document.getElementById('boardNickname');
    boardNickname.innerText = board.memberNickname;
    const boardDate = document.getElementById('boardDate');
    boardDate.innerText = board.boardEnrollDate;

    const feedMain = document.querySelector('.feedMain');
    feedMain.innerText = board.boardContent;

    const profileImage = document.getElementById('profileImage');

    if(board.memberProfile) {
      profileImage.src = board.memberProfile;
    } else {
      profileImage.src = defaultImage;
    }

    const indicator = document.querySelector('#indicator');
    
    if(board.memberAuthority == 2) {
      indicator.classList.add("fa-solid", "fa-circle-check");
      indicator.style.color = "#7743DB";
    }else {
      indicator.classList.remove("fa-solid", "fa-circle-check");
      indicator.style.color = ""; 
    }

    if(board.imageList && board.imageList.length > 0) {
      const feedImg = document.querySelector('.feedImg');
      feedImg.innerHTML = '';

      board.imageList.forEach(image => {
        const imgElement = document.createElement('img');
        const imagePath = image.boardImagePath + image.boardImageRename;

        imgElement.src = imagePath;

        feedImg.appendChild(imgElement);
      });


    } else {
      const feedImg = document.querySelector('.feedImg');
      feedImg.innerHTML ='';

    }

    const feedLikeCount = document.querySelector('#feedLikeCount');
    feedLikeCount.innerText = board.likeCount;

    console.log(board.likeCheck);

    if(board.likeCheck == 1){
      likeImg.classList.add("fa-solid");
      likeImg.classList.remove("fa-regular");
    }
    else{
      likeImg.classList.remove("fa-solid");
      likeImg.classList.add("fa-regular");
    }


    likeCount2 = board.likeCount;

    const textWrapper = document.querySelector('.text-wrapper');
    textWrapper.innerText = board.commentCount + "개의 댓글";

    dataObj.boardNo = boardNo;
    dataObj.likeCheck = board.likeCheck;


    
    
  });
}



likeImg.addEventListener("click", (e) => {
  if (!loginCheck) {
    alert("로그인을 먼저 해주세요");
    return;
  }


  if (e.target.classList.contains("fa-regular")) {
    likeCheck = 0;
  } else {
    likeCheck = 1;
  }


  console.log(dataObj);

  fetch("/AJAXboardDetail/like",{
    method: "post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  })
    .then((resp) => resp.text())
    .then((count) => {
      if (count == -1) {
        console.log("좋아요 실패");
        return;
      }
      e.target.classList.toggle("fa-regular");
      e.target.classList.toggle("fa-solid");
      const feedLikeCount = document.querySelector('#feedLikeCount');
      if(likeCheck == 1){
        likeCount2 = likeCount2 - 1;
        dataObj.likeCheck = 0;
      }else{
        likeCount2 = likeCount2 + 1;
        dataObj.likeCheck = 1;
      }

      e.target.nextElementSibling.innerText = likeCount2;
    })
    .catch((e) => {
      console.log(e);
    });
});



// function openModal(){
//   const modal = document.getElementById('feedDetail');
//   modal.classList.add("show");
//   document.body.style.overflow = "hidden";
  
// }

function wopenModal() {
  const modal = document.getElementById('feedWrite');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";

}



function wcloseModal() {
  const modal = document.getElementById('feedWrite');
  modal.classList.remove("show");
  document.body.style.overflow = "";

}



// 이미지 슬라이드
// let slideIndex = 1;
// showSlides(slideIndex);

// function plusSlides(n) {
//   showSlides(slideIndex += n);
// }

// function currentSlide(n) {
//   showSlides(slideIndex = n);
// }

// function showSlides(n) {
//   let i;
//   let slides = document.getElementsByClassName("slide");
//   let dots = document.getElementsByClassName("dot");
//   if (n > slides.length) { slideIndex = 1 }
//   if (n < 1) { slideIndex = slides.length }
//   for (i = 0; i < slides.length; i++) {
//     slides[i].style.display = "none";
//   }
//   for (i = 0; i < dots.length; i++) {
//     dots[i].className = dots[i].className.replace(" active", "");
//   }
//   slides[slideIndex - 1].style.display = "block";
//   dots[slideIndex - 1].className += " active";
// }

// /* 게시글 작성 modal */
// const wmodal = document.querySelector(".feedWriteModal");
// const btnOpenPopup = document.querySelector(".btn-open-popup");
// const modalCloseButton = document.querySelector(".modalCloseButton");
// const wmodal_background = document.querySelector(".feedWriteModal");

// btnOpenPopup.addEventListener("click", () => {
//   open();
// });

// //Hide modal
// modalCloseButton.addEventListener("click", () => {
//   close();
// });

// window.addEventListener("click", (e) => {
//   e.target === wmodal_background ? close() : false;
// });


// function close() {
//   wmodal.classList.remove("show");
//   wmodal_background.classList.remove("show");
//   document.body.style.overflow = "";

// }
// function open() {
//   wmodal.classList.add("show");
//   wmodal_background.classList.add("show");
//   document.body.style.overflow = "hidden";
// }

// const dmodal = document.querySelector(".feedDetailModal");
// const openModal = document.querySelector(".openModal");
// const closeModal = document.querySelector(".closeModal");
// const dmodal_background = document.querySelector(".feedDetailModal");

// openModal.addEventListener("click", () => {
//   dopen();
// });

// //Hide modal
// closeModal.addEventListener("click", () => {
//   dclose();
// });

// window.addEventListener("click", (e) => {
//   e.target === dmodal_background ? dclose() : false;
// });


// function dclose() {
//   dmodal.classList.remove("show");
//   dmodal_background.classList.remove("show");
//   document.body.style.overflow = "";

// }
// function dopen() {
//   dmodal.classList.add("show");
//   dmodal_background.classList.add("show");
//   document.body.style.overflow = "hidden";
// }