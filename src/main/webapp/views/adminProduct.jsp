<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title> Quản lý sản phẩm</title>
    <link rel="stylesheet"  href="/css/productStyle.css">
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
                <li class="categories ">
                    <a href="categories" >
                        <i class="fa-solid fa-bars"></i>
                        <p>Thể loại</p>
                    </a>
                </li>

                <li class="products active ">
                    <a href="products" >
                        <i class="fa-solid fa-shop"></i>
                        <p>Sản phẩm</p>
                    </a>
                </li>
                <li class="users ">
                    <a href="users" >
                        <i class="fa-solid fa-user"></i>
                        <p>Người dùng</p>
                    </a>
                </li>
                <li class=order">
                    <a href="orders" >
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
                <h2>Quản lý sản phẩm</h2>
            </div>
            <div class="user--info">
                <img src="/images/products/user1_a.jpg" alt="">
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
                    <div class="filterBtn">
                        <i class="fa-solid fa-filter"></i>
                        <span>Bộ lọc</span>
                    </div>
                    <div class="search-box">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="text" placeholder="Tìm kiếm"/>
                    </div>
                </div>

                <div class="event" id="event">
                    <div class="btn addbtn" id="addBtn">
                        <i class="fa-solid fa-plus"></i>
                    </div>
                    <div class="btn importBtn">
                        <i class="fas fa-file-import"></i>
                    </div>
                   <a href="/admin/products/import-history">
                       <div class="btn historyBtn">
                           <i class="fas fa-history"></i>
                       </div>
                   </a>
                </div>
                <div class="btn deleteBtn" id="deleteBtn">
                    <i class="fa-solid fa-trash fa-beat-fade"></i>
                </div>
            </div>
            <div class="filter-modal">
                <div class="category-filter">
                    <span>Thể loại</span>
                    <input type="text" id="myComboBox" class="combobox" list="values">
                    <datalist id="values">
                        <option value="Option 1">
                        <option value="Option 2">
                        <option value="Option 3">
                        <option value="Option 4">
                    </datalist>
                </div>
                <div class="price-range">
                    <div class="min-price-input">
                        <span>Từ: </span>
                        <input type="number" value="0" min="0" name="min-price" id="minpice">
                    </div>
                    <div class="max-price-input">
                        <span>Đến: </span>
                        <input type="number" value="0" max="0" name="max-price" id="maxprice" >
                    </div>

                </div>
                <div class="status-filter">
                    Trạng thái:
                </div>
                <div class="btnFilter">
                    <div class="ResetBtn">
                        Đặt lại
                    </div>
                    <div class="filter-Btn">
                        Lọc
                    </div>
                </div>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <c:choose>
                            <c:when test="${not empty productPage.content}">

                                <th><input type="checkbox" class="check_box" ></th>
                            </c:when>
                            <c:otherwise>
                                <th><input type="hidden" ></th>
                            </c:otherwise>
                        </c:choose>
                        <th>STT</th>
                        <th>Tên sản phẩm</th>
                        <th>Màu sắc</th>
                        <th>Thể loại</th>
                        <th>Nhà cung cấp</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${productPage.content}">
                        <tr>
                            <c:choose>
                                <c:when test="${product.getDeleteAt()==null}">
                                    <td><input type="checkbox" class="sub_checkbox"></td>
                                </c:when>
                                <c:otherwise>
                                    <td><input type="hidden" class=""></td>
                                </c:otherwise>
                            </c:choose>

                            <td class="product-id">${product.getId()}</td>
                            <td class="info_product">
                                <img src="/images/products/${product.getImgPath()} " alt="" class="product-img">
                                <span class="product-name" >${product.getName()}</span >
                            </td>
                            <td class="product-color">${product.getColor()}</td>
                            <td class="product-category">${product.getCategory().getName()}</td>
                            <td class="product-provider">${product.getProvider().getName()}</td>
                            <td class="product-price"> ${product.getPrice()}</td>
                            <td class="product-inStock">${product.getInStock()}</td>
                            <td class="product-status">${product.getStatus()}</td>
                            <td><button class="edit_btn_sub">Chỉnh sửa</button>
                                <button>Nhập hàng</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${productPage.totalPages > 1}">
                    <ul>
                        <c:forEach begin="0" end="${productPage.totalPages - 1}" varStatus="page">
                            <c:set var="pageIndex" value="${page.index}"/>
                            <li class="<c:if test='${pageIndex == productPage.number}'>active</c:if>">
                                <c:url value="/admin/products" var="pageUrl">
                                    <c:param name="page" value="${pageIndex}"/>
                                    <c:param name="size" value="${productPage.size}"/>
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

<div class="products_popup" style="display: none;">
    <div class="title">
        <p>Thêm sản phẩm mới</p>
        <div class="close-btn">
            <i class="fa-solid fa-xmark"></i>
        </div>
    </div>
    <form id="productForm" enctype="multipart/form-data">
        <div class="popup-add-content">
            <div class="input input-name">
                <div>
                    <p class="info-input">Tên sản phẩm</p>
                    <input type="text" name="name" id="name">
                </div>
                <div id="name-error" class="error">

                </div>
            </div>
            <div class="input input-color">
                <div>
                    <p class="info-input">Màu sắc</p>
                    <input type="text" name="color" id="color">
                </div>
                <div id="color-error" class="error">

                </div>
            </div>
            <div class="input input-price">
                <div>
                    <p class="info-input">Giá tiền</p>
                    <input type="text" name="price" id="price">
                </div>
                <div id="price-error" class="error">

                </div>
            </div>
            <div class="input input-image">
                <div>
                    <p class="info-input">Hình ảnh</p>
                    <input type="file" name="image" id="image" accept="image/*">
                    <div id="imagePreview" class="imagePre"> </div>
                </div>
                <div id="image-error" class="error">

                </div>
            </div>
            <div class="input input-status">
                <p>Trạng thái: </p>
                <select class="input-status" name="status" id="status">
                    <option value="Hoạt động"> Hoạt động</option>
                    <option value="Không hoạt động"> Không hoạt động</option>
                </select>
            </div>
            <div class="input input-category">
                <p>Thể loại: </p>
                <select path="category" name="category" id="category">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.getId()}">
                                ${category.getName()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="input input-provider">
                <div class="input-form provider-input">
                    <p>Nhà cung cấp: </p>
                    <select path="provider" name="provider" id="provider">
                        <c:forEach var="provider" items="${providers}">
                            <option value="${provider.getId()}">
                                    ${provider.getName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="insert-submit">
                    <button type="submit" id="insert-button">Thêm sản phẩm</button>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="edit_product_popup" style="display: none;">
    <div class="title">
        <p>Chỉnh sửa sản phẩm</p>
        <div class="close-btn">
            <i class="fa-solid fa-xmark"></i>
        </div>
    </div>
    <form id="edit_product_form" enctype="multipart/form-data">
        <div class="popup-add-content">
            <div class="input input-name">
                <div>
                    <p class="info-input">Tên sản phẩm</p>
                    <input type="text" name="name" id="name-edit">
                </div>
                <div id="name-error-edit" class="error">

                </div>
            </div>
            <div class="input input-color">
                <div>
                    <p class="info-input">Màu sắc</p>
                    <input type="text" name="color" id="color-edit">
                </div>
                <div id="color-error-edit" class="error">

                </div>
            </div>
            <div class="input input-price">
                <div>
                    <p class="info-input">Giá tiền</p>
                    <input type="text" name="price" id="price-edit">
                </div>
                <div id="price-error-edit" class="error">

                </div>
            </div>
            <div class="input input-image">
                <div>
                    <p class="info-input">Hình ảnh</p>
                    <input type="file" name="image" id="image-edit" accept="image/*">
                    <div id="imagePreview-edit" class="imagePre"> </div>
                </div>
                <div id="image-error-edit" class="error">

                </div>
            </div>
            <div class="input input-status">
                <p>Trạng thái: </p>
                <select class="input-status" name="status" id="status-edit">
                    <option value="Hoạt động"> Hoạt động</option>
                    <option value="Không hoạt động"> Không hoạt động</option>
                </select>
            </div>
            <div class="input input-category">
                <p>Thể loại: </p>
                <select path="category" name="category" id="category-edit">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.getId()}">
                                ${category.getName()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="input input-provider">
                <div class="input-form provider-input">
                    <p>Nhà cung cấp: </p>
                    <select path="provider" name="provider" id="provider-edit">
                        <c:forEach var="provider" items="${providers}">
                            <option value="${provider.getId()}">
                                    ${provider.getName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="insert-submit">
                    <button type="submit" id="update-button">Chỉnh sửa</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="/js/event.js" type="text/javascript"></script>
<script src="/js/productEvent.js" type="module"></script>
<script src="/js/adminProducts/_request.js" type="module"></script>
<script src="/js/adminProducts/validation.js" type="module"></script>
<script src="/js/adminProducts/_model.js" type="module"></script>



</body>
</html>