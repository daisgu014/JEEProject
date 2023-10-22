const AddForm = ()=>{
    return html`
        <div class="title">
            <p>Thêm thể loại mới</p>
            <div class="close-btn">
                <i class="fa-solid fa-xmark"></i>
            </div>
        </div>
        <div class="formPopup">
            <form action="<c:url value='/admin/categories/create' />" method="post" modelAttribute="category">

                <div class="txtFiled">
                    <label for="name">Name:</label>
                    <div class="txtName">
                        <input type="text" id="name" name="name" required></div>
                </div>
                <div class="saveBtn">
                    <input type="submit" value="Create">
                </div>

            </form>
        </div>
    `
}
document.getElementById("addBtn").addEventListener("click", function() {
  //  document.querySelector(".Add-popup").innerHTML=AddForm();
    document.querySelector("#overlay").style.display="block";
});
