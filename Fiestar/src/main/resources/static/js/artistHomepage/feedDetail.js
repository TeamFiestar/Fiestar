
function likeComment(btn, boardCommentNo) {
 
  let check;

 
  if (btn.classList.contains("fa-regular")) {
    check = 0;
  } else {
    check = 1;
  }

  const data = { 
    check: check,
    boardCommentNo : boardCommentNo,
  };

if(check == 0) { 

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
      btn.classList.toggle("fa-solid");
      btn.style.color = "#7743DB";

      
      btn.nextElementSibling.innerText = count;
    })
    .catch((e) => {
      console.log(e);
    });

  } else { 

    fetch("/deleteLike", {
      method: "DELETE",
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
  
        btn.nextElementSibling.innerText = count-1;
      })
      .catch((e) => {
        console.log(e);
      });

  }
}