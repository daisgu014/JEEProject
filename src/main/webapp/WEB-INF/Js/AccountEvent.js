var accouts = [];

var addAccountButton = document.getElementById("addAccountButton");
var addAccountForm = document.getElementById("addAccountForm");
var updateAccountForm = document.getElementById("updateAccountForm");
var overlay = document.getElementById("overlay");
var saveAccountButton = document.getElementById("saveAccountButton");
var closeFormButton = document.getElementById("closeFormButton");
var closeFormUpdateButton = document.getElementById("closeFormUpdateButton");
var accountTable = document.getElementById("accountTable");


document.addEventListener("DOMContentLoaded", function() {
    var rows = accountTable.getElementsByTagName("tr");

    var id = document.getElementById("accountIdUpdate");
    id.disabled = true;
    var username = document.getElementById("accountNameUpdate");
    username.disabled = true;
    var password = document.getElementById("accountPassUpdate");
    var fullname = document.getElementById("accountFullnameUpdate");
    var phone = document.getElementById("accountPhoneUpdate");
    var email = document.getElementById("accountEmailUpdate");
    var address = document.getElementById("accountAddressUpdate");
    var role = document.getElementById("accountRoleUpdate");
    for (var i = 1; i < rows.length; i++) {
        rows[i].addEventListener("click", function() {
            var cells = this.getElementsByTagName("td");
            id.value = cells[1].textContent;
            username.value = cells[2].textContent;
            fullname.value = cells[4].textContent;
            password.value = cells[3].textContent;
            role.value = cells[5].textContent;
            phone.value = cells[6].textContent;
            email.value = cells[7].textContent;
            address.value = cells[8].textContent;
            updateAccountForm.style.display = "block";
            overlay.style.display = "block";
        });
    }

});

addAccountButton.addEventListener("click", function() {
    addAccountForm.style.display = "block";
    overlay.style.display = "block";
});
closeFormButton.addEventListener("click", function() {
    addAccountForm.style.display = "none";
    overlay.style.display = "none";
});
closeFormUpdateButton.addEventListener("click", function() {
    updateAccountForm.style.display = "none";
    overlay.style.display = "none";
});

document.addEventListener("DOMContentLoaded", function() {
    var dataForm = document.getElementById("AccountForm");
    var resultMessage = document.getElementById("add-success");

    dataForm.addEventListener("submit", function(event) {
        event.preventDefault();

        var isValid = true;
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
        validateField("accountFullname", "fullnameError", "Vui lòng nhập tên người dùng.")
        validateField("accountPhone", "phoneError", "Vui lòng nhập số điện thoại.");
        validateField("accountEmail", "emailError", "Vui lòng nhập email.");
        validateField("accountAddress", "addressError", "Vui lòng nhập địa chỉ.");

        if (!isValid) {
            return false;
        } else {

            var username = document.getElementById("accountName");
            var password = document.getElementById("accountPass");
            var fullname = document.getElementById("accountFullname");
            var phone = document.getElementById("accountPhone");
            var email = document.getElementById("accountEmail");
            var address = document.getElementById("accountAddress");
            var role = document.getElementById("accountRole");
            var data = {
                "id": "",
                "username":username.value,
                "password":password.value,
                "fullname":fullname.value,
                "role":role.value,
                "phone": phone.value,
                "email": email.value,
                "address": address.value,
                "create_at":""
            }
            postDataJson(data,"/admin/account/add-account",resultMessage);/*
            overlay.style.display = "none";
            addSupplierForm.style.display = "none";*/

        }
    });
});

// Sửa tài khoản
document.addEventListener("DOMContentLoaded", function() {
    var dataForm = document.getElementById("AccountFormUpdate");
    var resultMessage = document.getElementById("update-success");

    dataForm.addEventListener("submit", function(event) {
        event.preventDefault();

        var isValid = true;
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

        validateField("accountNameUpdate", "usernameUpdateError", "Vui lòng nhập tên tài khoản.");
        validateField("accountPassUpdate", "passwordUpdateError", "Vui lòng nhập mật khẩu.");
        validateField("accountFullnameUpdate", "fullnameUpdateError", "Vui lòng nhập tên người dùng.");
        validateField("accountPhoneUpdate", "phoneUpdateError", "Vui lòng nhập số điện thoại.");
        validateField("accountEmailUpdate", "emailUpdateError", "Vui lòng nhập email.");
        validateField("accountAddressUpdate", "addressUpdateError", "Vui lòng nhập địa chỉ.");

        if (!isValid) {
            alert("Click");
            return false;
        } else {

            var id = document.getElementById("accountIdUpdate");
            var username = document.getElementById("accountNameUpdate");
            var password = document.getElementById("accountPassUpdate");
            var fullname = document.getElementById("accountFullnameUpdate");
            var phone = document.getElementById("accountPhoneUpdate");
            var email = document.getElementById("accountEmailUpdate");
            var address = document.getElementById("accountAddressUpdate");
            var role = document.getElementById("accountRoleUpdate");

            var data = {
                "id": id.value,
                "username":username.value,
                "password":password.value,
                "fullname":fullname.value,
                "role":role.value,
                "phone": phone.value,
                "email": email.value,
                "address": address.value
            }

            postDataJson(data,"/admin/account/update-account",resultMessage);/*
            overlay.style.display = "none";
            addSupplierForm.style.display = "none";*/

        }
    });
});
function postDataJson(data,path,resultMessage) {
    fetch(path, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
        .then(response => response.text())
        .then(message => {
            resultMessage.innerText = message;
        })
        .catch(error => {
            console.error("Lỗi: " + error);
        });
}
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
const checkboxes = document.querySelectorAll('.account-checkbox');
const checkAll = document.getElementById('checkAll');

checkAll.addEventListener('change', function () {
    checkboxes.forEach(checkbox => {
        checkbox.checked = this.checked;
    });
});

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', function () {
        checkAll.checked = checkboxes.length === document.querySelectorAll('.product-checkbox:checked').length;
    });
});