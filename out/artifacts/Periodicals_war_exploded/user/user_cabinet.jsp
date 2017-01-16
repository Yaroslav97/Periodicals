<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>cabinet</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">Periodicals</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/editProfile">Edit profile</a></li>
            <li><a href="/score">Refill account</a></li>
            <li class="active"><a href="/userCabinet">${sessionScope.authenticatedFullName}</a></li>
            <li><a href="/logout">LogOut</a></li>
        </ul>
    </div>
</nav>

<c:if test="${empty sessionScope.authenticatedLogin}">
    <c:redirect url="/index"/>
</c:if>

<c:if test="${sessionScope.authenticatedRole != 'user'}">
    <c:redirect url="/index"/>
</c:if>

<div align="right" class="container">
    <h3>Balance: ${sessionScope.authenticatedScore}$</h3>
</div>

<br>
<br>

<div class="container">
    <table class="table">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>subject</th>
            <th>price</th>
        </tr>

        <c:forEach items="${subscribesList}" var="subscribesList">
            <tr>
                <td>${subscribesList.id}</td>
                <td>${subscribesList.name}</td>
                <td>${subscribesList.subject}</td>
                <td>${subscribesList.price}</td>
                <td>
                    <a href="/unsubscribe?id=${subscribesList.id}">unsubscribe</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
