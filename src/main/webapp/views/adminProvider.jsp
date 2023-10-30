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
<%--            <div class="search-container-dai">--%>
<%--                <input type="text" id="search-input-dai" placeholder="Tìm kiếm..."/>--%>
<%--                <button id="search-button-dai">--%>
<%--                    <i class="fa-solid fa-magnifying-glass"></i>--%>
<%--                </button>--%>
<%--            </div>--%>
            <table id="supplierTable">
                <thead>
                    <tr>
                        <th><input type="checkbox" id="checkAll"></th>
                        <th>Mã</th>
                        <th>Tên</th>
                        <th>Ngày Tạo</th>
<%--                        <th>Ngày Xóa</th>--%>
                        <th>Thao tác</th>
                    </tr>
                <tbody>
                    <c:forEach var="provider" items="${providers}">
                        <c:choose>
                        <c:when test="${empty provider.deleteAt}">
                        <tr>
                            <td><input type="checkbox" class="provider-checkbox"></td>
                            <td>${provider.getId()}</td>
                            <td>${provider.getName()}</td>
                            <td>${provider.getCreateAt()}</td>
<%--                            <td>${provider.getDeleteAt()}</td>--%>
                            <td> <button class="deleteProviderBtn" onclick="deleteProvider(${provider.getId()})">Xóa</button> </td>
                        </tr>
                        </c:when>
                        </c:choose>
                    </c:forEach>

                </tbody>
                </thead>
            </table>
            <div id="delete-success"></div>
            <div id="pagination" class="page"></div>

            <div style="width: 100%;text-align: center;">
                <button id="addSupplierButton" class="btn-addProvider">
                    <i class="fa-solid fa-plus fa-beat"></i><br>
                    <span>Thêm tài khoản</span>
                </button>
            </div>>
            <div id="overlay"></div>

            <div id="addSupplierForm" class="form-addSupplier">
                <form id="supplierForm">
                    <h2>Thêm nhà cung cấp</h2>
                    <input  type="text" id="supplierName" name="supplierName" placeholder="Nhập tên nhà cung cấp"  /><br>
                    <div id="supplierNameError" style="color: red;"> </div>
                    <button type="submit" id="saveSupplierButton">Lưu</button>
                    <button type="button" id="closeAddFormButton">Đóng</button>
                </form>
                <div id="add-success"></div>
                <div id="add-fail"></div>
            </div>
            <div id="updateSupplierForm" class="form-updateSupplier">
                <form id="updateSupplier">
                    <h2>Chỉnh sửa nhà cung cấp</h2>
                    <input type="text" id="supplierNameUpdate" placeholder="Nhập tên nhà cung cấp" /><br>
                    <div id="supplierNameUpdateError" style="color: red;"> </div>
                    <button type="submit" id="updateSupplierButton">Lưu</button>
                    <button type="button" id="closeUpdateFormButton">Đóng</button>
                </form>
                <div id="update-success"></div>
                <div id="update-fail"></div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/js/ProviderEvent.js"></script>
</div>
</body>
</html>
