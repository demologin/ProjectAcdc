<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вы выиграли</title>
</head>
<body>
    <c:set var="name" value="${sessionScope.get('name')}"/>
    <h1>Поздравляем, <c:out value="${name}"/></h1>
    <h2>Вы выиграли. Хотите начать заново?</h2>
    <form method="post">
        <input type="submit" value="Рестарт">
    </form>
</body>
</html>