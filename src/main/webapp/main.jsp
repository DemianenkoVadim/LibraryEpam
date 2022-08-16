<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="fancybox/jquery.fancybox.css">
    <link rel="stylesheet" type="text/css" href="components/style.css">

    <title>LIBRARY: MAIN</title>
    <%@include file="components/allcss.jsp" %>
</head>
<body style="background-color: #f7f7f7">

<%@ include file="components/navbar.jsp" %>

<%-- Start Classic Books --%>
<div class="container-fluid">
    <h3 class="text-center"><fmt:message key="language.classic"/></h3>
    <div class="row">

        <c:forEach var="book" items="${classicBooks}" begin="0" end="3">
            <div class="col-md-3">
                <div class="card crd-ho">
                    <div class="card-body text-center ">

                        <div align="center">
                            <a class="first" title="Library" href="image/library/${book.photoName}">
                                <img alt="" src="image/library/${book.photoName}" style="width:150px;height: 200px"
                                     class="img-thumblin"></a>
                        </div>

                        <p><strong><fmt:message key="language.title"/></strong> ${book.title}</p>
                        <p><strong><fmt:message
                                key="language.author"/> </strong> ${book.authorFirstName} ${book.authorLastName}</p>
                        <p><strong><fmt:message key="language.genre"/> </strong> ${book.genreName}</p>
                        <p><strong><fmt:message key="language.publishingHouse"/></strong> ${book.publishingHouseName}
                        </p>
                        <p><strong><fmt:message key="language.published"/> </strong> ${book.published}</p>

                        <div class="row text-center m-lg-2">
                            <c:if test="${empty user}">
                                <a href="login.jsp" class="btn btn-primary btn-sm m-lg-1" style="width: max-content"><i
                                        class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>
                            <c:if test="${not empty user}">
                                <a href="cart?bookId=${book.id}&&userId=${user.id}"
                                   class="btn btn-primary btn-sm m-lg-1" style="width: max-content">
                                    <i class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/></a>
                            </c:if>

                            <a href="viewBook?bookId=${book.id}" class="btn btn-success btn-sm m-lg-1"
                               style="width: auto"><i class="fa-solid fa-file-circle-info"></i><fmt:message
                                    key="language.viewDetails"/></a>

                            <a href="" class="btn btn-outline-primary btn-sm m-lg-1"
                               style="width: auto"><fmt:message key="language.quantity"/> ${book.quantity}<i
                                    class="fa-solid fa-book-copy"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="text-center mt-lg-3">
        <a href="allClassic" class="btn btn-success btn-sm text-white"><fmt:message key="language.viewAllClassic"/> </a>
    </div>
    <hr/>
</div>
<%--  End Classic Books--%>

<%-- Start Fantasy Books  --%>
<div class="container-fluid">
    <h3 class="text-center"><fmt:message key="language.fantasy"/></h3>
    <div class="row">

        <c:forEach var="book" items="${fantasyBooks}" begin="0" end="3">
            <div class="col-md-3">
                <div class="card crd-ho">
                    <div class="card-body text-center ">

                        <div align="center">
                            <a class="first" title="Library" href="image/library/${book.photoName}">
                                <img alt="" src="image/library/${book.photoName}" style="width:150px;height: 200px"
                                     class="img-thumblin"></a>
                        </div>

                        <p><strong><fmt:message key="language.title"/></strong> ${book.title}</p>
                        <p><strong><fmt:message
                                key="language.author"/> </strong> ${book.authorFirstName} ${book.authorLastName}</p>
                        <p><strong><fmt:message key="language.genre"/> </strong> ${book.genreName}</p>
                        <p><strong><fmt:message key="language.publishingHouse"/> </strong> ${book.publishingHouseName}
                        </p>
                        <p><strong><fmt:message key="language.published"/> </strong> ${book.published}</p>

                        <div class="row text-center m-lg-2">
                            <c:if test="${empty user}">
                                <a href="login.jsp" class="btn btn-primary btn-sm m-lg-1" style="width: max-content"><i
                                        class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>
                            <c:if test="${not empty user}">
                                <a href="cart?bookId=${book.id}&&userId=${user.id}"
                                   class="btn btn-primary btn-sm m-lg-1" style="width: max-content">
                                    <i class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>

                            <a href="viewBook?bookId=${book.id}" class="btn btn-success btn-sm m-lg-1"
                               style="width: auto"><i class="fa-solid fa-file-circle-info"></i><fmt:message
                                    key="language.viewDetails"/> </a>

                            <a href="" class="btn btn-outline-primary btn-sm m-lg-1"
                               style="width: auto"><fmt:message key="language.quantity"/> ${book.quantity}<i
                                    class="fa-solid fa-book-copy"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="text-center mt-lg-3">
        <a href="allFantasy" class="btn btn-success btn-sm text-white"><fmt:message key="language.viewAllFantasy"/> </a>
    </div>
    <hr/>
</div>

<%--End Fantasy books--%>

<%-- Start Romance Book --%>
<div class="container-fluid">
    <h3 class="text-center"><fmt:message key="language.romance"/></h3>
    <div class="row">

        <c:forEach var="book" items="${romanceBooks}" begin="0" end="3">
            <div class="col-md-3">
                <div class="card crd-ho">
                    <div class="card-body text-center ">

                        <div align="center">
                            <a class="first" title="Library" href="image/library/${book.photoName}">
                                <img alt="" src="image/library/${book.photoName}" style="width:150px;height: 200px"
                                     class="img-thumblin"></a>
                        </div>

                        <p><strong><fmt:message key="language.title"/> </strong> ${book.title}</p>
                        <p><strong><fmt:message
                                key="language.author"/> </strong> ${book.authorFirstName} ${book.authorLastName}</p>
                        <p><strong><fmt:message key="language.genre"/> </strong> ${book.genreName}</p>
                        <p><strong><fmt:message key="language.publishingHouse"/> </strong> ${book.publishingHouseName}
                        </p>
                        <p><strong><fmt:message key="language.published"/> </strong> ${book.published}</p>

                        <div class="row text-center m-lg-2">
                            <c:if test="${empty user}">
                                <a href="login.jsp" class="btn btn-primary btn-sm m-lg-1" style="width: max-content"><i
                                        class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>
                            <c:if test="${not empty user}">
                                <a href="cart?bookId=${book.id}&&userId=${user.id}"
                                   class="btn btn-primary btn-sm m-lg-1" style="width: max-content">
                                    <i class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>

                            <a href="viewBook?bookId=${book.id}" class="btn btn-success btn-sm m-lg-1"
                               style="width: auto"><i class="fa-solid fa-file-circle-info"></i><fmt:message
                                    key="language.viewDetails"/> </a>

                            <a href="" class="btn btn-outline-primary btn-sm m-lg-1"
                               style="width: auto"><fmt:message key="language.quantity"/> ${book.quantity}<i
                                    class="fa-solid fa-book-copy"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="text-center mt-lg-3">
        <a href="allRomance" class="btn btn-success btn-sm text-white"><fmt:message key="language.viewAllRomance"/> </a>
    </div>
</div>
<%--End Romance Books--%>

<%@include file="/components/footer.jsp" %>
</body
</html>
