<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Sign Up" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<body class="back_main">
<form>
  <%--    <input type="submit" name="sessionLocale" value="en"/>--%>
  <select class="select-css" id="language" name="sessionLocale" onchange="submit()">
    <option value="en" <c:if test="${sessionScope.lang == 'en'}">selected</c:if>><fmt:message
            key="label.lang.en"/></option>
    <option value="uk" <c:if test="${sessionScope.lang == 'uk'}">selected</c:if>><fmt:message
            key="label.lang.uk"/></option>
  </select>

</form>

<div class="login-box">
  <p class="login-title"><fmt:message key="label.signUp"/></p>
  <form class="login-form" action="${pageContext.request.contextPath}/signup" method="post">
    <input name="login" class="login-input" placeholder="Login" required minlength="8" maxlength="20" >
    <input type="password" name="password" class="login-input" placeholder="Password" required minlength="8" maxlength="20">
    <input class="login-input" type="email" name="email" placeholder="Email">
    <input type="submit" class="login-button" value="<fmt:message key="label.signUp"/>">
  </form>
  <c:if test="${param.err != null}">
    <p class="login-error"><fmt:message key="label.signUpError"/></p>
  </c:if>
  <p class="login-register"><fmt:message key="label.signUpQuestion"/> <a href="${pageContext.request.contextPath}/login"><fmt:message key="label.signUpLogIn"/></a> </p>
</div>
<%@include file="../jspf/footer.jspf"%>

</body>
</html>