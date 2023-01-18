
<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="mylib2" uri="http://restaurant.com" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}>"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}>>
<c:set var="title" value="Cart" scope="page"/>
<%@include file="../jspf/head.jspf" %>
<body>
<%@include file="../jspf/header.jspf" %>

<jsp:useBean id="cart" scope="session" type="java.util.Map"/>
<div class="grey_background">
    <c:if test="${cart.size()==0}">
        <div class="cart-empty">
            <fmt:message key="label.cartEmpty"/>
        </div>
    </c:if>
    <c:if test="${cart.size() != 0}">

        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th><fmt:message key="label.dishDescription"/></th>
                <th><fmt:message key="label.amount"/></th>
                <th><fmt:message key="label.address-client"/> </th>
                <th><fmt:message key="label.delete"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cart}" var="item">
                <c:set var="dish" value="${item.key}" scope="page"/>

                <tr>
                    <td><img src="${pageContext.request.contextPath}/images/${dish.id}.jpg" class="cart-dish-img"
                             alt="${dish.name}.jpg"/></td>
                    <td>
                        <div class="dish_cart_text">
                            <p><strong>${dish.name}</strong></p>
                            <p>${dish.description}</p>
                            <p>${dish.weight} <fmt:message key="label.g"/></p>
                            <p>${dish.price} <fmt:message key="label.uah"/></p>
                        </div>
                    </td>
                    <td>
                        <form class="dish_cart_amount" method="post" action="${pageContext.request.contextPath}/cart">
                            <input class="amount-border" name="amount" type="number" min="1" value="${item.value}">
                            <input name="id" style="display: none" value="${dish.id}">
                            <input class="apply-cart" type="submit" value="<fmt:message key="label.applyButton"/>">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/address">
                            <input name="id" style="display: none" value="${user.id}">
                            <input class="amount-border" name="address" required minlength="5">
                            <input class="apply-cart" type="submit" value="<fmt:message key="label.applyButton"/>">
                        </form>


                    </td>

                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cart">
                            <input name="amount" style="display: none" type="number" value="0">
                            <input name="id" style="display: none" value="${dish.id}">
                            <input class="delete-button-cart" type="submit" value="<fmt:message key="label.delete"/>">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:useBean id="total" scope="session" type="java.lang.Integer"/>
        <div class="cart-submit-order">
            <p><fmt:message key="label.total"/> ${total}   <fmt:message key="label.uah"/></p>
            <form action="${pageContext.request.contextPath}/myOrders" method="post">
                <input class="cart-submit-button" type="submit" value="<fmt:message key="label.order"/>"/>
            </form>
        </div>

    </c:if>
</div>
</body>
</html>