<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Menu" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<body>
<%@include file="../jspf/header.jspf"%>
<jsp:useBean id="dishes" scope="request" type="java.util.List"/>
<%--<jsp:useBean id="maxPage" scope="session" type="java.lang.Integer"/>--%>

<div class="sort-filter-bar">
  <form class="menu_filter_sort" method="get" action="${pageContext.request.contextPath}/menu">
    <label for="category"><fmt:message key="label.chooseACategory"/></label>
    <select id="category" name="category">
      <option value="All"><fmt:message key="label.allCategories"/></option>
      <option value="pizza"><fmt:message key="label.pizza"/></option>
      <option value="sushi"><fmt:message key="label.sushi"/></option>
      <option value="burger"><fmt:message key="label.burger"/></option>
      <option value="drink"><fmt:message key="label.drink"/></option>
      <option value="salad"><fmt:message key="label.salad"/></option>
      <option value="dessert"><fmt:message key="label.dessert"/></option>
    </select>

    <label for="sortBy"><fmt:message key="label.sortBy"/></label>
    <select id="sortBy" name="sortBy">
      <option value="category"><fmt:message key="label.category"/></option>
      <option value="name"><fmt:message key="label.nameMenu"/></option>
      <option value="price"><fmt:message key="label.price"/></option>
    </select>
    <select name="page" style="display: none">
      <option value="1" selected></option>
    </select>

    <input class="menu-apply-button" type="submit" value="<fmt:message key="label.applyButton"/>">

  </form>
</div>


<div class="menu_dishes">
  <c:forEach var="dish" items="${dishes}">
    <%@include file="../jspf/dish.jspf"%>
  </c:forEach>
</div>




<div class="menu-pagination">
  <form method="get" action="${pageContext.request.contextPath}/menu">
    <table class="menu-pagination-item" border="1" cellpadding="5" cellspacing="5">
      <tr>
        <%--@elvariable id="noOfPages" type="java.lang.Integer"--%>
        <c:forEach begin="1" end="${noOfPages}" var="i">
          <c:choose>
            <c:when test="${currentPage eq i}">
              <td>${i}</td>
            </c:when>
            <c:otherwise>
              <td><a href="/menu?page=${i}">${i}</a></td>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </tr>
    </table>
      <div class="menu-pagination-item">
        <input name="sortBy" value="${param.sortBy}" style="display: none" >
        <input name="category" value="${param.category}" style="display: none">
<%--        <input type="submit" name="page" value="${number}" >--%>
      </div>

  </form>
</div>


</body>
</html>