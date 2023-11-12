import {errorQty,qty} from "./validation.js";
import{getAll,getProduct} from "./_request.js";
let dataTempt=[]
let AddBtn = document.getElementById('btn-qty');
let tableImport  = document.getElementById('import-products-table');
let tableBody  = tableImport.querySelector('tbody');
let tableRows =tableBody.querySelectorAll('tr');
let productOption = document.getElementById('product');
let BtnSave = document.getElementById('btn-save')
const saveProductQty=(dataList)=>{
    return fetch('http://localhost:8083/api/v1/product/saveProductQty',{
        method:'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(dataList)
    }).then(response=>{
        if(!response.ok){
            throw new Error("Lỗi thêm số lượng sản phẩm!")
        }
        return response.json();
    })
}
AddBtn.addEventListener('click',(e)=>{

    console.log(dataTempt)
    e.preventDefault();
    let newRow = document.createElement("tr");
    if(parseInt(qty.value)>0){
        errorQty.innerText=""
        let productQty = {
            id:parseInt(productOption.value),
            qty:parseInt(qty.value)
        };
        if(!dataTempt.some(product=>product.id===parseInt(productOption.value))){
            console.log(productOption.value)
            console.log("sau:",dataTempt)
            dataTempt.push(productQty);
            getProduct(productOption.value).then(data=>{
                newRow.innerHTML=`
                 <td class="product-id">${data.data.id}</td>
                     <td class="info_product">
                                <img src="/images/products/${data.data.imgPath}" alt="" class="product-img">
                                <span class="product-name" >${data.data.name}</span >
                            </td>
        <td>${data.data.color}</td>
        <td>${data.data.category.name}</td>
        <td>${data.data.provider.name}</td>
        <td>${data.data.price}VNĐ</td>
        <td class="qty-product">${productQty.qty}</td>
        <td>${data.data.status}</td>
        <td><button type="submit" class="deleteProduct">Xóa</button></td>
`
            })
            tableBody.appendChild(newRow)
        }else {
            let row;
           dataTempt.filter(product=> {
               if(product.id===parseInt(productOption.value)){
                   product.qty = product.qty + parseInt(qty.value)
                   let ids = document.querySelectorAll(".product-id")
                   ids.forEach(id=>{
                       if(parseInt(id.textContent)===parseInt(productOption.value)){
                           id.closest('tr').querySelector('.qty-product').textContent=product.qty;
                       }
                   })
               }

           })
        }
    }else {
        errorQty.innerText="Vui lòng nhập số lượng sản phẩm";
    }
})
let deleteBtn; // Define deleteBtn variable
document.addEventListener('DOMContentLoaded', () => {
    // Check if there are any delete buttons
    tableBody.addEventListener('click', (event) => {
        if (event.target.classList.contains('deleteProduct')) {
            const row = event.target.closest('tr');
            row.remove();

            // Your additional delete logic here, e.g., updating the dataTempt array
            const productId = row.querySelector('.product-id').textContent;
            dataTempt = dataTempt.filter(product => product.id !== parseInt(productId));
            console.log("Sau khi xóa: ",dataTempt)
            // Your delete logic here
        }
    });
    BtnSave.addEventListener('click',()=>{
        if(dataTempt.length>0){
            saveProductQty(dataTempt).then(data=>{
                alert(data.status)
                tableBody.remove();
            })
        }else {
            alert("Vui lòng thêm sản phẩm nhập và số lượng!!!!")
        }
    })
});


