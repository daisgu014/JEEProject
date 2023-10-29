let inputName = document.getElementById('name');
let errorName = document.getElementById('name-error');
let inputColor =document.getElementById('color');
let errorColor =document.getElementById('color-error');
let inputPrice =document.getElementById('price');
let errorPrice =document.getElementById('price-error');
let inputFile =document.getElementById('image');
let errorFile =document.getElementById('image-error');
let category =document.getElementById('category');
let provider = document.getElementById('provider');
const validationName=()=>{
    const nameValue = inputName.value.trim();
    if (nameValue === '') {
        errorName.textContent = 'Tên sản phẩm là bắt buộc nhập.';
    } else {
        errorName.textContent = '';
    }
}
const validationColor=()=>{
    const value = inputColor.value.trim();
    if (value === '') {
        errorColor.textContent = 'Màu sắc là bắt buộc nhập.';
    } else {
        errorColor.textContent = '';
    }
}
const validationPrice=()=>{
    const vndValue = inputPrice.value.trim();
    if (!(/^\d+(\.\d{1,2})?$/.test(vndValue) && parseFloat(vndValue) > 0)) {
        errorPrice.textContent = 'Vui lòng nhập một số lớn hơn 0.';
    } else {
        errorPrice.textContent = '';
    }
}
const validationInputFile=()=>{
    if (inputFile.files.length === 0) {
        errorFile.textContent = 'Vui lòng chọn một tệp.';
    } else {
        errorFile.textContent = '';
    }

}
export {validationName,validationColor,validationPrice,validationInputFile,
    inputName,
    errorName,
    inputColor,
    inputFile,
    inputPrice,
    errorColor,
    errorFile,
    errorPrice,
    category,
    provider}