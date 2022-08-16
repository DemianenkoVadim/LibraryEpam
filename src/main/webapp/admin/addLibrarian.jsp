<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ADMIN: ADD LIBRARIAN</title>
    <%@include file="/components/admin/allcss.jsp" %>
</head>
<body style="background-color: #f0f2f2">
<%@include file="/components/admin/navbar.jsp" %>

<c:if test="${empty admin}">
    <c:redirect url="/login.jsp"/>
</c:if>

<div class="container p-2">
    <div class="row mt-2">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">
                    <h4 class="text-center"><fmt:message key="language.createLibrarian"/></h4>

                    <c:if test="${not empty successMessage}">
                        <h5 class="text-center text-success">${successMessage}</h5>
                    </c:if>

                    <form action="<c:url value="/admin/createLibrarian"/>" method="post">

                        <div class="mb-3">
                            <label for="exampleInputName1" class="form-label"><fmt:message
                                    key="language.enterName"/> </label>
                            <input type="text" class="form-control" id="exampleInputName1"
                                   aria-describedby="nameHelp" required="required" name="name">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label"><fmt:message
                                    key="language.emailAddress"/></label>
                            <input type="email" class="form-control" id="exampleInputEmail1"
                                   aria-describedby="emailHelp" required="required" name="email">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPhoneNumber1" class="form-label"><fmt:message
                                    key="language.phoneNumber"/></label>
                            <input type="text" class="form-control" id="exampleInputPhoneNumber1"
                                   aria-describedby="phoneNumberHelp" required="required" name="phone">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"><fmt:message
                                    key="language.password"/></label>
                            <input type="password" class="form-control" id="exampleInputPassword1" required="required"
                                   name="password">
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" name="check" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1"><fmt:message
                                    key="language.condition"/></label>
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="language.submit"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/components/admin/footer.jsp" %>

</body>
</html>
