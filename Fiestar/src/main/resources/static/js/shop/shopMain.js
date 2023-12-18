const dateBtn = document.getElementById("dateBtn");
const highPrice = document.getElementById("highPrice");
const lowPrice = document.getElementById("lowPrice");


dateBtn.addEventListener('click', () => {
  selectHigh(1)
});
highPrice.addEventListener('click', () => {
  selectHigh(2)
});
lowPrice.addEventListener('click', () => {
  selectHigh(3)
});

let queryString;
// const params = {};
let urlParams;
let url;

function selectHigh(key){

  queryString = window.location.search;  
  urlParams = new URLSearchParams(queryString);
  const shopSearchValue = urlParams.get("shopSearch");


  if(!queryString){
    
    url = `/shop/home/sortList?key=${key}`;

    const productContainer = document.querySelector('.product-area-detail');
    productContainer.innerHTML = "";

    fetch(url)

    .then(response =>  response.json())
    .then(productList => {
      for(let product of productList){
        
        const productNo = product.productNo;

        // 각 제품을 위한 div 엘리먼트를 생성
        const productDiv = document.createElement('div');
        productDiv.className = 'product-list';

        // href 속성과 'product-profile' 클래스를 가진 anchor 엘리먼트를 생성
        const anchorElement = document.createElement('a');
        anchorElement.href = '/shop/shopDetail/' + productNo ;
        anchorElement.className = 'product-profile';

        // 제품 이미지 영역을 위한 div를 생성.
        const imageAreaDiv = document.createElement('div');
        imageAreaDiv.className = 'product-image-area';
        imageAreaDiv.innerHTML = `<img src="${product.productImageThumbnail}${product.productImageThumbnailRename}">`;

        // 제품 이름과 가격 영역을 위한 div를 생성
        const nameAreaDiv = document.createElement('div');
        nameAreaDiv.className = 'product-name-area';

        // 제품 이름을 나타내는 div를 생성
        const nameDiv = document.createElement('div');
        nameDiv.className = 'product-name';
        nameDiv.innerText = product.productName;

        // 줄 바꿈 엘리먼트를 생성
        const lineBreak = document.createElement('br');

        // 제품 가격을 나타내는 div를 생성
        const priceDiv = document.createElement('div');
        priceDiv.className = 'product-price';
        priceDiv.innerText = product.productPrice;

        // 생성한 엘리먼트들을 각각의 부모에 추가
        nameAreaDiv.appendChild(nameDiv);
        nameAreaDiv.appendChild(lineBreak);
        nameAreaDiv.appendChild(priceDiv);

        anchorElement.appendChild(imageAreaDiv);
        anchorElement.appendChild(nameAreaDiv);

        productDiv.appendChild(anchorElement);

        // 제품 div를 제품 컨테이너에 추가
        productContainer.appendChild(productDiv);
        
      }
    })
  }else{
    for (let UP of urlParams.keys()) {

      if(UP == 'artistGroupNo'){
  
        const artistGroupNoValue = urlParams.get("artistGroupNo");
  
        url = `/shop/home/groupSortList?artistGroupNo=${artistGroupNoValue}&key=${key}`;
  
        const productContainer = document.querySelector('.product-area-detail');
        productContainer.innerHTML = "";
  
        fetch(url)
  
        .then(response =>  response.json())
        .then(productList => {
          for(let product of productList){
  
            const productNo = product.productNo;
  
            const productDiv = document.createElement('div');
            productDiv.className = 'product-list';
  
            const anchorElement = document.createElement('a');
            anchorElement.href = '/shop/shopDetail/'+ productNo;
            anchorElement.className = 'product-profile';
  
            const imageAreaDiv = document.createElement('div');
            imageAreaDiv.className = 'product-image-area';
            imageAreaDiv.innerHTML = `<img src="${product.productImageThumbnail}${product.productImageThumbnailRename}">`;
  
            const nameAreaDiv = document.createElement('div');
            nameAreaDiv.className = 'product-name-area';
  
            const nameDiv = document.createElement('div');
            nameDiv.className = 'product-name';
            nameDiv.innerText = product.productName;
  
            const lineBreak = document.createElement('br');
  
            const priceDiv = document.createElement('div');
            priceDiv.className = 'product-price';
            priceDiv.innerText = product.productPrice;
  
            nameAreaDiv.appendChild(nameDiv);
            nameAreaDiv.appendChild(lineBreak);
            nameAreaDiv.appendChild(priceDiv);
  
            anchorElement.appendChild(imageAreaDiv);
            anchorElement.appendChild(nameAreaDiv);
  
            productDiv.appendChild(anchorElement);
  
            productContainer.appendChild(productDiv);
            
          }
        })
   
      }else if(UP == 'shopSearch'){
        url = `/shop/home/searchSortList?shopSearch=${shopSearchValue}&key=${key}`;
  
        const productContainer = document.querySelector('.product-area-detail');
        productContainer.innerHTML = "";
  
        fetch(url)
  
        .then(response =>  response.json())
        .then(productList => {
          for(let product of productList){
            
            const productNo = product.productNo;
  
            // 각 제품을 위한 div 엘리먼트를 생성
            const productDiv = document.createElement('div');
            productDiv.className = 'product-list';
  
            // href 속성과 'product-profile' 클래스를 가진 anchor 엘리먼트를 생성
            const anchorElement = document.createElement('a');
            anchorElement.href = '/shop/shopDetail/' + productNo ;
            anchorElement.className = 'product-profile';
  
            // 제품 이미지 영역을 위한 div를 생성.
            const imageAreaDiv = document.createElement('div');
            imageAreaDiv.className = 'product-image-area';
            imageAreaDiv.innerHTML = `<img src="${product.productImageThumbnail}${product.productImageThumbnailRename}">`;
  
            // 제품 이름과 가격 영역을 위한 div를 생성
            const nameAreaDiv = document.createElement('div');
            nameAreaDiv.className = 'product-name-area';
  
            // 제품 이름을 나타내는 div를 생성
            const nameDiv = document.createElement('div');
            nameDiv.className = 'product-name';
            nameDiv.innerText = product.productName;
  
            // 줄 바꿈 엘리먼트를 생성
            const lineBreak = document.createElement('br');
  
            // 제품 가격을 나타내는 div를 생성
            const priceDiv = document.createElement('div');
            priceDiv.className = 'product-price';
            priceDiv.innerText = product.productPrice;
  
            // 생성한 엘리먼트들을 각각의 부모에 추가
            nameAreaDiv.appendChild(nameDiv);
            nameAreaDiv.appendChild(lineBreak);
            nameAreaDiv.appendChild(priceDiv);
  
            anchorElement.appendChild(imageAreaDiv);
            anchorElement.appendChild(nameAreaDiv);
  
            productDiv.appendChild(anchorElement);
  
            // 제품 div를 제품 컨테이너에 추가
            productContainer.appendChild(productDiv);
            
          }
        })
      }
      
      
    
    }
  }
 
}






