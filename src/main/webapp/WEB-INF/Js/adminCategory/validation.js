let inputName  = document.querySelector("#name");
let errorName = document.querySelector("#name-error")
let inputNameEdit = document.querySelector("#name-edit")
let errorNameEdit = document.querySelector("#name-error-edit")
const validationName=()=>{
    const nameValue = inputName.value.trim();
    if (nameValue === '') {
        errorName.textContent = 'Tên thể loại là bắt buộc nhập.';
    } else {
        errorName.textContent = '';
    }
}
const validationNameEdit=()=>{
    const nameValue = inputNameEdit.value.trim();
    if (nameValue === '') {
        errorNameEdit.textContent = 'Tên thể loại là bắt buộc nhập.';
    } else {
        errorNameEdit.textContent = '';
    }
}
export {validationName,validationNameEdit,inputName,errorName,inputNameEdit,errorNameEdit}