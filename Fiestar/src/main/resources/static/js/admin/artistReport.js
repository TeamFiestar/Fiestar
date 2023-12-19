const reportModal = document.querySelector(".reportModal");
const btnOpenPopup = document.querySelector(".btn-open-popup");
const reportModalCloseButton = document.getElementById("modalCloseButton");
const report_modal_background = document.querySelector(".reportModal");

btnOpenPopup.addEventListener("click", () => {
  open();
});

//Hide modal
reportModalCloseButton.addEventListener("click", () => {
  close();
});

window.addEventListener("click", (e) => {
  e.target === report_modal_background ? close() : false;
});
function close() {
  reportModal.classList.remove("show");
  report_modal_background.classList.remove("show");
}
function open() {
  reportModal.classList.add("show");
  report_modal_background.classList.add("show");
}

// 피드 신고 열기
const openFeedReport = (boardNo) => {

  const modal = document.getElementById('feedDetail');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";
}

// 피드 신고 닫기
const closeFeedReport = () => {
  const modal = document.getElementById('feedDetail');
  modal.classList.remove("show");
  document.body.style.overflow = "";
}


let reportTr;
let reportType;

const selectReport = (reportContentNo, thisReport) => {

  const data = {};
  data.reportContentNo = reportContentNo;
  data.reportType = thisReport.value;
  reportType = thisReport.value;
  console.log(data);
  
  /* 게시판 신고 열기 */
  if(reportType == 'board'){
    openFeedReport
    fetch("/artistAdmin/selectBoardReport?boardNo=" + reportContentNo,  {
    })
    .then(resp => resp.json())
    .then(boardComment => {

      const boardNickname = document.getElementById('boardNickname');
      const boardEnrollDate = document.getElementById('boardDate');
      const boardContent = document.querySelector('.feedMain')

      boardNickname.textContent = boardComment.memberNickname;
      boardEnrollDate.textContent = boardComment.mediaCommentEnrollDate;
      boardContent.textContent = boardComment.mediaCommentContent;

    }) 
  }  

  const commentNickname = document.querySelector('.comment-writer');
  const commentDate = document.querySelector('.comment-date');
  const commentContent = document.querySelector('.comment-content');


  /* 게시판 댓글 신고 열기 */
  if(reportType == 'boardComment'){
    open();
    fetch("/artistAdmin/selectBoardCommentReport?boardCommentNo=" + reportContentNo,  {
    }) 
    .then(resp => resp.json())
    .then(boardComment => {

      commentNickname.textContent = boardComment.memberNickname;
      commentDate.textContent = boardComment.mediaCommentEnrollDate;
      commentContent.textContent = boardComment.mediaCommentContent;

    }) 
  }  

  /* 미디어 댓글 신고 열기 */
  if(reportType == 'mediaComment'){
    open();
    fetch("/artistAdmin/selectMediaCommentReport?mediaCommentNo=" + reportContentNo,  {
    })
    .then(resp => resp.json())
    .then(mediaComment => {

      commentNickname.textContent = mediaComment.memberNickname;
      commentDate.textContent = mediaComment.mediaCommentEnrollDate;
      commentContent.textContent = mediaComment.mediaCommentContent;

    }) 
  }  


}  

const deleteReport = (reportContentNo) => {
  fetch('/artistAdmin/deleteReport',{
    
  })
}
