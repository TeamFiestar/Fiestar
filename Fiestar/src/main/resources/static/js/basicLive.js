let gereateToken;
let chattingSock;
let liveChattingRoomNo;

// Agora 클라이언트 생성
var client = AgoraRTC.createClient({
  mode: "live",
  codec: "vp8"
});

// 로컬 트랙 및 원격 사용자, 옵션 초기화
var localTracks = {
  videoTrack: null,
  audioTrack: null
};
var remoteUsers = {};

// Agora 클라이언트 옵션 설정
const options = {
  appid: "8db96ea10154462a8fcb52cee3d45ccb",
  channel: artistGroupTitle,
  uid: null,
  token: "007eJxTYGipvMa+gdmjlPGF+u0XYm2mjpJvCpZ1npnC8j2lUPqp9hkFBouUJEuz1ERDA0NTExMzo0SLtOQkU6Pk1FTjFBPT5OQkiZlpqQ2BjAyc2t4MjFAI4rMwZCfmpTMwAAD7CB3N",
  role: "audience", // 기본 역할을 청취자로 설정
  audienceLatency: 1
};

// 데모는 URL 매개변수로 채널에 자동으로 참여할 수 있음
$(() => {
  tokenGenerator('audience', 2);
});



// 저지연 청취자로 역할 변경 버튼 클릭 이벤트 처리
$("#lowLatency").click(function (e) {
  options.role = "audience";
  options.audienceLatency = 1;
  $("#join-form").submit();
});

// 초저지연 청취자로 역할 변경 버튼 클릭 이벤트 처리
$("#ultraLowLatency").click(function (e) {
  options.role = "audience";
  options.audienceLatency = 2;
  $("#join-form").submit();
});

// 비동기 토큰생성 
async function tokenGenerator(role, flag){
  let token = "token";
  
  console.log("flag : " + flag);
  fetch("/media/generateToken?roleFlag="+flag +"&channelName="+artistGroupTitle)
  .then( (response) => {
    console.log(response);
    return response.text();
  })
  .then ( (res) =>{
    console.log("res : " + res);
    options.token = res;
    
    joinSubmit(role, res)
  })
  .catch( (e) => {
    console.log(e);
  })
  
  
};

// 폼 제출 시 이벤트 처리
async function joinSubmit(role, token) {
  await leave();
  options.role = role;

  $("#audience-join").attr("disabled", true);
  try {
    options.channel = artistGroupTitle;
    options.uid = null;
    options.appid = "8db96ea10154462a8fcb52cee3d45ccb";
    options.token = token;
    console.log(options);
    await join();
    if (options.role === "host") {
      $("#host-join").attr("disabled", true);
      $("#success-alert a").attr("href", `index.html?appid=${options.appid}&channel=${options.channel}&token=${options.token}`);
      if (options.token) {
        $("#success-alert-with-token").css("display", "block");
      } else {
        $("#success-alert a").attr("href", `index.html?appid=${options.appid}&channel=${options.channel}&token=${options.token}`);
        $("#success-alert").css("display", "block");
      }
    }
  } catch (error) {
    console.error(error);
  } finally {
    $("#leave").attr("disabled", false);
  }
};

// 퇴장 버튼 클릭 이벤트 처리
$("#leave").click(function (e) {
  leave();
});

// 채널 참여 함수
async function join() {
  if (options.role === "audience") {
    client.setClientRole(options.role, {
      level: options.audienceLatency
    });
    client.on("user-published", handleUserPublished);
    client.on("user-unpublished", handleUserUnpublished);
    console.log("user-published");
  } else {
    client.setClientRole(options.role);
    console.log("setClientRole(options.role)");
  }
  console.log("client.join()");
  options.uid = await client.join(options.appid, options.channel, options.token || null, options.uid || null);
  console.log("client.join().next");
  
  if (options.role === "host") {
    if (!localTracks.audioTrack) {
      localTracks.audioTrack = await AgoraRTC.createMicrophoneAudioTrack({
        encoderConfig: "music_standard"
      });
    }
    if (!localTracks.videoTrack) {
      localTracks.videoTrack = await AgoraRTC.createCameraVideoTrack();
    }
    createChattingRoom();

    localTracks.videoTrack.play("remote-playerlist");
    $("#local-player-name").text(`localTrack(${options.uid})`);
    $("#joined-setup").css("display", "flex");

    await client.publish(Object.values(localTracks));
    console.log("publish success");
  }
}

// 퇴장 함수
async function leave() {
  for (trackName in localTracks) {
    var track = localTracks[trackName];
    if (track) {
      track.stop();
      track.close();
      localTracks[trackName] = undefined;
    }
    if(chattingSock){
      chattingSock.close();
      chattingSock = null;
    }
  }

  remoteUsers = {};
  $("#remote-playerlist").html("");

  await client.leave();
  $("#local-player-name").text("");
  $("#host-join").attr("disabled", false);
  $("#audience-join").attr("disabled", false);
  $("#leave").attr("disabled", true);
  $("#joined-setup").css("display", "none");
  console.log("client leaves channel success");
}

// 원격 사용자 구독 함수
async function subscribe(user, mediaType) {
  console.log("시청자 참여 성공");
  const uid = user.uid;
  await client.subscribe(user, mediaType);
  console.log("subscribe success");
  
  if (mediaType === 'video') {
    const player = $(`
    <div id="player-wrapper-${uid}" class="player">
    <p class="player-name">remoteUser(${uid})</p>
    <div id="player-${uid}" class="player"></div>
    </div>
    `);
    $("#remote-playerlist").append(player);
    user.videoTrack.play(`player-${uid}`, {
      fit: "contain"
    });
  }
  
  if (mediaType === 'audio') {
    user.audioTrack.play();
  }
}

// 원격 사용자가 미디어를 게시할 때 호출되는 이벤트 처리 함수
function handleUserPublished(user, mediaType) {
  console.log('"user-published" event for remote users is triggered.');
  if(!chattingSock){
    selectLiveChattingRoom();
  }
  const id = user.uid;
  remoteUsers[id] = user;
  subscribe(user, mediaType);
}

// 원격 사용자가 미디어 게시를 중지할 때 호출되는 이벤트 처리 함수
function handleUserUnpublished(user, mediaType) {
  console.log('"user-unpublished" event for remote users is triggered.');
  if (mediaType === 'video') {
    const id = user.uid;
    delete remoteUsers[id];
    $(`#player-wrapper-${id}`).remove();
  }
}

const createChattingRoom = () => {
  fetch("/chatting/createChattingRoom",{
    method : "POST",
    headers : {"Content-Type" : "application/json"},
    body : artistGroupTitle
  })
  .then( resp => resp.text())
  .then(result =>{
    liveChattingRoomNo = result;
    console.log(result);
    chattingJoin();
  })
  .catch(err => console.log(err));
}

const selectLiveChattingRoom = () => {

  fetch("/chatting/selectLiveChattingRoom?artistGroupTitle=" + artistGroupTitle)
  .then(resp => resp.text())
  .then(result => {
    liveChattingRoomNo = result;
    console.log(result);
    chattingJoin();
  })
  .catch(err => console.log(err));

}


const chattingJoin = () => {

  if(chattingSock){
    chattingSock.close();
    console.log("채팅방 참가2");
  }
  
  console.log("채팅방 참가");
  console.log(chattingSock);
  chattingSock = new SockJS("/liveSock");
  
  
  chattingSock.onmessage = function (e) {
    const msg = JSON.parse(e.data);
    console.log(msg);
  
    const commentArea = document.createElement('div');
    commentArea.className = 'comment-area';
  
    const commentAreaIn = document.createElement('div');
    commentAreaIn.className = 'comment-area-in';
  
    const profileImg = document.createElement('img');
    profileImg.className = 'profile-img';
    profileImg.src = msg.memberProfile;
  
    const commentWriterArea = document.createElement('div');
    commentWriterArea.className = 'comment-writer-area';
  
    const commentWriter = document.createElement('div');
    commentWriter.className = 'comment-writer';
    commentWriter.textContent = msg.memberNickname;
  
    const commentDate = document.createElement('div');
    commentDate.className = 'comment-date';
    commentDate.textContent = msg.messageSendTime;
    
    commentWriterArea.append(commentWriter, commentDate)
  
    commentAreaIn.append(profileImg, commentWriterArea);
  
    const commentContentArea = document.createElement('div');
    commentContentArea.className = 'comment-content-area';
  
    const commentContent = document.createElement('div');
    commentContent.className = 'comment-content';
    commentContent.textContent = msg.messageContent;
  
    commentContentArea.append(commentContent);
  
    commentArea.append(commentAreaIn, commentContentArea);
  
    commentList = document.querySelector('.comment-list');
  
    commentList.append(commentArea);
  }
}




const inputChat = document.getElementById('chatting-input');

const sendMessage = () => {
  if (!chattingSock){
    alert("방송이 시작되지 않았습니다");
    return;
  }

  if (inputChat.value.trim().length == 0) {
    alert("채팅을 입력해주세요");
    inputChat.value = "";
  } else {
    var obj = {
      memberNo: loginMemberNo,
      memberNickname : loginMember.memberNickname,
      memberProfile : loginMember.memberProfile,
      sendAuthority: authority,
      messageContent: inputChat.value,
      liveChattingRoomNo : liveChattingRoomNo
    };
    console.log(obj);
    chattingSock.send(JSON.stringify(obj));

    inputChat.value = "";
  }
};

// 호스트로 역할 변경 버튼 클릭 이벤트 처리
$("#host-join").click(function (e) {
  console.log(e);
  options.role = "host";
});