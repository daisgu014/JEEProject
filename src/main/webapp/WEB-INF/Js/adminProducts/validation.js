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
//edit_form
let inputNameEdit = document.getElementById('name-edit');
let errorNameEdit = document.getElementById('name-error-edit');
let inputColorEdit =document.getElementById('color-edit');
let errorColorEdit =document.getElementById('color-error-edit');
let inputPriceEdit =document.getElementById('price-edit');
let errorPriceEdit =document.getElementById('price-error-edit');
let inputFileEdit =document.getElementById('image-edit');
let errorFileEdit =document.getElementById('image-error-edit');
let categoryEdit =document.getElementById('category-edit');
let providerEdit = document.getElementById('provider-edit');
let preImage = document.getElementById('imagePreview-edit');
let statusEdit = document.getElementById('status-edit');
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
const validationNameEdit=()=>{
    const nameValue = inputNameEdit.value.trim();
    if (nameValue === '') {
        errorNameEdit.textContent = 'Tên sản phẩm là bắt buộc nhập.';
    } else {
        errorNameEdit.textContent = '';
    }
}
const validationColorEdit=()=>{
    const value = inputColorEdit.value.trim();
    if (value === '') {
        errorColorEdit.textContent = 'Màu sắc là bắt buộc nhập.';
    } else {
        errorColorEdit.textContent = '';
    }
}
const validationPriceEdit=()=>{
    const vndValue = inputPriceEdit.value.trim();
    if (!(/^\d+(\.\d{1,2})?$/.test(vndValue) && parseFloat(vndValue) > 0)) {
        errorPriceEdit.textContent = 'Vui lòng nhập một số lớn hơn 0.';
    } else {
        errorPriceEdit.textContent = '';
    }
}
const validationInputFileEdit=()=>{
    if (inputFileEdit.files.length === 0) {
        errorFileEdit.textContent = 'Vui lòng chọn một tệp.';
    } else {
        errorFileEdit.textContent = '';
    }

}
export {validationName,validationColor,validationPrice,validationInputFile,validationNameEdit,validationColorEdit,validationInputFileEdit,validationPriceEdit,
    inputName,
    errorName,
    inputColor,
    inputFile,
    inputPrice,
    errorColor,
    errorFile,
    errorPrice,
    category,
    provider,
    inputNameEdit,
    inputColorEdit,
    errorNameEdit,
    errorColorEdit,
    errorFileEdit,
    errorPriceEdit,
    inputFileEdit,
    inputPriceEdit,
    preImage,
    providerEdit,
    categoryEdit,
    statusEdit}