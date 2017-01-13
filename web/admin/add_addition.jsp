<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>add addition</title>
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
            <li class="active"><a href="/addEdition">Add edition</a></li>
            <li><a href="/userList">User List</a></li>
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

<br>
<br>
<br>

<div class="container">
<form action="/addEdition" method="post" class="col-xs-6">
    <input name="name" minlength="3" placeholder="Name" class="form-control"><br>
    <input name="subject" minlength="3" placeholder="Subject" class="form-control"><br>
    <input type="number" name="price" minlength="1" placeholder="Price" class="form-control"><br>
    <input type="submit" value="add"><br>
</form>
</div>


<br>
<hr>
<br>

<div align="center" class="container"><h3>
    ${requestScope.addEditionInfo}
</h3></div>

</body>
</html>
