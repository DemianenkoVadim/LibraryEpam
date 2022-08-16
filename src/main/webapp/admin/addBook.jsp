<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ADMIN: ADD BOOK</title>
    <%@include file="/components/admin/allcss.jsp" %>
</head>
<body style="background-color: #f0f2f2">
<%@include file="/components/admin/navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">
                    <h4 class="text-center">Add book</h4>

                    <c:if test="${not empty successesMessage}">
                        <h5 class="text-center text-success">${successesMessage}</h5>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/addBook"
                          method="post"
                          enctype="multipart/form-data"
                          class="needs-validation" novalidate>

                        <div class="form-group input-area">
                            <label for="exampleInputBookTitle1" class="form-label"><fmt:message
                                    key="language.title"/></label>
                            <input name="title"
                                   type="text"
                                   class="form-control"
                                   id="exampleInputBookTitle1"
                                   aria-describedby="exampleInputBookTitle1"
                                   required="required"

                            <c:if test="${not empty book.title}">
                                   value="${book.title}"
                            </c:if>>
                            <c:if test="${not empty errors.title}">
                                <div class="text-danger" id="title1">
                                        ${errors.title}
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputPublishingHouse1"><fmt:message
                                    key="language.publishingHouse"/></label>
                            <input name="publishingHouse"
                                   type="text"
                                   class="form-control"
                                   id="exampleInputPublishingHouse1"
                                   aria-describedby="titleHelp"
                                   required="required"

                            <c:if test="${not empty book.publishingHouse}">
                                   value="${book.publishingHouse}"
                            </c:if>>
                            <c:if test="${not empty errors.publishingHouse}">
                                <div class="text-danger" id="publishingHouse1">
                                        ${errors.publishingHouse}
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputBookPublished1"><fmt:message
                                    key="language.published"/></label>
                            <input name="published"
                                   type="text"
                                   class="form-control"
                                   id="exampleInputBookPublished1"
                                   aria-describedby="titleHelp"
                                   required="required"
                            <c:if test="${not empty book.published}">
                                   value="${book.published}"
                            </c:if>>
                            <c:if test="${not empty errors.published}">
                                <div class="text-danger" id="published1">
                                        ${errors.published}
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputQuantity1"><fmt:message key="language.quantity"/> </label>
                            <input name="quantity"
                                   type="text"
                                   class="form-control"
                                   id="exampleInputQuantity1"
                                   aria-describedby="titleHelp"
                                   required="required"
                            <c:if test="${not empty book.quantity}">
                                   value="${book.quantity}"
                            </c:if>>
                            <c:if test="${not empty errors.quantity}">
                                <div class="text-danger" id="quantity1">
                                        ${errors.quantity}
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputDescription1"><fmt:message key="language.shortDescription"/></label>
                            <input name="description"
                                   type="text"
                                   class="form-control"
                                   id="exampleInputDescription1"
                                   aria-describedby="titleHelp"
                                   required="required"
                            <c:if test="${not empty book.description}">
                                   value="${book.description}"
                            </c:if>>
                            <c:if test="${not empty errors.description}">
                                <div class="text-danger" id="description">
                                        ${errors.description}
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="inputState"><fmt:message key="language.genre"/></label>
                            <select id="inputState" name="genre" class="form-control">
                                <option selected><fmt:message key="language.select"/></option>
                                <c:forEach var="genre" items="${genres}">
                                    <option value="${genre.genre}">${genre.genre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group" id="survey_options_first_name">
                            <label for="exampleInputBookAuthorFirstName1"><fmt:message
                                    key="language.authorFirstName"/> </label>
                            <input name="authorFirstName"
                                   type="text"
                                   class="form-control"
                                   id="exampleInputBookAuthorFirstName1"
                                   aria-describedby="titleHelp"
                                   required="required"
                            >
                        </div>
                        <div class="controls">
                            <a href="#" id="add_more_author_first_name_field" style="color: mediumseagreen"><i
                                    class="fa fa-plus"></i>Add More</a>
                            <a href="#" id="remove_field_author_first_name" style="color: mediumseagreen"><i
                                    class="fa fa-plus"></i>Remove Field</a>
                        </div>
                        <br>

                        <div class="form-group" id="survey_options_last_name">
                            <label for="exampleInputBookAuthorLastName1"><fmt:message
                                    key="language.authorLastName"/></label>
                            <input name="authorLastName"
                                   type="text"
                                   class="form-control"
                                   id="exampleInputBookAuthorLastName1"
                                   aria-describedby="titleHelp"
                                   required="required">
                        </div>

                        <div class="controls">
                            <a href="#" id="add_more_author_last_name_field" style="color:mediumseagreen"><i
                                    class="fa fa-plus"></i>Add More</a>
                            <a href="#" id="remove_field_author_last_name" style="color: mediumseagreen"><i
                                    class="fa fa-plus"></i>Remove Field</a>
                        </div>
                        <script src="${pageContext.request.contextPath}/components/script.js"></script>
                        <br>
                        <div class="form-group">
                            <label for="exampleFormControlFile1"><fmt:message key="language.uploadPhoto"/> </label>
                            <input name="bookImage"
                                   type="file"
                                   class="form-control-file"
                                   id="exampleFormControlFile1"
                                   required="required">
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary"><fmt:message
                                key="language.add"/></button>

                    </form>
                    <script src="${pageContext.request.contextPath}/components/validation.js"></script>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/components/admin/footer.jsp" %>
</body>
</html>
