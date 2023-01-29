<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<c:set var="title" value="My orders" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<body>

<%@include file="../jspf/header.jspf"%>

<div class="error_box" >
  <fmt:message key="label.errorBox"/><br>

  <p class="error_text"><fmt:message key="label.errorText"/></p>
</div>

</body>
</html>