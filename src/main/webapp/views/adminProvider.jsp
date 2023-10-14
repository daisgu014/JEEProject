<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Quản lý Nhà cung cấp</title>
    <style>
        .header-provider, .container-provider {
            width: 100%;
            height: 10%;
            /* border: black solid 1px; */
            border-radius: var(--border-radius-2);
            box-shadow: var(--box-shadow);
            background-color: #fff;
            padding: 5px 5px 5px 5px;
            margin: 5px 5px 5px 5px;
        }
        .container-provider {
            height: 90%;

        }
        #overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1;
        }

        .popup-provider{
            display: none;
            position: fixed;
            left: 40%;
            background-color: white;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: var(--border-radius-2);
            z-index: 2;
        }
        .popup-provider>form{
            text-align: center;
        }
        .popup-provider>form>h2{
            color: var(--color-primary);
        }
        .popup-provider>form>button{
            margin-top: 10%;
            width: 100px;
            color: #fff;
            border-radius: var(--border-radius-1);
            background-color: var(--color-primary);

        }
        .btn-addProvider{
            margin-top: 5%;
            margin-left: 45%;
            width: 200px;
            height: 50px;
            color: #fff;
            border-radius: var(--border-radius-1);
            background-color: var(--color-primary);
        }
        #supplierName{
            border: 1px solid var(--color-info-dark);
        }
        #addSupplierButton {
            cursor: pointer;
        }

        #addSupplierButton:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
<div class="container">
    <jsp:include page="../components/sidebar.jsp"/>
    <div class="main--content">
        <div class="header-wrapper">
            <div class="header--title">
                <span>Riêng tư</span>
                <h2>Quản lý Nhà Cung Cấp</h2>
            </div>
            <div class="user--info">
                <%--            <div class="search-box">--%>
                <%--                <i class="fa-solid fa-magnifying-glass"></i>--%>
                <%--                <input type="text" placeholder="Search"/>--%>
                <%--            </div>--%>
                <img src="./image/img.jpg" alt="">
            </div>

        </div>
        <div class="container-provider" style="width: 100%">
            <table id="supplierTable">
                <thead>
                    <tr>
                        <th>Mã</th>
                        <th>Tên</th>
                        <th>Ngày Tạo</th>
                        <th>Ngày Xóa</th>
                    </tr>
                </thead>
            </table>

            <button id="addSupplierButton" class="btn-addProvider">Thêm nhà cung cấp</button>

            <div id="overlay"></div>

            <div id="addSupplierForm" class="popup-provider">
                <form action="">
                    <h2>Thêm nhà cung cấp</h2>
                    <input type="text" id="supplierName" placeholder="Nhập tên nhà cung cấp"><br>
                    <button id="saveSupplierButton">Lưu</button>
                    <button id="closeFormButton">Đóng</button>
                </form>
            </div>
        </div>
    </div>

    <script >
        var suppliers = [];


        var addSupplierButton = document.getElementById("addSupplierButton");
        var addSupplierForm = document.getElementById("addSupplierForm");
        var overlay = document.getElementById("overlay");
        var supplierNameInput = document.getElementById("supplierName");
        var saveSupplierButton = document.getElementById("saveSupplierButton");
        var closeFormButton = document.getElementById("closeFormButton");
        var supplierTable = document.getElementById("supplierTable");


        addSupplierButton.addEventListener("click", function() {
            addSupplierForm.style.display = "block";
            overlay.style.display = "block";
        });
        closeFormButton.addEventListener("click", function() {
            addSupplierForm.style.display = "none";
            overlay.style.display = "none";
            supplierNameInput.value = "";
        });
        saveSupplierButton.addEventListener("click", function() {
            addSupplierForm.style.display = "none";
            overlay.style.display = "none";
            supplierNameInput.value = "";
        });

    </script>
</div>
</body>
</html>
