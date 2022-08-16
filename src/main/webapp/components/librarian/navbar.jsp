<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<div class="container-fluid" style="height: 10px;background-color:#212529"></div>

<div class="container-fluid p-3 bg-light">
    <div class="row">
        <div class="col-md-3 text-success">
            <h3><i class="fas fa-book"></i><fmt:message key="language.library"/></h3>
        </div>

        <div class="col-md-6">
            <form class="d-flex" role="search" action="search" method="get">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-primary" type="submit"><fmt:message key="language.search"/></button>
            </form>
        </div>

        <%--        <c:if test="${not empty librarian}">--%>
        <div class="col-md-3">
            <a href="" class="btn btn-success"><i
                    class="fa-solid fa-user-plus m-lg-1"></i>${user.name}
            </a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary"><i
                    class="fa-solid fa-right-to-bracket m-lg-1"></i><fmt:message key="language.logOut"/></a>
        </div>
        <%--        </c:if>--%>

        <%--        <c:if test="${empty userobj}">--%>
        <%--            <div class="col-md-3">--%>
        <%--                <a href="login.jsp" class="btn btn-success"><i class="fa-solid fa-user-plus m-lg-1"></i>Login</a>--%>
        <%--                <a href="registration.jsp" class="btn btn-primary"><i class="fa-solid fa-right-to-bracket m-lg-1"></i>Registration</a>--%>
        <%--            </div>`--%>
        <%--        </c:if>--%>

    </div>
</div>


<nav class="navbar navbar-expand-lg bg-dark bg-primary ">
    <div class="container-fluid">
        <a class="nav-link active" aria-current="page"
           href=${pageContext.request.contextPath}/librarian/librarianHome.jsp><i
                class="fa-solid fa-house"></i></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="libNavbar">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/librarian/librarianHome.jsp"><fmt:message
                            key="language.home"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>
