const updateModal = document.querySelector(".updateModal");


const modal_background = document.querySelector(".noticeModal");
const updateCloseBtn = document.getElementById('update-close');

const inputNoticeTitle = document.getElementById('input-noticeTitle');
const inputNoticeContent = document.getElementById('input-noticeContent');
let updateNoticeNo;


const btnOpenPopup = document.querySelector(".btn-open-popup");
const modal = document.querySelector(".noticeModal");
const modalCloseButton = document.querySelector(".modalCloseButton");
updateCloseBtn.addEventListener("click", () =>{
  updateClose();
})

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
}
function open() {
  modal.classList.add("show");
}

function updateClose() {
  updateModal.classList.remove("show");
}
function updateOpen() {
  updateModal.classList.add("show");
}

const updateBtn = document.getElementById('updateBtn');
const deleteBtn = document.getElementById('deleteBtn');
const modalNoticeTitle = document.querySelector('.modal-noticeTitle')
const modalNoticeContent = document.querySelector('.modal-noticeContent');
let targetTr;



function selectArtistNotice(noticeNo, thisTr){
  targetTr = thisTr.parentElement;
  fetch("/artistAdmin/selectNotice?artistGroupNoticeNo=" + noticeNo)
  .then(resp => resp.json())
  .then(notice =>{

    modalNoticeTitle.innerHTML = notice.artistGroupNoticeTitle;
    modalNoticeContent.innerHTML = notice.artistGroupNoticeContent;
    deleteBtn.setAttribute('onclick', `deleteNotice(${noticeNo})`);
    updateBtn.setAttribute('onclick', `updateNotice(${noticeNo})`);

    inputNoticeTitle.value = notice.artistGroupNoticeTitle;
    inputNoticeContent.innerHTML = notice.artistGroupNoticeContent;

    open();

  })
  .catch(err => console.log(err));
}

function deleteNotice(noticeNo){
  console.log(noticeNo);


  fetch("/artistAdmin/deleteNotice",{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : noticeNo
  })
  .then(resp => resp.text())
  .then(result => {
    if(result > 0 ){
      alert("삭제되었습니다");
      close();
      thisTr.remove();
    }
  })
  .catch(err => console.log(err));


}

function updateNotice(noticeNo) {
  updateNoticeNo = noticeNo;
  updateOpen();
}

const updateNoticeBtn = document.getElementById('update-notice-btn');

updateNoticeBtn.addEventListener("click", () =>{
  const data = {};
  data.artistGroupNoticeNo = updateNoticeNo;
  data.artistGroupNoticeTitle = inputNoticeTitle.value;
  data. artistGroupNoticeContent = inputNoticeContent.value;
  console.log(data);
   
  fetch("/artistAdmin/updateNotice",{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result => {

    if(result > 0){
      alert("수정 성공")
    }

  })
  .catch(err => console.log(err));
  
});