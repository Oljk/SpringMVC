<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>This is the homepage!</p>
    </body>
    <div class="container">
        <h1>This is secured!</h1>
        <p>
            Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
        </p>

        <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')"> ROLE_ANONYMOUS </sec:authorize>
        <sec:authorize access="hasAnyRole('ADMIN')"> ADMIN </sec:authorize>
        <sec:authorize access="hasAnyRole('USER')"> USER </sec:authorize>
    </div>
</html>
