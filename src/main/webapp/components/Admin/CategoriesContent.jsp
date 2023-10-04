<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="main--content">
    <div class="header-wrapper">
        <div class="header--title">
            <span>Riêng tư</span>
            <h2>Quản lý</h2>
        </div>
        <div class="user--info">
            <div class="search-box">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Search"/>
            </div>
            <img src="./image/img.jpg" alt="">
        </div>

    </div>
    <div class="tabular--wrapper">
        <div class="title-table">
            <h3 class="main-title">Thể loại</h3>
            <div class="addbtn" id="addBtn">
                <i class="fa-solid fa-plus"></i>
            </div>
        </div>

        <div class="table-container">
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
                <tr>
                    <td>1</td>
                    <td>Bàn công thái học</td>
                    <td>Hết</td>
                    <td><button>Edit</button></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Bàn học sinh</td>
                    <td>Còn hàng</td>
                    <td><button>Edit</button></td>
                </tr>
                </tbody>
                <tfoot>
                <td colspan="4">Tổng số thể loại: 2</td>
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
        <div class="nameInput">
            <div class="nameLabel">
                <p>Nhập tên thể loại</p>
            </div>
            <div class="txtName">
                <input type="text" placeholder="Nhập tên thể loại">
            </div>
        </div>
        <div class="saveBtn">
            <p>Lưu</p>
        </div>
    </div>
</div>
</div>