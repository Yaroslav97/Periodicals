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

<c:if test="${empty sessionScope.authenticatedLogin}">
    <c:redirect url="/index"/>
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
    <br>
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

<%--User List: review subscription info in new page, list all sub, total sum;
    search user in user list--%>

</body>
</html>
