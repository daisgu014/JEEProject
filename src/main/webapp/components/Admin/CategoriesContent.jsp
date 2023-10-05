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
        </div>
    </div>
</div>
</div>