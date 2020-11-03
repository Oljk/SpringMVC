<%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 10/26/2020
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

login : ${user.name} <br>
name :  ${user.name} <br>
phone : ${user.phone_number} <br>

</body>
</html>
