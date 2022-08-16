<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<html>
<head>
    <title>ADMIN: HOME</title>
    <%@include file="/components/admin/allcss.jsp" %>
    <style type="text/css">
        a {
            text-decoration: none;
            color: black;
        }

        a:hover {
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>
<%@include file="/components/navbar.jsp" %>

<div class="container">
    <div class="row p-5">
        <div class="col-md-3">
            <a href="${pageContext.request.contextPath}/admin/addBook">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fas fa-plus-square fa-3x text-primary"></i><br>
                        <h4><fmt:message key="language.addBook"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="${pageContext.request.contextPath}/admin/booksToDelete">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fa-solid fa-trash-can fa-3x text-danger"></i><br>
                        <h4><fmt:message key="language.deleteBook"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="${pageContext.request.contextPath}/admin/update">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fa-solid fa-file-pen fa-3x text-success"></i><br>
                        <h4><fmt:message key="language.updateBook"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="addLibrarian.jsp">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fa-solid fa-user-plus fa-3x text-success"></i><br>
                        <h4><fmt:message key="language.createNewLibrarian"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="${pageContext.request.contextPath}/admin/librariansDelete">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fa-solid fa-user-minus fa-3x text-danger"></i><br>
                        <h4><fmt:message key="language.deleteLibrarian"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="${pageContext.request.contextPath}/admin/block">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fa-solid fa-user-lock fa-3x text-danger"></i><br>
                        <h4><fmt:message key="language.blockUser"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="${pageContext.request.contextPath}/admin/unblock">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fa-solid fa-unlock-keyhole fa-3x text-success"></i><br>
                        <h4><fmt:message key="language.unblockUser"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                <div class="card mt-2">
                    <div class="card-body text-center">
                        <i class="fa-solid fa-right-from-bracket fa-3x text-primary"></i><br>
                        <h4><fmt:message key="language.logOut"/></h4>
                        --------------
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<%--logout modal--%>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <h4><fmt:message key="language.logoutQuotation"/></h4>
                <div class="text-center">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                            key="language.close"/></button>
                    <a href="${pageContext.request.contextPath}/logout" type="button"
                       class="btn btn-primary text-white"><fmt:message key="language.logOut"/></a>
                </div>
            </div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>

<%--end logout modal--%>

<%@include file="/components/admin/footer.jsp" %>
</body>
</html>