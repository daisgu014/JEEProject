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
<h2>Create Category</h2>
<form action="<c:url value='/admin/categories/create' />" method="post" modelAttribute="category">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
        <input type="submit" value="Create">
    </form>

<!-- Danh sách danh mục hiện tại -->
<table>
    <thead>
    <tr>
        <th>No.</th>
        <th>Tên thể loại</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    </thead>

    <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                <td>${category.getId()}</td>
                <td>${category.getName()}</td>
                    <c:choose>
                        <c:when test="${not empty category.getDeleteAt() }">
                            <td style="color: #FF0060">Hết hàng</td>
                        </c:when>
                        <c:otherwise>
                            <td style="color: #1B9C85">Còn hàng</td>
                        </c:otherwise>
                    </c:choose>


                <td>    <button onclick="toggleEditForm(${category.getId()} , '${category.getName()}','${category.getDeleteAt()}' )" >Edit</button>
                        <button onclick="confirmDelete(${category.getId()})">Delete</button>
                </td>
        </tr>
            </c:forEach>

    </tbody>
    <tfoot>
    <td colspan="4">Tổng số thể loại: 2</td>
    </tfoot>
</table>
<div id="form"></div>
</body>
<script type="text/javascript">
    function toggleEditForm(categoryId,categoryName,DeleteAt){
        document.querySelector('#form').innerHTML=`
            <form action="<c:url value='/admin/categories/edit/' />`+categoryId+`" method="post" style="display: block;">
                    <input type="text" name="editedCategoryName" value="`+categoryName+`" required>
                    <input type="text" name="ediStatus" value="`+DeleteAt+`" required>
                    <input type="submit" value="Update">
                </form>
    `;
    }
</script>
</html>