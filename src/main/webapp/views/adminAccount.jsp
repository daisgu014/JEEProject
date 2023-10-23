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
                <h2>Quản lý Tài khoản</h2>
            </div>
            <div class="header-search">


            </div>
            <div class="user--info">
                <img src="./image/img.jpg" alt="">
            </div>

        </div>
        <div class="container-account" style="width: 100%">
            <table id="accountTable">
                <thead>
                <tr>
                    <th>Mã</th>
                    <th>Username</th>
                    <th>Loại</th>
                    <th>Ngày Tạo</th>
                    <th>Ngày Xóa</th>
                    <th>Thao tác</th>
                </tr>
                <tbody>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.getId()}</td>>
                        <td>${account.getUsername()}</td>>
                        <td>${account.getRole()}</td>>
                        <td>${account.getCreateAt()}</td>>
                        <td>${account.getDeleteAt()}</td>>
                        <td>Xóa</td>>
                    </tr>
                </c:forEach>
                </tbody>
                </thead>
            </table>

            <button id="addAccountButton" class="btn-addAccount">Thêm tài khoản</button>

            <div id="overlay"></div>

            <div id="addAccountForm" class="popup-account">
                <form action="">
                    <h2>Thêm tài khoản</h2>

                    Username: <input type="text" id="accountName" placeholder="Nhập username"><br>
                    <span id="usernameError" style="color: red;"></span><br>
                    Password: <input type="password" id="accountPass" placeholder="Nhập password"><br>
                    <span id="passwordError" style="color: red;"></span><br>
                    Loại: <select id="accountRole">
                        <option value="apple">Customer</option>
                        <option value="banana">Employee</option>
                        <option value="cherry">Admin</option>
                    </select> <br>
                    Số điện thoại: <input type="text" id="accountPhone" placeholder="Nhập số điện thoại"><br>
                    <span id="phoneError" style="color: red;"></span><br>
                    Email: <input type="text" id="accountEmail" placeholder="Nhập email"><br>
                    <span id="emailError" style="color: red;"></span><br>
                    Địa chỉ: <input type="text" id="accountAddress" placeholder="Nhập địa chỉ"><br>
                    <span id="addressError" style="color: red;"></span><br>
                    <div class="btn-addAccountForm">
                        <button id="saveAccountButton">Lưu</button>
                        <button id="closeFormButton">Đóng</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="/js/AccountEvent.js" type="text/javascript"></script>
</div>
</body>
</html>
