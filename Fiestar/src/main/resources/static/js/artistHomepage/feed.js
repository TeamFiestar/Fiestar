
let likeCheck;
let likeCount2;
const dataObj = {};
const likeImg = document.querySelector('#likeImg');
let boardNo2;

function openModal(boardNo) {
  // 새로운 URL 생성
  var newUrl = "/" + artistGroupTitle + "/feed/" + boardNo;
  console.log(artistGroupTitle);
  console.log(boardNo);
  console.log(newUrl);

  // 상태 객체 (필요에 따라 사용)
  var stateObj = { artistGroupTitle: artistGroupTitle, boardNo: boardNo };

  // 새로운 URL로 이동 (페이지의 내용을 로드하지 않음)
  history.pushState(stateObj, "", newUrl);

  // 페이지의 내용을 동적으로 업데이트하는 함수 호출 (예시로 updatePageContent 함수 사용)
  updatePageContent(boardNo);
  generateComment(boardNo);

  const modal = document.getElementById('feedDetail');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";

}


function closeModal(stateObj){

  var newUrl = "/" + artistGroupTitle + "/feed";
  history.pushState(stateObj, "", newUrl);

  updatePageContent();
  const modal = document.getElementById('feedDetail');
  modal.classList.remove("show");
  document.body.style.overflow = "";
  
}


function updatePageContent(boardNo) {


  // 여기에 페이지 내용을 동적으로 업데이트하는 로직을 추가
  fetch("/AJAXboardDetail?boardNo="+ boardNo)
  .then(resp => resp.json())
  .then(board => {
    console.log(board);

    /* ----------------- 아이디 등 인적사항 및 피드 내용 -------------------------- */
    const boardNickname = document.getElementById('boardNickname');
    boardNickname.innerText = board.memberNickname;
    const boardDate = document.getElementById('boardDate');
    boardDate.innerText = board.boardEnrollDate;

    const feedMain = document.querySelector('.feedMain');
    feedMain.innerText = board.boardContent;

    const profileImage = document.getElementById('profileImage');

    if(board.memberProfile) {
      profileImage.src = board.memberProfile;
    } else {
      profileImage.src = defaultImage;
    }

    const indicator = document.querySelector('#indicator');
    
    if(board.memberAuthority == 2) {
      indicator.classList.add("fa-solid", "fa-circle-check");
      indicator.style.color = "#7743DB";
    }else {
      indicator.classList.remove("fa-solid", "fa-circle-check");
      indicator.style.color = ""; 
    }
     /* ------------------------------------------------------------ */

    /* ----------------- 업로드된 이미지 표시 -------------------------- */
    if(board.imageList && board.imageList.length > 0) {
      const feedImg = document.querySelector('.feedImg');
      feedImg.innerHTML = '';

      board.imageList.forEach(image => {
        const imgElement = document.createElement('img');
        const imagePath = image.boardImagePath + image.boardImageRename;

        imgElement.src = imagePath;

        feedImg.appendChild(imgElement);
      });


    } else {
      const feedImg = document.querySelector('.feedImg');
      feedImg.innerHTML ='';

    }

     /* ------------------------------------------------------------ */

    /* ----------------- 피드 좋아요 표시 -------------------------- */
    const feedLikeCount = document.querySelector('#feedLikeCount');
    feedLikeCount.innerText = board.likeCount;

    console.log(board.likeCheck);

    if(board.likeCheck == 1){
      likeImg.classList.add("fa-solid");
      likeImg.classList.remove("fa-regular");
    }
    else{
      likeImg.classList.remove("fa-solid");
      likeImg.classList.add("fa-regular");
    }


    likeCount2 = board.likeCount;

    
    
    const commentList = board.commentList;

    // 'boardCommentDelFl'이 'N'인 댓글만 필터링
    const validComments = commentList.filter(comment => comment.boardCommentDelFl === 'N');

    const commentCount = validComments.length;

    const textWrapper = document.querySelector('.text-wrapper');

    textWrapper.innerText = commentCount + "개의 댓글";


    dataObj.boardNo = boardNo;
    dataObj.likeCheck = board.likeCheck;

    boardNo2 = board.boardNo;
    /* ------------------------------------------------------------ */

    /* ----------------- 댓글 표시 -------------------------- */
    


    

    /* ------------------------------------------------------------ */


    
    
  });
}

function generateComment(boardNo3) {

  if(!boardNo3) {
    console.log("Invalid boardNo3:", boardNo3);
    return;
  }

  const commentLists = document.querySelector(".comment-list");
  commentLists.innerHTML = "";

  fetch("/AJAXboardDetail?boardNo="+ boardNo3)
  .then(resp => resp.json())
  .then(response => {
    console.log("response", response);
    

    // console.log("commentList" + commentList.board.boardCommentList);

    if (response.commentList && response.commentList.length > 0) {
      const commentList = response.commentList;


    for (let comment of commentList) {

      console.log("Comment content:", comment.boardCommentContent);

      if (comment.boardCommentDelFl === "Y") {
        // 삭제된 댓글인 경우 목록에 추가하지 않음
        continue;
      }
    
      const  commentArea = document.createElement("div");
      commentArea.className = "comment-area";

      const  commentAreaIn = document.createElement("div");
      commentAreaIn.className = "comment-area-in";

      if(comment.boardParentCommentNo !=0) commentArea.classList.add("child-comment");
      if(comment.boardCommentDelFl == "Y") commentArea.innerText = "삭제된 댓글 입니다.";
      else {
    

      // "img" 클래스를 가진 이미지 엘리먼트 생성하고 src 속성 설정

      const div1 = document.createElement("div")
      div1.className = "div1";


      const img = document.createElement("img");
      img.className = "profile-img";
      if(comment.memberProfile) {
        img.src = comment.memberProfile;
      } else {
        img.src = defaultImage;
      }

      var commentWriterArea = document.createElement("div");
      commentWriterArea.className = "comment-writer-area";
    
      // "comment-writer" 클래스를 가진 div 생성하고 텍스트 내용 설정
      var commentWriter = document.createElement("div");
      commentWriter.className = "comment-writer";
      commentWriter.textContent = comment.memberNickname;

      var indicator2 = document.createElement("i");
      indicator2.className = "indicator";

    if(comment.memberAuthority == 2) {
      indicator2.classList.add("fa-solid");
      indicator2.classList.add("fa-circle-check");
      indicator2.style.color = "#7743DB";
    }else {
      indicator2.classList.remove("fa-solid");
      indicator2.classList.remove("fa-circle-check");
      indicator2.style.color = ""; 
    }

      var commentDate = document.createElement("div");
      commentDate.className = "comment-date";
      commentDate.textContent = comment.boardCommentEnrollDate;

      commentWriterArea.append(commentWriter, indicator2, commentDate);

      div1.append(img, commentWriterArea);

      var commentProfile = document.createElement("div");
      commentProfile.className = "comment-profile";

      var reportImg = document.createElement("img");
      reportImg.className = "report-img";
      reportImg.src = "/img/report.png";
      // reportImg.onclick = reportOpen;

      const deleteBtn = document.createElement("button");
      deleteBtn.classList.add("delete-cross");
      deleteBtn.innerText = "X";
      deleteBtn.setAttribute("onclick", "deleteComment(" + comment.boardCommentNo + ")");

      if(loginMemberNo == comment.memberNo) {
      commentProfile.append(deleteBtn, reportImg);
    } else {
      commentProfile.append(reportImg);
    }

      commentAreaIn.append(div1, commentProfile);
     
      
      var commentContentArea = document.createElement("div");
      commentContentArea.className = "comment-content-area";
      
      var commentContent = document.createElement("div");
      commentContent.className = "comment-content";
      commentContent.textContent = comment.boardCommentContent;
      
      commentContentArea.appendChild(commentContent);
      
      
      const commentLikeArea = document.createElement("div");
      commentLikeArea.className = "comment-like-area";

      const heartComment = document.createElement("i");
      heartComment.className = "fa-heart";
      if(comment.likeClickComment == 0 ) heartComment.classList.add("fa-regular");
      else heartComment.classList.add("fa-solid");

      heartComment.setAttribute("onclick", "likeComment(this, " +comment.boardCommentNo + ")" );
      heartComment.setAttribute("comment-no", comment.boardCommentNo);

      const likeCountComment = document.createElement("span");
      likeCountComment.className = "commentLikeCount";
      likeCountComment.innerText = comment.likeCountComment;

      const childCommentBtn = document.createElement("i");
      childCommentBtn.className = "fa-regular fa-comment fa-l";
      childCommentBtn.setAttribute("onclick", "showInsertComment(" + comment.boardCommentNo + ", this)");
    
      commentLikeArea.append(heartComment, likeCountComment, childCommentBtn );
      

      commentArea.append(commentAreaIn, commentContentArea,commentLikeArea );

    }
    commentLists.appendChild(commentArea);
  
  }
  updateCommentCount(response.commentList.length);

  } else {
    console.error(e.error);
  }
}
  
  )
  .catch((e) => console.log(e));

};



likeImg.addEventListener("click", (e) => {
  if (!loginCheck) {
    alert("로그인을 먼저 해주세요");
    return;
  }


  if (e.target.classList.contains("fa-regular")) {
    likeCheck = 0;
  } else {
    likeCheck = 1;
  }


  console.log(dataObj);

  fetch("/AJAXboardDetail/like",{
    method: "post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  })
    .then((resp) => resp.text())
    .then((count) => {
      if (count == -1) {
        console.log("좋아요 실패");
        return;
      }
      e.target.classList.toggle("fa-regular");
      e.target.classList.toggle("fa-solid");
      const feedLikeCount = document.querySelector('#feedLikeCount');
      if(likeCheck == 1){
        likeCount2 = likeCount2 - 1;
        dataObj.likeCheck = 0;
      }else{
        likeCount2 = likeCount2 + 1;
        dataObj.likeCheck = 1;
      }

      e.target.nextElementSibling.innerText = likeCount2;
    })
    .catch((e) => {
      console.log(e);
    });
});


function submitComment() {
  console.log(boardNo2);
  const commentInput = document.getElementById('comment-input');
  const commentContent = commentInput.value.trim();

  if (commentContent.trim().length == 0) {
    alert("댓글을 먼저 작성해주세요");

    commentInput.value = "";
    commentInput.focus();
    return;
  }


  const dataObj = {
    boardCommentContent: commentContent,
    memberNo: loginMemberNo,
    boardNo: boardNo2

  };
  console.log(boardNo2);
  console.log(dataObj);

  fetch("/comment",{
    method : "POST",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(dataObj)
  })
  .then(resp => resp.text())
  .then(result => {

    if(result > 0){
      alert("댓글 등록 성공");

      commentInput.value = "";
      generateComment(boardNo2);

    } else {
      alert("댓글을 등록하지 못했습니다.");
    }

  })
  .catch(err => console.log(err));

}

function deleteComment(boardCommentNo) {
  if (confirm("정말로 삭제 하시겠습니까?")) {
    fetch("/comment", {
      method: "DELETE",
      headers: { "Content-type": "application/json" },
      body: boardCommentNo,
    })
      .then((resp) => resp.text())
      .then((result) => {
        if (result > 0) {
          alert("삭제되었습니다.");
          generateComment(boardNo2);
        } else {
          alert("삭제를 실패하였습니다.");
        }
      })
      .catch((e) => console.log(e));
  }
}


/* ----------------------------------------------------------------------------------- */
function showInsertComment(boardParentCommentNo, btn) {

  const textarea = document.createElement("input");
  textarea.type = "text";
  textarea.placeholder = "댓글을 입력하세요";
  textarea.color = "gray";
  textarea.classList.add("commentInsertContent");


  btn.parentElement.after(textarea);

  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");

  const insertBtn = document.createElement("button");
  insertBtn.innerText = "등록";
  insertBtn.setAttribute("onclick", "insertChildComment(" + boardParentCommentNo + ", this)");

  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.setAttribute("onclick", "insertCancel(this)");

  commentBtnArea.append(insertBtn, cancelBtn);

  textarea.after(commentBtnArea);
}

function insertCancel(btn) {
  btn.parentElement.previousElementSibling.remove();
  btn.parentElement.remove();
}


function insertChildComment(boardParentCommentNo, btn) {
  const boardCommentContent = btn.parentElement.previousElementSibling.value;

  if (boardCommentContent.trim().length == 0) {
    alert("답글 작성 후 등록 버튼을 클릭해주세요.");
    btn.parentElement.previousElementSibling.value = "";
    btn.parentElement.previousElementSibling.focus();
    return;
  }

  const dataObj = {
    boardCommentContent: boardCommentContent,
    memberNo: loginMemberNo,
    boardNo: boardNo2,
    boardParentCommentNo: boardParentCommentNo,
  };

  fetch("/comment", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  })
    .then((resp) => resp.text())
    .then((result) => {
      if (result > 0) {
        alert("답글이 등록되었습니다.");
        generateComment(boardNo2);
      } else {
        alert("답글 등록에 실패했습니다...");
      }
    })
    .catch((err) => console.log(err));
}


function likeComment(btn, boardCommentNo) {
 
  let check;

 
  if (btn.classList.contains("fa-regular")) {
    check = 0;
  } else {
    check = 1;
  }

  const data = { 
    check: check,
    boardParentCommentNo : boardCommentNo,
  };



  fetch("/commentLike", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
    .then((resp) => resp.text())
    .then((count) => {
      if (count == -1) {
        console.log("좋아요 실패");
        return;
      }
      btn.classList.toggle("fa-regular");
      btn.classList.toggle("fa-solid");

      btn.nextElementSibling.innerText = count;
    })
    .catch((e) => {
      console.log(e);
    });
}






function wopenModal() {
  const modal = document.getElementById('feedWrite');
  modal.classList.add("show");
  document.body.style.overflow = "hidden";

}



function wcloseModal() {
  const modal = document.getElementById('feedWrite');
  modal.classList.remove("show");
  document.body.style.overflow = "";

}

