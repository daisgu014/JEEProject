<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Nhập sản phẩm </title>
    <link rel="stylesheet"  href="/css/productStyle.css">
    <link rel="stylesheet"  href="/css/AdminImportProduct.css">
    <link rel="stylesheet"  href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div class="container" >
    <div class="left">
        <div class="logo">
            <a href="#" style="    display: flex;
    justify-content: center;">
                <img src="/images/avt.png" style="height: 100px; width: 100px; object-fit:cover;">
            </a>
        </div>
        <div class="sidebar">

            <ul class="menu">
                <li >
                    <a href="#">
                        <i class="fa-sharp fa-solid fa-gauge"></i>
                        <p>Quản lý</p>
                    </a>
                </li>
                <li class="categories ">
                    <a href="/admin/categories" >
                        <i class="fa-solid fa-bars"></i>
                        <p>Thể loại</p>
                    </a>
                </li>

                <li class="products active ">
                    <a href="/admin/products" >
                        <i class="fa-solid fa-shop"></i>
                        <p>Sản phẩm</p>
                    </a>
                </li>
                <li class="users ">
                    <a href="/admin/account" >
                        <i class="fa-solid fa-user"></i>
                        <p>Người dùng</p>
                    </a>
                </li>
                <li class=order">
                    <a href="/admin/orders" >
                        <i class="fas fa-shopping-cart"></i>
                        <p>Đơn hàng</p>
                    </a>
                </li>
                <li>
                    <div id="LogoutBtn">
                        <a href="/api/v1/auth/logout">
                            <i class="fa-solid fa-arrow-right-from-bracket"></i>
                            <p>Đăng xuất</p>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <div class="main--content">
        <div class="header-wrapper">
            <div class="header--title">
                <span>Riêng tư</span>
                <h2>Nhập sản phẩm</h2>
            </div>
            <span style="color: #07B3F9; font-weight: 600; font-size:16px" data-id="${user.getId()}">${user.getFull_name()}</span>
            <div class="user-modal" id="user-modal">
                <ul>
                    <l1 class="user_sub">
                        <a class="user-sub-menu">
                            <div>
                                <i class="fa-solid fa-user"></i>
                            </div>
                            <span>Hồ sơ</span>
                        </a>
                    </l1>
                    <l1 class="user_sub">
                        <a class="user-sub-menu">
                            <div>
                                <i class="fa-solid fa-cart-shopping"></i>
                            </div>
                            <span>Đơn hàng</span>
                        </a>
                    </l1>
                    <l1 class="user_sub">
                        <a class="user-sub-menu">
                            <div>
                                <i class="fa-solid fa-right-from-bracket"></i>
                            </div>
                            <span>Đăng xuất</span>
                        </a>
                    </l1>
                </ul>
            </div>

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
                </div>
               <div class="btn-qty"><button id="btn-qty">Thêm </button></div>
           </div>

            <div class="title-table">
            <div class="table-container">
                <table>
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
<script src="/js/event.js" type="text/javascript"></script>
</body>