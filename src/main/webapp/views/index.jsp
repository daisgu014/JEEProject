<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <link type="text/css" href="../css/style.css" rel="stylesheet">
    <title>Hello Spring MVC</title>
</head>
<body>
<button id="btn_click">Button</button>
<h1>${name}</h1>
<h1>${category}</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <c:forEach items="${categories}" var="category" varStatus="status">
        <tr>
            <td>${category.getId()}</td>
            <td>${category.getName()}</td>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript">
    let btn_click =document.getElementById("btn_click");
    btn_click.addEventListener("click", function () {
        alert("Hello Mn");
    })
</script>
</body>
</html>