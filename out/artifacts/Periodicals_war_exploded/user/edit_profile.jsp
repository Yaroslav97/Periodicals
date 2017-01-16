<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit profile</title>
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
            <li class="active"><a href="/editProfile">Edit profile</a></li>
            <li><a href="/score">Refill account</a></li>
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

<br>
<br>
<br>

<div class="container">
    <form action="/editProfile" method="post" class="col-xs-6">
        <input name="fullName" required minlength="4" placeholder="Full Name"
               value="${sessionScope.authenticatedFullName}" class="form-control"><br>
        <input type="email" name="email" required minlength="4" placeholder="Email"
               value="${sessionScope.authenticatedEmail}" class="form-control"><br>
        <input name="notification" value="${sessionScope.notification}" pattern="^true|false$"
               required placeholder="Notification" class="form-control"><br>
        <input type="password" name="password" required minlength="4" placeholder="New password"
               class="form-control"><br>
        <input type="submit" value="edit"><br>
    </form>
</div>

<br>
<hr>
<br>

<div class="container">
    <a href="/deleteUser?login=${sessionScope.authenticatedLogin}">delete profile</a>
</div>

</body>
</html>
