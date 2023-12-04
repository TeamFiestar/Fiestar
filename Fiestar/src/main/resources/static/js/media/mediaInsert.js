
const inputUrl = document.getElementById("input-url");
const youtubeImg = document.getElementById("youtube-url");
const mediaAddress = document.getElementById("mediaAddress");
const mediaTime = document.getElementById("mediaTime");

inputUrl.addEventListener("input", () => {

  let youtubeUrl = null
  

  youtubeUrl = youtubeId(inputUrl.value);

  console.log(youtubeUrl);

  if(youtubeUrl != null){
    // youtubeImg.src = `https://img.youtube.com/vi/${youtubeUrl}/0.jpg `
    youtubeImg.src = `https://www.youtube.com/embed/${youtubeUrl}`
    mediaAddress.value = youtubeUrl;

    console.log(parsingVideoDuration(youtubeUrl));
  }

});

// https://img.youtube.com/vi/ [id] /0.jpg  
// 유투브 썸네일 주소

function youtubeId(url) {

  var tag = "";

  if(url)  {

      var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;

      var matchs = url.match(regExp);

      if (matchs) {

          tag += "유튜브 아이디 : "+matchs[7];

          if (matchs[7].length == 11){
            console.log(matchs[7]);
            return matchs[7];
          }
      }


  }

}

// 유투브 동영상 시간 추출
function parsingVideoDuration(url){

  // YouTube Data API 키
  var apiKey = 'AIzaSyDeMNRoJHA_ZSIPhLSeGsYo0r9M__1acqk';
  
  // YouTube 동영상 ID
  var videoId = url;
  
  // YouTube Data API 엔드포인트
  var apiUrl = 'https://www.googleapis.com/youtube/v3/videos';
  
  // YouTube Data API 호출
  fetch(apiUrl + '?part=contentDetails&id=' + videoId + '&key=' + apiKey)
      .then(function(response) {
          return response.json();
      })
      .then(function(data) {
          // API 응답에서 동영상의 재생 시간 정보 추출
          var duration = data.items[0].contentDetails.duration;
          console.log('동영상 변환 시간 :' + convertDuration(duration)) ;
          mediaTime.value =  convertDuration(duration);
      })
      .catch(function(error) {
          console.error('API 호출 중 오류 발생: ', error);
      });
}

// 시간 00:00 식으로 변환
function convertDuration(isoDuration) {
  // 정규표현식을 사용하여 분과 초를 추출
  var matches = isoDuration.match(/PT(\d+)M(\d+)S/);

  if (!matches) {
      return null; // 올바르지 않은 형식이라면 null 반환
  }

  var minutes = parseInt(matches[1], 10);
  var seconds = parseInt(matches[2], 10);

  // mm:ss 형식으로 변환
  var formattedDuration = minutes.toString().padStart(2, '0') + ':' + seconds.toString().padStart(2, '0');

  return formattedDuration;
}