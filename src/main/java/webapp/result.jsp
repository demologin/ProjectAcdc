<!-- result.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Результат</title>
</head>
<body>
<h1>${sessionScope.result}</h1>
<form action="quest" method="post">
    <input type="submit" value="Начать заново">
</form>
</body>
</html>
