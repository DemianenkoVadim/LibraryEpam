<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/user/style.css">
    <title>USER: HOME PAGE</title>
    <%@include file="/components/user/allcss.jsp" %>
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
<%@include file="/components/user/navbar.jsp" %>

<%--<c:if test="${empty user}">--%>
<%--    <c:redirect url="../login.jsp"/>--%>
<%--</c:if>--%>

<div class="container">
    <c:if test="${not empty user}">
        <h3 class="text-center"><fmt:message key="language.greeting"/> ${user.name} </h3>
    </c:if>
    <div class="row p-5">
        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/user/userSubscription">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <div class="text-primary">
                            <i class="fas fa-book-open fa-3x"></i>
                        </div>
                        <h3><fmt:message key="language.booksOnSubscription"/></h3>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/user/editProfile.jsp">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <div class="text-primary">
                            <i class="fa-solid fa-id-card fa-3x"></i>
                        </div>
                        <h3><fmt:message key="language.loginSecurity"/></h3>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/user/userOrders">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <div class="text-primary">
                            <i class="fas fa-box-open fa-3x"></i>
                        </div>
                        <h3><fmt:message key="language.myOrders"/></h3>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-6">
            <a data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <div class="text-primary">
                            <i class="fa-solid fa-right-from-bracket fa-3x text-primary"></i><br>
                        </div>
                        <h3><fmt:message key="language.logOut"/></h3>
                    </div>
                </div>
            </a>
        </div>

    </div>
</div>

<%--logout modal--%>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h4><fmt:message key="language.logoutQuotation"/></h4>
                <div class="text-center">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                            key="language.close"/></button>
                    <a href="${pageContext.request.contextPath}/logout" type="button"
                       class="btn btn-primary text-white"><fmt:message key="language.logOut"/></a>
                </div>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>

<%--end logout modal--%>

<%@include file="/components/user/footer.jsp" %>

</body>
</html>
