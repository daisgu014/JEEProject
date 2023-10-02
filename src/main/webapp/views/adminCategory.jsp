<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%--<html lang="en">--%>

<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Dashboard</title>--%>
<%--    <link rel="stylesheet" href="../css/style.css">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"--%>
<%--          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="--%>
<%--          crossorigin="anonymous" referrerpolicy="no-referrer" />--%>
<%--</head>--%>

<%--<body>--%>
<%--<table>--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>No.</th>--%>
<%--        <th>Tên thể loại</th>--%>
<%--        <th>Trạng thái</th>--%>
<%--        <th>Hành động</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>

<%--        <c:forEach var="category" items="${categories}">--%>
<%--            <tr>--%>
<%--            <td>${category.getId()}</td>--%>
<%--            <td>${category.getName()}</td>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty category.getDeleteAt() }">--%>
<%--                        <td>Hết hàng</td>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <td>Còn hàng</td>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>

<%--            --%>
<%--            <td><a href="<c:url value='/admin/categories/${category.getId()}' />">Delete</a>--%>
<%--                <a href="<c:url value='/admin/categories/edit/${category.getId()}' />">Edit</a>--%>
<%--            </td>--%>
<%--    </tr>--%>
<%--        </c:forEach>--%>

<%--    </tbody>--%>
<%--    <tfoot>--%>
<%--    <td colspan="4">Tổng số thể loại: 2</td>--%>
<%--    </tfoot>--%>
<%--</table>--%>
<%--<h2>Create Category</h2>--%>
<%--<form action="<c:url value='/admin/categories' />" method="post" modelAttribute="category">--%>
<%--    <label for="name">Name:</label>--%>
<%--    <input type="text" id="name" name="name" required><br>--%>

<%--    <input type="submit" value="Create">--%>
<%--</form>--%>
<%--<h1>Edit Category</h1>--%>
<%--<form action="<c:url value='/admin/categories/edit/${category.getId()}' />" method="post">--%>
<%--    <label for="name">Category Name:</label>--%>
<%--    <input type="text" id="updateName" name="name" value="${category.name}" required>--%>
<%--    <br>--%>
<%--    <input type="submit" value="Update">--%>
<%--</form>--%>
<%--<br>--%>
<%--</body>--%>

<%--</html>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Categories</title>
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
<ul>
    <c:forEach var="category" items="${categories}">
        <li>
            <span>${category.getName()}</span>

            <!-- Form để cập nhật danh mục -->
            <form action="<c:url value='/admin/categories/edit/' />${category.getId()}" method="post" style="display: block;">
                <input type="text" name="editedCategoryName" value="${category.getName()}" required>
                <input type="submit" value="Update">
            </form>
            <button onclick="toggleEditForm(${category.getId()})">Edit</button>

            <!-- Form để xóa danh mục -->
            <form action="<c:url value='/admin/categories/delete/' />${category.getId()}" method="post" style="display: block;">
                <input type="submit" value="Delete">
            </form>
            <button onclick="confirmDelete(${category.getId()})">Delete</button>
        </li>
    </c:forEach>
</ul>

<script>
    function toggleEditForm(categoriid) {
        const editForm = document.querySelector(`form[action='/admin/categories/edit/categoriid']`);
        editForm.style.display = editForm.style.display === "none" ? "block" : "none";
    }

    function confirmDelete() {
        if (confirm("Are you sure you want to delete this category?")) {
            const deleteForm = document.querySelector(`form[action='/admin/categories/delete/categoriid']`);
            deleteForm.submit();
        }
    }
</script>
</body>
</html>