const modal = document.querySelector(".noticeModal");
const btnOpenPopup = document.querySelector(".btn-open-popup");
const modalCloseButton = document.getElementById("modalCloseButton");
const modal_background = document.querySelector(".noticeModal");

btnOpenPopup.addEventListener("click", () => {
  open();
});

//Hide modal
modalCloseButton.addEventListener("click", () => {
  close();
});

window.addEventListener("click", (e) => {
  e.target === modal_background ? close() : false;
});
function close() {
  modal.classList.remove("show");
  modal_background.classList.remove("show");
}
function open() {
  modal.classList.add("show");
  modal_background.classList.add("show");
}
