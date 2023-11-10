import {authenticate} from '/_request.js'
import {saveToken,getToken,removeToken} from "./AuthHelper.js";

let Login_Btn = document.getElementById('btnLogin');
let username = document.getElementById('username');
let password =document.getElementById('password');
// const foward=()=>{
//     fetch('/admin/products', {
//         method: 'GET', // Hoặc POST hoặc phương thức khác tùy vào yêu cầu của bạn
//         headers: {
//             // Thêm headers của yêu cầu ban đầu tại đây
//             'Authorization': `Bearer ${getToken()}`,
//         },
//     })
//         .then(response => {
//             // Xử lý kết quả của yêu cầu
//             if (response.ok) {
//                 // Nếu yêu cầu thành công, bạn có thể chuyển hướng bằng cách sử dụng JavaScript
//                 window.location.href = "/admin/products";
//
//             } else {
//                 // Xử lý lỗi hoặc trường hợp khác tùy vào mã trạng thái
//             }
//         }).catch(err=>{
//             throw new err;
//     })
// }
Login_Btn.addEventListener("click",(e)=>{
    e.preventDefault();
    let userNameValue=username.value.trim();
    let passwordValue=password.value.trim();
    authenticate(userNameValue,passwordValue).then(data=>{
        saveToken(data.access_token,data.refresh_token)
        fetch('https://example.com/your-page', {
            method: 'GET',
            headers: {
                'Accept': 'text/html'
            }
        })
            .then(response => {
                if (response.status === 200) {
                    // Nếu yêu cầu thành công, bạn có thể chuyển hướng bằng cách sử dụng JavaScript
                    window.location.replace("https://example.com/new-page");
                } else {
                    // Xử lý lỗi hoặc trường hợp khác tùy vào mã trạng thái
                }
            })
            .catch(error => {
                console.error('Lỗi:', error);
            });
    }).catch(err=>
    {
        alert(err)
    })
})