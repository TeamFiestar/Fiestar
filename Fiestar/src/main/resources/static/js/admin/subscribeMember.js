const modal = document.querySelector(".modal");
const btnOpenPopup = document.querySelector(".btn-open-popup");
const modalCloseButton = document.getElementById("modalCloseButton");
const modal_background = document.querySelector(".modal");
const tbody = document.querySelector(".tbody");
const tbody2 = document.querySelector(".tbody2");
const h1 = document.querySelector(".h1");

const tr = document.querySelector("tr");

function selectBoard(memberNo, artistGroupTitle) {
  fetch("/admin/selectSubscribeBoard?memberNo=" + memberNo + "&artistGroupTitle=" + artistGroupTitle)
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
        // h1.innerHTML = board.memberNickname;
        tr2.append(td1, td2, td3);

        tbody.append(tr2);
      });
    });
}

function selectSubscribeMemberAjax() {
  fetch("/admin/selectSubscribeMemberAjax")
    .then((resp) => resp.json())
    .then((map) => {
      console.log(map);

      tbody2.innerHTML = "";

      map.subscribeList.forEach(function (member) {
        const tr = document.createElement("tr");
        tr.classList.add("tr-body");

        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");
        const td4 = document.createElement("td");
        const td5 = document.createElement("td");
        const td6 = document.createElement("td");
        const td7 = document.createElement("td");
        const btn = document.createElement("button");
        btn.setAttribute("data-flag", member.memberDelFl);
        btn.setAttribute("onclick", "withDraw(this)");
        btn.innerText = "탈퇴";

        td1.innerHTML = member.memberNo;
        td2.innerHTML = member.memberNickname;
        td3.innerHTML = member.memberEmail;
        td3.setAttribute("onclick", "openModal(" + member.memberNo + ")");
        td4.innerHTML = member.memberAddress;

        if (member.memberAuthority === 1) td5.innerText = "일반";
        else if (member.memberAuthority === 2) td5.innerText = "아티스트";
        else if (member.memberAuthority === 3) td5.innerText = "일반 관리자";
        else td5.innerText = "아티스트 관리자";
        // const select = document.createElement("select");
        // select.setAttribute("name", "memberAuthority");
        // select.setAttribute("onClick", "changeAuthority(" + member.memberNo + ", this)");
        // select.classList.add("authority");

        // const option1 = document.createElement("option");
        // const option2 = document.createElement("option");
        // const option3 = document.createElement("option");
        // const option4 = document.createElement("option");

        // option1.innerText = "일반";
        // option2.innerText = "아티스트";
        // option3.innerText = "일반 관리자";
        // option4.innerText = "아티스트 관리자";

        // select.append(option1, option2, option3, option4);

        // td5.append(select);
        td6.innerHTML = member.countReport;
        td7.append(btn);
        // td7.append(select);

        tr.append(td1, td2, td3, td4, td5, td6, td7);

        tbody2.append(tr);
      });
    });
}

function openModal(memberNo) {
  // Show the modal
  modal.style.display = "block";
  // Call the selectBoard function with the memberNo parameter
  selectBoard(memberNo, artistGroupTitle);
}

// Close the modal
function closeModal() {
  modal.style.display = "none";
}

// Call openModal function with the memberNo parameter when needed
// For example, when a button is clicked
btnOpenPopup.addEventListener("click", function () {
  openModal(memberNo);
});
window.addEventListener("click", (e) => {
  e.target === modal_background ? closeModal() : false;
});

function withDraw(thisbtn) {
  const flag = thisbtn.getAttribute("data-flag") == "N" ? "Y" : "Y";

  const targetNo = thisbtn.parentElement.parentElement.children[0].innerText;

  const data = { flag: flag, targetNo: targetNo };

  fetch("/admin/withDraw", {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
    .then((resp) => resp.json())
    .then((result) => {
      console.log(result);

      if (result > 0) {
        alert("탈퇴 성공");
      } else {
        alert("탈퇴 실패");
      }
      selectSubscribeMemberAjax();
    })
    .catch((e) => {
      console.log(e);
    });
}
