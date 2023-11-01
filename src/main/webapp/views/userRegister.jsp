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
            <h1 style="color: white;">Không có gì đâuƯ! :)))</h1>
            <table>
               <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>password</th>
                    <th>role</th>
                    <th>phone</th>
                    <th>email</th>
                    <th>address</th>
                </tr>
                <c:forEach var="user" items="${account}">
                    <tr>
                        <td>${user.getId()}</td>
                        <td>${user.getUsername()}</td>
                        <td>${user.getPassword()}</td>
                        <td>${user.getRole()}</td>
                        <td>${user.getPhone()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getAddress()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="log-right">
            <div class="frm-register">
                <div class="log-header-frm" >
                    <h1 class="log-header-content">Đăng ký</h1>
                </div>
                <form action="/user/register/create" method="POST" class="log-container-frm" onsubmit="return ValidateRegister()">
                    <label class="lab-log-content">Email</label>
                    <input class="input-log-content" type="text" name="email"  id="inputEmail" placeholder="Email">
                    <label class="lab-log-content">Tên đăng nhập</label>
                    <input class="input-log-content" type="text" name="username"  id="inputUsername"  placeholder="Tên đăng nhập">
                    <label class="lab-log-content">Số điện thoại</label>
                    <input class="input-log-content" type="text" name="phone" id="inputPhone"  placeholder="Số điện thoại">
                    <label class="lab-log-content">Địa chỉ</label>
                    <input class="input-log-content" type="text" name="address" id="inputAddress"  placeholder="Địa chỉ">
                    <label class="lab-log-content">Mật khẩu</label>
                    <input class="input-log-content" type="password" name="password" id="inputPassword"  placeholder="Mật khẩu">
                    <label class="lab-log-content">Nhập lại mật khẩu</label>
                    <input class="input-log-content" type="password" id="inputRePassword"  placeholder="Nhập lại mật khẩu">
                    <p class="error-log-text" id="labError">${error}</p>
                    <button class="btn-log-submit" type="submit">Đăng ký</button>
                </form>
                <div class="log-footer-frm">
                    <hr class="log-hr">
                    <label class="lab-log-content">Bạn có tài khoản?</label>
                    <button class="btn-log-button" type="button">Đăng nhập</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="/js/userInfoValidation.js"></script>
</html>