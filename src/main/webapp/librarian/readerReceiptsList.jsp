<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>LIBRARIAN: USER RECEIPTS LIST</title>
    <%@include file="/components/librarian/allcss.jsp" %>
</head>
<body>
<%@include file="/components/librarian/navbar.jsp" %>

<c:if test="${empty user}">
    <c:redirect url="../login.jsp"/>
</c:if>

<c:if test="${not empty successMessage}">
    <div class="alert alert-success text-center" role="alert">${successMessage}</div>
    <c:remove var="successMessage" scope="session"/>
</c:if>

<c:if test="${not empty failedMessage}">
    <div class="alert alert-danger text-center" role="alert">${failedMessage}</div>
    <c:remove var="failedMessage" scope="session"/>
</c:if>

<div class="container p-3">
    <c:if test="${not empty user}">
        <h3 class="text-center"><fmt:message key="language.greeting"/> ${user.name} </h3>
        <div class="row">
            <div class="col">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="fs-6-bold text-center text-black">All Users description receipts</p>
                        <table class="table table-striped">
                            <thead class="bg-primary text-white">
                            <tr>
                                <th scope="col"><fmt:message key="language.userEmail"/></th>
                                <th scope="col" class="text-center"><fmt:message key="language.orderID"/></th>
                                <th scope="col"><fmt:message key="language.image"/></th>
                                <th scope="col"><fmt:message key="language.title"/></th>
                                <th scope="col"><fmt:message key="language.author"/></th>
                                <th scope="col"><fmt:message key="language.receivingDate"/></th>
                                <th scope="col"><fmt:message key="language.estimateReturnDate"/></th>
                                <th scope="col"><fmt:message key="language.realReturnDate"/></th>
                                <th scope="col"><fmt:message key="language.stage"/></th>
                                <th scope="col" class="text-center"><fmt:message key="language.penalty"/></th>
                                <th scope="col"><fmt:message key="language.rent"/></th>
                                <th scope="col"><fmt:message key="language.action"/></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="order" items="${receipt}">
                                <tr>
                                    <th>${order.email}</th>
                                    <th class="text-center">${order.id}</th>
                                    <td><img src="../image/library/${order.photoName}"
                                             style="width: 50px; height: 50px" alt="smth"></td>
                                    <td>${order.title}</td>
                                    <td>${order.authorFirstName} ${order.authorLastName}</td>
                                    <td>${order.receivingDate.toLocalDate()} ${order.receivingDate.toLocalTime().withNano(0)}</td>
                                    <td>${order.estimateReturnDate.toLocalDate()} ${order.estimateReturnDate.toLocalTime().withNano(0)}</td>
                                    <td>${order.realReturnDate.toLocalDate()} ${order.estimateReturnDate.toLocalTime().withNano(0)}</td>
                                    <td>${order.stage}</td>
                                    <td class="text-center">${order.penalty}</td>
                                    <td>${order.rent}</td>
                                    <td>
                                        <div class="d-grid gap-1">
                                            <a href="${pageContext.request.contextPath}/librarian/proceed?bookId=${order.bookId}&&userId=${order.userId}&&receiptId=${order.id}"
                                               class="btn btn-sm btn-warning"><fmt:message key="language.process"/> </a>
                                            <a href="${pageContext.request.contextPath}/librarian/lend?bookId=${order.bookId}&&userId=${order.userId}&&receiptId=${order.id}"
                                               class="btn btn-sm  btn-success"><fmt:message key="language.lend"/></a>
                                            <a href="${pageContext.request.contextPath}/librarian/return?bookId=${order.bookId}&&userId=${order.userId}&&receiptId=${order.id}"
                                               class="btn btn-sm  btn-primary"><fmt:message key="language.return"/></a>
                                        </div>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>

<%@include file="/components/footer.jsp" %>
</body>
</html>
