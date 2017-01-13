<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>sign in</title>

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
            <li class="active"><a href="/signIn">Sign In</a></li>
            <li><a href="/registration">Registration</a></li>
            <li><a href="/restoreAccess">Restore Access</a></li>
            <c:if test="${sessionScope.authenticatedRole == 'user'}">
                <li><a href="/userCabinet">${sessionScope.authenticatedFullName}</a></li>
            </c:if>
            <c:if test="${sessionScope.authenticatedRole == 'admin'}">
                <li><a href="/adminCabinet">${sessionScope.authenticatedFullName}</a></li>
            </c:if>            <c:if test="${!empty sessionScope.authenticatedLogin}">
            <li><a href="/logout">LogOut</a></li>
            </c:if>
        </ul>
    </div>
</nav>

<br>
<br>
<br>

<div class="container">
<form action="/signIn" method="post" class="col-xs-6">
    <input name="login" placeholder="Login" minlength="4" class="form-control"><br>
    <input type="password" name="password" placeholder="Password" minlength="4" class="form-control"><br>
    <input type="submit" value="SignIn"><br>
</form>
</div>

<hr>
<br>
<br>
<div align="center">
    <h3>${requestScope.signInInfo}</h3>
</div>
</body>
</html>
