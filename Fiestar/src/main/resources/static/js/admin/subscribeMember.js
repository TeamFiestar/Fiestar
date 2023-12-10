const modal = document.querySelector(".modal");
const btnOpenPopup = document.querySelector(".btn-open-popup");
const modalCloseButton = document.getElementById("modalCloseButton");
const modal_background = document.querySelector(".modal");
const tbody = document.querySelector(".tbody");

const tr = document.querySelector("tr");

function selectBoard(memberNo) {
  fetch("/admin/selectBoard?memberNo=" + memberNo)
    .then((resp) => resp.json())
    .then((boardList) => {
      console.log(boardList);

      tbody.innerHTML = "";

      const tr = document.createElement("tr");
      tr.classList.add("th");
      const th1 = document.createElement("th");
      const th2 = document.createElement("th");
      const th3 = document.createElement("th");
      th1.innerText = "게시글 아티스트";
      th2.innerText = "게시글 내용";
      th3.innerText = "게시글 작성 날짜";
      tr.append(th1, th2, th3);
      tbody.append(tr);

      boardList.forEach(function (board) {
        console.log(board);
        const tr2 = document.createElement("tr");
        tr2.classList.add("tr");

        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");

        td1.innerHTML = board.artistGroupTitle;
        td2.innerHTML = board.boardContent;
        td3.innerHTML = board.boardEnrollDate;

        tr2.append(td1, td2, td3);

        tbody.append(tr2);
      });
    });
}