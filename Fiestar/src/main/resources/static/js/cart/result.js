document.querySelectorAll(".item-price").forEach(price => {
   price.innerText = "₩" + Number(price.innerText).toLocaleString();
})


document.querySelectorAll(".sc").forEach(sc => {
   sc.innerText = "₩" + Number(sc.innerText).toLocaleString();
})



  // 페이지 로딩 후 실행되는 함수
  window.onload = function() {
    // 전화번호를 포함하는 요소를 선택
    var phoneNumberElement = document.querySelector('.phonenumber-area');

    // 현재 텍스트(전화번호)를 가져옴
    var currentNumber = phoneNumberElement.textContent || phoneNumberElement.innerText;

    // 전화번호에 하이픈 추가하는 함수
    var formattedNumber = formatPhoneNumber(currentNumber);

    // 포맷된 전화번호로 요소의 텍스트를 업데이트
    phoneNumberElement.textContent = formattedNumber;
  };

  // 전화번호에 하이픈을 추가하는 함수
  function formatPhoneNumber(number) {
    // 정규 표현식을 사용하여 숫자만 추출
    var cleaned = ('' + number).replace(/\D/g, '');

    // 숫자를 세 부분으로 나누어 하이픈 추가
    var match = cleaned.match(/^(\d{3})(\d{4})(\d{4})$/);

    // 하이픈을 추가하여 포맷된 문자열 반환
    if (match) {
      return match[1] + '-' + match[2] + '-' + match[3];
    }

    // 일치하는 형식이 없으면 원래 번호 반환
    return number;
  }

