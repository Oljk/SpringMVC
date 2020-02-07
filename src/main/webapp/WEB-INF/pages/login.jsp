<%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 2/5/2020
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="label.title" /></title>
</head>
<body>

<a href="<c:url value="/logout" />">
    <spring:message code="label.logout" />
</a>

<h2><spring:message code="label.title" /></h2>


<form:form method="post" action="add" modelAttribute="user">

    <table>
        <tr>
            <td><form:label path="name">
                <spring:message code="label.firstname" />
            </form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="surname">
                <spring:message code="label.lastname" />
            </form:label></td>
            <td><form:input path="surname" /></td>
        </tr>
        <tr>
            <td><form:label path="email">
                <spring:message code="label.email" />
            </form:label></td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td><form:label path="phone_number">
                <spring:message code="label.telephone" />
            </form:label></td>
            <td><form:input path="phone_number" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="<spring:message code="label.addcontact"/>" /></td>
        </tr>
    </table>
</form:form>

</body>
</html>

