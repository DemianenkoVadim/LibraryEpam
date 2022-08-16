<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>USER: PAGE SUCCESS RESULT</title>
    <%@include file="/components/user/allcss.jsp" %>
</head>

<body style="background-color: #f0f1f2">

<div class="container text-center mt-3">
    <i class="fas fa-check-circle fa-5x text-success"></i>
    <h1><fmt:message key="language.thanks"/></h1>
    <h2><fmt:message key="language.orderSuccess"/></h2>
    <h5><fmt:message key="language.infoMessage"/></h5>
    <a href="userHome.jsp" class="btn btn-primary mt-3"><fmt:message key="language.home"/> </a>
    <a href="${pageContext.request.contextPath}/user/userOrders" class="btn btn-danger mt-3"><fmt:message
            key="language.viewOrder"/> </a>
</div>

<%@include file="/components/footer.jsp" %>
</body>
</html>