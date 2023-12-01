let checked_sub_list = document.querySelectorAll('.sub_checkbox_cart');
let listSelect = [];
function checkEx(array,productId){
    return array.some(item => item.productID === productId);
}
function addValueToArray(array, value) {
    if(!checkEx(array,value.productID)){
        array.push(value)
    }
}
function removeFromArray(arr, value, property='productID') {
    const index = arr.findIndex(item => item[property] === value[property]);
    if (index !== -1) {
        arr.splice(index, 1);
    }
}
checked_sub_list.forEach(checkbox=>{
    checkbox.addEventListener('click', function (){
        for (let i = 0; i < checked_sub_list.length; i++) {
            if (checked_sub_list[i].checked) {
                addValueToArray(listSelect,
                    {"productID":parseInt(checked_sub_list[i].closest('tr').querySelector('.product-id').textContent),
                                 "qty":parseInt(checked_sub_list[i].closest('tr').querySelector('.cart-qty').textContent)})
            }else{
                removeFromArray(listSelect,
                    {"productID":parseInt(checked_sub_list[i].closest('tr').querySelector('.product-id').textContent),
                                 "qty":parseInt(checked_sub_list[i].closest('tr').querySelector('.cart-qty').textContent)})

            }

        }

    })
})
const deleteCart=(list)=>{
    return fetch("/deleteCart",{
        method:"POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(list)
    }).then(response=>{
        if(!response.ok){
            throw new Error()
        }
        return response.json();
    })
}
const deleteCartItem=(item)=>{
    return fetch("/deleteCartItem",{
        method:"POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(item)
    }).then(response=>{
        if(!response.ok){
            throw new Error()
        }
        return response.json();
    })
}
const payment=(list)=>{
    return fetch("/payment",{
            method:"POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(list)
        }).then(response=>{
            if(!response.ok){
                console.log(response)
                throw new Error()
            }
            return response.json();
        })
}
document.querySelector('.deleteBtn').addEventListener('click',(e)=>{
    e.preventDefault();
    if(listSelect.length===0){
        alert("Vui lòng chọn sản phẩm xóa")
    }else {
        deleteCart(listSelect).then(data=>{
            alert(data.message)
            window.location.reload();
        })
    }
})
document.querySelector('.paymentBtn').addEventListener('click',(e)=>{
    e.preventDefault();
    if(listSelect.length===0){
            alert("Vui lòng chọn sản phẩm cần thanh toán")
        }else {
            payment(listSelect).then(data=>{
                alert(data.message)
                window.location.href="http://localhost:8083/home";
            })
        }
})
document.querySelector('.BtnDeleteCartItem').addEventListener('click',(e)=>{
    e.preventDefault();
    let item ={
        "productID":parseInt(document.querySelector('.BtnDeleteCartItem').closest('tr').querySelector('.product-id').textContent),
        "qty":parseInt(document.querySelector('.BtnDeleteCartItem').closest('tr').querySelector('.cart-qty').textContent)
    }
    deleteCartItem(item).then(data=>{
        alert(data.message)
        window.location.href="http://localhost:8083/cart";})
})
