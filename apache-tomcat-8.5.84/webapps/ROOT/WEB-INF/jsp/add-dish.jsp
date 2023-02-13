<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 16.01.2023
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${requestScope.lang}">
<c:set var="title" value="add_dish" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>

<body class="back_info" >
<%@include file="../jspf/header.jspf"%>
<br><br><br><br>

<table class="table" border="3" cellpadding="5" cellspacing="5">
    <tr>
        <th><fmt:message key="label.nameMenu"/></th>
        <th><fmt:message key="label.price"/></th>
        <th><fmt:message key="label.weight"/></th>
        <th><fmt:message key="label.category"/></th>
        <th><fmt:message key="label.desc"/></th>
        <th><fmt:message key="label.addDish"/></th>
    </tr>

    <td><form class="dish_cart_amount" method="post" action="${pageContext.request.contextPath}/controller?command=add">
        <input  class="change-input" type="text" name="newName" placeholder="<fmt:message key="label.nameMenu"/>" required minlength="5"></td>
    <td><input class="change-input" type="number" name="newPrise" placeholder="<fmt:message key="label.price"/>" required min="1" ></td>
    <td><input class="change-input" type="number" name="newWeight"  placeholder="<fmt:message key="label.weight"/>" required min="1"></td>
    <td><input  class="change-input" type="number" name="newCategory"  placeholder="<fmt:message key="label.category"/>" required min="1" max="6"></td>
    <td><input class="change-input" type="text" name="newDesc" placeholder="<fmt:message key="label.desc"/>" required minlength="10"></td>
    <td><input class="apply-cart" type="submit" value="<fmt:message key="label.addDish"/>"></td>
    </form>
</table>

</body>
</html>