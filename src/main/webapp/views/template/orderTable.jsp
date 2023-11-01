<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="table-container">
                <table>
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
                    <tbody>
                    <c:set var="count" value="0" scope="page"></c:set>
                    <c:forEach var="order" items="${orders}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td>${count}</td>
                            <td>${order.getId()}</td>
                            <td>${order.getCreate_at()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${!order.isConfirm()}">
                                        "Đang chờ xác nhận"
                                        <br />
                                    </c:when>
                                    <c:otherwise>
                                        "Đã xác nhận"
                                        <br />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${String.format("%,d",order.getTotal_price())}</td>
                            <td><a href="/admin/orders/confirm/?id=${order.getId()}">Edit</a></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td colspan="3">Mã khách hàng: ${order.getUser().getId()}</td>
                                            <td colspan="3">Tên khách hàng: ${order.getUser().getUsername()}l</td>
                                        </tr>
                                        <tr>
                                            <td colspan="6"><h3>Chi tiết sản phẩm</h1></td>
                                        </tr>
                                        <tr>
                                            <th >Mã sản phẩm</th>
                                            <th >Tên sản phẩm</th>
                                            <th >Màu sản phẩm</th>
                                            <th >Số lượng</th>
                                            <th >Đơn giá</th>
                                        </tr>
                                        <c:forEach var="od" items="${order.getDetails()}">
                                            <c:set var="p" value="${od.getProduct()}" />
                                            <tr>
                                                <th >${p.getId()}</th>
                                                <td >${p.getName()}</td>
                                                <td >${p.getColor()}</td>
                                                <td >${od.getQty()}</td>
                                                <td >${String.format("%,d",p.getPrice())}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test = "${count == 0}">
                        <p>No order</p>
                    </c:if>
                    </tbody>
                    <tfoot>
                        <td colspan="6">Tổng số Đơn hàng: ${count}</td>
                    </tfoot>
                </table>
            </div>