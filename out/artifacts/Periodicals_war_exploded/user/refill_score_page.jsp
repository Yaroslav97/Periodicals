<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>score</title>

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
<fmt:message key="score" var="Score"/>
<fmt:message key="password" var="Password"/>
<fmt:message key="balance" var="Balance"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">${Periodicals}</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/editProfile">${EditProfile}</a></li>
            <li class="active"><a href="/score">${RefillAccount}</a></li>
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

<div align="right" class="container">
    <h3>${Balance}: ${sessionScope.authenticatedScore}$</h3>
</div>

<br>
<br>
<br>

<div class="container">
    <form action="/score" method="post" class="col-xs-6">
        <input type="text" pattern="^[0-9]+\.?[0-9]?$" name="score" required minlength="1" maxlength="3"
               placeholder="${Score}" class="form-control"><br>
        <input type="password" name="password" required minlength="4" maxlength="25"
               placeholder="${Password}" class="form-control"><br>
        <input type="submit" value="ok"><br>
    </form>
</div>

<br>
<hr>
<br>

<div class="container" align="center">
    <h4>${requestScope.scoreInfo}</h4>
</div>

</body>
</html>