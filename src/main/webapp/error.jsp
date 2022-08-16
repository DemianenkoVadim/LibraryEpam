<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="div" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ERROR PAGE</title>
    <%@include file="/components/allcss.jsp" %>
    <link rel="stylesheet" type="text/css" href="components/error.css"/>
</head>

<body>

<div class="container text-center mt-3">
    <i class="fas fa-exclamation-circle fa-5x text-danger"></i>
    <h1><fmt:message key="language.error"/></h1>
</div>

<ul>
        <li>Message: ${pageContext.exception.message}</li>
        <li>Request URI: ${pageContext.errorData.requestURI}</li>
</ul>

<%@include file="/components/footer.jsp" %>
</body>
</html>