// 상수 선언
const selectAll = document.getElementById("selectAll");
const selectEachList = document.getElementsByClassName("selectEach");
const totalPrice = document.getElementById("totalPrice");
const xBtnList = document.querySelectorAll(".x-btn");
const plusList = document.querySelectorAll(".plus");
const minusList = document.querySelectorAll(".minus");

// 페이지 로드 시 각 가격에 원화 기호와 toLocaleString 적용
document.addEventListener('DOMContentLoaded', () => {
    updatePrices();
    checkedPrice();  // 초기 총액 계산
});

// 각 가격 업데이트 함수
function updatePrices() {
    const prices = document.querySelectorAll('.price');
    prices.forEach(price => {
        const numericPrice = Number(price.innerText.replace(/[^0-9.-]+/g, ""));
        price.innerText = '₩' + numericPrice.toLocaleString('ko-KR');
    });
}

// 상품 삭제 처리
xBtnList.forEach(xBtn => {
    xBtn.addEventListener("click", e => {
        const confirmation = confirm("선택하신 상품을 삭제하겠습니까?");
        if (confirmation) {
            const row = e.target.closest('.item-list');
            row.remove();
            checkedPrice();  // 총액 재계산
        }
    });
});

// 전체 선택 처리
selectAll.addEventListener("change", () => {
    Array.from(selectEachList).forEach(selectEach => {
        selectEach.checked = selectAll.checked;
    });
    checkedPrice();  // 총액 재계산
});

// 가격 계산 함수
function calcPrice(itemCount, defaultPrice) {
    return '₩' + (Number(itemCount) * Number(defaultPrice.replace('₩', '').replace(/,/g, ''))).toLocaleString('ko-KR');
}

// 체크된 상품의 가격 계산
function checkedPrice() {
    let sum = 0;
    Array.from(document.querySelectorAll(".selectEach:checked")).forEach(checkbox => {
        const itemRow = checkbox.closest('.item-list');
        const itemCount = itemRow.querySelector('.item-count').innerText;
        const defaultPrice = itemRow.querySelector('.default-price').innerText;
        sum += Number(itemCount) * Number(defaultPrice.replace('₩', '').replace(/,/g, ''));
    });
    totalPrice.innerText = '₩' + sum.toLocaleString('ko-KR');
}

// 수량 증가 처리
plusList.forEach(plus => {
    plus.addEventListener("click", e => {
        const itemRow = e.target.closest('.item-list');
        let itemCount = itemRow.querySelector('.item-count');
        let defaultPrice = itemRow.querySelector('.default-price').innerText;
        let amountPrice = itemRow.querySelector('.amount-price');
        if (Number(itemCount.innerText) < 10) {
            itemCount.innerText = Number(itemCount.innerText) + 1;  // 수량 증가
            amountPrice.innerText = calcPrice(itemCount.innerText, defaultPrice);  // 가격 업데이트
            checkedPrice();  // 총액 재계산
        } else {
            alert("1인당 최대 10개까지 구매 가능합니다.");
        }
    });
});

// 수량 감소 처리
minusList.forEach(minus => {
    minus.addEventListener("click", e => {
        const itemRow = e.target.closest('.item-list');
        let itemCount = itemRow.querySelector('.item-count');
        let defaultPrice = itemRow.querySelector('.default-price').innerText;
        let amountPrice = itemRow.querySelector('.amount-price');
        if (Number(itemCount.innerText) > 1) {
            itemCount.innerText = Number(itemCount.innerText) - 1;  // 수량 감소
            amountPrice.innerText = calcPrice(itemCount.innerText, defaultPrice);  // 가격 업데이트
            checkedPrice();  // 총액 재계산
        }
    });
});

// 각각의 selectEach 체크박스 상태 변경 시
Array.from(selectEachList).forEach(selectEach => {
    selectEach.addEventListener("change", () => {
        checkedPrice();  // 총액 재계산
    });
});

// 결제 버튼 클릭 시 처리
const form = document.getElementById("checkoutFrm");
form.addEventListener("submit", e => {
    if (document.querySelectorAll(".item-list").length == 0 || totalPrice.innerText === '₩0') {
        alert("선택된 상품이 없습니다.");
        e.preventDefault();  // 폼 제출 방지
    }
});
