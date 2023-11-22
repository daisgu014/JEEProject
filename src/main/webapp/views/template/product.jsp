<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="catalog--products-content">
        <c:forEach var="product" items="${products}">
            <div class="item">
                <a href="productDetail/${product.getId()}">
                    <img src="/images/products/${product.getImgPath()}" />
                </a>
                <div class="item-content">
                    <a href="productDetail/${product.getId()}" class="item--name">${product.getName()}</a>
                    <a href="productDetail/${product.getId()}" class="item--price">
                        <fmt:setLocale value="vi_VN" />
                        <fmt:formatNumber value="${product.getPrice()}" type="currency" currencyCode="VND"
                            maxFractionDigits="0" />
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

    <style>
    .catalog--products-content{
        display: flex;
        flex-wrap: nowrap;
        overflow-x: scroll;
    }
    </style>