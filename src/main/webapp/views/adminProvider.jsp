<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Quản lý Nhà cung cấp</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }

        table, th, td {
            border: 1px solid black;
            text-align: left;
        }

        th, td {
            padding: 8px;
        }

        form {
            width: 80%;
            margin: 20px auto;
        }

        label, input {
            display: block;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container" >
    <jsp:include page="../components/sidebar.jsp"/>
    <div style="border: red solid 1px; width: 100%; height: 100%">
        <!-- Bảng để hiển thị danh sách nhà cung cấp -->
        <h2 style="border-bottom: red solid 1px; width: 100%; height: 20%">Thêm Nhà cung cấp</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên Nhà cung cấp</th>
                <th>Ngày Tạo</th>
                <th>Ngày Xóa</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <!-- Dữ liệu nhà cung cấp sẽ được thêm vào đây -->
            <tr>
                <td>1  </td>
                <td>Công ty ABC</td>
                <td>2023-10-04 10:00:00</td>
                <td></td>
                <td>
                    <button>Edit</button>
                    <button>Delete</button>
                </td>
            </tr>

            </tbody>
        </table>

        <!-- Biểu mẫu để thêm hoặc chỉnh sửa Nhà cung cấp -->
        <form >
            <label style="display: flex; justify-content: center" for="name">Tên Nhà cung cấp:</label>
            <input style="border-bottom: black solid 1px; display: flex; justify-content: center" type="text" id="name" name="name" required><br>
            <input type="hidden" id="id" name="id"><!-- Field này dùng để lưu id khi chỉnh sửa -->
            <input style="display: flex; justify-content: center" type="submit" value="Lưu Nhà cung cấp">
        </form>

    </div>
</div>
</body>
</html>
