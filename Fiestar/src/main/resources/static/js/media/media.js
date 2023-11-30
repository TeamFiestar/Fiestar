function loadReplies(element) {
  // 클릭한 댓글을 제외한 모든 댓글을 사라지게 함
  var allComments = document.querySelectorAll('.comment-area');
  allComments.forEach(function (c) {
    if (c !== element.parentElement ) {
      c.classList.add('hide');
    }
  });

  // 뒤로가기 버튼 표시
  var backButton = document.querySelector('.back-button');
  backButton.style.display = 'block';

  // 비동기로 답글을 불러와서 new-comment-area에 삽입
  var newCommentArea = document.querySelector('.new-comment-area');
  newCommentArea.style.opacity = 1;
  newCommentArea.style.height = element.clientHeight + 'px';
}

function goBack() {
  // 모든 댓글을 다시 보이게 함
  var allComments = document.querySelectorAll('.comment-area');
  allComments.forEach(function (c) {
    c.classList.remove('hide');
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
  // 댓글을 서버에 제출하는 로직 추가

  // 서버에 제출한 후에 새로운 댓글 창을 다시 숨김
  var newCommentArea = document.querySelector('.new-comment-area');
  newCommentArea.style.opacity = 0;
  newCommentArea.style.height = '0';
}


const modal = document.getElementById('modalContainer');

function modalOpen() {
  modal.classList.remove('hidden');
}
function modalClose() {
  modal.classList.add('hidden');
}