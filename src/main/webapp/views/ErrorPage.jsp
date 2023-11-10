<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Không tìm thấy trang </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>


<body>
<div class="d-flex align-items-center justify-content-center vh-100">
    <div class="text-center">
        <h1 class="display-1 fw-bold">404</h1>
        <p class="fs-3"> <span class="text-danger">Opps!</span> Trang không tìm thấy.</p>
        <p class="lead">
            Trang của bạn tìm kiếm không tìm thấy.
        </p>
        <a href="/login" class="btn btn-primary">Đăng nhập Admin</a>
        <a href="/catalog" class="btn btn-primary">Trở về Trang sản phẩm</a>
    </div>
</div>
</body>


</html>