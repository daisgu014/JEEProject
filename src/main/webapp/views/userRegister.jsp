<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
       <link rel="stylesheet" href="/css/style.css">
       <link rel="stylesheet" href="/css/userLog-Profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
    <div class="Login">
        <div class="log-left">
            <h1 style="color: white; display: none;">Không có gì đâuƯ! :)))</h1>
        </div>
        <div class="log-right">
            <div class="frm-register">
                <div class="log-header-frm" >
                    <h1 class="log-header-content">Đăng ký</h1>
                </div>
                <form action="/user/register" method="POST" class="log-container-frm" onsubmit="return ValidateSecondPage()">
                    <div class="log-page-1" id="Page1">
                        <label class="lab-log-content">Tên đăng nhập</label>
                        <input class="input-log-content" type="text" name="username"  id="inputUsername"  placeholder="Tên đăng nhập">
                        <label class="lab-log-content">Họ và tên</label>
                        <input class="input-log-content" type="text" name="fullname"  id="inputFull_Name"  placeholder="Họ và tên">
                        <label class="lab-log-content">Email</label>
                        <input class="input-log-content" type="text" name="email"  id="inputEmail" placeholder="Email">
                        <label class="lab-log-content">Số điện thoại</label>
                        <input class="input-log-content" type="text" name="phone" id="inputPhone"  placeholder="Số điện thoại">
                        <label class="lab-log-content">Địa chỉ</label>
                        <input class="input-log-content" type="text" name="address" id="inputAddress"  placeholder="Địa chỉ">
                        <p class="success-log-text" id="labSuccess">${success}</p>
                        <p class="error-log-text" id="labError">${error}</p>
                        <button class="btn-log-submit" type="button" onclick="ValidateFirstPage()">Tiếp theo</button>
                    </div>
                    <div class="log-page-2"id="Page2" style="display:none">
                        <label class="lab-log-content">Mật khẩu</label>
                        <input class="input-log-content" type="password" name="password" id="inputPassword"  placeholder="Mật khẩu">
                        <label class="lab-log-content">Nhập lại mật khẩu</label>
                        <input class="input-log-content" type="password" id="inputRePassword"  placeholder="Nhập lại mật khẩu">
                        <p class="error-log-text" id="labError2">${error}</p>
                        <button class="btn-log-button" type="button" onclick="OpenPage1()">Quay lại</button>
                    </div>
                    <button class="btn-log-submit" id="btnSubmit" style="display:none;" type="submit">Đăng ký</button>
                </form>
                <div class="log-footer-frm">
                    <hr class="log-hr">
                    <label class="lab-log-content">Bạn có tài khoản?</label>
                    <a href="/user/login" style="text-decoration: none;"><button class="btn-log-button" type="button">Đăng nhập</button></a>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="/js/userInfoValidation.js"></script>
</html>