
// 클릭한 댓글을 제외한 모든 댓글을 사라지게 함
function loadReplies(element, commentNo) {
  const parentCommentNo = document.getElementById('parent-comment-no');

  parentCommentNo.value = commentNo;


  var allComments = document.querySelectorAll('.comment-area');
  allComments.forEach(function (c) {
    if (c !== element.parentElement ) {
      c.classList.add('hide');
    }
  });

  var hideComment = document.querySelectorAll('.new-comment-area');
  hideComment.forEach(function (c) {
    c.classList.remove('hide');
  });

  // 뒤로가기 버튼 표시
  var backButton = document.querySelector('.back-button');
  backButton.style.display = 'block';

  // 비동기로 답글을 불러와서 new-comment-area에 삽입
  var newCommentArea = document.querySelector('.new-comment-area');
  newCommentArea.style.opacity = 1;
  newCommentArea.style.height = element.clientHeight + 'px';
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

  // 새로운 댓글 창을 숨김
  var newCommentArea = document.querySelector('.new-comment-area');
  newCommentArea.style.opacity = 0;
  newCommentArea.style.height = '0';
}


function submitComment() {
  const parentCommentNo = document.getElementById('parent-comment-no');
  const commentContent = document.getElementById('comment-input');
  
  let data = {
    'mediaCommentContent' : commentContent.value,
    'memberNo' : loginMemberNo,
    'mediaNo' : mediaNo,
    'parentCommentNo' : parentCommentNo.value
  }

  console.log(data);

  fetch("/mediaComment")
}


const modal = document.getElementById('modalContainer');

function modalOpen() {
  modal.classList.remove('hidden');
}
function modalClose() {
  modal.classList.add('hidden');
}

function generateComment(){
  // "comment-area" 클래스를 가진 새로운 div 엘리먼트 생성
  var commentArea = document.createElement("div");
  commentArea.className = "comment-area";

  // "comment-area-in" 클래스를 가진 내부 div 생성
  var commentAreaIn = document.createElement("div");
  commentAreaIn.className = "comment-area-in";

  // "img" 클래스를 가진 이미지 엘리먼트 생성하고 src 속성 설정
  var img = document.createElement("img");
  img.className = "img";
  img.src = "/img/male-user.png";

  // "comment-writer-area" 클래스를 가진 div 생성
  var commentWriterArea = document.createElement("div");
  commentWriterArea.className = "comment-writer-area";

  // "comment-writer" 클래스를 가진 div 생성하고 텍스트 내용 설정
  var commentWriter = document.createElement("div");
  commentWriter.className = "comment-writer";
  commentWriter.textContent = "댓글 작성자";

  // "comment-date" 클래스를 가진 div 생성하고 텍스트 내용 설정
  var commentDate = document.createElement("div");
  commentDate.className = "comment-date";
  commentDate.textContent = "11.21. 02:43";

  // comment-writer와 comment-date를 comment-writer-area에 추가
  commentWriterArea.appendChild(commentWriter);
  commentWriterArea.appendChild(commentDate);

  // "comment-profile" 클래스를 가진 div 생성
  var commentProfile = document.createElement("div");
  commentProfile.className = "comment-profile";

  // "report-img" 클래스를 가진 이미지 엘리먼트 생성하고 onclick 이벤트 설정
  var reportImg = document.createElement("img");
  reportImg.className = "report-img";
  reportImg.src = "/img/report-img.svg";
  reportImg.onclick = modalOpen;

  // report-img를 comment-profile에 추가
  commentProfile.appendChild(reportImg);

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
  commentContent.textContent = "와 재밌다!-------------------------------------------------------------------------------------------------------------------------------------------";

  // comment-content를 comment-content-area에 추가
  commentContentArea.appendChild(commentContent);

  // "speech-bubble-wrapper" 클래스를 가진 div 생성
  var speechBubbleWrapper = document.createElement("div");
  speechBubbleWrapper.className = "speech-bubble-wrapper";

  // "speech-bubble" 클래스를 가진 이미지 엘리먼트 생성하고 onclick 이벤트 설정
  var speechBubble = document.createElement("img");
  speechBubble.className = "speech-bubble";
  speechBubble.src = "/img/speech-bubble.png";
  speechBubble.onclick = function() {
      loadReplies(speechBubble);
  };

  // speech-bubble을 speech-bubble-wrapper에 추가
  speechBubbleWrapper.appendChild(speechBubble);

  // comment-area-in, comment-content-area, speech-bubble-wrapper를 comment-area에 추가
  commentArea.appendChild(commentAreaIn);
  commentArea.appendChild(commentContentArea);
  commentArea.appendChild(speechBubbleWrapper);

  const commentList = document.querySelector(".comment-list");
  commentList.append(commentArea)
}