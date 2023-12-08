

function openModal(){
  const modal = document.getElementById('feedDetail');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";
  
}

function wopenModal() {
  const modal = document.getElementById('feedWrite');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";

}

function closeModal(){
  const modal = document.getElementById('feedDetail');
  modal.classList.remove("show");
  document.body.style.overflow = "";
  
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