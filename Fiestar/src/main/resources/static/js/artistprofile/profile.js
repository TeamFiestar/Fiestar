const modal = document.querySelector(".modal");
const btnOpenPopup = document.querySelector(".btn-open-popup");
const modalCloseButton = document.getElementById("modalCloseButton");
const modal_background = document.querySelector(".modal");

function openModal() {
  // Show the modal
  modal.style.display = "block";

  // Call the selectBoard function with the memberNo parameter
  // selectBoard(memberNo);
}
function closeModal() {
  modal.style.display = "none";
}
btnOpenPopup.addEventListener("click", function () {
  openModal(memberNo);
});
window.addEventListener("click", (e) => {
  e.target === modal_background ? closeModal() : false;
});
