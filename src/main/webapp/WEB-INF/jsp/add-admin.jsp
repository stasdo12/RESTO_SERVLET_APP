<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 22.02.2023
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${requestScope.lang}">
<c:set var="title" value="add_admin" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
<body class="change-body">
<%@include file="../jspf/header.jspf"%>

<table class="table" border="3" cellpadding="5" cellspacing="5">
    <tr>
    <th><fmt:message key="label.userId"/></th>
    <th><fmt:message key="label.userLogin"/></th>
    <th><fmt:message key="label.addAdmin"/></th>
    </tr>

        <c:forEach items="${requestScope.users}" var="user">
    <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
        <td>
        <form  method="post" action="${pageContext.request.contextPath}/controller?command=make_admin">
        <input name="id" style="display: none" value="${user.id}">
        <input class="delete-button-cart" type="submit" value="<fmt:message key="label.addAdmin"/>">



        </form>
        </td>
    </tr>
     </c:forEach>
</table>


</body>
</html>
