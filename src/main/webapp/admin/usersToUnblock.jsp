<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ADMIN: UNLOCK USER</title>
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
        <th scope="col"><fmt:message key="language.name"/></th>
        <th scope="col"><fmt:message key="language.email"/></th>
        <th scope="col"><fmt:message key="language.phone"/></th>
        <th scope="col"><fmt:message key="language.role"/></th>
        <th scope="col"><fmt:message key="language.status"/></th>
        <th scope="col"><fmt:message key="language.create"/></th>
        <th scope="col"><fmt:message key="language.updated"/></th>
        <th scope="col"><fmt:message key="language.action"/></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${customers}">
    <tr>
        <th>${user.id}</th>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.phone}</td>
        <td>${user.role}</td>
        <td>${user.status.name()}</td>
        <td>${user.created.toLocalDate() }</td>
        <td>${user.updated.toLocalDate()}</td>
        <td>
            <a href="${pageContext.request.contextPath}/admin/unblockUser?id=${user.id}" class="btn btn-sm btn-success"><fmt:message
                    key="language.unblock"/> </a>
        </td>
        </c:forEach>
    </tr>
    </tbody>
</table>
<%@include file="/components/admin/footer.jsp" %>
</body>
</html>
