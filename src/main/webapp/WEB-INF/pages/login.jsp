<%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 10/21/2020
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="{pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div class="container" style="width: 300px;">
    <c:url value="/j_spring_security_check" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus value="oljk">
        <input type="password" class="form-control" name="j_password" placeholder="Password" required value="123">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form>
    <c:url value="/registration" var="registrUrl" />
    <a href="${registrUrl}">Registation</a>
</div>

</body>
</html>
