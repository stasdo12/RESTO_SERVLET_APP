
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@include file="../jspf/header.jspf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Change" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>


<body class="change-body">


<table class="table" border="3" cellpadding="5" cellspacing="5">
    <tr>
        <th><fmt:message key="label.dishId"/></th>
        <th><fmt:message key="label.dishName"/></th>
        <th><fmt:message key="label.dishPrice"/></th>
        <th><fmt:message key="label.dishWeight"/></th>
        <th><fmt:message key="label.dishDescription"/></th>
        <th><fmt:message key="label.category"/></th>
        <th><fmt:message key="label.Change"/></th>
        <th><fmt:message key="label.delete"/></th>
    </tr>
<jsp:useBean id="dishes" scope="request" type="java.util.List"/>
<%--<jsp:useBean id="page" scope="request" type="java.lang.Integer"/>--%>

    <c:forEach var="dish" items="${requestScope.dishes}">
        <tr>
            <td> ${dish.id}</td>
            <td> ${dish.name}</td>
            <td>${dish.price}</td>
            <td>${dish.weight}</td>
            <td>${dish.description}</td>
            <td>${dish.category}</td>
            <td>
                <div class="form-for-change">
                <form class="dish_cart_amount" method="post" action="${pageContext.request.contextPath}/change-dish">
                    <label>
                        <input class="change-input" name="id" style="display: none" value="${dish.id}">
                    </label>
                    <br>
                        <fmt:message key="label.newName"/>   <label>
                        <input  class="change-input" type="text" name="newName" value="${dish.name}" placeholder="Name" required minlength="5">
                    </label>
                    <br>
                        <fmt:message key="label.newPrice"/>   <label>
                        <input class="change-input" type="number" name="newPrise" value="${dish.price}" placeholder="Price" required min="1">
                    </label>
                    <br>
                        <fmt:message key="label.newWeight"/><label>
                        <input class="change-input" type="number" name="newWeight" value="${dish.weight}" placeholder="Weight" required min="1">
                    </label>
                    <br>
                        <fmt:message key="label.newCategory"/><label>
                        <input  class="change-input" type="number" name="newCategory" value="${dish.category}" placeholder="<fmt:message key="label.category"/>"  required min="1" max="6">
                    </label>
                    <br>
                        <fmt:message key="label.newDesc"/><label>
                        <input class="change-input" type="text" name="newDesc" value="${dish.description}" placeholder="Desc" required minlength="5" >
                    </label>
                    <br>
                    <input class="apply-cart" type="submit" value="<fmt:message key="label.Change"/>">
            </td>

            <td>
                </form>
                    <form class="dish_cart_amount" method="post" action="${pageContext.request.contextPath}/delete">
                        <input class="delete-input" name="id" style="display: none" value="${dish.id}">
                        <input class="delete-button-cart" type="submit" value="<fmt:message key="label.delete"/>">
                        <br>

                    </form>
                </div>
            </td>
        </tr>

    </c:forEach>

</table>


<div class="menu-pagination">
    <table class="menu-pagination-item" border="1" cellpadding="5" cellspacing="5">
        <tr>
            <%--@elvariable id="noOfPages" type="java.lang.Integer"--%>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="change-dish?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <div class="menu-pagination">


</body>
</html>