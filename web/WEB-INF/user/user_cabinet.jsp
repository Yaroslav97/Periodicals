<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>cabinet</title>
    <%@include file="/WEB-INF/jspf/libraries.jspf"%>
</head>
<body>

<fmt:setBundle basename="i18n"/>

<fmt:message key="periodicals" var="Periodicals"/>
<fmt:message key="edit.profile" var="EditProfile"/>
<fmt:message key="refill.account" var="RefillAccount"/>
<fmt:message key="log.out" var="LogOut"/>
<fmt:message key="balance" var="Balance"/>
<fmt:message key="id" var="Id"/>
<fmt:message key="name" var="Name"/>
<fmt:message key="subject" var="Subject"/>
<fmt:message key="price" var="Price"/>
<fmt:message key="unsubscribe" var="Unsibscribe"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">${Periodicals}</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/editProfile">${EditProfile}</a></li>
            <li><a href="/score">${RefillAccount}</a></li>
            <li class="active"><a href="/userCabinet">${sessionScope.authenticatedFullName}</a></li>
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

<div class="container">
    <table class="table">
        <tr>
            <th>${Id}</th>
            <th>${Name}</th>
            <th>${Subject}</th>
            <th>${Price}</th>
        </tr>

        <c:forEach items="${subscribesList}" var="subscribesList">
            <tr>
                <td>${subscribesList.id}</td>
                <td>${subscribesList.name}</td>
                <td>${subscribesList.subject}</td>
                <td>${subscribesList.price}</td>
                <td>
                    <a href="/unsubscribe?id=${subscribesList.id}">${Unsibscribe}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<br>
<hr>
<br>

<div align="right" class="container">
    <a href="/report">Generate report</a>
</div>

</body>
</html>
