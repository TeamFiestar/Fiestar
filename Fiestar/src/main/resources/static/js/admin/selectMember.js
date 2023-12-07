const modalBody = document.querySelector(".modal");

const modal = document.querySelector(".modal");
const btnOpenPopup = document.querySelector(".btn-open-popup");
const modalCloseButton = document.getElementById("modalCloseButton");
const modal_background = document.querySelector(".modal");
const tbody = document.querySelector(".tbody");

const tr = document.querySelector("tr");

function selectBoard() {
  fetch("admin/selectBoard?memberNo=" + memberNo)
    .then((resp) => resp.text)
    .then((boardList) => {
      for (let board of boardList) {
        const tr = document.createElement("tr");
        tr.classList.add("th");
        const th1 = document.createElement("th");
        const th2 = document.createElement("th");
        const th3 = document.createElement("th");
        th1.innerText = "게시글 내용";
        th2.innerText = "게시글 아티스트";
        th3.innerText = "게시글 작성 날짜";
        tr.append(th1, th2, th3);

        boardList.forEach(function (board) {
          console.log(board);
          const tr2 = document.createElement("tr");
          tr2.classList.add("tr");

          const td1 = document.createElement("td");
          const td2 = document.createElement("td");
          const td3 = document.createElement("td");

          td1.innerHTML = board.boardContent;
          td2.innerHTML = board.artistGroupTitle;
          td3.innerHTML = board.boardEnrollDate;

          tr2.append(td1, td2, td3);

          tbody.append(tr, tr2);
        });
      }
    });
}
// btnOpenPopup.addEventListener("click", () => {
//   fetch("/admin/selectBoard?memberNo=" + memberNo)
//     .then((resp) => resp.json)
//     .then((boardList) => {
//       tr.innerHTML = "";
//       for(let board of boardList){
//         const
//       }
//     });
// });

// function createTable(boardList) {
//   var table = document.createElement("table");
//   table.border = "1";
//   table.style.borderCollapse = "collapse";

//   var thead = document.createElement("thead");
//   var headerRow = document.createElement("tr");

//   var headers = ["게시글 내용", "게시글 아티스트", "게시글 작성 날짜"];

//   headers.forEach(function (headerText) {
//     var th = document.createElement("th");
//     th.appendChild(document.createTextNode(headerText));
//     headerRow.appendChild(th);
//   });

//   thead.appendChild(headerRow);
//   table.appendChild(thead);

//   var tbody = document.createElement("tbody");

//   boardList.forEach(function (board) {
//     var row = document.createElement("tr");

//     var cell1 = document.createElement("td");
//     cell1.appendChild(document.createTextNode(board.boardContent));
//     row.appendChild(cell1);

//     var cell2 = document.createElement("td");
//     cell2.appendChild(document.createTextNode(board.artistGroupTitle));
//     row.appendChild(cell2);

//     var cell3 = document.createElement("td");
//     cell3.appendChild(document.createTextNode(board.boardEnrollDate));
//     row.appendChild(cell3);

//     tbody.appendChild(row);
//   });

//   table.appendChild(tbody);

//   // 동적으로 생성된 테이블을 모달 내에 추가
//   document.getElementById("dynamicTableContainer").appendChild(table);
// }

function openModal(memberNo) {
  // 모달을 열 때 비동기적으로 데이터를 가져와 테이블 생성
  selectBoard();
  modalBody.style.display = "show";
}

function closeModal() {
  // 모달 내부의 동적 테이블을 삭제
  document.getElementById("dynamicTableContainer").innerHTML = "";
  modalBody.style.display = "none";
}
