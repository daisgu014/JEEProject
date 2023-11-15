<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="left">
    <div class="logo">
        <a href="#" style="    display: flex;
    justify-content: center;">
            <img src="/images/avt.png" style="height: 100px; width: 100px; object-fit:cover;">
        </a>
    </div>
    <div class="sidebar">
        <ul class="menu">
            <li class="orders">
                <a href="/admin/orders" >
                    <i class="fas fa-shopping-cart"></i>
                    <p>Đơn hàng</p>
                </a>
            </li>
            <li>
                <a href="/api/v1/auth/logout">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i>
                    <p>Đăng xuất</p>
                </a>
            </li>
        </ul>
    </div>
</div>

<script>
    document.querySelector(".active").classList.remove("active");
    document.querySelector("."+window.location.href.split("/")[4]).classList.add("active");
</script>