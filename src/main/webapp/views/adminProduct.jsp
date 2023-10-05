<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
    <title> Quản lý sản phẩm</title>
    <link rel="stylesheet"  href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
<div id="overlay"></div>
<div class="container" >
    <div class="left">
        <div class="logo">
            <h1>LOGO IMAGE</h1>
        </div>
        <div class="sidebar">

            <ul class="menu">
                <li class="active">
                    <a href="#">
                        <i class="fa-sharp fa-solid fa-gauge"></i>
                        <p>Quản lý</p>
                    </a>
                </li>
                <li>
                    <a href="categories">
                        <i class="fa-solid fa-bars"></i>
                        <p>Thể loại</p>
                    </a>
                </li>

                <li>
                    <a href="products">
                        <i class="fa-solid fa-shop"></i>
                        <p>Sản phẩm</p>
                    </a>
                </li>
                <li>
                    <a href="users">
                        <i class="fa-solid fa-user"></i>
                        <p>Người dùng</p>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa-solid fa-arrow-right-from-bracket"></i>
                        <p>Đăng xuất</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main--content">
        <div class="header-wrapper">
            <div class="header--title">
                <span>Riêng tư</span>
                <h2>Quản lý</h2>
            </div>
            <div class="user--info">
                <div class="search-box">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" placeholder="Search"/>
                </div>
                <img src="./image/img.jpg" alt="">
            </div>

        </div>
        <div class="tabular--wrapper">
            <div class="title-table">
                <h3 class="main-title">Thể loại</h3>
                <div class="addbtn" id="addBtn">
                    <i class="fa-solid fa-plus"></i>
                </div>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>No.</th>
                        <th>Tên thể loại</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Bàn công thái học</td>
                        <td>Hết</td>
                        <td><button>Edit</button></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Bàn học sinh</td>
                        <td>Còn hàng</td>
                        <td><button>Edit</button></td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <td colspan="4">Tổng số thể loại: 2</td>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="Add-popup" style="display: none;">
    <div class="title">
        <p>Thêm thể loại mới</p>
        <div class="close-btn">
            <i class="fa-solid fa-xmark"></i>
        </div>
    </div>
    <div class="formPopup">
        <div class="nameInput">
            <div class="nameLabel">
                <p>Nhập tên thể loại</p>
            </div>
            <div class="txtName">
                <input type="text" placeholder="Nhập tên thể loại">
            </div>
        </div>
        <div class="saveBtn">
            <p>Lưu</p>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="/js/event.js"></script>
</body>
</html>