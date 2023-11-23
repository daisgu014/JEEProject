<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


        <div class="table-container">
            <table class="order-main-table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Mã đơn hàng</th>
                        <th>Ngày đặt hàng</th>
                        <th>Trạng thái</th>
                        <th>Tổng tiền</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody border="1">
                    <c:set var="count" value="0" scope="page"></c:set>
                    <c:forEach var="order" items="${orders}">
                        <c:set var="count" value="${count + 1}" scope="page" />

                        <tr class="od-info">
                            <td>${count}</td>
                            <td>${order.getId()}</td>
                            <td>${order.getCreate_at()}</td>
                            <c:choose>
                                <c:when test="${order.getState() == 'WAITING'}">
                                    <td class="yl-bg">
                                        ${order.getState().getValue()}
                                    </td>
                                </c:when>
                                <c:when test="${order.getState() == 'DONE'}">
                                    <td class="gr-bg">
                                        ${order.getState().getValue()}
                                    </td>
                                </c:when>
                                <c:when test="${order.getState() == 'CANCEL'}">
                                    <td class="rd-bg">
                                        ${order.getState().getValue()}
                                    </td>
                                </c:when>
                            </c:choose>

                            <td>${String.format("%,d",order.getTotal_price())}</td>
                            <td class="action-tag">
                                <a href="/admin/orders/confirm/?id=${order.getId()}">
                                    <i class="fa-regular fa-circle-check fa-2xl"></i>
                                </a>
                                <a href="/admin/orders/cancel/?id=${order.getId()}">
                                    <i class="fa-solid fa-circle-xmark fa-2xl"></i>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <table>
                                    <tbody>
                                        <tr class="od-show">
                                            <td colspan="6">
                                                <details class="od-details">
                                                    <summary>
                                                        Chi tiết sản phẩm <i class="fa-solid fa-table-list"></i>
                                                    </summary>
                                                    <table class="od-details-table">
                                                        <tbody>
                                                            <tr>
                                                                <td colspan="2">Mã khách hàng:
                                                                    ${order.getUser().getId()}</td>
                                                                <td colspan="3">Tên khách hàng:
                                                                    ${order.getUser().getUsername()}l</td>
                                                            </tr>
                                                            <tr>
                                                                <th>Mã sản phẩm</th>
                                                                <th>Tên sản phẩm</th>
                                                                <th>Hình ảnh</th>
                                                                <th>Màu sản phẩm</th>
                                                                <th>Số lượng</th>
                                                                <th>Đơn giá</th>
                                                            </tr>
                                                            <c:forEach var="od" items="${order.getDetails()}">
                                                                <c:set var="p" value="${od.getProduct()}" />
                                                                <tr>
                                                                    <th>${p.getId()}</th>
                                                                    <td>${p.getName()}</td>
                                                                    <td>
                                                                            <img src="/images/products/${p.getImgPath()}"
                                                                            style="object-fit: cover; border-radius: 50%; width: 60px; height: 60px">
                                                                    </td>
                                                                    <td>${p.getColor()}</td>
                                                                    <td>${od.getQty()}</td>
                                                                    <td>${String.format("%,d",p.getPrice())}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </details>
                                            </td>
                                        </tr>


                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${count == 0}">
                        <p>No order</p>
                    </c:if>
                </tbody>
                <tfoot>
                    <td colspan="6">Tổng số Đơn hàng: ${count}</td>
                </tfoot>
            </table>
        </div>
        <style>
            .action-tag i {
                height: 15px;
            }

            .action-tag .fa-circle-xmark {
                color: #FF5B22;
            }

            .action-tag .fa-circle-check {
                color: #219C90;
            }

            .gr-bg {
                color: #fff;
                background-color: #219C90;
                border-radius: 10px;
            }

            .yl-bg {
                color: #fff;
                background-color: #E9B824;
                border-radius: 10px;
            }

            .rd-bg {
                color: #fff;
                background-color: #FF5B22;
                border-radius: 10px;
            }

            .od-details * {
                transition: all 1s ease;
            }

            .od-details>summary {
                list-style-type: none;
                cursor: pointer;
                margin: 5px;
            }

            input[type="date"],
            ::placeholder {
                text-align: center;
            }

            .od-details[open]>summary>i {
                display: block;
            }

            .od-details>summary>i {
                color: #6C9BCF;
            }

            .table-container {
                padding: auto;
                height: 90%;
                width: 100%;
                overflow-y: scroll;
            }

            .order-main-table>tbody {
                overflow-y: scroll;
            }

            .order-main-table>tbody>tr:nth-child(even) {
                margin: 5px;
                border-top: 2px solid #6C9BCF;
            }

            .order-main-table thead {
                position: sticky;
                inset-block-start: 0;
            }

            .order-main-table tfoot {
                position: sticky;
                inset-block-end: 0;
            }
        </style>