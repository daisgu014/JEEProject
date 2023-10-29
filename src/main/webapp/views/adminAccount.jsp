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
    <title>Quản lý Tài khoản</title>

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
                    <th><input type="checkbox" id="checkAll"></th>
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
                        <td><input type="checkbox" class="account-checkbox"></td>
                        <td>${account.getId()}</td>
                        <td>${account.getUsername()}</td>
                        <td style="display: none">${account.getPassword()}</td>
                        <td style="display: none">${account.getFullname()}</td>
                        <td>${account.getRole()}</td>
                        <td style="display: none">${account.getPhone()}</td>
                        <td style="display: none">${account.getEmail()}</td>
                        <td style="display: none">${account.getAddress()}</td>
                        <td>${account.getCreateAt()}</td>
                        <td>${account.getDeleteAt()}</td>
                        <td>Xóa</td>
                    </tr>
                </c:forEach>
                </tbody>
                </thead>
            </table>

            <div style="width: 100%;text-align: center;">
                <button id="addAccountButton" class="btn-addAccount">Thêm tài khoản</button>
            </div>>

            <div id="overlay"></div>

            <div id="addAccountForm" class="popup-account">
                <form action="" id="AccountForm">
                    <h2>Thêm tài khoản</h2>
                    Username: <input type="text" id="accountName" placeholder="Nhập username"><br>
                    <span id="usernameError" style="color: red;"></span><br>
                    Password: <input type="password" id="accountPass" placeholder="Nhập password"><br>
                    <span id="passwordError" style="color: red;"></span><br>
                    Fullname: <input type="text" id="accountFullname" placeholder="Nhập tên"><br>
                    <span id="fullnameError" style="color: red;"></span><br>
                    Loại: <select id="accountRole">
                        <option value="level1">Level 1</option>
                        <option value="level2">Level 2</option>
                        <option value="level3">Level 3</option>
                    </select> <br>
                    Số điện thoại: <input type="text" id="accountPhone" placeholder="Nhập số điện thoại"><br>
                    <span id="phoneError" style="color: red;"></span><br>
                    Email: <input type="text" id="accountEmail" placeholder="Nhập email"><br>
                    <span id="emailError" style="color: red;"></span><br>
                    Địa chỉ: <input type="text" id="accountAddress" placeholder="Nhập địa chỉ"><br>
                    <span id="addressError" style="color: red;"></span><br>
                    <div class="btn-addAccountForm">
                        <button type="submit" id="saveAccountButton">Lưu</button>
                        <button type="button" id="closeFormButton">Đóng</button>
                    </div>
                </form>
                <div id="add-success"></div>
            </div>
            <div id="updateAccountForm" class="popup-updateAccount">
                <form action="" id="AccountFormUpdate">
                    <h2>Chỉnh sửa tài khoản</h2>
                    ID: <input type="text" id="accountIdUpdate" placeholder=""><br>
                    Username: <input type="text" id="accountNameUpdate" placeholder="Nhập username"><br>
                    <span id="usernameUpdateError" style="color: red;"></span><br>
                    Password: <input type="password" id="accountPassUpdate" placeholder="Nhập password"><br>
                    <span id="passwordUpdateError" style="color: red;"></span><br>
                    Fullname: <input type="text" id="accountFullnameUpdate" placeholder="Nhập tên"><br>
                    <span id="fullnameUpdateError" style="color: red;"></span><br>
                    Loại: <select id="accountRoleUpdate">
                    <option value="level1">Level 1</option>
                    <option value="level2">Level 2</option>
                    <option value="level3">Level 3</option>
                </select> <br>
                    Số điện thoại: <input type="text" id="accountPhoneUpdate" placeholder="Nhập số điện thoại"><br>
                    <span id="phoneUpdateError" style="color: red;"></span><br>
                    Email: <input type="text" id="accountEmailUpdate" placeholder="Nhập email"><br>
                    <span id="emailUpdateError" style="color: red;"></span><br>
                    Địa chỉ: <input type="text" id="accountAddressUpdate" placeholder="Nhập địa chỉ"><br>
                    <span id="addressUpdateError" style="color: red;"></span><br>
                    <div class="btn-updateAccountForm">
                        <button type="submit" id="updateAccountButton">Lưu</button>
                        <button type="button" id="closeFormUpdateButton">Đóng</button>
                    </div>
                </form>
                <div id="update-success"></div>
            </div>
        </div>
    </div>

    <script src="/js/AccountEvent.js" type="text/javascript"></script>
</div>
</body>
</html>
