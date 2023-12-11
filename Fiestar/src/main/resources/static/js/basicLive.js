let gereateToken;


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
  var urlParams = new URL(location.href).searchParams;
  options.appid = urlParams.get("appid");
  options.channel = urlParams.get("channel");
  options.token = urlParams.get("token");
  options.uid = urlParams.get("uid");
  if (options.appid && options.channel) {
    console.log(options.appid);
    $("#uid").val(options.uid);
    $("#appid").val(options.appid);
    $("#token").val(options.token);
    $("#channel").val(options.channel);
    $("#join-form").submit();
  }
});

// 호스트로 역할 변경 버튼 클릭 이벤트 처리
$("#host-join").click(function (e) {
  options.role = "host";
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
  options.role = role;

  $("#host-join").attr("disabled", true);
  $("#audience-join").attr("disabled", true);
  try {
    options.channel = artistGroupTitle;
    options.uid = null;
    options.appid = "8db96ea10154462a8fcb52cee3d45ccb";
    options.token = token;
    console.log(options);
    await join();
    if (options.role === "host") {
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








// // Agora 클라이언트 생성
// var client = AgoraRTC.createClient({
//   mode: "live",
//   codec: "vp8"
// });

// // 로컬 트랙 및 원격 사용자, 옵션 초기화
// var localTracks = {
//   videoTrack: null,
//   audioTrack: null
// };
// var remoteUsers = {};

// // Agora 클라이언트 옵션 설정
// var options = {
//   appid: "8db96ea10154462a8fcb52cee3d45ccb",
//   channel: "kang",
//   uid: null,
//   token: "007eJxTYGipvMa+gdmjlPGF+u0XYm2mjpJvCpZ1npnC8j2lUPqp9hkFBouUJEuz1ERDA0NTExMzo0SLtOQkU6Pk1FTjFBPT5OQkiZlpqQ2BjAyc2t4MjFAI4rMwZCfmpTMwAAD7CB3N",
//   role: "audience", // 기본 역할을 청취자로 설정
//   audienceLatency: 1
// };

// // 데모는 URL 매개변수로 채널에 자동으로 참여할 수 있음
// window.addEventListener('DOMContentLoaded', function () {
//   var urlParams = new URL(location.href).searchParams;
//   options.appid = urlParams.get("appid");
//   options.channel = urlParams.get("channel");
//   options.token = urlParams.get("token");
//   options.uid = urlParams.get("uid");
//   if (options.appid && options.channel) {
//     console.log(options.appid);
//     document.getElementById("uid").value = options.uid;
//     document.getElementById("appid").value = options.appid;
//     document.getElementById("token").value = options.token;
//     document.getElementById("channel").value = options.channel;
//     document.getElementById("join-form").submit();
//   }
// });

// // 호스트로 역할 변경 버튼 클릭 이벤트 처리
// document.getElementById("host-join").addEventListener("click", function (e) {
//   options.role = "host";
// });

// // 저지연 청취자로 역할 변경 버튼 클릭 이벤트 처리
// document.getElementById("lowLatency").addEventListener("click", function (e) {
//   options.role = "audience";
//   options.audienceLatency = 1;
//   document.getElementById("join-form").submit();
// });

// // 초저지연 청취자로 역할 변경 버튼 클릭 이벤트 처리
// document.getElementById("ultraLowLatency").addEventListener("click", function (e) {
//   options.role = "audience";
//   options.audienceLatency = 2;
//   document.getElementById("join-form").submit();
// });

// // 폼 제출 시 이벤트 처리
// async function joinSubmit(role) {
//   options.role = role;
//   document.getElementById("host-join").disabled = true;
//   document.getElementById("audience-join").disabled = true;
//   try {
//     options.channel = "kang";
//     options.uid = null;
//     options.appid = "8db96ea10154462a8fcb52cee3d45ccb";
//     options.token = "007eJxTYGipvMa+gdmjlPGF+u0XYm2mjpJvCpZ1npnC8j2lUPqp9hkFBouUJEuz1ERDA0NTExMzo0SLtOQkU6Pk1FTjFBPT5OQkiZlpqQ2BjAyc2t4MjFAI4rMwZCfmpTMwAAD7CB3N";
//     console.log(options);
//     await join();
//     if (options.role === "host") {
//       document.getElementById("success-alert a").href = `index.html?appid=${options.appid}&channel=${options.channel}&token=${options.token}`;
//       if (options.token) {
//         document.getElementById("success-alert-with-token").style.display = "block";
//       } else {
//         document.getElementById("success-alert a").href = `index.html?appid=${options.appid}&channel=${options.channel}&token=${options.token}`;
//         document.getElementById("success-alert").style.display = "block";
//       }
//     }
//   } catch (error) {
//     console.error(error);
//   } finally {
//     document.getElementById("leave").disabled = false;
//   }
// }

// // 퇴장 버튼 클릭 이벤트 처리
// document.getElementById("leave").addEventListener("click", function (e) {
//   console.log("leave1");
//   leave();
//   console.log("leave2");
// });

// // 채널 참여 함수
// async function join() {
//   if (options.role === "audience") {
//     client.setClientRole(options.role, {
//       level: options.audienceLatency
//     });
//     client.on("user-published", handleUserPublished);
//     client.on("user-unpublished", handleUserUnpublished);
//     console.log("user-published");
//   } else {
//     client.setClientRole(options.role);
//     console.log("setClientRole(options.role)");
//   }
//   console.log("client.join()");
//   console.log(options);
//   options.uid = await client.join(options.appid, options.channel, options.token || null, options.uid || null);
//   console.log("client.join().next");

//   if (options.role === "host") {
//     if (!localTracks.audioTrack) {
//       localTracks.audioTrack = await AgoraRTC.createMicrophoneAudioTrack({
//         encoderConfig: "music_standard"
//       });
//     }
//     if (!localTracks.videoTrack) {
//       localTracks.videoTrack = await AgoraRTC.createCameraVideoTrack();
//     }

//     localTracks.videoTrack.play("local-player");
//     document.getElementById("local-player-name").textContent = `localTrack(${options.uid})`;
//     document.getElementById("joined-setup").style.display = "flex";

//     await client.publish(Object.values(localTracks));
//     console.log("publish success");
//   }
// }

// // 퇴장 함수
// async function leave() {
//   console.log("leave3");

//   for (var trackName in localTracks) {
//     var track = localTracks[trackName];
//     if (track) {
//       track.stop();
//       track.close();
//       localTracks[trackName] = undefined;
//     }
//   }
//   console.log("leave4");

//   remoteUsers = {};
//   document.getElementById("remote-playerlist").innerHTML = "";

//   await client.leave();
//   document.getElementById("local-player-name").textContent = "";
//   document.getElementById("host-join").disabled = false;
//   document.getElementById("audience-join").disabled = false;
//   document.getElementById("leave").disabled = true;
//   document.getElementById("joined-setup").style.display = "none";
//   console.log("client leaves channel success");
// }

// // 원격 사용자 구독 함수
// async function subscribe(user, mediaType) {
//   const uid = user.uid;
//   await client.subscribe(user, mediaType);
//   console.log("subscribe success");

//   if (mediaType === 'video') {
//     const playerWrapper = document.createElement('div');
//     playerWrapper.id = `player-wrapper-${uid}`;
//     playerWrapper.innerHTML = `
//       <p class="player-name">remoteUser(${uid})</p>
//       <div id="player-${uid}" class="player"></div>
//     `;
//     document.getElementById("remote-playerlist").appendChild(playerWrapper);
//     user.videoTrack.play(`player-${uid}`, {
//       fit: "contain"
//     });
//   }

//   if (mediaType === 'audio') {
//     user.audioTrack.play();
//   }
// }

// // 원격 사용자가 미디어를 게시할 때 호출되는 이벤트 처리 함수
// function handleUserPublished(user, mediaType) {
//   console.log('"user-published" event for remote users is triggered.');
//   const id = user.uid;
//   remoteUsers[id] = user;
//   subscribe(user, mediaType);
// }

// // 원격 사용자가 미디어 게시를 중지할 때 호출되는 이벤트 처리 함수
// function handleUserUnpublished(user, mediaType) {
//   console.log('"user-unpublished" event for remote users is triggered.');
//   if (mediaType === 'video') {
//     const id = user.uid;
//     delete remoteUsers[id];
//     document.getElementById(`player-wrapper-${id}`).remove();
//   }
// }
