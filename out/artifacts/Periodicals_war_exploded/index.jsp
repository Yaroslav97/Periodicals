<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>index</title>

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
            <c:if test="${empty sessionScope.authenticatedLogin}">
                <li><a href="${ser}/signIn">Sign In</a></li>
                <li><a href="/registration">Registration</a></li>
                <li><a href="/restoreAccess">Restore Access</a></li>
            </c:if>
            <c:if test="${sessionScope.authenticatedRole == 'user'}">
                <li><a href="/userCabinet">${sessionScope.authenticatedFullName}</a></li>
            </c:if>
            <c:if test="${sessionScope.authenticatedRole == 'admin'}">
                <li class="active"><a href="">${sessionScope.authenticatedFullName}</a></li>
                <li><a href="/addEdition">Add edition</a></li>
                <li><a href="/userList">User List</a></li>
            </c:if>
            <c:if test="${!empty sessionScope.authenticatedLogin}">
                <li><a href="/logout">LogOut</a></li>
            </c:if>
        </ul>
    </div>
</nav>

<div align="right" class="container">
    ${requestScope.subscribeInfo}
</div>

<br>

<div class="container">

    <div align="right">
        <form action="/index" class="col-xs-2">
            <select name="sort" class="form-control">
                <option selected value="subject">subject</option>
                <option value="name">name</option>
                <option value="price">price</option>
                <option value="rank">rank</option>
                <option value="id">id</option>
            </select>
            <input type="submit" value="Sort"><br><br>
        </form>
    </div>

    <div align="right">
        <form action="/index" class="col-xs-2">
            <input name="search" required placeholder="Search" class="form-control">
            <input type="submit" value="Search"><br><br>
        </form>
    </div>


    <div align="right">
        <form action="/index" class="col-xs-2">
            <input name="filter1" required pattern="^[0-9]+\.?[0-9]?$" placeholder="Price from" maxlength="2"
                   class="form-control">
            <input name="filter2" required pattern="^[0-9]+\.?[0-9]?$" placeholder="Price to" maxlength="2"
                   class="form-control">
            <input type="submit" value="Filter"><br>
        </form>
    </div>

    <table class="table">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>subject</th>
            <th>price</th>
        </tr>

        <c:forEach items="${editionList}" var="editionList">
            <tr>
                <td>${editionList.id}</td>
                <td>${editionList.name}</td>
                <td>${editionList.subject}</td>
                <td>${editionList.price}</td>

                <c:if test="${!empty sessionScope.authenticatedLogin}">
                    <c:choose>
                        <c:when test="${sessionScope.authenticatedRole == 'user'}">
                            <td>
                                <a href="/subscribe?id=${editionList.id}">subscribe</a>
                            </td>
                        </c:when>

                        <c:when test="${sessionScope.authenticatedRole == 'admin'}">
                            <td>
                                <a href="/editEdition?eId=${editionList.id}&eName=${editionList.name}&eSubject=${editionList.subject}&ePrice=${editionList.price}">edit</a>
                            </td>
                            <td>
                                <a href="/removeEdition?id=${editionList.id}">remove</a>
                            </td>

                            <c:forEach items="${countSub}" var="countSub">
                                <c:choose>
                                    <c:when test="${countSub.id == editionList.id}">
                                        <td><a href="/editionInfo?info=${editionList.id}">info</a></td>
                                    </c:when>
                                </c:choose>
                            </c:forEach>

                        </c:when>
                    </c:choose>

                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>