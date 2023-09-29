<html>
<head>
    <title>Hello Spring MVC</title>
</head>
<body>
<button id="btn_click">Button</button>
<h1>${name}</h1>
<script type="text/javascript">
    let btn_click =document.getElementById("btn_click");
    btn_click.addEventListener("click", function () {
        alert("Hello Mn");
    })
</script>
</body>
</html>