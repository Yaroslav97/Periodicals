<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>add addition</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<fmt:setBundle basename="i18n"/>

<fmt:message key="periodicals" var="Periodicals"/>
<fmt:message key="add.edition" var="AddEdition"/>
<fmt:message key="user.list" var="UserList"/>
<fmt:message key="edit.edition" var="EditEdition"/>
<fmt:message key="log.out" var="LogOut"/>

<fmt:message key="name" var="Name"/>
<fmt:message key="subject" var="Subject"/>
<fmt:message key="price" var="Price"/>
<fmt:message key="edit" var="Edit"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">${Periodicals}</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/index">${sessionScope.authenticatedFullName}</a></li>
            <li><a href="/addEdition">${AddEdition}</a></li>
            <li class="active"><a href="/editEdition">${EditEdition}</a></li>
            <li><a href="/userList">${UserList}</a></li>
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

<c:if test="${sessionScope.authenticatedRole != 'admin'}">
    <c:redirect url="/index"/>
</c:if>

<br>
<br>
<br>

<div class="container">
<form action="/editEdition" method="post" class="col-xs-6">
    <input name="name" pattern="^[A-zА-я0-9 ]+$" required value="${sessionScope.editName}" minlength="3" placeholder="${Name}" class="form-control"><br>
    <input name="subject" pattern="^[A-zА-я ]+$" required value="${sessionScope.editSubject}" minlength="3" placeholder="${Subject}" class="form-control"><br>
    <input name="price" pattern="^[0-9]+\.?[0-9]?$" required value="${sessionScope.editPrice}" minlength="1" placeholder="${Price}" class="form-control"><br>
    <input type="submit" value="${Edit}"><br>
</form>
</div>

<br>
<hr>
<br>

<div align="center" class="container">
    <h4>${requestScope.editInfo}</h4>
</div>

</body>
</html>
