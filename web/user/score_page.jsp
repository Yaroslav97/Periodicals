<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>score</title>

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
            <li class="active"><a href="/score">Refill account</a></li>
            <li><a href="/userCabinet">${sessionScope.authenticatedFullName}</a></li>
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

<h3>Score: ${sessionScope.authenticatedScore}</h3>

<br>
<br>
<br>

<div class="container">
<form action="/score" method="post" class="col-xs-6">
    <input type="number" name="score" minlength="1" maxlength="4" placeholder="Score" class="form-control"><br>
    <input type="password" name="password" minlength="4" maxlength="25" placeholder="Password" class="form-control"><br>
    <input type="submit" value="OK"><br>
</form>
</div>

<br>
<hr>
<br>

</body>
</html>