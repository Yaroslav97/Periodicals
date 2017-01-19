<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">Periodicals</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="">${sessionScope.authenticatedFullName}</a></li>
            <li><a href="/addEdition">Add edition</a></li>
            <li class="active"><a href="/userList">User List</a></li>
            <li><a href="/logout">LogOut</a></li>
        </ul>
    </div>
</nav>

<c:if test="${empty sessionScope.authenticatedLogin}">
    <c:redirect url="/index"/>
</c:if>

<c:if test="${sessionScope.authenticatedRole != 'admin'}">
    <c:redirect url="/index"/>
</c:if>

<c:if test="${sessionScope.authenticatedBan == true}">
    <c:redirect url="/logout"/>
</c:if>

<form action="/userList" method="post" class="col-xs-2">
    <select name="role" class="form-control">
        <option selected value="users">users</option>
        <option value="admins">admins</option>
    </select>
    <input type="submit" value="ok">
</form>

<form action="/userList" method="post" class="col-xs-2">
    <input name="search" placeholder="Full Name" required class="form-control">
    <input type="submit" value="search">
</form>

    <div class="container">
        <table class="table">
            <tr>
                <th>FullName</th>
                <th>Login</th>
                <th>Email</th>
                <th>Score</th>
                <th>Role</th>
                <th>Ban</th>
                <th>Change Ban</th>
            </tr>

            <c:forEach items="${userList}" var="userList">
                <tr>
                    <td>${userList.fullName}</td>
                    <td>${userList.login}</td>
                    <td>${userList.email}</td>
                    <td>${userList.score}</td>
                    <td>${userList.role}</td>
                    <td>${userList.ban}</td>
                    <td><a href="/changeStatus?login=${userList.login}&role=${userList.role}">change status</a></td>
                    <td><a href="/userInfo?login=${userList.login}">info</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>