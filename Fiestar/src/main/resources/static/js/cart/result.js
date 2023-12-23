document.querySelectorAll(".item-price").forEach(price => {
   price.innerText = "₩" + Number(price.innerText).toLocaleString();
})


document.querySelectorAll(".sc").forEach(sc => {
   sc.innerText = "₩" + Number(sc.innerText).toLocaleString();
})

