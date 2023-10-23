var accouts = [];


var addAccountButton = document.getElementById("addAccountButton");
var addAccountForm = document.getElementById("addAccountForm");
var overlay = document.getElementById("overlay");

var saveAccountButton = document.getElementById("saveAccountButton");
var closeFormButton = document.getElementById("closeFormButton");
var accountTable = document.getElementById("accountTable");


addAccountButton.addEventListener("click", function() {
    addAccountForm.style.display = "block";
    overlay.style.display = "block";
});
closeFormButton.addEventListener("click", function() {
    accountTable.style.display = "none";
    overlay.style.display = "none";
    // supplierNameInput.value = "";
});


saveAccountButton.addEventListener("click",function (event){
    event.preventDefault();
    var isValid = true;

    // Hàm kiểm tra và hiển thị thông báo lỗi
    function validateField(inputId, errorId, errorMessage) {
        var input = document.getElementById(inputId);
        var error = document.getElementById(errorId);

        if (input.value.trim() === "") {
            error.textContent = errorMessage;
            isValid = false;
        } else {
            error.textContent = "";
        }
    }

    validateField("accountName", "usernameError", "Vui lòng nhập tên người dùng.");
    validateField("accountPass", "passwordError", "Vui lòng nhập mật khẩu.");
    validateField("accountPhone", "phoneError", "Vui lòng nhập số điện thoại.");
    validateField("accountEmail", "emailError", "Vui lòng nhập email.");
    validateField("accountAddress", "addressError", "Vui lòng nhập địa chỉ.");

    if (!isValid) {
        return false;
    } else {
        // Thồng báo đã gửi form
        // document.getElementById("message").textContent = "Biểu mẫu đã được gửi.";
        addAccountForm.style.display = "none";
        overlay.style.display = "none";

        return true;
    }
});

document.getElementById("accountName").addEventListener("input", function() {
    document.getElementById("usernameError").textContent = "";
});
document.getElementById("accountPass").addEventListener("input", function() {
    document.getElementById("passwordError").textContent = "";
});
document.getElementById("accountPhone").addEventListener("input", function() {
    document.getElementById("phoneError").textContent = "";
});
document.getElementById("accountEmail").addEventListener("input", function() {
    document.getElementById("emailError").textContent = "";
});
document.getElementById("accountAddress").addEventListener("input", function() {
    document.getElementById("addressError").textContent = "";
});
