<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link type="text/css" href="/css/header.css" rel="stylesheet">
<div id="header">
    <div id="header-top">
        <a href="/" style="display: flex; justify-content: center; align-items: center; gap: 4px" class="logo">
            <img src="/images/avt.png" style="height: 60px; width: 60px; object-fit:cover;">
            <h3 style="font-size: 1.4rem">Table Store</h3>
        </a>
        <div class="header-top-actions">
            <a href="/">FAQ</a>
            <a href="/">Theo dõi đơn hàng</a>
            <a href="/" class="shopping-cart" qty="1"><i class='bx bx-cart'></i></a>
            <a href="/user/login" class="btn-login">Đăng nhập</a>
            <a href="/" class="btn-user" style="display: none"><i class='bx bx-user'></i></a>
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
<script>
    window.onscroll = function() {stick()};

    let header = document.getElementById("sticky-bar");
    let sticky = header.offsetTop;

    function stick() {
        if (window.pageYOffset > sticky) {
            header.classList.add("sticky");
        } else {
            header.classList.remove("sticky");
        }
    }
</script>