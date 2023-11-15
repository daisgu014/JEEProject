<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/importHistory.css">
    <link rel="stylesheet" href="/css/productStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Lịch sử nhập hàng</title>
</head>
<body>
<div class="container">
    <div class="main--content">
        <div class="header-wrapper">
            <div class="header--title">
                <span>Riêng tư</span>
                <h2>Nhập ký nhập sản phẩm</h2>
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
        <div class="pageBefore">
            <a href="/admin/products">Quản lý sản phẩm </a>
            <i class="fa-solid fa-right-long"></i>
            <a href="">Nhập ký nhập sản phẩm</a>
        </div>
        <div class="tabular--wrapper">
            <div class="title-table">
                <div class="filter">
                    <div class="search-box">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="text" placeholder="Tìm kiếm"/>
                    </div>
                </div>

            </div>
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên sản phẩm</th>
                        <th>Thể loại</th>
                        <th>Màu sắc</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Người nhập</th>
                        <th>Ngày</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty imports}">
                        <c:forEach var="item" items="${imports.content}">
                            <tr>
                                <td>${item.getId()}</td>
                                <td class="info_product">
                                    <span>${item.getProductId().getName()}</span></td>
                                <td>${item.getProductId().getCategory().getName()}</td>
                                <td>${item.getProductId().getColor()}</td>
                                <td>${item.getProductId().getPrice()}VNĐ</td>
                                <td style="color: var(--color-success); font-weight: 600;">${item.getQty()}</td>
                                <td>${item.getImportHistory().getUser().getFull_name()} </td>
                                <td>${item.getImportHistory().getTimeImport()}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                    <tfoot>
                    </tfoot>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${imports.totalPages > 1}">
                    <ul>
                        <c:forEach begin="0" end="${imports.totalPages - 1}" varStatus="page">
                            <c:set var="pageIndex" value="${page.index}"/>
                            <li class="<c:if test='${pageIndex == imports.number}'>active</c:if>">
                                <c:url value="/admin/products/import-history" var="pageUrl">
                                    <c:param name="page" value="${pageIndex}"/>
                                    <c:param name="size" value="${imports.size}"/>
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
<script src="./event.js"></script>
<script src="./pageBefore.js"></script>
</body>
</html>