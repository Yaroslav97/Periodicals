<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Subscribers Information</title>
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
<fmt:message key="information" var="Info"/>
<fmt:message key="log.out" var="LogOut"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">${Periodicals}</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/index">${sessionScope.authenticatedFullName}</a></li>
            <li><a href="/addEdition">${AddEdition}</a></li>
            <li><a href="/userList">${UserList}</a></li>
            <li class="active"><a href="">${Info}</a></li>
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

<div class="container">

    <table class="table">
        <tr>
            <th>name</th>
            <th>subject</th>
            <th>subscribers</th>
            <th>total sum</th>
        </tr>

        <c:forEach items="${editionInfo}" var="editionInfo">
            <tr>
                <td>${editionInfo.name}</td>
                <td>${editionInfo.subject}</td>
                <td>${editionInfo.countSubscribe}</td>
                <td>${editionInfo.price}</td>
            </tr>
        </c:forEach>
    </table>

    <hr>
    <br>

    <table class="table">
        <tr>
            <th>fullName</th>
            <th>login</th>
            <th>email</th>
            <th>ban</th>
        </tr>

        <c:forEach items="${subList}" var="subList">
            <tr>
                <td>${subList.fullName}</td>
                <td>${subList.login}</td>
                <td>${subList.email}</td>
                <td>${subList.ban}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
