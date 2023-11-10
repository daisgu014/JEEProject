import {getToken, saveToken} from "./AuthHelper.js";

const authenticate_URL="/api/v1/auth/authenticate"
const authenticate=(username,password)=>{
    const request={
        "username": username,
        "password": password
    }
    return fetch(authenticate_URL,{
        method:"POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(request)
    }).then(response=>{
        if(response.status===404){
            alert("Tài khoản không tồn tại!!!")
        }
        if(response.ok){
            window.location.href='/admin/products'
        }
        return response.json();
    }).catch(err=>{
        alert("Lỗi đăng nhập")
    })
}
// const forward=()=>{
//     // Xây dựng yêu cầu fetch với các tiêu đề (header) cần thiết
//     const headers = new Headers({
//         "Authorization": `Bearer ${getToken()}`
//     });
//     return fetch('admin/products',{
//         method:'GET',
//         headers: headers
//     }).then(response=>{
//         if(response.status===404){
//             console.log("Tài khoản không tồn tại!!!")
//         }else if(response.status===302){
//             console.log("Mật khẩu không đúng!!!!!")
//         }
//     })
// }
let Login_Btn = document.getElementById('btnLogin');
let username = document.getElementById('username');
let password =document.getElementById('password');
const forward1=(url,newUrlValue)=>{
    const originalUrl = url; // URL gốc
    const headers = {
        "Authorization": `Bearer ${getToken()}`
    };


}
Login_Btn.addEventListener("click",(e)=>{
    e.preventDefault();
    authenticate(username.value.trim(),password.value.trim()).then(data=>{
        saveToken(data.access_token,data.refresh_token);
    })
})


export {authenticate}