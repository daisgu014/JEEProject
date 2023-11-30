<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Nhập sản phẩm </title>
    <link rel="stylesheet"  href="/css/productStyle.css">
    <link rel="stylesheet"  href="/css/AdminImportProduct.css">
    <link rel="stylesheet" href="/css/importHistory.css">
    <link rel="stylesheet"  href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div class="container" >
    <div style="width: 21%">
        <div class="left" style="position: fixed;">
            <div class="logo">
                <a href="#" style="    display: flex;
    justify-content: center;">
                    <img src="/images/avt.png" style="height:60px; width: 60px; object-fit:cover; margin: 1rem">
                </a>
            </div>
            <div class="sidebar">
                <ul class="menu">
                    <li class="statistic">
                        <a href="/admin/statistic">
                            <i class="fa-sharp fa-solid fa-gauge"></i>
                            <p>Quản lý</p>
                        </a>
                    </li>
                    <li class="categories active">
                        <a href="/admin/categories" >
                            <i class="fa-solid fa-bars"></i>
                            <p>Thể loại</p>
                        </a>
                    </li>
                    <li class="provider">
                        <a href="/admin/provider" >
                            <i class="fas fa-warehouse"></i>
                            <p>Nhà cung cấp</p>
                        </a>
                    </li>

                    <li class="products">
                        <a href="/admin/products" >
                            <i class="fa-solid fa-shop"></i>
                            <p>Sản phẩm</p>
                        </a>
                    </li>
                    <li class="account">
                        <a href="/admin/account" >
                            <i class="fa-solid fa-user"></i>
                            <p>Người dùng</p>
                        </a>
                    </li>
                    <li class="orders">
                        <a href="/admin/orders" >
                            <i class="fas fa-shopping-cart"></i>
                            <p>Đơn hàng</p>
                        </a>
                    </li>
                    <li>
                        <a href="/api/v1/auth/logout">
                            <i class="fa-solid fa-arrow-right-from-bracket"></i>
                            <p>Đăng xuất</p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="main--content">
        <jsp:include page="/headerAdmin">
            <jsp:param name="title" value="Nhập số lượng sản phẩm"/>
        </jsp:include>
        <div class="pageBefore">
            <a href="/admin/products">Quản lý sản phẩm </a>
            <i class="fa-solid fa-right-long"></i>
            <a href="">Nhập sản phẩm</a>
        </div>
        <div class="tabular--wrapper">
           <div class="add-qty-content" style="display: flex">
               <select path="product" name="product" id="product" class="product-input">
                   <c:forEach var="product" items="${products}">
                       <option value="${product.getId()}">
                            ${product.getName()} ${product.getColor()} ${product.getPrice()}VNĐ
                       </option>
                   </c:forEach>
               </select>
                <div class="input-qty">
                    <input name="qty-product" id="qty-product" type="number" min=1 max=100>
                    <div><span id="error-qty-products"></span></div>
                </div>
               <div class="btn-qty"><button id="btn-qty">Thêm </button></div>
               <div class="btn-save"><button id="btn-save">Lưu thay đổi</button></div>
           </div>

            <div class="title-table">
            <div class="table-container">
                <table id="import-products-table">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên sản phẩm</th>
                        <th>Màu sắc</th>
                        <th>Thể loại</th>
                        <th>Nhà cung cấp</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
<script src="/js/ImportProduct/event.js" type="module"></script>
</body>