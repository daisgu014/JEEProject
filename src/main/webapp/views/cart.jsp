<%@ page import="com.JEEProject.TableStore.Model.Account" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <title>Table Shop</title>
    <link type="text/css" href="/css/style.css" rel="stylesheet">
    <link type="text/css" href="/css/cart.css" rel="stylesheet">
    <link type="text/css" href="/css/header.css" rel="stylesheet">
    <link type="text/css" href="/css/footer.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
<div id="cart--wrapper">
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
                <a href="/cart/<%= account.getId() %>" class="shopping-cart"><i class='bx bx-cart'></i></a>
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

    <div id="cart--slider">
        <p>Giỏ hàng</p>
        <div class="overlay"></div>
    </div>
    <div id="cart--container">
        <table>
            <thead>
                <tr>
                    <th></th>
                    <th>Sản phẩm</th>
                    <th>Màu sắc</th>
                    <th>Đơn giá</th>
                    <th>Số lượng</th>
                    <th>Tổng tiền</th>
                </tr>
            </thead>
            <tbody>
            <c:if test="${length > 0}">
                <c:forEach var="i" begin="0" end="${length}">
                    <tr>
                        <td style="padding-left: 60px"><input type="checkbox" class="sub_checkbox_category"></td>
                        <td>
                            <img src="/images/products/${products[i].getImgPath()}" style="height: 60px; width: 60px; object-fit:cover;">
                            <p>${products[i].getName()}</p>
                        </td>
                        <td>${products[i].getColor()}</td>
                        <td>
                            <fmt:setLocale value="vi_VN"/>
                            <fmt:formatNumber value="${products[i].getPrice()}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                        </td>
                        <td>${carts[i].getQty()}</td>
                        <td>
                            <fmt:formatNumber value="${carts[i].getQty() * products[i].getPrice()}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div class="cart-total">
            <p>Thành tiền</p>
            <fmt:formatNumber value="${total}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
        </div>
        <div class="cart-actions">
            <button type="submit" class="product-button deleteBtn ">Xóa khỏi giỏ hàng</button>
            <button type="submit" class="product-button">Mua hàng</button>
        </div>
    </div>
    <div id="cart-footer"></div>
    <script src="/js/footer.js" type="text/javascript"></script>
    <script>includeFooter("cart-footer");</script>
    <script src="/js/header.js" type="text/javascript"></script>
</div>
</body>
</html>