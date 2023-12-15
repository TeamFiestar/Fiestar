
/* 모달 */
const modal = document.getElementById('modalContainer');

function modalOpen() {
  modal.classList.remove('hidden');
}
function modalClose() {
  modal.classList.add('hidden');
}

/* 삭제 모달 */
const deleteModal = document.getElementById('deleteModalContainer');
let deleteCommentNo;

function deleteModalOpen(commentNo) {
  deleteCommentNo = commentNo;
  console.log(deleteCommentNo);
  deleteModal.classList.remove('hidden');
}
function deleteModalClose() {
  deleteModal.classList.add('hidden');
}


// 클릭한 댓글을 제외한 모든 댓글을 사라지게 함
function loadReplies(element, commentNo) {
  const parentCommentNo = document.getElementById('parent-comment-no');

  parentCommentNo.value = commentNo;


  var allComments = document.querySelectorAll('.comment-area');
  allComments.forEach(function (c) {
    if (c != element.parentElement.parentElement.parentElement ) {
      c.classList.add('hide');
    }

  });

  element.parentElement.parentElement.classList.remove('hide');

  var hideComment = document.querySelectorAll('.new-comment-area');
  hideComment.forEach(function (c) {
    c.remove();
  });

  // 뒤로가기 버튼 표시
  var backButton = document.querySelector('.back-button');
  backButton.style.display = 'block';

  generateComment();

}



// 댓글창으로 돌아가기
function goBack() {
  const parentCommentNo = document.getElementById('parent-comment-no');
  parentCommentNo.value = 0;


  // 모든 댓글을 다시 보이게 함
  var allComments = document.querySelectorAll('.comment-area');
  allComments.forEach(function (c) {
    c.classList.remove('hide');
  });

  var hideComment = document.querySelectorAll('.new-comment-area');
  hideComment.forEach(function (c) {
    c.remove();
  });

  // 뒤로가기 버튼을 숨김
  var backButton = document.querySelector('.back-button');
  backButton.style.display = 'none';

}



// 댓글 등록
function submitComment() {
  const parentCommentNo = document.getElementById('parent-comment-no').value;
  const commentContent = document.getElementById('comment-input').value;
  
  let data = {
    'mediaCommentContent' : commentContent,
    'memberNo' : 3,
    'mediaNo' : mediaNo,
    'mediaParentCommentNo' : (Number)(parentCommentNo)
  }

  console.log(data);

  fetch("/mediaComment/inputComment",{
    method : "POST",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result => {

    if(result > 0){
      alert("댓글 등록 성공");
      generateComment();

    }

  })
  .catch(err => console.log(err));
}




function generateComment(){
  const commentLists = document.querySelector(".comment-list");
  const newCommentLists = document.querySelector(".new-comment-list");
  
  const parentCommentNo = document.getElementById('parent-comment-no').value;
  
  if(parentCommentNo == 0){
    commentLists.innerHTML = "";
  }
  var hideComment = document.querySelectorAll('.new-comment-area');
  hideComment.forEach(function (c) {
    c.remove();
  });
  
  
  fetch("/mediaComment/selectComment?mediaNo=" + mediaNo + "&mediaParentCommentNo=" + parentCommentNo+ "&memberNo=3")
  .then(resp => resp.json())
  .then(commentList =>{
    console.log(commentList);
    for(let comment of commentList){

      // "comment-area" 클래스를 가진 새로운 div 엘리먼트 생성
      var commentArea = document.createElement("div");
      if(parentCommentNo == 0){
        commentArea.className = "comment-area";
      }
      else{
        commentArea.className = "new-comment-area";
      }
    
      // "comment-area-in" 클래스를 가진 내부 div 생성
      var commentAreaIn = document.createElement("div");
      commentAreaIn.className = "comment-area-in";
    
      // "img" 클래스를 가진 이미지 엘리먼트 생성하고 src 속성 설정
      var img = document.createElement("img");
      img.className = "profile-img";
      img.src = "/img/male-user.png";
    
      // "comment-writer-area" 클래스를 가진 div 생성
      var commentWriterArea = document.createElement("div");
      commentWriterArea.className = "comment-writer-area";
    
      // "comment-writer" 클래스를 가진 div 생성하고 텍스트 내용 설정
      var commentWriter = document.createElement("div");
      commentWriter.className = "comment-writer";
      commentWriter.textContent = comment.memberNickname;
    
      // "comment-date" 클래스를 가진 div 생성하고 텍스트 내용 설정
      var commentDate = document.createElement("div");
      commentDate.className = "comment-date";
      commentDate.textContent = comment.mediaCommentEnrollDate;
    
      // comment-writer와 comment-date를 comment-writer-area에 추가
      commentWriterArea.appendChild(commentWriter);
      commentWriterArea.appendChild(commentDate);
    
      // "comment-profile" 클래스를 가진 div 생성
      var commentProfile = document.createElement("div");
      commentProfile.className = "comment-profile";
    
      // "report-img" 클래스를 가진 이미지 엘리먼트 생성하고 onclick 이벤트 설정
      var reportImg = document.createElement("img");
      reportImg.className = "report-img";
      reportImg.src = "/img/siren.png";
      reportImg.onclick = modalOpen;

      const deleteImg = document.createElement("img");
      deleteImg.className = "delete-cross";
      deleteImg.src = "/img/close_ring.png";
      deleteImg.setAttribute('onclick', `deleteModalOpen(${comment.mediaCommentNo})`);

    
      // report-img를 comment-profile에 추가
      commentProfile.append(deleteImg, reportImg);
    
      // img, comment-writer-area, comment-profile을 comment-area-in에 추가
      commentAreaIn.appendChild(img);
      commentAreaIn.appendChild(commentWriterArea);
      commentAreaIn.appendChild(commentProfile);
    
      // "comment-content-area" 클래스를 가진 div 생성
      var commentContentArea = document.createElement("div");
      commentContentArea.className = "comment-content-area";
    
      // "comment-content" 클래스를 가진 div 생성하고 텍스트 내용 설정
      var commentContent = document.createElement("div");
      commentContent.className = "comment-content";
      commentContent.textContent = comment.mediaCommentContent;
    
      // comment-content를 comment-content-area에 추가
      commentContentArea.appendChild(commentContent);
    
      // "speech-bubble-wrapper" 클래스를 가진 div 생성
      var speechBubbleWrapper = document.createElement("div");
      speechBubbleWrapper.className = "speech-bubble-wrapper";
    
      // "speech-bubble" 클래스를 가진 이미지 엘리먼트 생성하고 onclick 이벤트 설정
      var speechBubble = document.createElement("img");
      if(parentCommentNo == 0){
        
        speechBubble.className = "speech-bubble";
        speechBubble.src = "/img/speech-bubble.png";
        speechBubble.setAttribute("onclick", `loadReplies(this, ${comment.mediaCommentNo})`);
      
      }

      // 좋아요
      var likeheart = document.createElement("img");
      if(comment.memberCommentLike == 0){
        likeheart.className = "like-heart gray";
        likeheart.src = "/img/like_heart_w.svg";
        likeheart.setAttribute("onclick",`changeLike(this, ${comment.mediaCommentNo})`);
      }
      else{
        likeheart.className = "like-heart red";
        likeheart.src = "/img/like_heart_r.svg";
        likeheart.setAttribute("onclick",`changeLike(this, ${comment.mediaCommentNo})`);
      }
      console.log(comment.memberCommentLike);


      // speech-bubble을 speech-bubble-wrapper에 추가
      speechBubbleWrapper.append(speechBubble ,likeheart );
    
      // comment-area-in, comment-content-area, speech-bubble-wrapper를 comment-area에 추가
      commentArea.appendChild(commentAreaIn);
      commentArea.appendChild(commentContentArea);
      commentArea.appendChild(speechBubbleWrapper);
    
  
      commentLists.append(commentArea)
    }

    const commentCounter = document.querySelector('.comment-count-wrapper')
    commentCounter.textContent = `${commentList.length}개의 답글`


  })
  .catch(err => console.log(err));
}

function deleteComment(){
  fetch("/mediaComment/deleteComment",{
    method : "Put",
    headers : {"Content-Type" : "application/json"},
    body : deleteCommentNo
  })
  .then(resp => resp.text())
  .then(reulst => {
    if(reulst > 0){
      alert("댓글이 삭제되었습니다")
      generateComment();
      deleteModalClose()
    }
  })
  .catch(err => console.log(err));

}


function changeLike(likeBtn, commentNo){

  if( loginMemberNo == null){
    alert("로그인 후 이용해 주십시오")
    return;
  }
  
  const data = {
    memberNo : loginMemberNo,
    mediaCommentNo : commentNo
  }

  if(likeBtn.classList.contains('gray')){
    
    
    fetch("/mediaComment/insertLike",{
      method : "POST",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify(data)
    })
    .then(resp => resp.text())
    .then(result => {
      if(result > 0){
        likeBtn.src = "/img/like_heart_r.svg";
        likeBtn.classList.add('red');
        likeBtn.classList.remove('gray');
        
      }
    })
    .catch(err => console.log(err));
  }
  if(likeBtn.classList.contains('red')){
    
    
    fetch("/mediaComment/deleteLike",{
      method : "DELETE",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify(data)
    })
    .then(resp => resp.text())
    .then(result => {
      if(result > 0){
        likeBtn.src = "/img/like_heart_w.svg";
        likeBtn.classList.add('gray');
        likeBtn.classList.remove('red');
        
      }
    })
    .catch(err => console.log(err));
  }


}