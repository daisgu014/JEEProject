import {create, edit, deleteId, deleteProducts, addQty,search} from "./adminProducts/_request.js";
import {
    validationName,
    validationColor,
    validationPrice,
    validationInputFile,
    validationNameEdit,
    validationColorEdit,
    validationPriceEdit,
    validationQty,
    inputName,
    inputColor,
    inputPrice,
    inputFile,
    errorName,
    errorPrice,
    errorColor,
    errorFile,
    inputColorEdit,
    inputNameEdit,
    inputPriceEdit,
    inputFileEdit,
    categoryEdit,
    preImage,
    providerEdit,
    statusEdit, errorNameEdit, errorColorEdit, errorPriceEdit,qty,qtyError
} from "./adminProducts/validation.js";
function addValueToArray(array, value) {
    if(!array.includes(value)){
        array.push(value)
    }
}
function removeFromArray(arr, value) {
    const index = arr.indexOf(value);
    if (index !== -1) {
        arr.splice(index, 1);
    }
}
let selectedProductIds=[]
let editFormProduct = document.querySelector(".edit_product_popup");
let add_qty_popup = document.querySelector(".qty_product_popup");
let filter_form= document.querySelector("#product--filter");
function Init() {


    document.getElementById("addBtn").addEventListener("click", function() {
        document.querySelector(".products_popup").style.display="block";
        document.querySelector("#overlay").style.display="block";
    });

    document.querySelector("#overlay").addEventListener("click", ()=>{
        document.querySelector(".products_popup").style.display="none";
        editFormProduct.style.display="none";
        add_qty_popup.style.display="none";
        document.querySelector("#overlay").style.display="none";

    })
    if(document.querySelector(".close-btn")!=null){
        document.querySelector(".close-btn").addEventListener("click", ()=>{
            document.querySelector(".products_popup").style.display="none";
            editFormProduct.style.display="none";
            document.querySelector("#overlay").style.display="none"

        })
    }
    if(document.querySelector("#btn-edit-close")!=null){
        document.querySelector("#btn-edit-close").addEventListener("click", ()=>{
            editFormProduct.style.display="none";
            document.querySelector("#overlay").style.display="none"

        })
    }
    if(document.querySelector("#btn-close-add")!=null){
        document.querySelector("#btn-close-add").addEventListener("click", ()=>{
            document.querySelector('.qty_product_popup').style.display="none"
            document.querySelector("#overlay").style.display="none"

        })
    }



    let checkbox = document.querySelector('.check_box');
    let checkbox_subs = document.querySelectorAll('.sub_checkbox');
    if(checkbox!=null && checkbox_subs!=null){
        checkbox_subs.forEach(checkbox=>{
            checkbox.addEventListener('click', function (){
                for (let i = 0; i < checkbox_subs.length; i++) {
                    if (checkbox_subs[i].checked) {
                        addValueToArray(selectedProductIds,checkbox_subs[i].closest('tr').querySelector('.product-id').textContent)
                        document.getElementById('event').style.display = 'none';
                        document.getElementById('deleteBtn').style.display = 'block';
                        return
                    }else{
                        removeFromArray(selectedProductIds,checkbox_subs[i].closest('tr').querySelector('.product-id').textContent)

                    }

                }
                document.getElementById('event').style.display = 'flex';
                document.getElementById('deleteBtn').style.display = 'none'

                console.log(selectedProductIds)
            })

        })

        checkbox.addEventListener('click',function(){
            if(this.checked){
                document.getElementById('event').style.display='none';
                document.getElementById('deleteBtn').style.display='block';
                checkbox_subs.forEach(checkbox =>{
                        checkbox.checked = true;
                        addValueToArray(selectedProductIds,checkbox.closest('tr').querySelector('.product-id').textContent)
                    }
                )

            }else{
                document.getElementById('event').style.display='flex';
                document.getElementById('deleteBtn').style.display='none';
                checkbox_subs.forEach(checkbox =>{
                    checkbox.checked = false;
                    removeFromArray(selectedProductIds,checkbox.closest('tr').querySelector('.product-id').textContent)
                })
            }
            console.log(selectedProductIds)
        })

    }


}
Init();

const deleteButton = document.getElementById("deleteBtn");
deleteButton.addEventListener("click",()=>{
    if(selectedProductIds.length>1){
        deleteProducts(selectedProductIds).then(data=>{
            alert("Xóa thành công");
            location.reload()
        }).catch(error => {
            alert(error.message); // Hiển thị thông báo lỗi nếu có lỗi
        });
    }else if(selectedProductIds.length==1) {
        deleteId(selectedProductIds[0]).then(data => {
            alert("Xóa thành công");
            location.reload()
        }).catch(error => {
            alert(error.message);
        });
    }else {
        alert("Không còn gì để xóa");
    }


})
let inputImage=document.getElementById('image');
let imagePreview = document.getElementById('imagePreview');
inputImage.addEventListener('change', function (e) {
    const file = e.target.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function (event) {
            const img = document.createElement('img');
            img.src = event.target.result;
            imagePreview.innerHTML = ''; // Xóa bất kỳ ảnh trước đó
            imagePreview.appendChild(img);
        };
        reader.readAsDataURL(file);
    } else {
        imagePreview.innerHTML = 'Không có ảnh được chọn.';
    }
});
let insertBtn = document.getElementById('insert-button');
inputName.addEventListener('input',validationName);
inputColor.addEventListener('input',validationColor);
inputPrice.addEventListener('input',validationPrice);
inputFile.addEventListener('change',validationInputFile);
document.getElementById('productForm').addEventListener('submit',function (e){
    e.preventDefault();

    validationName()
    validationColor()
    validationPrice()
    validationInputFile()
    if(errorName.textContent===''&&
        errorPrice.textContent===''&&
        errorColor.textContent===''&&
        errorFile.textContent==='') {
        const formData = new FormData(this);
        create(formData).then(data =>
        {
            location.reload();
        }).catch(error=>
        {
            alert(error.message)
        })
    }

})
const findOptionValue=(selectElement, optionName)=>{
    var options=selectElement.options;
    let value=null;
    for(var i=0;i<options.length;i++){
        if(options[i].text===(optionName.trim())){
            value=options[i].value
        }
    }
    return value;
}
let btnEdits = document.querySelectorAll(".edit_btn_sub");
let idEdit=0;
btnEdits.forEach(btn=>{
    btn.addEventListener("click",()=>{
        idEdit=btn.closest('tr').querySelector('.product-id').textContent;
        editFormProduct.style.display="block";
        inputNameEdit.value = btn.closest('tr').querySelector('.info_product .product-name').textContent;
        inputColorEdit.value=btn.closest('tr').querySelector('.product-color').textContent;
        inputPriceEdit.value=btn.closest('tr').querySelector('.product-price').textContent;
        statusEdit.value=btn.closest('tr').querySelector('.product-status').textContent;
        categoryEdit.value=findOptionValue(categoryEdit,(btn.closest('tr').querySelector('.product-category').textContent));
        providerEdit.value=findOptionValue(providerEdit,(btn.closest('tr').querySelector('.product-provider').textContent))
        preImage.innerHTML=
            `
            <img src="${btn.closest('tr').querySelector('.product-img').getAttribute('src')}"></img>
            `
        document.querySelector('#overlay').style.display="block";
    })
})
inputNameEdit.addEventListener('input',validationNameEdit);
inputColorEdit.addEventListener('input',validationColorEdit);
inputPriceEdit.addEventListener('input',validationPriceEdit);
function updateProduct(id){
    inputFileEdit.addEventListener('change', function (e) {
        const file = e.target.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function (event) {
                const img = document.createElement('img');
                img.src = event.target.result;
                preImage.innerHTML = ''; // Xóa bất kỳ ảnh trước đó
                preImage.appendChild(img);
            };
            reader.readAsDataURL(file);
        } else {
            preImage.innerHTML = 'Không có ảnh được chọn.';
        }
    });
    validationNameEdit();
    validationColorEdit();
    validationPriceEdit();


}
inputFileEdit.addEventListener('change', function (e) {
    const file = e.target.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function (event) {
            const img = document.createElement('img');
            img.src = event.target.result;
            preImage.innerHTML = ''; // Xóa bất kỳ ảnh trước đó
            preImage.appendChild(img);
        };
        reader.readAsDataURL(file);
    } else {
        preImage.innerHTML = 'Không có ảnh được chọn.';
    }
});
document.getElementById('edit_product_form').addEventListener('submit',function (e){
    e.preventDefault();
    updateProduct(idEdit)
    if(errorNameEdit.textContent===''
        && errorColorEdit.textContent===''
        && errorPriceEdit.textContent===''){
        const formData = new FormData(this);
        edit(formData,idEdit).then(data=>location.reload()).catch(error=>alert(error))
    }
})
let idUpdate;
document.querySelectorAll('.add_qty_sub').forEach(btn=>{
    btn.addEventListener('click',function (){
        idUpdate=btn.closest('tr').querySelector('.product-id').textContent;
        add_qty_popup.style.display="block";
        document.querySelector("#overlay").style.display="block";
        document.querySelector('.product-name-qty').innerHTML=btn.closest('tr').querySelector('.info_product .product-name').textContent;
        document.querySelector('.product-color-qty').innerHTML=btn.closest('tr').querySelector('.product-color').textContent;
    })
})
if(add_qty_popup){
    qty.addEventListener("change",validationQty);
    document.getElementById('qty_product_form').addEventListener('submit', (e)=>{
        e.preventDefault();
        validationQty();
        if(qtyError.textContent===''){
            addQty(idUpdate,qty.value).then(data=>{
                alert(data.message)
                location.reload()


            }).catch(error=>{
                alert(error);
            })
        }

    })
}
filter_form.addEventListener("submit",function (e){
    const FilterData = new FormData(this);

})
function getURLParameters() {
    let searchParams = new URLSearchParams(window.location.search);
    let params = {};

    for (let [key, value] of searchParams) {
        params[key] = value;
    }

    return params;
}

// Sử dụng hàm để lấy các tham số từ URL
let urlParameters = getURLParameters();
if(location.href.includes("http://localhost:8083/admin/products/search?")){
    // Truy cập các giá trị cụ thể
    let nameFilter = urlParameters['nameFilter'];
    let category = urlParameters['category'];
    let minPrice = urlParameters['min-price'];
    let maxPrice = urlParameters['max-price'];
    let status = urlParameters['status'];
    let provider = urlParameters['provider'];
    document.getElementById('nameFilterProduct').value=nameFilter;
    document.getElementById('category-filter').value=category;
    document.getElementById('minpice').value=minPrice
    document.getElementById('maxprice').value=maxPrice
    document.getElementById('status-filter').value=status;
    document.getElementById('provider-filter').value=provider;

}


