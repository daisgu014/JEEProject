<%@ page import="com.JEEProject.TableStore.Model.Account" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Spring MVC</title>
    <link type="text/css" href="/css/style.css" rel="stylesheet">
    <link type="text/css" href="/css/productDetailStyle.css" rel="stylesheet">
    <link type="text/css" href="/css/header.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
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

    <div id="wrapper">
        <div class="product-detail-breadcrumb">
            <a href="/catalog/all" class="breadcrumb">
                <i class='bx bx-shopping-bag' ></i>
                Trang Catalog
            </a>
            <i class='bx bx-chevron-right' ></i>
            <a href="/catalog/category/${category.getId()}" class="breadcrumb">${category.getName()}</a>
            <i class='bx bx-chevron-right' ></i>
            <p style="display: inline">${product.getName()}</p>
        </div>
        <div id="product-detail-wrapper">
            <div><img src="/images/products/${product.getImgPath()}" alt="" class="product-detail-img"></div>
            <div id="product-detail-content">
                <h1 class="product-title">${product.getName()}</h1>
                <div class="separating-line"></div>
                <h2 class="product-price">
                    <fmt:formatNumber value="${product.getPrice()}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                </h2>
                <div class="separating-line"></div>
                <div class="product-info">
                    <h4>Thông tin chung</h4>
                    <p>
                        Bảo hành: 36 tháng <br>
                        Tình trạng: ${product.getStatus()} <br>
                        Màu sắc: ${product.getColor()}
                    </p>
                </div>
                <div class="separating-line"></div>
                <div class="product-detail-quantity-stock">
                    <div class="product-input-quantity">
                        <i class='bx bx-minus' id="btnMinus"></i>
                        <input type="number" disabled value="1" min="1" max="10" id="quantityInput" name="quantityInput">
                        <i class='bx bx-plus' id="btnPlus"></i>
                    </div>
                    <p>Còn ${product.getInStock()} sản phẩm</p>
                </div>
                <div class="product-detail-buttons">
                    <form action="../cart/add" method="post" id="addToCartForm">
                        <input name="productID" hidden="hidden" value="${product.getId()}">
                        <input name="quantityInput" id="quantityInput2" hidden="hidden">
                        <button type="submit" class="product-add-to-cart-button">
                            <i class='bx bx-cart-add'></i>
                            Thêm vào giỏ hàng
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <h2 class="product-detail-title-related">Các sản phẩm liên quan</h2>
        <div class="catalog--products-content">
            <c:forEach var="product" items="${products}">
                <div class="item">
                    <a href="../productDetail/${product.getId()}">
                        <img src="/images/products/${product.getImgPath()}"/>
                    </a>
                    <div class="item-content">
                        <a href="../productDetail/${product.getId()}" class="item--name">${product.getName()}</a>
                        <a href="../productDetail/${product.getId()}" class="item--price">
                            <fmt:formatNumber value="${product.getPrice()}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                        </a>
                        <form action="/cart/add" method="post">
                            <input hidden="hidden" name="productID" value="${product.getId()}">
                            <button type="submit" class="catalog--cart-button">
                                <i class='bx bx-cart-add'></i>
                                <span>Thêm vào giỏ hàng</span>
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div id="product-detail-footer"></div>
    <script src="/js/footer.js" type="text/javascript"></script>
    <script>includeFooter("product-detail-footer")</script>

    <script>
        const quantityInput = document.getElementById("quantityInput");
        const addToCartForm = document.getElementById("addToCartForm");
        document.getElementById("btnMinus").addEventListener("click", () => {
            if(quantityInput.value > 1) {
                quantityInput.value = (parseInt(quantityInput.value) - 1).toString();
            }
        });
        document.getElementById("btnPlus").addEventListener("click", () => {
            if(quantityInput.value < 10) {
                quantityInput.value = (parseInt(quantityInput.value) + 1).toString();
            }
        });
        addToCartForm.addEventListener("submit", () => {
            document.getElementById("quantityInput2").value = quantityInput.value;
        });

    </script>
</body>
</html>