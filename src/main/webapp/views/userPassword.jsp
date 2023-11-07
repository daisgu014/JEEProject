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
        <div class="user-left">
            <div class="logo">
                <a style="display: flex; justify-content: center;">
                    <img src="/images/avt.png" style="height: 100px; width: 100px; object-fit:cover;">
                </a>
            </div>
            <div class="user-sidebar">
                <ul class="user-menu">
                    <li>
                        <a href="/user/profile" class="user-menu-list">
                            <i class="fa-solid fa-user" style="margin-right: 5px; margin-top: 2px;"></i>
                            <p> Thông tin tài khoản</p>
                        </a>
                    </li>
                    <li>
                        <a class="user-menu-list user-menu-list-active">
                            <i class="fa-solid fa-lock" style="margin-right: 5px; margin-top: 2px;"></i>
                            <p> Đổi mật khẩu</p>
                        </a>
                    </li>
                    <li style="margin-bottom: 200px;">
                        <a href="/user/purchased" class="user-menu-list">
                            <i class="fa-solid fa-money-bill" style="margin-right: 5px; margin-top: 2px;"></i>
                            <p>Đơn hàng đã mua</p>
                        </a>
                    </li>
                    <li>
                        <a href="/catalog" class="user-menu-list-exit">
                            <i class="fa-solid fa-cart-shopping" style="margin-right: 5px; margin-top: 2px;"></i>
                            <p>Về trang mua hàng</p>
                        </a>
                    </li>
                    <li>
                         <a href="/user/logout" id="user-back-to-shop" class="user-menu-list-exit">
                            <i class="fa-solid fa-right-from-bracket" style="margin-right: 5px; margin-top: 2px;"></i>
                            <p>Đăng xuất</p>
                         </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="userRight">
            <div class="user-head"><h1 class="user-lab-title">Thay đổi mật khẩu<h1></div>
            <div class="user-content">
                <form class="user-infomation" action="/user/password" method="POST" onsubmit="return ValidatePassword()">
                    <div class="user-password-content">
                        <label class="user-lab">Mật khẩu cũ</label>
                        <input class="user-input-password" type="password"  name="password" id="inputPassword" placeholder="Mật khẩu cũ">
                    </div>
                    <div class="user-password-content">
                        <label class="user-lab">Mật khẩu mới</label>
                        <input class="user-input-password" type="password" name="newPassword"  id="inputNewPassword"  placeholder="Mật khẩu mới">
                    </div>
                    <div class="user-password-content">
                        <label class="user-lab">Nhập lại mật khẩu mới</label>
                        <input class="user-input-password" type="password" id="inputReNewPassword"  placeholder="Nhập lại mật khẩu mới">
                    </div>
                    <p class="error-log-text" id="labError">${error}</p>
                    <button class="user-btn-submit" id="btnSubmit" type="submit">Xác nhận</button>
                    <button class="user-btn" id="btnCancel" type="button" onclick="ClearPassword()">Làm mới</button>
                </form>
            </div>
        </div>
    </div>
</body>
<script src="/js/userProfile.js"></script>
</html>