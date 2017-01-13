<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>restore_access</title>
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
            <li><a href="/signIn">Sign In</a></li>
            <li><a href="/registration">Registration</a></li>
            <li class="active"><a href="/restoreAccess">Restore Access</a></li>
            <c:if test="${sessionScope.authenticatedRole == 'user'}">
                <li><a href="/userCabinet">${sessionScope.authenticatedFullName}</a></li>
            </c:if>
            <c:if test="${sessionScope.authenticatedRole == 'admin'}">
                <li><a href="/adminCabinet">${sessionScope.authenticatedFullName}</a></li>
            </c:if>
            <c:if test="${!empty sessionScope.authenticatedLogin}">
                <li><a href="/logout">Log Out</a></li>
            </c:if>
        </ul>
    </div>
</nav>

<br>
<br>
<br>

<div class="container">
<form action="/restoreAccess" method="post" class="col-xs-6">
    <input name="login" minlength="4" placeholder="Login" class="form-control"><br>
    <input type="email" name="email" minlength="4" placeholder="Email" class="form-control"><br>
    <input type="submit" value="send"><br>
    <br>
    <hr>
</form>
</div>
</body>
</html>