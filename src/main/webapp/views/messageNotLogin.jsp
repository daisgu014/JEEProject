<%@ page import="com.JEEProject.TableStore.Model.Account" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Spring MVC</title>
    <link type="text/css" href="/css/style.css" rel="stylesheet">
    <link type="text/css" href="/css/header.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<style>
    .btn-login {
        background: var(--color-primary);
        padding: 18px 60px;
        color: #fff;
        cursor: pointer;
        display: inline-block;
        font-size: 16px !important;
        font-weight: 500;
        line-height: 1;
        -moz-user-select: none;
        border: none;
        border-radius: 45px;
        box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
        transition: all 0.3s ease 0s;
        outline: none;
        position: relative;
    }

   .btn-login:hover {
       background-color: var(--color-dark);
       box-shadow: 0px 15px 20px rgba(56, 65, 65, 0.4);
       color: #fff;
       transform: translateY(-7px);
    }

   #wrapper-message {
       display: flex;
       flex-direction: column;
       align-items: center;
       justify-content: center;
       min-height: 500px;
       gap: 20px;
   }
</style>
<div id="header">
    <div id="header-top">
        <a href="/" style="display: flex; justify-content: center; align-items: center; gap: 4px" class="logo">
            <img src="/images/avt.png" style="height: 60px; width: 60px; object-fit:cover;">
            <h3 style="font-size: 1.4rem">Table Store</h3>
        </a>
        <div class="header-top-actions">
            <a href="/">FAQ</a>
            <a href="/">Theo dõi đơn hàng</a>
            <%
                HttpServletRequest httpServletRequest = (HttpServletRequest) pageContext.getRequest();
                HttpSession session2 = httpServletRequest.getSession();
                Account account = (Account) session2.getAttribute("account");
            %>
            <% if(account!=null) {%>
            <a href="/cart/<%= account.getId() %>" class="shopping-cart" qty="0"><i class='bx bx-cart'></i></a>
            <i class='bx bx-user btn-user'>
                <div>
                    <a href="#">Tải Khoản Của Tôi</a>
                    <a href="#">Đăng xuất</a>
                </div>
            </i>
            <% } else{  %>
            <a href="/messageNotLogin" class="shopping-cart" qty="0"><i class='bx bx-cart'></i></a>
            <a href="/user/login" class="btn-login">Đăng nhập</a>
            <% }%>

        </div>
    </div>
    <div class="header-bottom" id="sticky-bar">
        <div id="header-nav">
            <a href="/home">Home</a>
            <a href="/catalog">Catalog</a>
            <a href="/">Giới thiệu</a>
            <a href="/">Liên hệ</a>
        </div>
    </div>
</div>
<div id="wrapper-message">


    <h2 style="font-size: 2rem">Bạn chưa đăng nhập !!</h2>
    <p>Nhấn vào đây để đăng nhập.</p>
    <a href="/user/login" class="btn-login">Đăng nhập</a>
    <a style="text-decoration: underline" href="/catalog">Trở về trang home</a>
</div>
<div id="message-page-footer"></div>
<script src="/js/footer.js" type="text/javascript"></script>
<script>
    includeFooter("message-page-footer")
</script>
</body>
</html>