<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
       <link rel="stylesheet" href="/css/style.css">
       <link rel="stylesheet" href="/css/userLog-Profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="user-container">
        <div class="user-up">
            <div class="logo">
                <a href="/catalog" style="display: flex; justify-content: center;">
                    <img src="/images/avt.png" style="padding: var(--padding-1);height: 80px; object-fit:cover;">
                </a>
            </div>
            <div class="user-head"><h1 class="user-lab-title">Đơn hàng đã mua<h1></div>
            <div class="user-menu">
                <div>
                    <a href="/user/logout" id="user-back-to-shop" class="user-menu-list-exit">
                        <i class="fa-solid fa-right-from-bracket" style="margin-right: 5px; margin-top: 2px;"></i>
                        <p class="user-menu-name">Đăng xuất</p>
                    </a>
                </div>
                <div>
                    <a href="/catalog" class="user-menu-list-exit">
                        <i class="fa-solid fa-cart-shopping" style="margin-right: 5px; margin-top: 2px;"></i>
                        <p class="user-menu-name">Về trang mua hàng</p>
                    </a>
                </div>
                <div>
                    <a class="user-menu-list-active">
                        <i class="fa-solid fa-money-bill" style="margin-right: 5px; margin-top: 2px;"></i>
                        <p class="user-menu-name-active">Đơn hàng đã mua</p>
                    </a>
                </div>
                <div>
                    <a href="/user/password" class="user-menu-list">
                        <i class="fa-solid fa-lock" style="margin-right: 5px; margin-top: 2px;"></i>
                        <p class="user-menu-name"> Đổi mật khẩu</p>
                    </a>
                </div>
                <div>
                    <a href="/user/profile" class="user-menu-list">
                        <i class="fa-solid fa-user" style="margin-left: 2px; font-size: 20px;"></i>
                        <p class="user-menu-name">Thông tin người dùng</p>
                    </a>
                </div>
            </div>
        </div>
        <div class="userRight">
            <div class="user-content">
                <div class="user-table">
                    <div class="user-table-header">
                        <p>STT</p>
                        <p>Ngày mua</p>
                        <p>Tổng giá</p>
                        <p>Tình trạng</p>
                    </div>
                    <div class="user-table-data">
                        <c:set var="count" value="0" scope="page"></c:set>
                        <c:forEach var="order" items="${orders}">
                            <c:set var="count" value="${count + 1}" scope="page"></c:set>
                            <div class="user-order">
                                <div class="user-main-data">
                                    <p>${count}</p>
                                    <p>${order.getCreate_at()}</p>
                                    <p>${String.format("%,d",order.getTotal_price())}</p>
                                    <c:choose>
                                        <c:when test="${!order.isConfirm()}">
                                        <p>Chờ xác nhận</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>Đã xác nhận</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="user-sub-data">
                                    <div class="user-table-sub-header">
                                        <p>Tên sản phẩm</p>
                                        <p>Màu sản phẩm</p>
                                        <p>Số lượng</p>
                                        <p>Đơn giá</p>
                                    </div>
                                    <c:forEach var="od" items="${order.getDetails()}">
                                        <c:set var="p" value="${od.getProduct()}"></c:set>
                                        <div>
                                            <div class="user-table-sub-content">
                                                <p>${p.getName()}</p>
                                                <p>${p.getColor()}</p>
                                                <p>${od.getQty()}</p>
                                                <p>${String.format("%,d",p.getPrice() * od.getQty() )}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="/js/userProfile.js"></script>
</html>