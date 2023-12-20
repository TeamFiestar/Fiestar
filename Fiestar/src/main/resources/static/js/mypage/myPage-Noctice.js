// 모달 열기
function openModal() {
    document.getElementById('myModal').style.display = 'block';
}

// 모달 닫기
function closeModal() {
    document.getElementById('myModal').style.display = 'none';
}

// 모달 외부를 클릭하면 모달 닫기
window.onclick = function (event) {
    var modal = document.getElementById('myModal');
    if (event.target === modal) {
        closeModal();
    }
};


const modalNoticeTitle = document.querySelector(".modalHeader_Title");
const modalNoticeDate = document.querySelector(".modalHeader_Date");
const modalNoticeContent = document.querySelector(".modalDetail_Content");
let thisTr;

function selectNoctice(siteNoticeNo, thisTr) {
    targetTr = thisTr.parentElement;

    console.log(siteNoticeNo);
    fetch('/myPage/ajaxNotice?siteNoticeNo=' + siteNoticeNo)
        .then(resp => resp.json())
        .then(SiteNotice => {

            modalNoticeTitle.innerHTML = SiteNotice.siteNoticeTitle;
            modalNoticeContent.innerHTML = SiteNotice.siteNoticeContent;

            openModal();

        })
        .catch(err => console.log(err));
}