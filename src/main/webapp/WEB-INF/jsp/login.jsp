
<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>


<html  lang="${sessionScope.lang}">
<c:set var="title" value="Log In" scope="page"/>
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
  <p class="login-title"><fmt:message key="label.login"/></p>
  <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
    <input placeholder="Login" type="text" class="login-input" name="login"/>
    <input placeholder="Password" type="password" class="login-input" name="password" />
    <input type="submit" class="login-button" value="<fmt:message key="label.loginButton"/>"/>
  </form>
  <c:if test="${param.err != null}">
    <p class="login-error"><fmt:message key="label.loginError"/></p>
  </c:if>
  <p class="login-register"><fmt:message key="label.loginRegister"/><a class="text_in_but"
                                                                       href="${pageContext.request.contextPath}/signup"><fmt:message key="label.signUp"/></a></p>
</div>

<%@include file="../jspf/footer.jspf"%>

</body>
</html>