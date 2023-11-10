const ACCESS_TOKEN="accessToken";
const REFRESH_TOKEN = "refreshToken"
const saveToken=(accessToken,refreshToken)=>{
    if(!sessionStorage){
        return
    }
    try {
        sessionStorage.setItem(ACCESS_TOKEN,accessToken);
        sessionStorage.setItem(REFRESH_TOKEN,refreshToken);
    }catch (error){
        console.error("Lỗi lưu token: ",error)
    }

}
const getToken=()=>{
    if(!sessionStorage){
        return
    }
    try {
        return sessionStorage.getItem(ACCESS_TOKEN)
    }catch (error){
        console.error("Lỗi lấy token: ",error)
    }
}
const removeToken=()=>{
    if(!sessionStorage){
        return
    }
    try {
         sessionStorage.removeItem(ACCESS_TOKEN)
    }catch (error){
        console.error("Lỗi xóa token: ",error)
    }
}
export {getToken,saveToken,removeToken,ACCESS_TOKEN,REFRESH_TOKEN}