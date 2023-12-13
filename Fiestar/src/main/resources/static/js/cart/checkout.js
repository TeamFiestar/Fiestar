// 변경 버튼 클릭 -> 인풋에 채워진 텍스트 모두 지우기

const nameBtn = document.getElementById("name-btn");

nameBtn.addEventListener("click", e => {

    let nameInput = e.target.nextElementSibling;
    
    if(!nameInput.trim().length == 0){
        nameInPut = ("");
    }

});













const memberAddress = /*[[${session.loginMember.memberAddress}]]*/ "회원주소"

// 주소가 있을 경우 (A^^^B^^^c)
if(memberAddress != null) {

    // 1) 문자열을 ^^^을 기준으로 쪼개어 배열로 반환
    // const = memberAddress.split("^^^"); // 

}

//     // 2) name 속성값이 "memberAddress"인 요소를 모두 얻어옴
//     const inputs = document.querySelectorAll("[name='memberAddress']");
    
//     // 배열 or NodeList.forEach( () => {} )
//     // 배열 or NodeList의 모든 요소를 순차 접근하는 향상된 for문
//     // item : 현재 접근 중인 요소
//     // index : 현재 접근 중인 요소의 인덱스가 담긴다

//     // 3) input 요소에 arr의 값을 순서대로 대입
//     inputs.forEach( (item, index) => {

//         item.value = arr[index]; 

//     } );
// }