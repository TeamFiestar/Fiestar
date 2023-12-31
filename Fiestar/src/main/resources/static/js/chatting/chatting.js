let selectChattingNo; // 선택한 채팅방 번호
let selectTargetNo; // 현재 채팅 대상
let selectTargetName; // 대상의 이름
let selectTargetProfile; // 대상의 프로필

let chattingRoomNo;

let nicknameList;

const display = document.getElementsByClassName("chat-ul")[0];

function selectUserList() {
  fetch("chatting?artistGroupTitle=" + artistGroupTitle)
    .then((resp) => resp.text)
    .then((userList) => {
      console.log(userList);

      const userNameList = document.querySelector(".user-list");
      userNameList.innerHTML = "";

      for (let user of userList) {
        const li = document.createElement("li");
        li.classList.add("userName");
        li.innerHTML = user.memberNickname;

        userNameList.append(li);
      }
    });
}

let userSock;

if (loginMemberNo != "") {
  console.log(userSock);
  userSock = new SockJS("/userSock");
}
const userList = () => {
  var obj = {
    memberNo: loginMemberNo,
    memberNickname: memberNickname,
    artistGroupNo: artistGroupNo,
  };
  console.log(obj);
  userSock.send(JSON.stringify(obj));
};

userSock.onmessage = function (e) {
  result = JSON.parse(e.data);
  console.log(result);

  if (typeof result == "object") {
    nicknameList = result;
  } else {
    nicknameList.splice(nicknameList.indexOf(result), 1);
  }

  const ul = document.querySelector(".user-list");
  ul.innerHTML = "";

  nicknameList.forEach((nickname) => {
    const li = document.createElement("li");

    li.classList.add("userName");
    li.innerHTML = nickname;
    ul.append(li);
  });
};

//-------------------------------------------------채팅 입력
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
      memberProfile: memberProfile,
      memberNickname: memberNickname,
    };
    console.log(obj);
    console.log(artistGroupNo);
    chattingSock.send(JSON.stringify(obj));

    inputChat.value = "";
  }
};

inputChat.addEventListener("keyup", (e) => {
  if (e.key == "Enter") {
    // if (!e.shiftKey) {
    //   sendMessage();
    // }
    sendMessage();
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
      if (authority == 1) {
        if (msg.sendAuthority == 2) {
          li.classList.add("chat-list");

          const img = document.createElement("img");
          if (msg.memberProfile == null) img.setAttribute("src", userDefaultImage);
          else img.setAttribute("src", msg.memberProfile);

          const div = document.createElement("div");

          const b = document.createElement("b");
          b.innerText = msg.memberNickname;

          const br = document.createElement("br");

          div.append(b, br, p, span);
          li.append(img, div);
        }
      } else if (authority == 2) {
        li.classList.add("chat-list");

        const img = document.createElement("img");
        if (msg.memberProfile == null) img.setAttribute("src", userDefaultImage);
        else img.setAttribute("src", msg.memberProfile);

        const div = document.createElement("div");

        const b = document.createElement("b");
        b.innerText = msg.memberNickname;

        const br = document.createElement("br");

        div.append(b, br, p, span);
        li.append(img, div);
      }
    }

    ul.append(li);

    display.scrollTop = display.scrollHeight;
  }
};

document.addEventListener("DOMContentLoaded", () => {
  send.addEventListener("click", sendMessage);
});
