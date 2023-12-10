const trBody = document.querySelector(".tr-body");
const recoverBtn = document.querySelector(".recoverBtn");
const tbody = document.querySelector(".tbody");

// function selectDeleteMember() {
//   fetch("/admin/deleteMember")
//     .then((resp) => resp.json())
//     .then((map) => {
//       console.log(map);

//       tbody.innerHTML = "";

//       const tr = document.createElement("tr");
//       tr.classList.add("tr-head");

//       const th1 = document.createElement("th");
//       const th2 = document.createElement("th");
//       const th3 = document.createElement("th");
//       const th4 = document.createElement("th");
//       const th5 = document.createElement("th");
//       const th6 = document.createElement("th");
//       th1.innerText = "회원 번호";
//       th2.innerText = "닉네임";
//       th3.innerText = "아이디(이메일)";
//       th4.innerText = "주소";
//       th5.innerText = "권한";
//       th6.innerText = "탈퇴 복구";

//       tr.append(th1, th2, th3, th4, th5, th6);
//       tbody.append(tr);

//       map.deleteList.forEach(function (member) {
//         console.log(member);
//         const tr2 = document.createElement("tr");
//         tr.classList.add("tr-body");

//         const td1 = document.createElement("td");
//         const td2 = document.createElement("td");
//         const td3 = document.createElement("td");
//         const td4 = document.createElement("td");
//         const td5 = document.createElement("td");
//         const td6 = document.createElement("td");
//         const btn = document.createElement("button");
//         btn.classList.add("recoverBtn");
//         btn.setAttribute("data-flag", member.memberDelFl);

//         td1.innerHTML = member.memberNo;
//         td2.innerHTML = member.memberNickname;
//         td3.innerHTML = member.memberEmail;
//         td4.innerHTML = member.memberAddress;

//         if (member.memberAuthority === 1) td5.innerText = "일반";
//         else if (member.memberAuthority === 2) td5.innerText = "아티스트";
//         else if (member.memberAuthority === 3) td5.innerText = "일반 관리자";

//         td6.append(btn);

//         tr2.append(td1, td2, td3, td4, td5, td6);
//         tbody.append(tr2);
//       });
//     });
// }
// recoverBtn.setAttribute("data-flag", memberDelFl);
recoverBtn.addEventListener("click", (e) => {
  const flag = e.target.getAttribute("data-flag") == "N" ? "Y" : "N";

  const temp = e.target.parentElement.parentElement.children[0];
  const targetNo = temp.innerText;

  const obj = {};
  obj.flag = flag;
  obj.targetNo = targetNo;

  fetch("/admin/deleteMember", {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(obj),
  })
    .then((resp) => resp.json)
    .then((result) => {
      if (result > 0) {
        if (flag == "Y") {
          recoverBtn.setAttribute("data-flag", flag);
        } else {
          alert("복구 실패");
        }
      }
      // selectDeleteMember();
    })
    .catch((e) => {
      console.log(e);
    });
});

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
