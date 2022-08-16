<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>USER: PROFILE</title>
    <%@include file="/components/user/allcss.jsp" %>
</head>
<body style="background-color: #f0f1f2">
<%@include file="/components/user/navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">

                    <h4 class="text-center text-primary"><fmt:message key="language.editProfile"/></h4>

                    <c:if test="${not empty failedMessage}">
                        <h5 class="text-center text-danger">${failedMessage}</h5>
                        <c:remove var="failedMessage" scope="session"/>
                    </c:if>

                    <c:if test="${not empty successMessage}">
                        <h5 class="text-center text-success">${successMessage}</h5>
                        <c:remove var="successMessage" scope="session"/>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/user/updateProfile" method="post">
                        <input type="hidden" value="${user.id}" name="id">

                        <div class="mb-3">
                            <label for="exampleInputName1" class="form-label"><fmt:message
                                    key="language.enterName"/> </label>
                            <input type="text" class="form-control" id="exampleInputName1"
                                   aria-describedby="nameHelp" required="required" name="name" value="${user.name}">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label"><fmt:message
                                    key="language.emailAddress"/> </label>
                            <input type="email" class="form-control" id="exampleInputEmail1"
                                   aria-describedby="emailHelp" required="required" name="email"
                                   value="${user.email}">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPhoneNumber1" class="form-label"><fmt:message
                                    key="language.phoneNumber"/> </label>
                            <input type="text" class="form-control" id="exampleInputPhoneNumber1"
                                   aria-describedby="phoneNumberHelp" required="required" name="phone"
                                   value="${user.phone}">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"><fmt:message
                                    key="language.password"/> </label>
                            <input type="password" class="form-control" id="exampleInputPassword1" required="required"
                                   name="password">
                        </div>
                        <div class="text-center p-2">
                            <button type="submit" class="btn btn-primary"><fmt:message key="language.update"/></button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/components/user/footer.jsp" %>

</body>
</html>