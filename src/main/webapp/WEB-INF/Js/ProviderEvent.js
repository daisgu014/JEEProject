
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

addSupplierButton.addEventListener("click", function() {
    addSupplierForm.style.display = "block";
    overlay.style.display = "block";
});
closeAddFormButton.addEventListener("click", function() {
    addSupplierForm.style.display = "none";
    overlay.style.display = "none";
    supplierNameError.textContent = "";
    supplierNameInput.value = "";
});
closeUpdateFormButton.addEventListener("click", function() {
    updateSupplierForm.style.display = "none";
    overlay.style.display = "none";
    supplierNameError.textContent = "";
    supplierNameInput.value = "";
});
/*saveSupplierButton.addEventListener("click",function (event){
    event.preventDefault();
    if (supplierNameInput.value===""){
        supplierNameError.textContent = "Tên nhà cung cấp không được bỏ trống.";
    }else {

        overlay.style.display = "none";
        addSupplierForm.style.display = "none";
    }
});*/
// myfile.js
document.addEventListener("DOMContentLoaded", function() {
    var dataForm = document.getElementById("supplierForm");
    var resultMessage = document.getElementById("result-message");

    dataForm.addEventListener("submit", function(event) {
        event.preventDefault();
        var data = document.getElementById("supplierName").value;
        postData(data.toString());
    });

    function postData(data) {
        fetch("/add-data", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: "data=" + data,
        })
            .then(response => response.text())
            .then(message => {
                resultMessage.innerText = message;
            })
            .catch(error => {
                console.error("Lỗi: " + error);
            });
    }
});

updateSupplierButton.addEventListener("click",function (event){
    event.preventDefault();
    if (supplierNameUpdate.value===""){
        supplierNameUpdateError.textContent = "Tên nhà cung cấp không được bỏ trống.";
    }else {
        overlay.style.display = "none";
        updateSupplierForm.style.display = "none";
    }
});
document.addEventListener("DOMContentLoaded", function() {
    var table = document.getElementById("supplierTable");
    var rows = table.getElementsByTagName("tr");


    for (var i = 1; i < rows.length; i++) {
        rows[i].addEventListener("click", function() {
            var cells = this.getElementsByTagName("td");
            supplierNameUpdate.value = cells[1].textContent;
            updateSupplierForm.style.display = "block";
            overlay.style.display = "block";
        });
    }

});
