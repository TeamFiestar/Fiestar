const reportModal = document.querySelector(".reportModal");
const btnOpenPopup = document.querySelector(".btn-open-popup");
const reportModalCloseButton = document.getElementById("modalCloseButton");
const report_modal_background = document.querySelector(".reportModal");

// 삭제 버튼
const deleteReportBtn = document.getElementById('delete-report-btn');

// 처리 버튼
const proceedReportBtn = document.getElementById('proceed-report-btn');

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
let reportType2;

const selectReport = (reportNo, reportContentNo, thisReport) => {

  const data = {};
  data.reportContentNo = reportContentNo;
  data.reportType2 = thisReport.value;
  reportType2 = thisReport.value;

  reportTr = thisReport;
  
  /* 게시판 신고 열기 */
  if(reportType2 == 'board'){
    openFeedReport();
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
  if(reportType2 == 'boardComment'){
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
  if(reportType2 == 'mediaComment'){
    open();
    fetch("/artistAdmin/selectMediaCommentReport?mediaCommentNo=" + reportContentNo,  {
    })
    .then(resp => resp.json())
    .then(mediaComment => {

      commentNickname.textContent = mediaComment.memberNickname;
      commentDate.textContent = mediaComment.mediaCommentEnrollDate;
      commentContent.textContent = mediaComment.mediaCommentContent;

      deleteReportBtn.setAttribute('onclick', `deleteReport(${reportNo}, ${reportContentNo})`)
      proceedReportBtn.setAttribute('onclick', `proceedReport(${reportNo}, ${reportContentNo})`)

    }) 
  }  


}  


// 삭제처리
const deleteReport = (reportNo, reportContentNo) => {

  const deleteData = {};
  
  deleteData.reportNo = reportNo;
  deleteData.reportContentNo = reportContentNo;
  deleteData.reportType2 = reportType2;
  const proceed = reportTr.parentElement.nextElementSibling.firstElementChild;

  fetch('/artistAdmin/deleteReport',{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(deleteData)
  })
  .then(resp => resp.text())
  .then(result => {
    if(result > 0){
      alert("신고 삭제 처리 완료");
      close();
      closeFeedReport();
      proceed.textContent = 'Y';
    }
    else{
      alert("처리 실패");
    }
  })
  .catch(e => console.log(e))

}


const proceedReport = (reportNo, reportContentNo) => {

  const proceedData = {};

  proceedData.reportNo = reportNo;
  proceedData.reportContentNo = reportContentNo;
  proceedData.reportType2 = reportType2;
  const proceed = reportTr.parentElement.nextElementSibling.firstElementChild;

  fetch('/artistAdmin/proceedReport',{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(proceedData)
  })
  .then(resp => resp.text())
  .then(result => {
    if(result > 0){
      alert("처리 완료");
      close();
      closeFeedReport();
      proceed.textContent = 'Y';
    }
    else{
      alert("처리 실패");
    }
  })
  .catch(e => console.log(e))

}
