const trBody = document.querySelector(".tr-body");
// const recoverBtn = document.querySelector(".recoverBtn");
const tbody = document.querySelector(".tbody");

function selectDelMember() {
  fetch("/admin/selectDelMember")
    .then((resp) => resp.json())
    .then((map) => {
      console.log(map);
      console.log(map.deleteList);

      tbody.innerHTML = "";

      map.deleteList.forEach(function (member) {
        // var member = [...map.deleteList] {
        console.log(member);
        const tr2 = document.createElement("tr");
        tr2.classList.add("tr-body");

        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");
        const td4 = document.createElement("td");
        const td5 = document.createElement("td");
        const td6 = document.createElement("td");
        const btn = document.createElement("button");
        btn.classList.add("recoverBtn");
        btn.setAttribute("data-flag", member.memberDelFl);
        btn.setAttribute("onClick", "recoverBtn(this)");
        btn.innerText = "복구";

        td1.innerHTML = member.memberNo;
        td2.innerHTML = member.memberNickname;
        td3.innerHTML = member.memberEmail;
        td4.innerHTML = member.memberAddress;

        if (member.memberAuthority === 1) td5.innerText = "일반";
        else if (member.memberAuthority === 2) td5.innerText = "아티스트";
        else if (member.memberAuthority === 3) td5.innerText = "일반 관리자";
        else td5.innerText = "아티스트 관리자";

        td6.append(btn);

        tr2.append(td1, td2, td3, td4, td5, td6);
        tbody.append(tr2);
      });
    });
}
// recoverBtn.setAttribute("data-flag", memberDelFl);
function recoverBtn(thisbtn) {
  const flag = thisbtn.getAttribute("data-flag") == "N" ? "Y" : "N";

  const temp = thisbtn.parentElement.parentElement.children[0];
  const targetNo = temp.innerText;

  const obj = {};
  obj.flag = flag;
  obj.targetNo = targetNo;

  fetch("/admin/deleteMember", {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(obj),
  })
    .then((resp) => resp.text())
    .then((result) => {
      console.log(result);
      if (result > 0) {
        // recoverBtn.setAttribute("data-flag", flag);
        alert("복구 성공");
      } else {
        alert("복구 실패");
      }
      selectDelMember();
      // selectDeleteMember();
    })
    .catch((e) => {
      console.log(e);
    });
}

// document.addEventListener("DOMContentLoaded", function () {
//   var memberTableBody = document.getElementById("memberTableBody");

//   memberTableBody.addEventListener("click", function (event) {
//     var target = event.target;

//     // 복구 버튼을 클릭한 경우에만 처리
//     if (target.classList.contains("recoverBtn")) {
//       var memberNo = target.getAttribute("data-member-no");

//       // Fetch를 사용한 비동기 GET 요청
//       fetch("/restoration?memberNo=" + memberNo, {
//         method: "GET",
//         headers: {
//           "Content-Type": "application/json",
//         },
//       })
//         .then(function (response) {
//           if (!response.ok) {
//             throw new Error("Network response was not ok");
//           }
//           return response.json();
//         })
//         .then(function (data) {
//           // 서버에서 전달한 데이터(data)를 활용
//           console.log("서버 응답", data);
//           // 여기에 필요한 추가 로직을 추가할 수 있습니다.
//         })
//         .catch(function (error) {
//           console.error("요청 실패", error);
//           // 여기에 필요한 추가 로직을 추가할 수 있습니다.
//         });
//     }
//   });
// });
