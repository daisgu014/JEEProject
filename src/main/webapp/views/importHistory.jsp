<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/importHistory.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="main--content">
        <div class="header-wrapper">
            <div class="header--title">
                <span>Riêng tư</span>
                <h2>Nhập ký nhập sản phẩm</h2>
            </div>
            <div class="user--info">
                <img src="./image/img.jpg" alt="">
            </div>
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
        <div class="pageBefore">
            <a href="/admin/products">Quản lý sản phẩm </a>
            <i class="fa-solid fa-right-long"></i>
            <a href="">Nhập ký nhập sản phẩm</a>
        </div>
        <div class="tabular--wrapper">
            <div class="title-table">
                <div class="filter">
                    <div class="search-box">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="text" placeholder="Tìm kiếm"/>
                    </div>
                </div>

            </div>
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên sản phẩm</th>
                        <th>Thể loại</th>
                        <th>Màu sắc</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Người nhập</th>
                        <th>Giờ</th>
                        <th>Ngày</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td class="info_product">
                            <span>Bàn công thái học</span></td>
                        <td>Bàn</td>
                        <td>Đen</td>
                        <td>3.400.000VNĐ</td>
                        <td>10</td>
                        <td>Nguyễn Hoàng Gia Đại </td>
                        <td>7:30</td>
                        <td>15/10/2023</td>
                    </tr>

                    </tbody>
                    <tfoot>
                    <td colspan="9">Tổng số lần nhập sản phẩm: 1</td>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>

</div>
<script src="./event.js"></script>
<script src="./pageBefore.js"></script>
</body>
</html>