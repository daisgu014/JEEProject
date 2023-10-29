import {inputColor, inputName, inputPrice, category, provider} from "./validation.js";

const createProduct=  () =>{try{
    const Category = {
        id:parseInt(category.value),
        name:category.options[category.selectedIndex].text
    }
    const Provider ={
        id:parseInt(provider.value),
        name:provider.options[provider.selectedIndex].text
    }
    const Product = {
        name:inputName.value.trim(),
        color:inputColor.value.trim(),
        inStock:0,
        imgPath:"",
        price:inputPrice.value.trim(),
        CreateAt:null,
        DeleteAt:null,
        category:Category,
        provider:{
            id:1,
            name:'Coffee'
        }
    }
   return fetch('/admin/products/create',{
       method: 'POST',
       headers:{
           'Content-Type': 'application/json'
       },
       body: JSON.stringify(Product)
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
const editProduct = async (Product) =>{
    try {

    }catch (error){
        console.error('Chỉnh sửa sản phẩm không thành công');
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
export {createProduct,editProduct,deleteId,deleteProducts}
