<%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 2/5/2020
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page  contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"
%>
<%request.setCharacterEncoding("UTF-8");%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="label.title" /></title>
    <link       rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
</head>
<body>

<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Books</h1>
            <p>Add books</p>
        </div>
    </div>
</section>

<a href="<c:url value="/logout" />">
    <spring:message code="label.logout" />
</a>

<h2><spring:message code="label.title" /></h2>
<form:form method="post" action="add" modelAttribute="user">

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please sign in</h3>
                    </div>
                    <div class="panel-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                <spring:message code="login.badCred.label"/><br />
                            </div>
                        </c:if>
                        <form action=${contextPath}/j_spring_security_check method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="User Name"
                                           name='j_username' type="text">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password"
                                           name='j_password' type="password" value="">
                                </div>
                                <input class="btn btn-lg btn-success btn-block"
                                       type="submit" value="Login">
                            </fieldset>
                            <input type="hidden" name="${__csrf.parameterName}"  value="${__csrf.token}"/>
                        </form>
                    </div>
                </div>
        </div>
        </div>
    </div>

</form:form>

</body>
</html>

