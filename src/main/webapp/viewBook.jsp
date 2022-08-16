<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ page errorPage="error.jsp" %>--%>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>LIBRARY: BOOK VIEW</title>
    <%@include file="components/allcss.jsp" %>
    <link rel="stylesheet" type="text/css" href="components/style.css">
</head>
<body style="background: #f0f1f2">
<%@include file="components/navbar.jsp" %>

<div class="container p-3">
    <div class="row p-2">

        <div class="col-md-6 text-center p-5 border bg-white">
            <img src="image/library/${book.photoName}" style="height: 200px;width: 150px" alt=""><br>
            <h4 class="mt-3"><fmt:message key="language.title"/> <span class="text-success">${book.title}</span></h4>
            <h4><fmt:message key="language.author"/> <span
                    class="text-success">${book.authorFirstName} ${book.authorLastName}</span></h4>
            <h4><fmt:message key="language.genre"/> <span class="text-success">${book.genreName}</span></h4>
        </div>

        <div class="col-md-6 text-center p-5 border bg-white">

            <h2><fmt:message key="language.shortDescription"/></h2>
            <h6><fmt:message key="language.dateAddBookToLibrary"/> ${book.created.toLocalDate()}</h6>
            <div class="row">
                <blockquote class="blockquote">
                    <p><h6>${book.description}</h6></p>
                </blockquote>
            </div>
            <div class="text-center">

                <c:if test="${empty user}">
                    <a href="lo4gin.jsp" class="btn btn-primary btn-sm m-lg-1" style="width: max-content"><i
                            class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                </c:if>

                <c:if test="${not empty user}">
                    <a href="${pageContext.request.contextPath}/user/cart?bookId=${book.id}&&userId=${user.id}"
                       class="btn btn-primary btn-sm m-lg-1" style="width: max-content">
                        <i class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                </c:if>

                <a href="" class="btn btn-primary btn-sm m-lg-1" style="width: auto"><fmt:message
                        key="language.quantity"/> ${book.quantity}</a>
            </div>

        </div>
    </div>
</div>

<%@include file="components/footer.jsp" %>
</body>
</html>


