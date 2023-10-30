function ValidateLogin(){
    ClearInputColor();
    ClearError();
    var isNull = false;
    var username = document.getElementById("inputUsername");
    var password = document.getElementById("inputPassword");
    var attentionUsername = document.getElementById("labErrorUsername");
    var attentionPassword = document.getElementById("labErrorPassword");
    if (username.value === ""){
       username.style.borderColor = 'var(--color-danger)';
       attentionUsername.textContent = "Username không được để trống!";
       isNull = true;
    }

    if (password.value === ""){
        password.style.borderColor = 'var(--color-danger)';
        attentionPassword.textContent = "Mật khẩu không được để trống!";
        isNull = true;
    }

    if (isNull){
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
    var inputField = document.querySelectorAll('.input-log-content')
    for (var i = 0; i < inputField.length; i++) {
        inputField[i].style.borderColor = 'var(--color-dark-variant)';
    }
}

function ValidateRegister(){
    ClearInputColor();
    ClearError();
    var isNull = false;
    var username = document.getElementById("inputUsername");
    var email = document.getElementById("inputEmail");
    var phone = document.getElementById("inputPhone");
    var address = document.getElementById("inputAddress");
    var password = document.getElementById("inputPassword");
    var repassword = document.getElementById("inputRePassword");

    var attention = document.getElementById("labError");

    if (username.value === ""){
       username.style.borderColor = 'var(--color-danger)';
       isNull = true;
    }

    if (email.value === ""){
       email.style.borderColor = 'var(--color-danger)';
       isNull = true;
    }

    if (phone.value === ""){
       phone.style.borderColor = 'var(--color-danger)';
       isNull = true;
    }

    if (address.value === ""){
        address.style.borderColor = 'var(--color-danger)';
        isNull = true;
    }

    if (password.value === ""){
        password.style.borderColor = 'var(--color-danger)';
        isNull = true;
    }

    if (repassword.value === ""){
        repassword.style.borderColor = 'var(--color-danger)';
        isNull = true;
    }

    if (isNull){
        attention.textContent = "Xin hãy điền đầy đủ thông tin!";
        return false;
    }

    if (!isValidEmail(email.value)){
        email.style.borderColor = 'var(--color-danger)';
        attention.textContent = "Email không hợp lệ!";
        return false;
    }

    if(!isValidPhoneNumber(phone.value)){
        phone.style.borderColor = 'var(--color-danger)';
        attention.textContent = "Số điện thoại phải có 10 chữ số và bất đầu bằng số 0!";
        return false;
    }

    if (password.value != repassword.value){
        password.style.borderColor = 'var(--color-danger)';
        repassword.style.borderColor = 'var(--color-danger)';
        attention.textContent = "Mục Nhập lại mật khẩu không trùng khớp với mật khẩu đã nhập!";
        return false;
    }

    return true;
}

function isValidEmail(email) {
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}

function isValidPhoneNumber(phone) {
    var phonePattern = /^(0\d{9})$/;
    return phonePattern.test(phone);
}