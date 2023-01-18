<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 21.12.2022
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${requestScope.lang}">
<c:set var="title" value="Info" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<%@include file="../jspf/header.jspf"%>
<body class="back_info">
<br><br><br>
<div class="about_us">
<h2><fmt:message key="label.contactUs"/></h2>
<h2 class="text_contact_info"><fmt:message key="label.contactInformation"/></h2>
<p>+38 063 95 39 901</p>
<p><fmt:message key="label.address"/> </p>
<p>emap-restaurant@gmail.com</p>
</div>
<center><iframe class="map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2565.0418823488303!2d36.23210991588374!3d49.99182422818497!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4127a0f1ca30be15%3A0x71b0dd10a6d0a226!2z0YPQuy4g0J_Rg9GI0LrQuNC90YHQutCw0Y8sIDIsINCl0LDRgNGM0LrQvtCyLCDQpdCw0YDRjNC60L7QstGB0LrQsNGPINC-0LHQu9Cw0YHRgtGMLCA2MTAwMA!5e0!3m2!1sru!2sua!4v1671623300415!5m2!1sru!2sua" width="400" height="300" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></center>

<div class="about_us">
<h2 class="text_contact_info" ><fmt:message key="label.writeUs"/></h2>
</div>
<br>
<form class="form_about_us" action="${pageContext.request.contextPath}/info" method="post">
    <input placeholder="<fmt:message key="label.nameMenu"/>" class="about_us_input" type="text" id="name" name="name" required minlength="10"><br>
    <input placeholder="<fmt:message key="label.email"/>" class="about_us_input" type="email" id="email" name="email"><br>
    <textarea  style="width: 283px;" placeholder="<fmt:message key="label.writeUs"/>" id="message" name="message"></textarea><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
