<%@ page import="springmvc.model.entities.Item" %>
<%@ page import="org.springframework.web.bind.annotation.ModelAttribute" %>
<%@ page import="springmvc.model.entities.Book" %>
<%@ page import="springmvc.model.entities.ItemType" %><%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 3/1/2020
  Time: 9:00 PM
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
    <title>AddBook</title>
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
<section class="container">
    <form:form  modelAttribute="newbook" class="form-horizontal" method="post">
        <form:input name="type"  path="item.type" type="hidden" value="<%=String.valueOf(ItemType.BOOK) %>" />
        <fieldset>
            <legend>Add new book</legend>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="itemname">Name</label>
                <div class="col-lg-10">
                    <form:input id="itemname" path="item.name" type="text" value="${item.name}"  class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="amount">Name</label>
                <div class="col-lg-10">
                    <form:input id="amount" path="amount" type="text" value="${amount}"
                                class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="year">Year</label>
                <div class="col-lg-10">
                    <form:input id="year" path="year" type="text"
                                class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="publishing_house">Publishing house</label>
                <div class="col-lg-10">
                    <form:input id="publishing_house" path="publishing_house"  type="text"
                                class="form:input-large" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="price">Price</label>
                <div class="col-lg-10">
                    <form:input id="price" path="price" type="number"  min="0" value="0" step="0.1"
                                class="form:input-large" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2"
                       for="description">Description</label>
                <div class="col-lg-10">
                   <form:textarea id="description" path="item.description" rows = "2"/>
                </div>
            </div>

            <div class="form-group">
                <form:form modelAttribute="authamount" >
                    <form:input name="type"  value="${authamount.intValue}" path="intValue" type="hidden" />
                </form:form>

                <c:forEach var = "i" begin = "1" end = "${authamount.intValue}">
                <label class="control-label col-lg-2"
                       for="authname${i}">Author</label>
                <div class="col-lg-10">
                    <form:input id="authname${i}" path="authors[${i}].name"   type="text" class="form:input-large" />
                </div>
                </c:forEach>
                <input type="submit" id="btnAddAuth" class="btn btn-primary" name="addsbmbutton" value ="addAuthorSbmit"/>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" name="addsbmbutton" value ="addBookSbmit"/>
                </div>
            </div>
        </fieldset>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form:form>
</section>
</body>
</html>
