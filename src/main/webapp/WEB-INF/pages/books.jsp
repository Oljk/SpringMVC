<%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 2/13/2020
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page  contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
      href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>books</h1>
            <p>All the available books in our store</p>
        </div>
    </div>
</section>
<section class="container">
<div class="row">
<c:forEach items="${books}" var="book">
        <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
            <div class="thumbnail">
                <div class="caption">
                    <h3>${book.item.name}</h3>
                    <p>${book.year}</p>
                    <p>${book.price} USD</p>
                    <p>Available ${book.amount} units in stock</p>
                </div>
            </div>
        </div>
</c:forEach>
    </div>
</section>
</body>
</html>

</body>
</html>
