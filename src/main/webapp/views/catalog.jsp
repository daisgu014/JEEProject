<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Spring MVC</title>
    <link type="text/css" href="css/style.css" rel="stylesheet">
    <link type="text/css" href="css/CatalogStyle.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div id="catalog--wrapper">
<%--    <div id="catalog--header">--%>
<%--        <div class="logo">LOGO IMAGE</div>--%>
<%--        <div class="catalog--search-bar">--%>
<%--            <input type="text" placeholder="Tìm kiếm">--%>
<%--            <i class='bx bx-search'></i>--%>
<%--        </div>--%>
<%--        <div class="catalog--menu-right">--%>
<%--            <i class='bx bx-cart'></i>--%>
<%--            <i class='bx bx-user' ></i>--%>
<%--            <div class="text-login">Đăng nhập</div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div id="catalog--slider">
        <%--        <img src="/WEB-INF/imgs/banner.png"/>--%>
    </div>
    <div id="catalog--container">
        <div class="catalog--filter-side-bar">
            <div class="catalog--filter-side-bar-header">
                <i class='bx bx-filter-alt' ></i>
                <span>Bộ Lọc</span>
            </div>
            <div class="catalog--search-bar">
                <input type="text" placeholder="Tìm kiếm">
                <i class='bx bx-search'></i>
            </div>
            <div class="catalog--filter-side-bar-item catalog--filter-side-bar-category">
                <span>Theo danh mục</span>
                <div class="checkbox-list">
                    <div>
                        <input type="checkbox" id="ktable" name="category" value="bàn chữ K">
                        <label for="ktable">Bàn chữ K</label>
                    </div>
                    <div>
                        <input type="checkbox" id="gamingchair" name="category" value="ghế gaming">
                        <label for="gamingchair">Ghế gaming</label>
                    </div>
                </div>
            </div>
            <div class="separating-line"></div>
            <div class="catalog--filter-side-bar-item catalog--filter-side-bar-brand">
                <span>Thương hiệu</span>
                <div class="checkbox-list">
                    <div>
                        <input type="checkbox" id="k" name="brand" value="bàn chữ K">
                        <label for="k">Bàn chữ K</label>
                    </div>
                    <div>
                        <input type="checkbox" id="i" name="brand" value="ghế gaming">
                        <label for="i">Ghế gaming</label>
                    </div>
                </div>
            </div>
            <div class="separating-line"></div>
            <div class="catalog--filter-side-bar-item catalog--filter-side-bar-price">
                <span>Khoảng giá</span>
                <div>
                    <input type="number" placeholder="Từ">
                    <div class="separating-line"></div>
                    <input type="number" placeholder="Đến">
                </div>
            </div>
            <div class="separating-line"></div>
            <div class="catalog--filter-side-bar-item catalog--filter-side-bar-color">
                <span>Màu sắc</span>
                <div>
                    <input type="checkbox" id="black" name="color" value="đen">
                    <label for="black">Đen</label>
                </div>
                <div>
                    <input type="checkbox" id="gray" name="color" value="xám">
                    <label for="gray">Xám tro</label>
                </div>
            </div>
            <div class="catalog--filter-side-bar-search-btn">
                Tìm kiếm
            </div>
        </div>
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
            <div class="catalog--products-content">
                <div class="item">
                    <a href=""><img src="/images/product1.png"/></a>
                    <a href="" class="item--name">Ghế công thái học Sihoo M57-GRAY</a>
                    <a href="" class="item--price">3.990.000₫</a>
                    <div class="catalog--cart-button">
                        <i class='bx bx-cart-add'></i>
                        <a href="/product/">Thêm vào giỏ hàng</a>
                    </div>
                </div>
                <div class="item">
                    <img src="/images/product1.png"/>
                    <span class="item--name">Ghế công thái học Sihoo M57-GRAY</span>
                    <span class="item--price">3.990.000₫</span>
                    <div class="catalog--cart-button">
                        <i class='bx bx-cart-add'></i>
                        <a href="/product/">Thêm vào giỏ hàng</a>
                    </div>
                </div>
                <div class="item">
                    <img src="/images/product1.png"/>
                    <span class="item--name">Ghế công thái học Sihoo M57-GRAY</span>
                    <span class="item--price">3.990.000₫</span>
                    <div class="catalog--cart-button">
                        <i class='bx bx-cart-add'></i>
                        <a href="/product/">Thêm vào giỏ hàng</a>
                    </div>
                </div>
                <div class="item">
                    <img src="/images/product1.png"/>
                    <span class="item--name">Ghế công thái học Sihoo M57-GRAY</span>
                    <span class="item--price">3.990.000₫</span>
                    <div class="catalog--cart-button">
                        <i class='bx bx-cart-add'></i>
                        <a href="/product/">Thêm vào giỏ hàng</a>
                    </div>
                </div>
                <div class="item">
                    <img src="/images/product1.png"/>
                    <span class="item--name">Ghế công thái học Sihoo M57-GRAY</span>
                    <span class="item--price">3.990.000₫</span>
                    <div class="catalog--cart-button">
                        <i class='bx bx-cart-add'></i>
                        <a href="/product/">Thêm vào giỏ hàng</a>
                    </div>
                </div>
            </div>
            <div class="catalog--paging"></div>
        </div>
    </div>
</div>
</body>
</html>