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

function selectHigh(key){
  
  
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const artistGroupNoValue = urlParams.get('artistGroupNo');

  const url = `/shop/home/sortList?key=${key}&artistGroupNo=${artistGroupNoValue}`;

  const productContainer = document.querySelector('.product-area-detail');
  productContainer.innerHTML = "";

  fetch(url)
  
  .then(response =>  response.json())
  .then(productList => {
    for(let product of productList){
      // 각 제품을 위한 div 엘리먼트를 생성합니다.
      var productDiv = document.createElement('div');
      productDiv.className = 'product-list';
  
      // href 속성과 'product-profile' 클래스를 가진 anchor 엘리먼트를 생성합니다.
      var anchorElement = document.createElement('a');
      anchorElement.href = '/shop/shopDetail';
      anchorElement.className = 'product-profile';
  
      // 제품 이미지 영역을 위한 div를 생성합니다.
      var imageAreaDiv = document.createElement('div');
      imageAreaDiv.className = 'product-image-area';
      imageAreaDiv.innerHTML = `<img src="${product.productImage}">`;
  
      // 제품 이름과 가격 영역을 위한 div를 생성합니다.
      var nameAreaDiv = document.createElement('div');
      nameAreaDiv.className = 'product-name-area';
  
      // 제품 이름을 나타내는 div를 생성합니다.
      var nameDiv = document.createElement('div');
      nameDiv.className = 'product-name';
      nameDiv.innerText = product.productName;
  
      // 줄 바꿈 엘리먼트를 생성합니다.
      var lineBreak = document.createElement('br');
  
      // 제품 가격을 나타내는 div를 생성합니다.
      var priceDiv = document.createElement('div');
      priceDiv.className = 'product-price';
      priceDiv.innerText = product.productPrice;
  
      // 생성한 엘리먼트들을 각각의 부모에 추가합니다.
      nameAreaDiv.appendChild(nameDiv);
      nameAreaDiv.appendChild(lineBreak);
      nameAreaDiv.appendChild(priceDiv);
  
      anchorElement.appendChild(imageAreaDiv);
      anchorElement.appendChild(nameAreaDiv);
  
      productDiv.appendChild(anchorElement);
  
      // 제품 div를 제품 컨테이너에 추가합니다.
      productContainer.appendChild(productDiv);
      
    }
  })
}





// function selectHigh(key){

//   const productContainer = document.querySelector('.product-area-detail');
//   productContainer.innerHTML = "";
//   fetch("/shop/home/sortList?key=" + key)
//   .then(response =>  response.json())
//   .then(productList => {
//     for(let product of productList){
//       // 각 제품을 위한 div 엘리먼트를 생성합니다.
//       var productDiv = document.createElement('div');
//       productDiv.className = 'product-list';
  
//       // href 속성과 'product-profile' 클래스를 가진 anchor 엘리먼트를 생성합니다.
//       var anchorElement = document.createElement('a');
//       anchorElement.href = '/shop/shopDetail';
//       anchorElement.className = 'product-profile';
  
//       // 제품 이미지 영역을 위한 div를 생성합니다.
//       var imageAreaDiv = document.createElement('div');
//       imageAreaDiv.className = 'product-image-area';
//       imageAreaDiv.innerHTML = `<img src="${product.productImage}">`;
  
//       // 제품 이름과 가격 영역을 위한 div를 생성합니다.
//       var nameAreaDiv = document.createElement('div');
//       nameAreaDiv.className = 'product-name-area';
  
//       // 제품 이름을 나타내는 div를 생성합니다.
//       var nameDiv = document.createElement('div');
//       nameDiv.className = 'product-name';
//       nameDiv.innerText = product.productName;
  
//       // 줄 바꿈 엘리먼트를 생성합니다.
//       var lineBreak = document.createElement('br');
  
//       // 제품 가격을 나타내는 div를 생성합니다.
//       var priceDiv = document.createElement('div');
//       priceDiv.className = 'product-price';
//       priceDiv.innerText = product.productPrice;
  
//       // 생성한 엘리먼트들을 각각의 부모에 추가합니다.
//       nameAreaDiv.appendChild(nameDiv);
//       nameAreaDiv.appendChild(lineBreak);
//       nameAreaDiv.appendChild(priceDiv);
  
//       anchorElement.appendChild(imageAreaDiv);
//       anchorElement.appendChild(nameAreaDiv);
  
//       productDiv.appendChild(anchorElement);
  
//       // 제품 div를 제품 컨테이너에 추가합니다.
//       productContainer.appendChild(productDiv);
      
//     }
//   })
// }