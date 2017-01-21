<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>user list</title>
    <meta charset="utf-8">
    <link href="style//table.css" rel="stylesheet">
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

<fmt:message key="full.name" var="FullName"/>
<fmt:message key="login" var="Login"/>
<fmt:message key="email" var="Email"/>
<fmt:message key="score" var="Score"/>
<fmt:message key="role" var="Role"/>
<fmt:message key="ban" var="Ban"/>
<fmt:message key="change.ban" var="ChangeBan"/>
<fmt:message key="change.status" var="ChangeStatus"/>
<fmt:message key="info" var="Info"/>
<fmt:message key="user" var="Users"/>
<fmt:message key="admin" var="Admins"/>
<fmt:message key="search" var="Search"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">${Periodicals}</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/index">${sessionScope.authenticatedFullName}</a></li>
            <li><a href="/addEdition">${AddEdition}</a></li>
            <li class="active"><a href="/userList">${UserList}</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${!empty sessionScope.authenticatedLogin}">
                <li><a href="/logout">${LogOut}</a></li>
            </c:if>
        </ul>
        <form action="/userList" method="post" class="navbar-form navbar-left">
            <div class="input-group">
                <input name="search" required placeholder="${FullName}" class="form-control">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</nav>

<c:if test="${empty sessionScope.authenticatedLogin}">
    <c:redirect url="/signIn"/>
</c:if>

<c:if test="${sessionScope.authenticatedRole != 'admin'}">
    <c:redirect url="/index"/>
</c:if>

<c:if test="${sessionScope.authenticatedBan == true}">
    <c:redirect url="/logout"/>
    <%--todo when user ban, create method--%>
</c:if>


<%--<form action="/userList" method="post" class="col-xs-2">
    <input name="search" placeholder="${FullName}" required class="form-control">
    <input type="submit" value="${Search}">
</form>--%>

<div class="container">

    <form action="/userList" method="post" class="col-xs-2">
        <select name="role" class="form-control">
            <option selected value="users">${Users}</option>
            <option value="admins">${Admins}</option>
        </select>
        <input type="submit" value="ok">
    </form>

    <table class="table">
        <tr>
            <th>${FullName}</th>
            <th>${Login}</th>
            <th>${Email}</th>
            <th>${Score}</th>
            <th>${Role}</th>
            <th>${Ban}</th>
            <th>${ChangeBan}</th>
        </tr>

        <c:forEach items="${userList}" var="userList">
            <tr>
                <td>${userList.fullName}</td>
                <td>${userList.login}</td>
                <td>${userList.email}</td>
                <td>${userList.score}</td>
                <td>${userList.role}</td>
                <td>${userList.ban}</td>
                <td><a href="/changeStatus?login=${userList.login}&role=${userList.role}">${ChangeStatus}</a></td>
                <td><a href="/userInfo?login=${userList.login}">${Info}</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>