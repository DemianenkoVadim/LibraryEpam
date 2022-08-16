<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/language"/>

<%--if user import user--%>
<c:choose>
    <c:when test="${user.role == 'user'}">
        <%@include file="/components/user/navbar.jsp" %>
    </c:when>
    <c:when test="${user.role == 'admin'}">
        <%@include file="/components/admin/navbar.jsp" %>
    </c:when>
    <c:when test="${user.role == 'librarian'}">
        <%@include file="/components/librarian/navbar.jsp" %>
    </c:when>
    <c:otherwise>
        <div class="container-fluid" style="height: 10px;background-color:#212529"></div>

        <div class="container-fluid p-3 bg-light">
            <div class="row">
                <div class="col-md-3 text-success">
                    <h3><i class="fas fa-book"></i><fmt:message key="language.library"/></h3>
                </div>

                <div class="col-md-6">
                    <form class="d-flex" role="search" action="search" method="get">
                        <input class="form-control me-2" type="search" name="search"
                               aria-label="Search">
                        <button class="btn btn-primary" type="submit"><fmt:message key="language.search"/></button>
                    </form>
                </div>

                <div class="col-md-3">
                    <a href="login.jsp" class="btn btn-success"><i
                            class="fa-solid fa-user-plus m-lg-1"></i><fmt:message key="language.login"/></a>
                    <a href="registration.jsp" class="btn btn-primary"><i
                            class="fa-solid fa-right-to-bracket m-lg-1"></i><fmt:message key="language.registration"/></a>
                </div>
            </div>
        </div>

        <nav class="navbar navbar-expand-lg bg-dark bg-primary ">
            <div class="container-fluid">
                <a class="nav-link active" aria-current="page" href="main"><i class="fa-solid fa-house"></i></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="componentNavbar">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="main"><fmt:message key="language.home"/></a>
                        </li>

                        <li class="nav-item active">
                            <a class="nav-link" href="allClassic"><i
                                    class="fa-solid fa-book-open m-lg-1"></i><fmt:message key="language.classicBooks"/></a>
                        </li>

                        <li class="nav-item active">
                            <a class="nav-link" href="allFantasy"><i
                                    class="fa-solid fa-book-open m-lg-1"></i><fmt:message key="language.fantasyBooks"/></a>
                        </li>

                        <li class="nav-item active"><a class="nav-link" href="allRomance"><i
                                class="fa-solid fa-book-open m-lg-1"></i><fmt:message key="language.romanceBooks"/></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="allBooks"><i class="fa-solid fa-book-open m-lg-1"></i><fmt:message key="language.allBooks"/></a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="" id="navbarDarkDropdownMenuLink"
                               role="button" data-bs-toggle="dropdown" aria-expanded="true"><fmt:message key="language.language"/> </a>
                            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                                <li><a id="enUrl" class="dropdown-item"
                                       href=""${lang == 'en' ? 'selected' : ''}><fmt:message key="language.english"/></a></li>
                                <li><a id="uaUrl" class="dropdown-item"
                                       href=""${lang == 'ua' ? 'selected' : ''}><fmt:message key="language.ukrainian"/></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    </c:otherwise>
</c:choose>

<script type="text/javascript">
    const url = new URL(window.location.href)
    url.searchParams.set('lang', 'ua')
    document.getElementById('uaUrl').setAttribute('href', url.href)

    url.searchParams.set('lang', 'en')
    document.getElementById('enUrl').setAttribute('href', url.href)
</script>


