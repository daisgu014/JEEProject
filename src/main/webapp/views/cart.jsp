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
    <jsp:include page="/header" />

    <div id="cart--slider">
        <p>Giỏ hàng</p>
        <div class="overlay"></div>
    </div>
    <div id="cart--container">
        <table>
            <thead>
                <tr>
                    <th></th>
                    <th>Id sản phẩm</th>
                    <th>Sản phẩm</th>
                    <th>Màu sắc</th>
                    <th>Đơn giá</th>
                    <th>Số lượng</th>
                    <th>Tổng tiền</th>
                </tr>
            </thead>
            <tbody>
            <c:if test="${length > 0}">
                <c:forEach var="i" begin="0" end="${length-1}">
                    <tr>
                        <td style="padding-left: 60px"><input type="checkbox" class="sub_checkbox_cart"></td>
                        <td class="product-id">${products[i].getId()}</td>
                        <td>
                            <img src="/images/products/${products[i].getImgPath()}" style="height: 60px; width: 60px; object-fit:cover;">
                            <p>${products[i].getName()}</p>
                        </td>
                        <td>${products[i].getColor()}</td>
                        <td>
                            <fmt:setLocale value="vi_VN"/>
                            <fmt:formatNumber value="${products[i].getPrice()}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                        </td>
                        <td class="cart-qty">${carts[i].getQty()}</td>
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
            <button type="submit" class="product-button paymentBtn">Mua hàng</button>
        </div>
    </div>
    <jsp:include page="/footer" />
</div>
<script src="/js/Cart/cart.js" type="text/javascript"></script>
</body>
</html>