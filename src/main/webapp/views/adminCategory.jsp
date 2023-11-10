<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title> Quản lý thể loại</title>
    <link rel="stylesheet"  href="/css/productStyle.css">
    <link rel="stylesheet"  href="/css/categoryAdmin.css">
    <link rel="stylesheet"  href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
<div id="overlay"></div>
<div class="container" >
    <div class="left">
        <div class="logo">
            <a href="#" style="    display: flex;
    justify-content: center;">
                <img src="/images/avt.png" style="height: 100px; width: 100px; object-fit:cover;">
            </a>
        </div>
        <div class="sidebar">

            <ul class="menu">
                <li >
                    <a href="#">
                        <i class="fa-sharp fa-solid fa-gauge"></i>
                        <p>Quản lý</p>
                    </a>
                </li>
                <li class="categories active">
                    <a href="/admin/categories" >
                        <i class="fa-solid fa-bars"></i>
                        <p>Thể loại</p>
                    </a>
                </li>

                <li class="products  ">
                    <a href="/admin/products" >
                        <i class="fa-solid fa-shop"></i>
                        <p>Sản phẩm</p>
                    </a>
                </li>
                <li class="users ">
                    <a href="/admin/accoun  t" >
                        <i class="fa-solid fa-user"></i>
                        <p>Người dùng</p>
                    </a>
                </li>
                <li class=order">
                    <a href="/admin/orders" >
                        <i class="fas fa-shopping-cart"></i>
                        <p>Đơn hàng</p>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa-solid fa-arrow-right-from-bracket"></i>
                        <p>Đăng xuất</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main--content">
        <div class="header-wrapper">
            <div class="header--title">
                <span>Riêng tư</span>
                <h2>Quản lý thể loại</h2>
            </div>
            <div class="user--info">
                <img src="./image/img.jpg" alt="">
            </div>
            <div class="user-modal" id="user-modal">
                <ul>
                    <l1 class="user_sub">
                        <a class="user-sub-menu">
                            <div>
                                <i class="fa-solid fa-user"></i>
                            </div>
                            <span>Hồ sơ</span>
                        </a>
                    </l1>
                    <l1 class="user_sub">
                        <a class="user-sub-menu">
                            <div>
                                <i class="fa-solid fa-cart-shopping"></i>
                            </div>
                            <span>Đơn hàng</span>
                        </a>
                    </l1>
                    <l1 class="user_sub">
                        <a class="user-sub-menu">
                            <div>
                                <i class="fa-solid fa-right-from-bracket"></i>
                            </div>
                            <span>Đăng xuất</span>
                        </a>
                    </l1>
                </ul>
            </div>

        </div>
        <div class="tabular--wrapper">
            <div class="title-table">
                <div class="filter">
<%--                    <div class="filterBtn">--%>
<%--                        <i class="fa-solid fa-filter"></i>--%>
<%--                        <span>Bộ lọc</span>--%>
<%--                    </div>--%>
                </div>

                <div class="event" id="event">
                    <div class="btn addbtn" id="addBtnCategory">
                        <i class="fa-solid fa-plus"></i>
                    </div>
                </div>
                <div class="btn deleteBtn" id="deleteBtn">
                    <i class="fa-solid fa-trash fa-beat-fade"></i>
                </div>

            </div>
            <form id="product--filter" style="width: 100%" action="" method="get">
                <div class="filter-modal">
                    <div class=filter-modal-content>
                        <div class="search-container" style="margin-left: 30px;" >
                            <div class="search-box" style="width: 200px">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input type="text" placeholder="Tìm kiếm" name="nameFilter"/>
                            </div>
                        </div>
                        </div>
                        <div class="btnFilter" style="display: flex; align-items: center; gap: 50px" >
                            <div class="ResetBtn" >
                                Đặt lại
                            </div>
                            <button type="submit" id="filter-submit" style="margin-top: 0px">Lọc</button>
                        </div>
                    </div>

            </form>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <c:choose>
                            <c:when test="${not empty categoryPage.content}">

                                <th><input type="checkbox" class="check_box_category" ></th>
                            </c:when>
                            <c:otherwise>
                                <th><input type="hidden" ></th>
                            </c:otherwise>
                        </c:choose>
                        <th>Mã thể loại</th>
                        <th>Tên thể loại </th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="category" items="${categoryPage.content}">
                        <tr>
                            <c:choose>
                                <c:when test="${category.getDeleteAt()==null}">
                                    <td><input type="checkbox" class="sub_checkbox_category"></td>
                                </c:when>
                                <c:otherwise>
                                    <td><input type="hidden" class=""></td>
                                </c:otherwise>
                            </c:choose>

                            <td class="category-id">${category.getId()}</td>
                            <td>
                                <span class="category-name">${category.getName()}</span></td>
                            <td><button class="edit_btn_sub">Chỉnh sửa</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${categoryPage.totalPages > 1}">
                    <ul>
                        <c:forEach begin="0" end="${categoryPage.totalPages - 1}" varStatus="page">
                            <c:set var="pageIndex" value="${page.index}"/>
                            <li class="<c:if test='${pageIndex == categoryPage.number}'>active</c:if>">
                                <c:url value="/admin/categories" var="pageUrl">
                                    <c:param name="page" value="${pageIndex}"/>
                                    <c:param name="size" value="${categoryPage.size}"/>
                                </c:url>
                                <a href="${pageUrl}">${pageIndex + 1}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>
    </div>
</div>

<div class="products_popup" id="category_popup" style="display: none;">
    <div class="title">
        <p>Thêm thể loại mói</p>
        <div class="close-btn">
            <i class="fa-solid fa-xmark"></i>
        </div>
    </div>
    <form id="categoryAddForm" enctype="multipart/form-data">
        <div class="popup-add-content">
            <div class="input input-name">
                <div>
                    <p class="info-category">Tên thể loại</p>
                    <input type="text" name="name" id="name">
                </div>
                <div id="name-error" class="error">

                </div>
            </div>
            <div class="insert-submit">
                <button type="button" id="insert-button">Thêm thể loại</button>
            </div>
        </div>
    </form>
</div>
<div class="edit-category-form" style="display: none">
    <div class="title-edit">
        <p>Chỉnh sửa thể loại</p>
        <div class="close-btn-edit btn" id="close-btn-edit">
            <i class="fa-solid fa-xmark"></i>
        </div>
        <div class="popup-edit-content">
            <div class="input input-name-edit">

                <div>
                    <p class="info-category">Tên thể loại</p>
                    <input type="text" name="name" id="name-edit">
                </div>
                <div id="name-error-edit" class="error">
                </div>
            </div>
            <div class="insert-submit" id="update-button" >
                <button type="button" id="update">Cập nhật</button>
            </div>
        </div>
    </div>
</div>
<script src="/js/CategoryEvent.js" type="module"></script>
<script src="/js/event.js" type="text/javascript"></script>
</body>
</html>