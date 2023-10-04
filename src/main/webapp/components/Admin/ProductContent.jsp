<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div>
   <c:if test="${not empty name}">
      <h1>${name}</h1>
   </c:if>
</div>