

/* 아티스트 별 최신글 */
const swiper = new swiper('.swiper-container', {
  //기본 셋팅
  //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
  direction: 'horizontal',
  //한번에 보여지는 페이지 숫자
  slidesPerView: 3,
  //페이지와 페이지 사이의 간격
  spaceBetween: 100,
  //드레그 기능 true 사용가능 false 사용불가
  debugger: false,
  //마우스 휠기능 true 사용가능 false 사용불가
  mousewheel: false,
  //반복 기능 true 사용가능 false 사용불가
  loop: false,
  //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
  centeredSlides: false,
  // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
  // effect: 'fade',

  //방향표
  navigation: {
    //다음페이지 설정
    nextEl: '.swiper-button-next',
    //이전페이지 설정
    prevEl: '.swiper-button-prev',
  },
});

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
  updatePageContent(artistGroupTitle, boardNo);

  const modal = document.getElementById('feedDetail');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";


}

 // 예시로 {artistGroupTitle}과 {boardNo}에 값을 할당
 
 // openModal 함수 호출
 openModal(dynamicArtistGroupTitle, dynamicBoardNo);

// 예시로 페이지의 내용을 업데이트하는 함수
function updatePageContent(artistGroupTitle, boardNo) {
  // 여기에 페이지 내용을 동적으로 업데이트하는 로직을 추가
  console.log("Updating content for artistGroupTitle: " + artistGroupTitle + ", boardNo: " + boardNo);
}


function closeModal(stateObj){

  var newUrl = "/" + artistGroupTitle + "/feed";
  history.pushState(stateObj, "", newUrl);

  updatePageContent();
  const modal = document.getElementById('feedDetail');
  modal.classList.remove("show");
  document.body.style.overflow = "";
  
}