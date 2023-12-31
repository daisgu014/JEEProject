import {inputColor, inputName, inputPrice, category, provider} from "./validation.js";
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
const edit=(formData, id)=>{
    try{
        return fetch(`/admin/products/edit/${id}`,{
            method:'POST',
            body:formData
        }).then(response=>{
            if(response.ok) {
                alert("Chỉnh sửa sản phẩm thành công")
            }
        })
    }catch (error){
        console.log('Error:',error);
        throw  error;
    }
}

const addQty=(id,qty)=>{
    return fetch(`/admin/products/add-qty/${id}`,{
        method:'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(qty)
    }).then(response=>{
        if(!response.ok){
            throw new Error("Lỗi nhập sản phẩm")
        }
        return response.json();

    })
}
const deleteProducts=(selectedProductIds)=> {
    return fetch('/admin/products/delete-products/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedProductIds)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Lỗi xóa sản phẩm');
            }
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
const search=()=>{
    return fetch('/admin/products/search',{
        method:'GET'
    })
}
export {create,edit,deleteId,deleteProducts,addQty,search}
