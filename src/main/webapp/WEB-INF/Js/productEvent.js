import {create,deleteId, deleteProducts} from "./adminProducts/_request.js";
import {
    validationName,
    validationColor,
    validationPrice,
    validationInputFile,
    inputName, inputColor, inputPrice, inputFile, errorName, errorPrice, errorColor, errorFile
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

function Init() {


    document.getElementById("addBtn").addEventListener("click", function() {
        document.querySelector(".products_popup").style.display="block";
        document.querySelector("#overlay").style.display="block";
    });

    document.querySelector("#overlay").addEventListener("click", ()=>{
        document.querySelector(".products_popup").style.display="none";
        document.querySelector("#overlay").style.display="none";

    })
    if(document.querySelector(".close-btn")!=null){
        document.querySelector(".close-btn").addEventListener("click", ()=>{
            document.querySelector(".products_popup").style.display="none";
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
            alert(data.message);
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

document.getElementById('productForm').addEventListener('submit',function (e){
    e.preventDefault();
    inputName.addEventListener('input',validationName);
    inputColor.addEventListener('input',validationColor);
    inputPrice.addEventListener('input',validationPrice);
    inputFile.addEventListener('change',validationInputFile);
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

// insertBtn.addEventListener("click", ()=>{
//         inputName.addEventListener('input',validationName);
//         inputColor.addEventListener('input',validationColor);
//         inputPrice.addEventListener('input',validationPrice);
//         inputFile.addEventListener('change',validationInputFile);
//             validationName()
//             validationColor()
//             validationPrice()
//             validationInputFile()
//         if(errorName.textContent===''&&
//             errorPrice.textContent===''&&
//             errorColor.textContent===''&&
//             errorFile.textContent==='') {
//             createProduct().then(data => {
//                 location.reload();
//             }).catch(error => {
//                 alert(error.message); // Hiển thị thông báo lỗi nếu có lỗi
//             });
//         }
//     })