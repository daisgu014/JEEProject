<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Table Shop</title>
    <link type="text/css" href="css/style.css" rel="stylesheet">
    <link type="text/css" href="css/catalogStyle.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div id="catalog--wrapper">
    <jsp:include page="../components/header.jsp"/>
    <div id="catalog--slider"></div>
    <div id="catalog--container">
        <form:form method="POST" action="/catalog" class="catalog--filter-side-bar"
                   modelAttribute="criteria">
            <div class="catalog--filter-side-bar-header">
                <i class='bx bx-filter-alt' ></i>
                <span>Bộ Lọc</span>
            </div>
            <div class="catalog--search-bar">
                <form:input type="text" placeholder="Tìm kiếm" name="searchName" path="name"/>
                <i class='bx bx-search'></i>
            </div>
            <div class="catalog--filter-side-bar-item catalog--filter-side-bar-category">
                <span>Theo danh mục</span>
                <div class="checkbox-list">
                    <c:forEach var="category" items="${categories}">
                        <div>
                            <form:checkbox id="${category.getId()}" name="categories"
                                   value="${category.getId()}" path="categoryID"/>
                            <form:label for="${category.getId()}" path="categoryID">${category.getName()}</form:label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="separating-line"></div>
            <div class="catalog--filter-side-bar-item catalog--filter-side-bar-price">
                <span>Khoảng giá</span>
                <div>
                    <form:input type="number" placeholder="Từ" name="priceFrom"
                                path="minPrice" value="${criteria.getMinPrice()}"/>
                    <div class="separating-line"></div>
                    <form:input type="number" placeholder="Đến" name="priceTo"
                                path="maxPrice" value="${criteria.getMaxPrice()}"/>
                </div>
            </div>
            <div class="separating-line"></div>
            <div class="catalog--filter-side-bar-item catalog--filter-side-bar-color">
                <span>Màu sắc</span>
                <c:forEach var="color" items="${colors}">
                    <div>
                        <form:checkbox id="${color}" name="colors" value="${color}" path="colors"/>
                        <form:label for="${color}" path="colors">${color}</form:label>
                    </div>
                </c:forEach>
            </div>
            <button type="submit" name="btnSubmit" value="submit" class="catalog--filter-side-bar-search-btn">Tìm kiếm</button>
            <button type="submit" name="btnSubmit" value="cancel" class="catalog--filter-side-bar-cancel-btn">Xóa tất cả</button>
        </form:form>
        <div id="catalog--right-container">
            <div class="catalog--sort-top-bar">
                <div class="filer-sort-button">
                    <i class='bx bx-sort-alt-2' ></i>
                    <span>Sắp xếp</span>
                    <div class="filer-sort-menu">
                        <div>Từ A đến Z</div>
                        <div>Từ Z đến A</div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${productPages.totalElements > 0}">
                    <div class="catalog--products-content">
                        <c:forEach var="product" items="${productPages.content}">
                            <div class="item">
                                <a href="productDetail/${product.getId()}">
                                    <img src="/images/products/${product.getImgPath()}"/>
                                </a>
                                <div class="item-content">
                                    <a href="productDetail/${product.getId()}" class="item--name">${product.getName()}</a>
                                    <a href="productDetail/${product.getId()}" class="item--price">
<%--                                            ${product.getPrice()}₫--%>
                                            <fmt:setLocale value="vi_VN"/>
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
                </c:when>
                <c:otherwise>
                    <!-- Hiển thị thông báo khi 'end' < 0 -->
                    <p class="catalog--product-notfound">
                        <i class='bx bx-sad' style="font-size: 8rem"></i> Không tìm thấy sản phẩm phù hợp.
                    </p>
                </c:otherwise>
            </c:choose>
            <div class="catalog--paging">
                <c:if test="${productPages.totalPages > 1}">
                    <c:forEach begin="0" end="${productPages.totalPages - 1}" varStatus="page">
                        <a href="/catalog?page=${page.index}"
                           class="<c:if test='${page.index == productPages.number}'>active</c:if>">
                                ${page.index + 1}
                        </a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="../components/footer.jsp"/>
</div>
</body>
</html>