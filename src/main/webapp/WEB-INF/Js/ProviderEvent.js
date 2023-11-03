
var addSupplierButton = document.getElementById("addSupplierButton");
var addSupplierForm = document.getElementById("addSupplierForm");
var updateSupplierForm = document.getElementById("updateSupplierForm");
var overlay = document.getElementById("overlay");
var supplierNameInput = document.getElementById("supplierName");
var supplierNameUpdate = document.getElementById("supplierNameUpdate");
var saveSupplierButton = document.getElementById("saveSupplierButton");
var closeAddFormButton = document.getElementById("closeAddFormButton");
var updateSupplierButton = document.getElementById("updateSupplierButton");
var closeUpdateFormButton = document.getElementById("closeUpdateFormButton");
var supplierTable = document.getElementById("supplierTable");
var supplierNameError = document.getElementById("supplierNameError");
var supplierNameUpdateError = document.getElementById("supplierNameUpdateError");
const checkboxes = document.querySelectorAll('.provider-checkbox');
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
addSupplierButton.addEventListener("click", function() {
    addSupplierForm.style.display = "block";
    overlay.style.display = "block";
});
closeAddFormButton.addEventListener("click", function() {
    addSupplierForm.style.display = "none";
    overlay.style.display = "none";
    supplierNameError.textContent = "";
    supplierNameInput.value = "";
    window.location.reload();
});
closeUpdateFormButton.addEventListener("click", function() {
    updateSupplierForm.style.display = "none";
    overlay.style.display = "none";
    supplierNameError.textContent = "";
    supplierNameInput.value = "";
    window.location.reload();
});
document.addEventListener("DOMContentLoaded", function() {
    var table = document.getElementById("supplierTable");
    var rows = table.getElementsByTagName("tr");


    for (var i = 1; i < rows.length; i++) {
        rows[i].addEventListener("dblclick", function() {
            var cells = this.getElementsByTagName("td");
            supplierNameUpdate.value = cells[2].textContent;
            updateSupplierForm.style.display = "block";
            overlay.style.display = "block";
        });
    }

});
const itemsPerPage = 5; // Số lượng mục trên mỗi trang
const dataTable = document.getElementById('supplierTable');
const pagination = document.getElementById('pagination');
const tableBody = dataTable.querySelector('tbody');
const rows = tableBody.querySelectorAll('tr');
const pageCount = Math.ceil(rows.length / itemsPerPage);

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

// Thêm nhà cung cấp
document.addEventListener("DOMContentLoaded", function() {
    var dataForm = document.getElementById("supplierForm");
    var resultMessage = document.getElementById("add-success");

    dataForm.addEventListener("submit", function(event) {
        event.preventDefault();
        if (supplierNameInput.value===""){
            supplierNameError.textContent = "Tên nhà cung cấp không được bỏ trống.";
        }else {
            var data = document.getElementById("supplierName").value;
            postData(data.toString());/*
            overlay.style.display = "none";
            addSupplierForm.style.display = "none";*/
        }

    });

    function postData(data) {
        fetch("/admin/provider/add-provider", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: "data=" + data,
        })
            .then(response => response.text())
            .then(message => {
                if (message === "success"){
                    resultMessage.innerText = "Thành công!";
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
});
// Chỉnh sửa nhà cung cấp
document.addEventListener("DOMContentLoaded", function() {
    var dataForm = document.getElementById("updateSupplier");
    var resultMessage = document.getElementById("update-success");
    var table = document.getElementById("supplierTable");
    var rows = table.getElementsByTagName("tr");
    var cells;
    for (var i = 1; i < rows.length; i++) {
        rows[i].addEventListener("click", function() {
            cells = this.getElementsByTagName("td");
        });
    }
    dataForm.addEventListener("submit", function(event) {
        event.preventDefault();
        if (supplierNameUpdate.value===""){
            supplierNameUpdateError.textContent = "Tên nhà cung cấp không được bỏ trống.";
        }else {
            var data = {
                "id": cells[1].textContent,
                "name": supplierNameUpdate.value,
                "create_at": "",
                "delete_at":"",
            }
            postDataJson(data,"/admin/provider/update-provider",resultMessage);/*
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
            if (message === "success"){
                resultMessage.innerText = "Thành công!";
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
function deleteProvider(id){
    var resultMessage = document.getElementById("delete-success");
    const confirmation = window.confirm("Bạn có chắc chắn muốn xóa tài khoản: ?");
    if (confirmation) {
        var data = {"id":id};
        postDataJson(data,"/admin/provider/delete-provider",resultMessage);
        alert("Xóa thành công!");
        window.location.reload();
    } else {
        alert("Xóa thất bại!");
        window.location.reload();
    }
}