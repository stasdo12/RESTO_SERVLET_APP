<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${requestScope.lang}">
<c:set var="title" value="Orders" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<body>

<%@include file="../jspf/header.jspf"%>

<div class="grey_background">


  <table class="table">

    <thead>
    <tr>
      <th><fmt:message key="label.orderId"/></th>
      <th><fmt:message key="label.userId"/></th>
      <th><fmt:message key="label.status"/></th>
      <th><fmt:message key="label.dishes"/></th>
      <th><fmt:message key="label.totalOrder"/></th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="receipts" scope="session" type="java.util.List"/>
    <c:forEach items="${receipts}" var="receipt">
      <jsp:useBean id="receipt" class="com.epam.donetc.restaurant.database.entity.Receipt"/>

      <tr>
        <td>${receipt.id}</td>
        <td>${receipt.user.id}</td>
        <td>${receipt.status}

          <form class="menu_filter_sort" method="post" action="${pageContext.request.contextPath}/manageOrders">
            <select  id="status" name="status">
              <option value="New"><fmt:message key="label.statusNew"/></option>
              <option value="Approved"><fmt:message key="label.statusApproved"/></option>
              <option value="Cancelled"><fmt:message key="label.statusCancelled"/></option>
              <option value="Cooking"><fmt:message key="label.statusCooking"/></option>
              <option value="Delivering"><fmt:message key="label.statusDelivering"/></option>
              <option value="Received"><fmt:message key="label.statusReceived"/></option>
            </select>
            <input value="${receipt.id}" name="id" style="display: none">
            <input class="manager-orders-apply" type="submit" value="<fmt:message key="label.applyButton"/>">

          </form>
        </td>
        <td>
          <c:forEach items="${receipt.dishes}" var="dishAndAmount">

            ${dishAndAmount.key.name}: ${dishAndAmount.key.price} * ${dishAndAmount.value}<br>
          </c:forEach>
        </td>
        <td>${receipt.total}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="menu-pagination">
  <form method="get" action="${pageContext.request.contextPath}/manageOrders">
    <c:forEach var="number" begin="1" end="${maxPage}">
      <div class="menu-pagination-item">
        <input type="submit" name="currentPage" value="${number}"  >
      </div>
    </c:forEach>
  </form>
</div>


</body>
</html>