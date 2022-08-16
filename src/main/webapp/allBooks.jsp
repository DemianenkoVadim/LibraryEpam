<%--@elvariable id="pagination" type="ua.com.epam.library.util.pagination.PaginationPage"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>LIBRARY: ALL BOOKS</title>
    <%@include file="components/allcss.jsp" %>
    <link rel="stylesheet" type="text/css" href="components/style.css">
</head>
<body>

<%@include file="components/navbar.jsp" %>

<c:if test="${not empty addCart}">
    <div class="alert alert-success text-center" role="alert">${addCart}</div>
    <c:remove var="addCart" scope="session"/>
</c:if>

<c:if test="${not empty failed}">
    <div class="alert alert-danger text-center" role="alert">${failed}</div>
    <c:remove var="failed" scope="session"/>
</c:if>

<div class="container-fluid">
    <div class="col-md-2 offset-md-auto">

        <form class="d-flex" action="allBooks" method="get">
            <div class="col-md-10 p-2">
                <label for="inputState"></label>
                <select id="inputState" name="sort" class="form-control me-2">
                    <option ${sort == '' ? 'selected' : ''}><fmt:message key="language.selectSorting"/></option>
                    <option value="titleAsc" ${sort == 'titleAsc' ? 'selected' : ''}><fmt:message
                            key="language.titleAZ"/></option>
                    <option value="titleDesc" ${sort == 'titleDesc' ? 'selected' : ''}><fmt:message
                            key="language.titleZA"/></option>
                    <option value="authorAsc" ${sort == 'autorAsc' ? 'selected' : ''}><fmt:message
                            key="language.authorAZ"/></option>
                    <option value="authorDesc" ${sort == 'authorDesc' ? 'selected' : ''}><fmt:message
                            key="language.authorZA"/></option>
                    <option value="publishingAsc" ${sort == 'publishingAsc' ? 'selected' :''}><fmt:message
                            key="language.publishingAZ"/></option>
                    <option value="publishingDesc" ${sort == 'publishingDesc' ? 'selected': ''}><fmt:message
                            key="language.publishingZA"/></option>
                    <option value="publishingHouseAsc" ${sort == 'publishingHouseAsc' ? 'selected' :''}><fmt:message
                            key="language.publishingHouseAZ"/></option>
                    <option value="publishingHouseDesc" ${sort == 'publishingHouseDesc' ? 'selected': ''}><fmt:message
                            key="language.publishingHouseZA"/></option>
                </select>
                <input type="hidden" value="${pagination.page}" name="page"/>
            </div>

            <div class="col-md-10 p-2">
                <button class="btn btn-primary" type="submit"><fmt:message key="language.sort"/></button>
            </div>
        </form>

        <div class="d-inline-relative bd-highlight" style="margin-left: 1335px; margin-top: -60px">
            <ul class="pagination pagination justify-content-center">
                <li class="page-item  ${pagination.firstPage ? 'disabled' : ''}">
                    <a class="page-link" ${pagination.firstPage ? 'tabindex="-1" aria-disabled="true"' : ''}
                       href="${pageContext.request.contextPath}/allBooks?page=${pagination.activePage-1}&sort=${sort}"><fmt:message
                            key="language.previous"/> </a>
                </li>

                <%--Page Numbers--%>

                <li class="page-item ${pagination.previousPageUi == pagination.page ? 'active' : ''}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/allBooks?page=${pagination.previousPageUi}&sort=${sort}">${pagination.previousPageUi}</a>
                </li>

                <li class="page-item ${pagination.currentPageUi == pagination.page ? 'active' : ''}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/allBooks?page=${pagination.currentPageUi}&sort=${sort}">${pagination.currentPageUi}</a>
                </li>

                <li class="page-item ${pagination.nextPageUi == pagination.page ? 'active' : ''}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/allBooks?page=${pagination.nextPageUi}&sort=${sort}">${pagination.nextPageUi}</a>
                </li>

                <li class="page-item  ${pagination.lastPage ? 'disabled' : ''}">
                    <a class="page-link" ${pagination.lastPage ? 'tabindex="-1" aria-disabled="true"' : ''}
                       href="${pageContext.request.contextPath}/allBooks?page=${pagination.activePage+1}&sort=${sort}"><fmt:message
                            key="language.next"/> </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="row p-3">

        <c:forEach var="book" items="${catalog}">
            <div class="col-md-3">
                <div class="card crd-ho mt-2">
                    <div class="card-body text-center ">
                        <img alt="" src="${pageContext.request.contextPath}/image/library/${book.photoName}"
                             style="width:100px;height: 150px" class="img-thumblin">
                        <p><strong><fmt:message key="language.title"/> </strong> ${book.title}</p>
                        <p><strong><fmt:message
                                key="language.author"/> </strong> ${book.authorFirstName}${book.authorLastName}
                        <p><strong><fmt:message key="language.genre"/> </strong> ${book.genreName}</p>
                        <p><strong><fmt:message key="language.publishingHouse"/> </strong> ${book.publishingHouseName}
                        </p>
                        <p><strong><fmt:message key="language.published"/> </strong> ${book.published}</p>

                        <div class="row text-center m-lg-2">
                            <c:if test="${empty user}">
                                <a href="login.jsp" class="btn btn-primary btn-sm m-lg-1"
                                   style="width: max-content"><i
                                        class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>

                            <c:if test="${not empty user}">
                                <a href="${pageContext.request.contextPath}/user/cart?bookId=${book.id}&&userId=${user.id}"
                                   class="btn btn-primary btn-sm m-lg-1"
                                   style="width: max-content"><i
                                        class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>

                            <a href="viewBook?bookId=${book.id}" class="btn btn-success btn-sm m-lg-1"
                               style="width: auto"><i class="fa-solid fa-file-circle-info"></i><fmt:message
                                    key="language.viewDetails"/></a>
                            <a href="" class="btn btn-outline-primary btn-sm m-lg-1"
                               style="width: auto"><fmt:message key="language.quantity"/> ${book.quantity}<i
                                    class="fa-solid fa-book-copy"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="/components/footer.jsp" %>
</body>
</html>