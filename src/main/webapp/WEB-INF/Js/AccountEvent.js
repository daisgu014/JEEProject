var accouts = [];

var addAccountButton = document.getElementById("addAccountButton");
var addAccountForm = document.getElementById("addAccountForm");
var updateAccountForm = document.getElementById("updateAccountForm");
var overlay = document.getElementById("overlay");
var saveAccountButton = document.getElementById("saveAccountButton");
var closeFormButton = document.getElementById("closeFormButton");
var closeFormUpdateButton = document.getElementById("closeFormUpdateButton");
var accountTable = document.getElementById("accountTable");

const itemsPerPage = 5;
const pagination = document.getElementById('pagination');
const tableBody = accountTable.querySelector('tbody');
const rows = tableBody.querySelectorAll('tr');
const pageCount = Math.ceil(rows.length / itemsPerPage);
const deleteAll = document.getElementById('deleteAll-btn');

function displayPage(page) {
    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;

    rows.forEach((row, index) => {
        if (index >= start && index < end) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

function createPaginationButtons() {
    if (pageCount > 1){
        for (let i = 1; i <= pageCount; i++) {
            const button = document.createElement('button');
            button.innerText = i;
            button.addEventListener('click', () => {
                displayPage(i);
            });
            pagination.appendChild(button);
        }
    }
}

createPaginationButtons();
displayPage(1);
document.addEventListener("DOMContentLoaded", function() {
    var rows = accountTable.getElementsByTagName("tr");

    var id = document.getElementById("accountIdUpdate");
    var username = document.getElementById("accountNameUpdate");
    var password = document.getElementById("accountPassUpdate");
    var fullname = document.getElementById("accountFullnameUpdate");
    var phone = document.getElementById("accountPhoneUpdate");
    var email = document.getElementById("accountEmailUpdate");
    var address = document.getElementById("accountAddressUpdate");
    var role = document.getElementById("accountRoleUpdate");

    id.disabled = true;
    username.disabled = true;
    // phone.disabled = true;

    for (var i = 1; i < rows.length; i++) {
        rows[i].addEventListener("dblclick", function() {
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
    var form = document.getElementById("AccountForm");
    var inputElements = form.querySelectorAll("input[type='text']");
    inputElements.forEach(function(input) {
        input.value = null;
    });
    var spanElements = form.querySelectorAll("span");
    spanElements.forEach(function (span){
        span.textContent = null;
    })
    window.location.reload();
});
closeFormUpdateButton.addEventListener("click", function() {
    updateAccountForm.style.display = "none";
    overlay.style.display = "none";
    var form = document.getElementById("AccountFormUpdate");
    var inputElements = form.querySelectorAll("input[type='text']");
    inputElements.forEach(function(input) {
        input.value = "";
    });
    var spanElements = form.querySelectorAll("span");
    spanElements.forEach(function (span){
        span.textContent = null;
    });
    window.location.reload();
});
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
function isPhoneNumberValid(inputId, errorId, errorMessage) {
    const phoneNumberPattern = /^0\d{9}$/;
    var input = document.getElementById(inputId);
    var error = document.getElementById(errorId);
    if (input.value.trim() === "") {
        error.textContent = "Vui lòng nhập số điện thoại.";
        isValid = false;
    } else {
        if (phoneNumberPattern.test(input.value.trim())) {
            isValid = true;
            error.textContent = "";
        } else {
            error.textContent = errorMessage;
            isValid = false;
        }
    }
}
function isEmailValid(email,errorId,errorMessage) {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    var input = document.getElementById(email);
    var error = document.getElementById(errorId);
    if (input.value.trim() === "") {
        error.textContent = "Vui lòng nhập số email.";
        isValid = false;
    } else {
        if (emailPattern.test(input.value.trim())) {
            isValid = true;
            error.textContent = "";
        } else {
            error.textContent = errorMessage;
            isValid = false;
        }
    }
}

//Thêm tài khoản
document.addEventListener("DOMContentLoaded", function() {
    var dataForm = document.getElementById("AccountForm");
    var resultMessage = document.getElementById("add-success");

    dataForm.addEventListener("submit", function(event) {
        event.preventDefault();

        validateField("accountName", "usernameError", "Vui lòng nhập tên người dùng.");
        validateField("accountPass", "passwordError", "Vui lòng nhập mật khẩu.");
        validateField("accountFullname", "fullnameError", "Vui lòng nhập tên người dùng.")
        // validateField("accountPhone", "phoneError", "Vui lòng nhập số điện thoại.");
        isPhoneNumberValid("accountPhone", "phoneError", "Vui lòng nhập số điện thoại đủ 10 số.");
        // validateField("accountEmail", "emailError", "Vui lòng nhập email.");
        isEmailValid("accountEmail", "emailError", "Vui lòng nhập email đúng định dạng (vd: abc@abc).");
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
                "full_name":fullname.value,
                "role":role.value,
                "phone": phone.value,
                "email": email.value,
                "address": address.value,
                "create_at":"",
                "delete_dat":""
            }
            postDataJson(data,"/admin/account/add-account",resultMessage);

        }
    });
});

// Sửa tài khoản
document.addEventListener("DOMContentLoaded", function() {
    var dataForm = document.getElementById("AccountFormUpdate");
    var resultMessage = document.getElementById("update-success");

    dataForm.addEventListener("submit", function(event) {
        event.preventDefault();

        validateField("accountNameUpdate", "usernameUpdateError", "Vui lòng nhập tên tài khoản.");
        validateField("accountPassUpdate", "passwordUpdateError", "Vui lòng nhập mật khẩu.");
        validateField("accountFullnameUpdate", "fullnameUpdateError", "Vui lòng nhập tên người dùng.");
        validateField("accountPhoneUpdate", "phoneUpdateError", "Vui lòng nhập số điện thoại.");
        validateField("accountEmailUpdate", "emailUpdateError", "Vui lòng nhập email.");
        validateField("accountAddressUpdate", "addressUpdateError", "Vui lòng nhập địa chỉ.");

        if (!isValid) {
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
                "full_name":fullname.value,
                "role":role.value,
                "phone": phone.value,
                "email": email.value,
                "address": address.value
            }
            postDataJson(data,"/admin/account/update-account",resultMessage);
        }
    });
});
//Xóa tài khoản
function deleteAccount(id){
    var resultMessage = document.getElementById("delete-success");
    const confirmation = window.confirm("Bạn có chắc chắn muốn xóa tài khoản: ?");
    if (confirmation) {
        var data = {"id":id};
        postDataJson(data,"/admin/account/delete-account",resultMessage);
        alert("Xóa thành công!");
        window.location.reload();
    } else {
        alert("Xóa thất bại!");
        window.location.reload();
    }

}
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
            if (message === "success"){
                resultMessage.innerText = "Thành công";
                resultMessage.style.color = "green";
            }else {
                resultMessage.innerText = message;
                resultMessage.style.color = "red";
            }

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
document.getElementById("accountFullname").addEventListener("input", function() {
    document.getElementById("fullnameError").textContent = "";
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
    if ( checkAll.checked){
        deleteAll.style.display = "block";
    }else {
        deleteAll.style.display = "none";
    }
});

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', function () {
        checkAll.checked = checkboxes.length === document.querySelectorAll('.product-checkbox:checked').length;
        if ( checkbox.checked){
            deleteAll.style.display = "block";
        }else {
            deleteAll.style.display = "none";
        }
    });

});
const searchInput = document.getElementById('search-input-account');
searchInput.addEventListener('input', function() {
    const searchValue = searchInput.value.toLowerCase();

    for (let i = 0; i < rows.length; i++) {
        const row = rows[i];
        const cells = row.getElementsByTagName('td');
        let shouldShow = false;

        for (let j = 0; j < cells.length; j++) {
            const cell = cells[j];
            if (cell) {
                const cellText = cell.textContent.toLowerCase();
                if (cellText.includes(searchValue)) {
                    shouldShow = true;
                    break; // Không cần kiểm tra các ô khác nếu tìm thấy kết quả
                }
            }
        }

        if (shouldShow) {
            row.style.display = ''; // Hiển thị hàng nếu tìm thấy kết quả
        } else {
            row.style.display = 'none'; // Ẩn hàng nếu không tìm thấy kết quả
        }
    }
});
deleteAll.addEventListener('click',function (){
    var checkboxes = tableBody.getElementsByTagName("input");
    var rowsToDelete = [];
    var resultMessage = document.getElementById("delete-success");
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].type === "checkbox" && checkboxes[i].checked) {
            rowsToDelete.push(checkboxes[i].parentNode.parentNode);
        }
    }
    var listAccount= [];
    for (var i = 0; i < rowsToDelete.length; i++){
        var cell = rowsToDelete[i].getElementsByTagName("td");
        var data = {
            "id": cell[1].textContent
        }
        listAccount.push(data);
    }

    const confirmation = window.confirm("Bạn có chắc chắn muốn xóa tài khoản ?");
    if (confirmation) {
        postDataJson(listAccount,"/admin/account/deleteall-account",resultMessage);
        alert("Xóa thành công!");
        window.location.reload();
    } else {
        alert("Xóa thất bại!");
        window.location.reload();
    }
})