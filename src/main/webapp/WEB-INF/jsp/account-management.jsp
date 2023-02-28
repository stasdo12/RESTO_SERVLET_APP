<%--@elvariable id="user" type="com.epam.donetc.restaurant.database.entity.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 23.02.2023
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${requestScope.lang}">
<c:set var="title" value="acc_management" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<style>
  <%@include file="/WEB-INF/css/style.css"%>
</style>
<body class="back_info">
<%@include file="../jspf/header.jspf"%>
<br>
<br>
<div class="about_us">
<h2><fmt:message key="label.accManagement"/></h2>
<form class="form_about_us" method="post" action="${pageContext.request.contextPath}/controller?command=acc_management_post">
    <label>
        <input name="login"  value="${user.login}" style="display: none">
    </label>

    <label style="color: black" for="oldPass"> Old Pass</label>
    <input type="password" id="oldPass" name="oldPass" placeholder="Your OldPass"
           required minlength="8"><br><br>
    <label style="color: black" for="newEmail"><fmt:message key="label.newEmail"/>:</label>
    <input type="email" id="newEmail" name="newEmail" placeholder="new Email"
           required minlength="7"><br><br>
    <label style="color: black" for="newPassword"><fmt:message key="label.newPassword"/>:</label>
    <input type="password" id="newPassword" name="newPassword"
           required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$"><br><br>

    <input class="apply-cart" type="submit" value="<fmt:message key="label.updateAccount"/>">
</form>
</div>
</body>
</html>