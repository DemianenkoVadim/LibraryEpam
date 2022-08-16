<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>Library: Login</title>
    <%@include file="components/allcss.jsp" %>
    <link rel="stylesheet" type="text/css" href="components/style.css">
</head>
<body style="background-color: #f0f1f2;">
<%@include file="components/navbar.jsp" %>
<div class="container p-2">
    <div class="row mt-2">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">

                    <h4 class="text-center"><fmt:message key="language.loginPage"/></h4>


                    <c:if test="${not empty failedMessage}">
                        <h5 class="text-center text-danger">${failedMessage}</h5>
                        <c:remove var="failedMessage" scope="session"/>
                    </c:if>

                    <c:if test="${not empty successMessage}">
                        <h5 class="text-center text-success">${successMessage}</h5>
                        <c:remove var="successMessage" scope="session"/>
                    </c:if>
                    `
                    <form action="login" method="post">
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label"><fmt:message
                                    key="language.emailAddress"/> </label>
                            <input type="email" class="form-control" id="exampleInputEmail1"
                                   aria-describedby="emailHelp" required="required" name="email">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"><fmt:message
                                    key="language.password"/> </label>
                            <input type="password" class="form-control" id="exampleInputPassword1" required="required"
                                   name="password">
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1"><fmt:message
                                    key="language.checkMeOut"/> </label>
                        </div>

                        <div class="mb-3 text-center">
                            <button type="submit" class="btn btn-primary col-md-12"><fmt:message
                                    key="language.login"/></button>
                            <br><fmt:message key="language.accountQuestion"/> <a href="registration.jsp"
                                                                                 class="text-decoration-none"><fmt:message
                                key="language.createAccount"/> </a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/components/footer.jsp" %>

</body>
</html>