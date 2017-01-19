<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://periodicals.nure.ua" %>

<html>
<head>
    <title>user info</title>
    <link href="style//table.css" rel="stylesheet">
</head>
<body>

<tag:list list="${sessionScope.usersInfo}"/>

</body>
</html>
