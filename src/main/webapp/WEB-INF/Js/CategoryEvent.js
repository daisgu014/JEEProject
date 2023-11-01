import {deleteId, deleteCategories, create,editCategory} from "./adminCategory/_request.js";
import {errorName,inputName,validationName,validationNameEdit,inputNameEdit,errorNameEdit} from "./adminCategory/validation.js";

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
    document.getElementById("addBtnCategory").addEventListener("click", function() {
        document.querySelector("#category_popup").style.display="block";
        document.querySelector("#overlay").style.display="block";
    });

    document.querySelector("#overlay").addEventListener("click", ()=>{
        document.querySelector("#category_popup").style.display="none";
        document.querySelector(".edit-category-form").style.display="none"
        document.querySelector("#overlay").style.display="none";


    })
    if(document.querySelector(".close-btn")!=null) {
        document.querySelector(".close-btn").addEventListener("click", () => {
            document.querySelector("#category_popup").style.display = "none";
            document.querySelector(".edit-category-form").style.display = "none"
            document.querySelector("#overlay").style.display = "none"

        })

    }
    if(document.querySelector("#close-btn-edit")!=null){
        document.querySelector("#close-btn-edit").addEventListener("click", ()=>{
            document.querySelector(".edit-category-form").style.display="none"
            document.querySelector("#overlay").style.display="none"

        })
    }



    let checkbox = document.querySelector('.check_box_category');
    let checkbox_subs = document.querySelectorAll('.sub_checkbox_category');
    if(checkbox!=null && checkbox_subs!=null){
        checkbox_subs.forEach(checkbox=>{
            checkbox.addEventListener('click', function (){
                for (let i = 0; i < checkbox_subs.length; i++) {
                    if (checkbox_subs[i].checked) {
                        addValueToArray(selectedProductIds,checkbox_subs[i].closest('tr').querySelector('.category-id').textContent)
                        document.getElementById('event').style.display = 'none';
                        document.getElementById('deleteBtn').style.display = 'block';
                        return
                    }else{
                        removeFromArray(selectedProductIds,checkbox_subs[i].closest('tr').querySelector('.category-id').textContent)

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
                        addValueToArray(selectedProductIds,checkbox.closest('tr').querySelector('.category-id').textContent)
                    }
                )

            }else{
                document.getElementById('event').style.display='flex';
                document.getElementById('deleteBtn').style.display='none';
                checkbox_subs.forEach(checkbox =>{
                    checkbox.checked = false;
                    removeFromArray(selectedProductIds,checkbox.closest('tr').querySelector('.category-id').textContent)
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
        deleteCategories(selectedProductIds).then(data=>{
            alert("Xoa thanh cong");
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

let insertBtn = document.getElementById('insert-button');
insertBtn.addEventListener("click", ()=>{
    inputName.addEventListener('input',validationName);
    validationName()
    if(errorName.textContent==='') {
        create().then(data => {
            location.reload();
        }).catch(error => {
            alert(error.message); // Hiển thị thông báo lỗi nếu có lỗi
        });
    }
})
function updateCategory(id){
    inputNameEdit.addEventListener('input', validationNameEdit);
    validationNameEdit();
    const category = {
        id: null,
        name: inputNameEdit.value.trim()
    }
    if (errorNameEdit.textContent === '') {
        editCategory(id, category).then(data => {

        }).catch(error => {
            alert(error.message)
        })
    }
}


let editForm = document.querySelector(".edit-category-form");

let BtnEdits = document.querySelectorAll(".btn-edit");
let id="";
BtnEdits.forEach(btn=>{

    btn.addEventListener("click",()=>{
        id= btn.closest('tr').querySelector('.category-id').textContent;
       let name = btn.closest('tr').querySelector('.category-name').textContent;
       editForm.style.display="block";
       inputNameEdit.value=name;
        document.querySelector("#overlay").style.display="block";

        }
    )
})
document.getElementById("update").addEventListener("click",()=>{
    updateCategory(id);
})