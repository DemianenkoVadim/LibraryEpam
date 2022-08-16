<%--@elvariable id="book" type="ua.com.epam.library.entity.Book"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ADMIN: EDIT BOOK</title>
    <%@include file="/components/admin/allcss.jsp" %>
</head>
<body style="background-color: #f0f2f2">
<%@include file="/components/admin/navbar.jsp" %>

<c:if test="${empty admin}">
    <c:redirect url="/login.jsp"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">

                    <h4 class="text-center"><fmt:message key="language.editBook"/></h4>

                    <c:if test="${not empty successMessage}">
                        <p class="text-center text-success">${successMessage}</p>
                        <c:remove var="successMessage" scope="session"/>
                    </c:if>

                    <c:if test="${not empty failedMessage}">
                        <p class="text-center text-danger">${failedMessage}</p>
                        <c:remove var="failedMessage" scope="session"/>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/admin/updateBook" method="post"
                          enctype="multipart/form-data">
                        <input type="hidden" value="${book.id}" name="id">

                        <div class="form-group">
                            <label for="exampleInputBookTitle1" class="form-label"><fmt:message
                                    key="language.name"/> </label>
                            <input type="text" class="form-control" id="exampleInputBookTitle1"
                                   aria-describedby="titleHelp" required="required" name="title" value="${book.title}">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputPublishingHouse1"><fmt:message
                                    key="language.publishingHouse"/></label> <input
                                name="publishingHouse" type="text" class="form-control"
                                id="exampleInputPublishingHouse1" aria-describedby="titleHelp" required="required"
                                value="${book.publishingHouseName}">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputBookPublished1"><fmt:message key="language.published"/></label>
                            <input
                                    name="published" type="text" class="form-control"
                                    id="exampleInputBookPublished1" aria-describedby="titleHelp" required="required"
                                    value="${book.published}">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputQuantity1"><fmt:message key="language.quantity"/></label> <input
                                name="quantity" type="text" class="form-control"
                                id="exampleInputQuantity1" aria-describedby="titleHelp" required="required"
                                value="${book.quantity}">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputDescription1"><fmt:message key="language.shortDescription"/></label>
                            <input
                                    name="description" type="text" class="form-control"
                                    id="exampleInputDescription1" aria-describedby="titleHelp" required="required"
                                    value="${book.description}">
                        </div>

                        <div class="form-group">
                            <label for="inputState"><fmt:message key="language.genre"/> </label>
                            <select id="inputState" name="genre" class="form-control">
                                <option selected><fmt:message key="language.select"/></option>
                                <option value="Romance"><fmt:message key="language.romance"/></option>
                                <option value="Fantasy"><fmt:message key="language.fantasy"/></option>
                                <option value="Classic"><fmt:message key="language.classic"/></option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputBookAuthorFirstName1"><fmt:message
                                    key="language.authorFirstName"/></label> <input
                                name="authorFirstName" type="text" class="form-control"
                                id="exampleInputBookAuthorFirstName1" aria-describedby="titleHelp"
                                required="required" value="${book.authorFirstName}">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputBookAuthorLastName1"><fmt:message
                                    key="language.authorLastName"/></label> <input
                                name="authorLastName" type="text" class="form-control"
                                id="exampleInputBookAuthorLastName1" aria-describedby="titleHelp" required="required"
                                value="${book.authorLastName}">
                        </div>

                        <div class="form-group">
                            <label for="exampleFormControlFile1"><fmt:message key="language.uploadPhoto"/></label>
                            <input name="bookImage" type="file" class="form-control-file" id="exampleFormControlFile1"
                                   required="required" value="${book.photoName}">
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary "><fmt:message key="language.update"/></button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/components/admin/footer.jsp" %>
</body>
</html>
