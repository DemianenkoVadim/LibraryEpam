<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ADMIN: DELETE BOOK</title>
    <%@include file="/components/admin/allcss.jsp" %>
</head>
<body>
<%@include file="/components/admin/navbar.jsp" %>
<h3 class="text-center"><fmt:message key="language.greeting"/> Admin</h3>

<c:if test="${not empty successMessage}">
    <h5 class="text-center text-success">${successMessage}</h5>
    <c:remove var="successesMessage" scope="session"/>
</c:if>

<c:if test="${not empty failedMessage}">
    <h5 class="text-center text-danger">${failedMessage}</h5>
    <c:remove var="failedMessage" scope="session"/>
</c:if>

<table class="table table-striped">

    <thead class="bg-primary text-white">
    <tr>
        <th scope="col"><fmt:message key="language.id"/></th>
        <th scope="col"><fmt:message key="language.image"/></th>
        <th scope="col"><fmt:message key="language.title"/></th>
        <th scope="col"><fmt:message key="language.author"/></th>
        <th scope="col"><fmt:message key="language.publishingHouse"/></th>
        <th scope="col"><fmt:message key="language.published"/></th>
        <th scope="col"><fmt:message key="language.quantity"/></th>
        <th scope="col"><fmt:message key="language.genre"/></th>
        <th scope="col"><fmt:message key="language.action"/></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="book" items="${books}">
        <tr>
            <th>${book.id}</th>
            <td><img src="../image/library/${book.photoName}" style="width: 50px; height: 50px" alt="smth"></td>
            <td>${book.title}</td>
            <td>${book.authorFirstName} ${book.authorLastName}</td>
            <td>${book.publishingHouseName}</td>
            <td>${book.published}</td>
            <td>${book.quantity}</td>
            <td>${book.genreName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/deleteBook?id=${book.id}"
                   class="btn btn-sm btn-danger"><i
                        class="fas fa-trash-alt m-lg-1"></i><fmt:message key="language.remove"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@include file="/components/footer.jsp" %>
</body>
</html>
