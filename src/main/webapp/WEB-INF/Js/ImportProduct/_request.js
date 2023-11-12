const getProduct=(id)=>{
    return fetch(`/api/v1/product/getProduct/${id}`,{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(id)
    }).then(response=>{
        if(!response.ok){
            alert("Lỗi lấy sản phẩm",id)
        }
        return response.json();
    })
}
const getAll=()=>{
    return fetch(`/api/v1/product/getAll`,{
        method:'GET',
        headers:{
            'Content-Type': 'application/json'
        }
    }).then(response=>{
        if(!response.ok){
            alert("Lỗi lấy tất cả sản phẩm")
        }
        return response.json();
    })
}
export {getProduct,getAll}