<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/librarian/style.css">
    <title>Librarian: Home page</title>
    <%@include file="/components/librarian/allcss.jsp" %>
    <style type="text/css">
        a {
            text-decoration: none;
            color: black;
        }

        a:hover {
            text-decoration: none;
        }
    </style>
</head>
<body style="background-color: #f7f7f7">
<%@include file="/components/librarian/navbar.jsp" %>

<%--<c:if test="${empty user}">--%>
<%--    <c:redirect url="../login.jsp"/>--%>
<%--</c:if>--%>

<div class="container">
    <c:if test="${not empty user}">
        <h3 class="text-center"><fmt:message key="language.greeting"/> ${user.name} </h3>
    </c:if>
    <div class="row p-5">
        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/librarian/allReceipts">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <div class="text-primary">
                            <i class="fas fa-book-open fa-3x"></i>
                        </div>
                        <h4><fmt:message key="language.readerOrderList"/></h4>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/librarian/usersDescriptions">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <div class="text-primary">
                            <i class="fa-solid fa-user fa-3x"></i>
                        </div>
                        <h4><fmt:message key="language.listOfReadersAndTheirSubscriptions"/></h4>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
<%@include file="/components/footer.jsp" %>
</body>
</html>
