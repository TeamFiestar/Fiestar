
const inputUrl = document.getElementById("input-url");
const youtubeImg = document.getElementById("youtube-url");

inputUrl.addEventListener("input", () => {

  let youtubeUrl = null
  

  youtubeUrl = youtubeId(inputUrl.value);

  console.log(youtubeUrl);

  if(youtubeUrl != null){
    // youtubeImg.src = `https://img.youtube.com/vi/${youtubeUrl}/0.jpg `
    youtubeImg.src = `https://www.youtube.com/embed/${youtubeUrl}`
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

  var s1 = "https://www.youtube.com/watch?v=Vrwyo1A8XNg";

  var s2 = "http://youtu.be/Vrwyo1A8XNg";

youtubeId(s1);
youtubeId(s2);

