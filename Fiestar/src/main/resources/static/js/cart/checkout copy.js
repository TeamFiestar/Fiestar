// // 변경 버튼 클릭 -> 인풋에 채워진 텍스트 모두 지우기

// const nameChangeBtn = document.getElementById("nameChangeBtn");

// nameChangeBtn.addEventListener("click", e => {

//     let name = e.target.nextElementSibling[0];
    
//     if(!nameInput.trim().length == 0){
//         nameInPut = ("");
//     }

// });
const nameChangeBtn = document.getElementById("purchaseBtn");

purchaseBtn.addEventListener("click", e => {
    
    let nameInput = document.getElementById("nameInput");
    let address1 = document.getElementById("address1");
    let address2 = document.getElementById("address2");
    let address3 = document.getElementById("address3");

    if(nameInput.value.trim().length || address1.value.trim().length || address2.trim().length || address3.trim().length !== 0){
        nameInput.value = ("");
        address1.value =("");
        address2.value =("");
        address3.value =("");
    }
});

