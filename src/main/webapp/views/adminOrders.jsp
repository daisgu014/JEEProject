<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title> Quản lý Đơn hàng</title>
    <link rel="stylesheet"  href="/css/productStyle.css">
    <link rel="stylesheet"  href="/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" /> 
    

</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${user.role eq 'SALE'}">
                <jsp:include page="../components/SidebarSale.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="../components/sidebar.jsp"/>
            </c:otherwise>
        </c:choose>
        <div class="main--content">
            <form class="filter-form" action="/admin/orders/search">
                <input class="h-search" name="svalue" type="text" placeholder="Nhập mã đơn hàng để tìm kiếm" name="search" id="search">
                <label for="sday">From:</label>
                <input class="h-day" type="date" id="sday" name="sday">
                <label for="eday">To:</label>
                <input class="h-day" type="date" id="eday" name="eday">
                <input class="h-btn" type="submit" value="Tìm">
            </form>
            <jsp:include page="./template/orderTable.jsp"/>
        </div>
        
    </div>
    <style>
        .h-search{
            width: max(200px,30%);
            height: 35px;
            border-radius: 8px;
        }
        .filter-form{
            padding: 5px;
            display: flex;
            flex-wrap: wrap;
        }
        .filter-form > * {
            margin: 0 5px;
        }
        .h-day{
            width: max(100px,15%);
            height: 35px;
            border-radius: 8px;
        }
        .h-btn{
            margin: 5px;
            width: 150px;
            height: 35px;
            border-radius: 5px;
            background-color: var(--color-primary);
            color: #fff;
            cursor: pointer;
        }
    </style>
</body>
</html>