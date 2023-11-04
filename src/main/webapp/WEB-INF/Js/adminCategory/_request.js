import {inputName} from "./validation.js";

const create=  () =>{try{
    const Category = {
        id:null,
        name:inputName.value.trim()
    }

    return fetch('/admin/categories/create',{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(Category)
    }).then(response=>{
        if(response.status===201){
            alert("Thêm thành công")

        }else {
            throw new Error("Lỗi thêm thể loai");
        }
    })
}catch (error){
    console.error('Error:', error);
    throw error;
}
}
const editCategory = async (id,Category) =>{
 return fetch(`/admin/categories/edit/${id}`,{
     method: 'POST',
     headers:{
         'Content-Type': 'application/json'
     },
     body: JSON.stringify(Category)
 }).then(response=>{
        if(response.status===200) {
            alert("Thêm thành công")
            location.reload();
        }  else {
            // Phản hồi lỗi
            response.text().then(errorText => {
                alert(errorText); // Hiển thị thông báo lỗi
            });
        }
    }).catch(error => {
        console.error('Lỗi khi gửi yêu cầu:', error);
    });
}
const deleteCategories=(selectedCategoryId)=>{
    return fetch('/admin/categories/delete-categories/',{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedCategoryId)
    })
        .then(response=>{
            if(!response.ok){
                throw new Error('Lỗi xóa thể loại');
            }
        })
}
const deleteId = (CategoryId)=>{
    console.log(CategoryId)
    return fetch(`/admin/categories/delete-categories/${CategoryId}`,{
        method: 'DELETE'
    }).then(response=>{
        if(!response.ok){
            throw new Error('Lỗi xóa thể loại');
        }
        return response.text();
    })
}


export {create,editCategory,deleteId,deleteCategories}
