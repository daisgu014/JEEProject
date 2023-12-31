<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
       <link rel="stylesheet" href="/css/style.css">
       <link rel="stylesheet" href="/css/userLog-Profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
    <div class="Login">
        <div class="log-left">
            <h1 style="color: white; display: none">Không có gì :)))</h1>
        </div>
        <div class="log-right">
            <div class="frm-login">
                <div class="log-header-frm" >
                    <h1 class="log-header-content">Đăng nhập</h1>
                </div>
                <form action="/user/login" method="POST"  class="log-container-frm" onsubmit="return ValidateLogin()">
                    <label class="lab-log-content">Tên đăng nhập</label>
                    <input class="input-log-content" type="text" name="username" id="inputUsername" placeholder="Tên đăng nhập">
                    <p class="error-log-text" id="labErrorUsername"></p>
                    <label class="lab-log-content">Mật khẩu</label>
                    <input class="input-log-content" type="password" name="password" id="inputPassword" placeholder="Mật khẩu">
                    <p class="error-log-text" id="labErrorPassword"></p>
                    <p class="error-log-text">${error}</p>
                    <a href="/user/login/getPassword" class="log-getpass" href="">Quên mật khẩu!</a>
                    <button class="btn-log-submit" type="submit">Đăng nhập</button>
                </form>
                <div class="log-footer-frm">
                    <hr class="log-hr">
                    <label class="lab-log-content">Bạn chưa có tài khoản?</label>
                    <a href="/user/register" style="text-decoration: none;"><button class="btn-log-button" type="button">Đăng ký</button></a>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="/js/userInfoValidation.js"></script>
</html>