<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="div" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ERROR 500 PAGE</title>
    <%@include file="/components/allcss.jsp" %>
    <link rel="stylesheet" type="text/css" href="components/500.css"/>
</head>

<body>

<%@include file="/components/footer.jsp" %>
</body>
</html>