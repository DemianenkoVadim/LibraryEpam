<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>USER: CART</title>
    <%@include file="/components/user/allcss.jsp" %>

</head>
<body style="background-color: #f0f1f2">
<%@include file="/components/user/navbar.jsp" %>

<c:if test="${empty user}">
    <c:redirect>login.jsp</c:redirect>
</c:if>


<c:if test="${not empty successMessage}">
    <div class="alert alert-success text-center" role="alert">${successMessage}</div>
    <c:remove var="successMessage" scope="session"/>
</c:if>

<c:if test="${not empty failedMessage}">
    <div class="alert alert-danger text-center" role="alert">${failedMessage}</div>
    <c:remove var="failedMessage" scope="session"/>
</c:if>

<div class="container">
    <div class="row p-2">
        <div class="col-md-6">
            <div class="card bg-white">
                <div action="card-body">
                    <h3 class="text-center text-success"><fmt:message key="language.selectedItem"/></h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="language.id"/></th>
                            <th scope="col"><fmt:message key="language.image"/></th>
                            <th scope="col"><fmt:message key="language.title"/></th>
                            <th scope="col"><fmt:message key="language.title"/></th>
                            <th scope="col"><fmt:message key="language.quantity"/></th>
                            <th scope="col"><fmt:message key="language.total"/></th>
                            <th scope="col"><fmt:message key="language.action"/></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="cart" items="${books}">
                            <tr>
                                <td>${cart.id}</td>
                                <td><img src="${pageContext.request.contextPath}/image/library/${cart.photoName}"
                                         style="width: 50px; height: 50px"
                                         alt="smth"></td>
                                <td>${cart.authorLastName} ${cart.authorFirstName}</td>
                                <td>${cart.title}</td>
                                <td class="text-center">${cart.quantity}</td>
                                <td class="text-center">${cart.total}</td>

                                <td>
                                    <a href="${pageContext.request.contextPath}/user/remove?bookId=${cart.bookId}&&userId=${cart.userId}&&cartId=${cart.id}"
                                       class="btn btn-sm btn-danger"><fmt:message key="language.remove"/> </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center text"><fmt:message key="language.detailsOrder"/></h3>
                    <form class="row g-3" action="${pageContext.request.contextPath}/user/receipt" method="post">
                        <input type="hidden" value="${user.id}" name="userId">
                        <div class="form-group col-md-6">
                            <label for="inputName"><fmt:message key="language.name"/></label>
                            <input type="text" class="form-control" id="inputName" value="${user.name}" required
                                   name="name"
                                   readonly="readonly">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputEmail"><fmt:message key="language.email"/> </label>
                            <input type="email" class="form-control" id="inputEmail" value="${user.email}" required
                                   name="email"
                                   readonly="readonly">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="inputPhone"><fmt:message key="language.phone"/> </label>
                            <input type="tel" class="form-control" id="inputPhone" value="${user.phone}" required
                                   name="phone" readonly="readonly">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="inputTotal"><fmt:message key="language.totalBooks"/> </label>
                            <input type="text" class="form-control" id="inputTotal" value="${books.size()}" required
                                   readonly="readonly">
                        </div>

                        <div class="form-group">
                            <label><fmt:message key="language.serviceRent"/> </label><label>
                            <select class="form-control" name="rent">
                                <option value="UNSELECT"><fmt:message key="language.select"/></option>
                                <option value="SUBSCRIPTION"><fmt:message key="language.subscription"/></option>
                                <option value="READING_ROOM"><fmt:message key="language.readingRoom"/></option>
                            </select>
                        </label>
                        </div>

                        <div class="text-center">
                            <button class="btn btn-warning"><fmt:message key="language.orderNow"/></button>
                            <a href="${pageContext.request.contextPath}/allBooks" class="btn btn-success"><fmt:message
                                    key="language.chooseOneElseBook"/> </a>
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
