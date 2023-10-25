import {inputColor, inputName, inputPrice, category, provider} from "./validation.js";


const editProduct = async (Product) =>{
    try {

    }catch (error){
        console.error('Chỉnh sửa sản phẩm không thành công');
    }
}
const create=(formData)=>{
   try {
       return fetch('/admin/products/create-product/',{
           method:"POST",
           body:formData
       }).then(response=>{
           if(response.status===201){
               alert("Thêm thành công")
           }else {
               throw new Error("Lỗi thêm sản phẩm");
           }
       })
   }catch (error){
       console.error('Error:', error);
       throw error;
   }
}
const deleteProducts=(selectedProductIds)=>{
    return fetch('/admin/products/delete-products',{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedProductIds)
    })
        .then(response=>{
            if(!response.ok){
                throw new Error('Lỗi xóa sản phẩm');
            }
            return response.json()
        })
}
const deleteId = (productId)=>{
    console.log(productId)
    return fetch(`/admin/products/delete-products/${productId}`,{
        method: 'DELETE'
    }).then(response=>{
        if(!response.ok){
            throw new Error('Lỗi xóa sản phẩm');
        }
        return response.text();
    })
}
export {create,editProduct,deleteId,deleteProducts}
