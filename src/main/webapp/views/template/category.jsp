<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:forEach var="cate" items="${categories}">
    <div class="cate-holder">
        <h1>${cate.getName()}</h1>
        <jsp:include page="/product/${cate.getId()}" />
    </div>
</c:forEach>
<style>
    .cate-holder {
        padding: 10px;
        margin: 10px auto;
        border: 2px solid #6C9BCF;
        border-radius: 15px;
        display: 50%;
        width: 90vw;
        box-shadow: 5px 10px 10px rgba(0,0,0,0.2);
    }

    .cate-holder h1 {
        background-color: #6C9BCF;
        color: aliceblue;
        padding-left: 5%;
    }
</style>