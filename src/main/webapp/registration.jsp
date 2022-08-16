<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>LIBRARY: REGISTRATION</title>
    <%@include file="components/allcss.jsp" %>
    <link rel="stylesheet" type="text/css" href="components/style.css">
</head>
<body style="background-color: #f0f1f2;">
<%@include file="components/navbar.jsp" %>
<div class="container p-2">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">
                    <h4 class="text-center"><fmt:message key="language.registrationPage"/> </h4>

                    <c:if test="${not empty successMessage}">
                        <p class="text-center text-success">${successMessage}</p>
                        <c:remove var="succesMessage" scope="session"/>
                    </c:if>

                    <c:if test="${not empty failedMessage}">
                        <p class="text-center text-danger">${failedMessage}</p>
                        <c:remove var="failedMessage" scope="session"/>
                    </c:if>

                    <form action="registration" method="post">

                        <div class="mb-3">
                            <label for="exampleInputName1" class="form-label"><fmt:message key="language.enterName"/> </label>
                            <input type="text" class="form-control" id="exampleInputName1"
                                   aria-describedby="nameHelp" required="required" name="name">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label"><fmt:message key="language.emailAddress"/> </label>
                            <input type="email" class="form-control" id="exampleInputEmail1"
                                   aria-describedby="emailHelp" required="required" name="email">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPhoneNumber1" class="form-label"><fmt:message key="language.phoneNumber"/> </label>
                            <input type="text" class="form-control" id="exampleInputPhoneNumber1"
                                   aria-describedby="phoneNumberHelp" required="required" name="phone">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"><fmt:message key="language.password"/> </label>
                            <input type="password" class="form-control" id="exampleInputPassword1" required="required"
                                   name="password">
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" name="check" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1"><fmt:message key="language.condition"/> </label>
                        </div>

                        <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="language.submit"/> </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="components/footer.jsp" %>
</body>
</html>
