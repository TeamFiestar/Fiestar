// 채팅방 입장 = 바로 접속
function chattingEnter(e) {
  console.log(e.target);
  console.log(e.currentTarget);

  const targetNo = e.currentTarget.getAttribute("artistGroupNo");

  fetch("/chatting/enter?targetNo=" + targetNo)
    .then((resp) => resp.text())
    .then((artistGroupNo) => {
      console.log(artistGroupNo);
    });
}

// 채팅 조회(비동기)
function selectChatting() {
  fetch("/chatting/select?+" + `chattingRoomNo=${selectChattingRoomNo}`)
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

        if (loginMemberNo == msg.senderNo) {
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
  chattingSock = new SockJs("/chatSock");
}
// let chattingSock = new SockJS("/chatSock");

const send = document.querySelector("#send");

const sendMessage = () => {
  const inputChat = document.querySelector("#inputChat");

  if (inputChat.value.trim().length == 0) {
    alert("채팅을 입력해주세요");
    inputChat.value = "";
  } else {
    var obj = {
      senderNo: loginMemberNo,
      targetNo: selectTargetNo,
      chattingRoomNo: selectChattingRoomNo,
      messageContent: inputChat.value,
    };
    console.log(obj);

    chattingSock.send(JSON.stringify(obj));

    inputChat.value = "";
  }
};

inputChat.addEventListner("keyup", (e) => {
  if (e.key == "Enter") {
    if (!e.shiftKey) {
      sendMessage();
    }
  }
});

chattingSock.onmesage = (e) => {
  const msg = JSON.parse(e.data);
  console.log(msg);

  // 현재 보고있는 경우 비동기
  if (selectChattingRoomNo == msg.chattingRoomNo) {
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

      if (loginMemberNo == msg.senderNo) {
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
  }
};
