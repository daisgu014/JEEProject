<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/ProviderStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Quản lý Nhà cung cấp</title>

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
            <div class="header-search">
            </div>
            <div class="user--info">
                <img src="./image/img.jpg" alt="">
            </div>

        </div>
        <div class="container-provider" style="width: 100%">
            <div class="search-container-dai">
                <input type="text" id="search-input-dai" placeholder="Tìm kiếm...">
                <button id="search-button-dai"><i class="fa-solid fa-magnifying-glass"></i></button>
            </div>
            <table id="supplierTable">
                <thead>
                    <tr>
                        <th>Mã</th>
                        <th>Tên</th>
                        <th>Ngày Tạo</th>
                        <th>Ngày Xóa</th>
                        <th>Thao tác</th>
                    </tr>
                <tbody>
                    <c:forEach var="provider" items="${providers}">
                        <tr>
                            <td>${provider.getId()}</td>>
                            <td>${provider.getName()}</td>>
                            <td>${provider.getCreateAt()}</td>>
                            <td>${provider.getDeleteAt()}</td>>
                            <td>Xóa</td>>
                        </tr>
                    </c:forEach>
                </tbody>
                </thead>
            </table>

            <button id="addSupplierButton" class="btn-addProvider">Thêm nhà cung cấp</button>
            <%--<div>${error}</div>--%>
            <div id="overlay"></div>

            <div id="addSupplierForm" class="form-addSupplier">
                <form id="supplierForm">
                    <h2>Thêm nhà cung cấp</h2>
                    <input  type="text" id="supplierName" name="supplierName" placeholder="Nhập tên nhà cung cấp"  /><br>
                    <div id="supplierNameError" style="color: red;"> </div>
                    <button type="submit" id="saveSupplierButton">Lưu</button>
                    <button type="button" id="closeAddFormButton">Đóng</button>
                </form>
                <div id="result-message">123</div>
            </div>
            <div id="updateSupplierForm" class="form-updateSupplier">
                <form:form id="" action="/admin/provider/update" method="POST">
                    <h2>Chỉnh sửa nhà cung cấp</h2>
                    <input type="text" id="supplierNameUpdate" placeholder="Nhập tên nhà cung cấp" /><br>
                    <div id="supplierNameUpdateError" style="color: red;"> </div>
                    <button type="submit" id="updateSupplierButton">Lưu</button>
                    <button type="button" id="closeUpdateFormButton">Đóng</button>
                </form:form>

            </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="/js/ProviderEvent.js"  > </script>

</body>
</html>
