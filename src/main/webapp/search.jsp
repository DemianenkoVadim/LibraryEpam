<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>LIBRARY: SEARCH BOOKS</title>
    <%@include file="components/allcss.jsp" %>
    <link rel="stylesheet" type="text/css" href="components/style.css">
</head>
<body>

<%@include file="components/navbar.jsp" %>
<div class="container-fluid">
    <div class="row p-3">

        <c:forEach var="book" items="${findResult}">
            <div class="col-md-3">
                <div class="card crd-ho mt-2">
                    <div class="card-body text-center ">
                        <img alt="" src="image/library/${book.photoName}"
                             style="width:100px;height: 150px" class="img-thumblin">
                        <p><strong><fmt:message key="language.title"/> </strong>${book.title}</p>
                        <p><strong><fmt:message
                                key="language.author"/> </strong>${book.authorFirstName} ${book.authorLastName}</p>
                        <p><strong><fmt:message key="language.genre"/> </strong>${book.genreName}</p>
                        <p><strong><fmt:message key="language.publishingHouse"/> </strong>${book.publishingHouseName}
                        </p>
                        <p><strong><fmt:message key="language.published"/> </strong>${book.published}</p>
                        <div class="row text-center m-lg-2">
                            <c:if test="${empty user}">
                                <a href="login.jsp" class="btn btn-primary btn-sm m-lg-1" style="width: max-content"><i
                                        class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>
                            <c:if test="${not empty user}">
                                <a href="cart?bookId=${book.id}&&userId=${user.id}"
                                   class="btn btn-primary btn-sm m-lg-1"
                                   style="width: max-content"><i
                                        class="fas fa-cart-plus m-lg-1"></i><fmt:message key="language.addCart"/> </a>
                            </c:if>

                            <a href="viewBook.jsp?bookId=${book.id}" class="btn btn-success btn-sm m-lg-1"
                               style="width: auto"><i class="fa-solid fa-file-circle-info"></i><fmt:message
                                    key="language.viewDetails"/> </a>
                            <a href="" class="btn btn-outline-primary btn-sm m-lg-1" style="width: auto"><fmt:message
                                    key="language.quantity"/> ${book.quantity}<i
                                    class="fa-solid fa-book-copy"></i>
                            </a>
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