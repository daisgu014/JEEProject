<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Spring MVC</title>
    <link type="text/css" href="../css/style.css" rel="stylesheet">
    <link type="text/css" href="../css/productDetailStyle.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
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
                <h2 class="product-price">${product.getPrice()}₫</h2>
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
                        <input type="number" value="1" min="1" max="10" id="quantityInput">
                        <i class='bx bx-plus' id="btnPlus"></i>
                    </div>
                    <p>Còn ${product.getInStock()} sản phẩm</p>
                </div>
                <div class="product-detail-buttons">
                    <a href="" class="product-buy-button">Mua hàng</a>
                    <a href="" class="product-add-to-cart-button">
                        <i class='bx bx-cart-add'></i>
                        Thêm vào giỏ hàng
                    </a>
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
                            <a href="../productDetail/${product.getId()}" class="item--price">${product.getPrice()}₫</a>
                            <a href="/huyen" class="catalog--cart-button">
                                <i class='bx bx-cart-add'></i>
                                <span>Thêm vào giỏ hàng</span>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
    </div>
    <script>
        document.getElementById("btnMinus").addEventListener("click", () => {
            const quantityInput = document.getElementById("quantityInput");
            if(quantityInput.value > 1) {
                quantityInput.value = (parseInt(quantityInput.value) - 1).toString();
            }
        });
        document.getElementById("btnPlus").addEventListener("click", () => {
            const quantityInput = document.getElementById("quantityInput");
            if(quantityInput.value < 10) {
                quantityInput.value = (parseInt(quantityInput.value) + 1).toString();
            }
        })
    </script>
</body>
</html>