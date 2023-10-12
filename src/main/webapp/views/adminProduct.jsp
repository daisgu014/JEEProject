<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
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
                    <div class="btn historyBtn">
                        <i class="fas fa-history"></i>
                    </div>
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
                        <th><input type="checkbox" class="check_box"></th>
                        <th>STT</th>
                        <th>Tên sản phẩm</th>
                        <th>Thể loại</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="checkbox" class="sub_checkbox"></td>
                        <td>1</td>
                        <td class="info_product"> <img src="./image/img.jpg" alt="" class="info_avt">
                            <span>Bàn công thái học</span></td></td>
                        <td>Bàn</td>
                        <td>3.400.000VNĐ</td>
                        <td>10</td>
                        <td>Hoạt động</td>
                        <td><button>Edit</button></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" class="sub_checkbox"></td>
                        <td>1</td>
                        <td class="info_product"> <img src="./image/img.jpg" alt="" class="info_avt">
                            <span>Bàn công thái học</span></td></td>
                        <td>Bàn</td>
                        <td>3.400.000VNĐ</td>
                        <td>10</td>
                        <td>Hoạt động</td>
                        <td><button>Edit</button></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" class="sub_checkbox"></td>
                        <td>1</td>
                        <td class="info_product"> <img src="./image/img.jpg" alt="" class="info_avt">
                            <span>Bàn công thái học</span></td></td>
                        <td>Bàn</td>
                        <td>3.400.000VNĐ</td>
                        <td>10</td>
                        <td>Hoạt động</td>
                        <td><button>Edit</button></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" class="sub_checkbox"></td>
                        <td>1</td>
                        <td class="info_product"> <img src="./image/img.jpg" alt="" class="info_avt">
                            <span>Bàn công thái học</span></td></td>
                        <td>Bàn</td>
                        <td>3.400.000VNĐ</td>
                        <td>10</td>
                        <td>Hoạt động</td>
                        <td><button>Edit</button></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" class="sub_checkbox"></td>
                        <td>1</td>
                        <td class="info_product"> <img src="./image/img.jpg" alt="" class="info_avt">
                            <span>Bàn công thái học</span></td></td>
                        <td>Bàn</td>
                        <td>3.400.000VNĐ</td>
                        <td>10</td>
                        <td>Hoạt động</td>
                        <td><button>Edit</button></td>
                    </tr>

                    <tr>
                        <td><input type="checkbox" class="sub_checkbox"></td>
                        <td>1</td>
                        <td class="info_product">
                            <img src="./image/img.jpg" alt="" class="info_avt">
                            <span>Bàn công thái học</span></td>
                        <td>Bàn</td>
                        <td>3.400.000VNĐ</td>
                        <td>10</td>
                        <td>Hoạt động</td>
                        <td><button>Edit</button></td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <td colspan="8">Tổng số thể loại: 2</td>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="Add-popup" style="display: none;">
    <div class="title">
        <p>Thêm thể loại mới</p>
        <div class="close-btn">
            <i class="fa-solid fa-xmark"></i>
        </div>
    </div>
    <div class="formPopup">

        <form action="<c:url value='/admin/categories/edit/' />">
            <div class="txtFiled">
                <div class="nameLabel">
                    <p>Nhập tên thể loại</p>
                </div>
                <div class="txtName">
                    <input type="text" placeholder="Nhập tên thể loại">
                </div>
            </div>
            <div class="saveBtn">
                <input type="submit" value="Cập nhật">
            </div>
        </form>



    </div>
</div>
<script src="/js/event.js" type="text/javascript"></script>
<script src="/js/productEvent.js" type="text/javascript"></script>

</body>
</html>