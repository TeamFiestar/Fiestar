let selectChattingNo; // 선택한 채팅방 번호
let selectTargetNo; // 현재 채팅 대상
let selectTargetName; // 대상의 이름
let selectTargetProfile; // 대상의 프로필

let chattingRoomNo;

const display = document.getElementsByClassName("chat-ul")[0];
// 채팅방 입장 = 바로 접속
function chattingEnter(e) {
  console.log(e.target);
  console.log(e.currentTarget);

  const targetNo = e.currentTarget.getAttribute("artistGroupNo");

  // targetNo == 채팅방 번호 == 아티스트 그룹 번호
  fetch("/chatting/enter?targetNo=" + targetNo)
    .then((resp) => resp.text())
    .then((artistGroupNo) => {
      console.log(artistGroupNo);
    });
}

// 채팅 조회(비동기)
function selectChatting() {
  fetch(
    "/chatting/select?+" +
      `chattingRoomNo=${selectChattingRoomNo}&memberNo=${loginMemberNo}`
  )
    .then((resp) => resp.text())
    .then((messageList) => {
      console.log(messageList);

      const ul = document.querySelector(".chat-ul");

      ul.innerHTML = "";

      for (let msg of messageList) {
        const li = document.createElement("li");

        const span = document.createElement("span");
        span.classList.add("chat-date");
        span.innerText = msg.messageSendTime;

        const p = document.createElement("p");
        p.classList.add("chat");
        p.innerHTML = msg.messageContent;

        if (chattingRoomNo == msg.chattingRoomNo) {
          li.classList.add("mychat-list");
          li.append(span, p);
        } else {
          li.classList.add("chat-list");

          const img = document.createElement("img");
          img.setAttribute("src", selectTargetProfile);

          const div = document.createElement("div");

          const b = document.createElement("b");
          b.innerText = selectTargetName;

          const br = document.createElement("br");

          div.append(b, br, p, span);
          li.append(img, div);
        }

        ul.append(li);
        display.scrollTop = display.scrollHeight;
      }
    })
    .catch((e) => console.log(e));
}

let chattingSock;

if (loginMemberNo != "") {
  console.log(chattingSock);
  chattingSock = new SockJS("/chatSock");
}
// let chattingSock = new SockJS("/chatSock");

const send = document.querySelector("#send");

const inputChat = document.querySelector("#inputChat");

const sendMessage = () => {
  if (inputChat.value.trim().length == 0) {
    alert("채팅을 입력해주세요");
    inputChat.value = "";
  } else {
    var obj = {
      memberNo: loginMemberNo,
      sendAuthority: authority,
      // chattingRoomNo: artistGroupNo,
      artistGroupNo: artistGroupNo,
      messageContent: inputChat.value,
    };
    console.log(obj);
    console.log(artistGroupNo);
    chattingSock.send(JSON.stringify(obj));

    inputChat.value = "";
  }
};

inputChat.addEventListener("keyup", (e) => {
  if (e.key == "Enter") {
    if (!e.shiftKey) {
      sendMessage();
    }
  }
});

chattingSock.onmessage = function (e) {
  const msg = JSON.parse(e.data);
  console.log(msg);

  // 현재 보고있는 경우 비동기
  if (artistGroupNo == msg.artistGroupNo) {
    const ul = document.querySelector(".chat-ul");

    // ul.innerHTML = "";

    // for (let msg of messageList) {
    const li = document.createElement("li");

    const span = document.createElement("span");
    span.classList.add("chat-date");
    span.innerText = msg.messageSendTime;

    const p = document.createElement("p");
    p.classList.add("chat");
    p.innerHTML = msg.messageContent;

    if (loginMemberNo == msg.memberNo) {
      li.classList.add("mychat-list");
      li.append(span, p);
    } else {
      li.classList.add("chat-list");

      const img = document.createElement("img");
      img.setAttribute("src", selectTargetProfile);

      const div = document.createElement("div");

      const b = document.createElement("b");
      b.innerText = selectTargetName;

      const br = document.createElement("br");

      div.append(b, br, p, span);
      li.append(img, div);
    }

    ul.append(li);
    display.scrollTop = display.scrollHeight;
    // }
  }
};

document.addEventListener("DOMContentLoaded", () => {
  send.addEventListener("click", sendMessage);
});
