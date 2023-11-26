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
        <jsp:include page="../components/sidebar.jsp"/>
    <div class="main--content">
        <jsp:include page="/headerAdmin">
            <jsp:param name="title" value="Quản lý thể loại"/>
        </jsp:include>
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
            <form id="category--filter" style="width: 100%" action="/admin/categories/search" method="get">
                <div class="filter-modal">
                    <div class=filter-modal-content>
                        <div class="search-container" style="margin-left: 30px;" >
                            <div class="search-box" style="width: 200px">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input type="text" placeholder="Tìm kiếm" name="nameFilter" id="nameFilterCategory"/>
                            </div>
                        </div>
                        </div>
                        <div class="btnFilter" style="display: flex; align-items: center; gap: 50px" >
                          <a href="/admin/categories" style="text-decoration: none">
                              <div class="ResetBtn" >
                                  Đặt lại
                              </div>
                          </a>
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
        <p>Thêm thể loại mới</p>
        <div class="close-btn">
            <i class="fa-solid fa-xmark"></i>
        </div>
    </div>
    <form id="categoryAddForm" enctype="multipart/form-data">
        <div class="popup-add-content">
            <div class="input input-name">
                <div class="input-category">
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
    <div class="title">
        <p>Chỉnh sửa thể loại</p>
        <div class="close-btn" id="close-btn-edit">
            <i class="fa-solid fa-xmark"></i>
        </div>
        <div class="popup-edit-content">
            <div class="input input-name">
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
</body>
</html>