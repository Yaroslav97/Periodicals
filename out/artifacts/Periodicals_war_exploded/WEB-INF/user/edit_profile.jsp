<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>edit profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<fmt:setBundle basename="i18n"/>

<fmt:message key="periodicals" var="Periodicals"/>
<fmt:message key="edit.profile" var="EditProfile"/>
<fmt:message key="refill.account" var="RefillAccount"/>
<fmt:message key="log.out" var="LogOut"/>
<fmt:message key="full.name" var="FullName"/>
<fmt:message key="email" var="Email"/>
<fmt:message key="notification" var="Notice"/>
<fmt:message key="new.password" var="NewPassword"/>
<fmt:message key="edit" var="Edite"/>
<fmt:message key="delete.profile" var="DeleteProfile"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">${Periodicals}</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/editProfile">${EditProfile}</a></li>
            <li><a href="/score">${RefillAccount}</a></li>
            <li><a href="/userCabinet">${sessionScope.authenticatedFullName}</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${!empty sessionScope.authenticatedLogin}">
                <li><a href="/logout">${LogOut}</a></li>
            </c:if>
        </ul>
    </div>
</nav>

<c:if test="${empty sessionScope.authenticatedLogin}">
    <c:redirect url="/signIn"/>
</c:if>

<c:if test="${sessionScope.authenticatedRole != 'user'}">
    <c:redirect url="/index"/>
</c:if>

<br>
<br>
<br>

<div class="container">
    <form action="/editProfile" method="post" class="col-xs-6">
        <input name="fullName" pattern="[A-zА-я]+ [A-zА-я]+" required minlength="4" placeholder="${FullName}"
               value="${sessionScope.authenticatedFullName}" class="form-control"><br>
        <input type="email" pattern="^[a-z0-9]+\.?_?[a-z0-9]+@.{2,9}\..{2,3}$" name="email" required minlength="4"
               placeholder="${Email}" value="${sessionScope.authenticatedEmail}" class="form-control"><br>
        <input name="notification" value="${sessionScope.notification}" pattern="^true|false$"
               required placeholder="${Notice}" class="form-control"><br>
        <input type="password" name="password" required minlength="4" placeholder="${NewPassword}"
               class="form-control"><br>
        <input type="submit" value="${Edite}"><br>
    </form>
</div>

<br>
<hr>
<br>

<div class="container">
    <a href="/deleteUser?login=${sessionScope.authenticatedLogin}">${DeleteProfile}</a>
</div>

<div class="container" align="center">
    <h4>${sessionScope.editInfo}</h4>
</div>

</body>
</html>
