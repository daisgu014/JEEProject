<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="header-wrapper">
    <div class="header--title">
        <span>Riêng tư</span>
        <h2>${param.title}</h2>
    </div>
    <span style="color: #07B3F9; font-weight: 500; font-size:14px">${user.getFull_name()}</span>
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