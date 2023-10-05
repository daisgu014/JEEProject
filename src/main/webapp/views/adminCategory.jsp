<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<h1>Categories</h1>
<%--<h2>Create Category</h2>--%>
<%--<form action="<c:url value='/admin/categories/create' />" method="post" modelAttribute="category">--%>
<%--    <label for="name">Name:</label>--%>
<%--    <input type="text" id="name" name="name" required><br>--%>
<%--        <input type="submit" value="Create">--%>
<%--    </form>--%>
<div id="overlay"></div>
<div class="container" >
    <jsp:include page="../components/sidebar.jsp"/>
    <jsp:include page="../components/Admin/CategoriesContent.jsp">
        <jsp:param name="categpries" value="${categories}"/>
    </jsp:include>

</div>
<div class="Add-popup" style="display: none;">
    <div class="title">
        <p>Thêm thể loại mới</p>
        <div class="close-btn">
            <i class="fa-solid fa-xmark"></i>
        </div>
    </div>
    <div class="formPopup">
        <form action="<c:url value='/admin/categories/create' />" method="post" modelAttribute="category">

            <div class="txtFiled">
                <label for="name">Name:</label>
                <div class="txtName">
                <input type="text" id="name" name="name" required></div>
            </div>
            <div class="saveBtn">
                <input type="submit" value="Create">
            </div>

        </form>
    </div>
</div>
<script type="text/javascript" src="/js/event.js"></script>
</body>


</html>