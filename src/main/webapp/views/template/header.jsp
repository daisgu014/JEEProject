<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <div id="header">
                <div id="header-top">
                    <a href="/" style="display: flex; justify-content: center; align-items: center; gap: 4px"
                        class="logo">
                        <img src="/images/avt.png" style="height: 60px; width: 60px; object-fit:cover;">
                        <h3 style="font-size: 1.4rem">Table Store</h3>
                    </a>
                    <div class="header-top-actions">
                        <c:choose>
                            <c:when test="${sessionScope.account != null}">
                                <a href="/user/purchased">Theo dõi đơn hàng</a>

                                <a href="/cart" class="shopping-cart" qty="${cartSize}">
                                    <i class='bx bx-cart'></i>
                                </a>
                                <i class='bx bx-user btn-user'>
                                    <div>
                                        <a href="/user/profile">Tải Khoản Của Tôi</a>
                                        <a href="/user/logout">Đăng xuất</a>
                                    </div>
                                </i>
                                <br />
                            </c:when>
                            <c:otherwise>
                                <a href="/user/login" class="btn-login">Đăng nhập</a>
                                <br />
                            </c:otherwise>
                        </c:choose>




                    </div>
                </div>
                <div class="header-bottom" id="sticky-bar">
                    <div id="header-nav">
                        <a href="/home">Home</a>
                        <a href="/catalog">Catalog</a>
                        <a href="/about">Giới thiệu</a>
                    </div>
                </div>
            </div>
            <script src="/js/header.js" type="text/javascript"></script>