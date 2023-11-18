var btnSubmit = document.getElementById("btnSubmit");
var btnOpen = document.getElementById("btnOpen");
var btnCancel = document.getElementById("btnCancel");

var email = document.getElementById("inputEmail");
var phone = document.getElementById("inputPhone");
var address = document.getElementById("inputAddress");
var full_name = document.getElementById("inputFull_Name");

var password = document.getElementById("inputPassword");
var newPassword = document.getElementById("inputNewPassword");
var reNewPassword = document.getElementById("inputReNewPassword");

var inputValues = {};

function ClearPassword(){
    password.style.border = "1px var(--color-info-dark) solid";
    newPassword.style.border = "1px var(--color-info-dark) solid";
    reNewPassword.style.border = "1px var(--color-info-dark) solid";
    ClearError();
    password.value = "";
    newPassword.value ="";
    reNewPassword.value ="";
}

function ClearSuccess(){
    var attention = document.getElementById("labSuccess");
    attention.textContent = "";
}

function ValidatePassword(){
    ClearSuccess();
    password.style.border = "1px var(--color-info-dark) solid";
    newPassword.style.border = "1px var(--color-info-dark) solid";
    reNewPassword.style.border = "1px var(--color-info-dark) solid";
    ClearError();
    
    var isNull = false;

    var attention = document.getElementById("labError");

    if (password.value === ""){
        password.style.border = "1px var(--color-danger) solid";
        isNull = true;
    }

    if (newPassword.value === ""){
        newPassword.style.border = "1px var(--color-danger) solid";
        isNull = true;
    }

    if (reNewPassword.value === ""){
        reNewPassword.style.border = "1px var(--color-danger) solid";
        isNull = true;
    }

    if (isNull){
        attention.textContent = "Xin hãy điền đầy đủ thông tin!";
        return false;
    }

    if (password.value == newPassword.value){
        newPassword.style.borderColor = 'var(--color-danger)';
        password.style.borderColor = 'var(--color-danger)';
        attention.textContent = "Mật khẩu bạn muốn thay đổi trùng với mật khẩu cũ!";
        return false;
    }

    if (newPassword.value != reNewPassword.value){
        newPassword.style.borderColor = 'var(--color-danger)';
        reNewPassword.style.borderColor = 'var(--color-danger)';
        attention.textContent = "Mục Nhập lại mật khẩu mới không trùng khớp với mật khẩu mới đã nhập!";
        return false;
    }

    return true;
}

function OpenSetting(){
    SaveInputValues();
    btnCancel.style.display = "block";
    btnOpen.style.display = "none";
    btnSubmit.style.display = "block";

    OpenInput();
}

function CloseSetting(){
    RestoreInputValues();
    btnCancel.style.display = "none";
    btnOpen.style.display = "block";
    btnSubmit.style.display = "none";
    CloseInput();
}

function OpenInput(){
    var inputElements = document.querySelectorAll(".user-input");
    inputElements.forEach(function(inputElement) {
        inputElement.disabled = false;
        inputElement.style.border = "1px var(--color-info-dark) solid";
        inputElement.style.color = "var(--color-dark)";
    });
}

function CloseInput(){
    ClearError();
    var inputElements = document.querySelectorAll(".user-input");
    inputElements.forEach(function(inputElement) {
        inputElement.disabled = true;
        inputElement.style.color = "var(--color-info-dark)";
        inputElement.style.border = "1px var(--color-white) solid";
    });
}

function SaveInputValues() {
    var inputElements = document.querySelectorAll(".user-input");
    inputElements.forEach(function (inputElement) {
        var inputId = inputElement.getAttribute("id");
        var inputValue = inputElement.value;
        inputValues[inputId] = inputValue;
    });
}

function RestoreInputValues() {
    for (var inputId in inputValues) {
        var inputElement = document.getElementById(inputId);
        if (inputElement) {
            inputElement.value = inputValues[inputId];
        }
    }
}

function ClearError(){
    var errorText = document.querySelectorAll('.error-log-text');

    for (var i = 0; i < errorText.length; i++) {
        errorText[i].textContent = "";
    }
}


function ValidateProfile(){
    ClearSuccess();
    full_name.style.border = "1px var(--color-info-dark) solid";
    email.style.border = "1px var(--color-info-dark) solid";
    phone.style.border = "1px var(--color-info-dark) solid";
    address.style.border = "1px var(--color-info-dark) solid";
    ClearError();
    var isNull = false;

    var attention = document.getElementById("labError");

    if (full_name.value === ""){
        full_name.style.border = "1px var(--color-danger) solid";
        isNull = true;
     }

    if (email.value === ""){
       email.style.border = "1px var(--color-danger) solid";
       isNull = true;
    }

    if (phone.value === ""){
       phone.style.border = "1px var(--color-danger) solid";
       isNull = true;
    }

    if (address.value === ""){
        address.style.border = "1px var(--color-danger) solid";
        isNull = true;
    }

    if (isNull){
        attention.textContent = "Xin hãy điền đầy đủ thông tin!";
        return false;
    }

     if(!isValidFullName(full_name.value)){
         full_name.style.border = "1px var(--color-danger) solid";
         attention.textContent = "Họ và tên không hợp lệ!";
         return false;
     }

    if (!isValidEmail(email.value)){
        email.style.border = "1px var(--color-danger) solid";
        attention.textContent = "Email không hợp lệ!";
        return false;
    }

    if(!isValidPhoneNumber(phone.value)){
        phone.style.border = "1px var(--color-danger) solid";
        attention.textContent = "Số điện thoại phải có 10 chữ số và bất đầu bằng số 0!";
        return false;
    }

    var inputElements = document.querySelectorAll(".user-input");
    var count = 0;
    inputElements.forEach(function (inputElement) {
        var inputValue = inputElement.value;
        var defaultValue = inputElement.defaultValue;

        if (inputValue === defaultValue) {
            count++;
        }
    });

    if (count == 4) {
        attention.textContent = "Bạn chưa thay đổi thông tin.";
        return false;
    }
    
    return true;
}

function ClearError(){
    var errorText = document.querySelectorAll('.error-log-text');

    for (var i = 0; i < errorText.length; i++) {
        errorText[i].textContent = "";
    }
}

function ClearInputColor(){
    var inputField = document.querySelectorAll('user-input')
    for (var i = 0; i < inputField.length; i++) {
        inputField[i].style.borderColor = 'var(--color-dark)';
    }
}


function isValidEmail(email) {
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}

function isValidPhoneNumber(phone) {
    var phonePattern = /^(0\d{9})$/;
    return phonePattern.test(phone);
}

function isValidFullName(input) {
  var pattern = /^[\p{L}\s]{3,}$/u;
  return pattern.test(input);
}