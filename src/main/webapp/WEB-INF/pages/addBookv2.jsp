<%@ page import="springmvc.model.entities.ItemType" %><%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 4/29/2020
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>AddBooks v2</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Books</h1>
            <p>Add bookv2</p>
        </div>
    </div>
</section>
<section class="container">
    <form:form modelAttribute="newbook" class="form-horizontal">
        <fieldset>
            <legend>Add new book v2</legend>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"  for="productId"><spring:message code="addBook.form.Bookname.label"/></label>
                <div class="col-lg-10">
                    <form:input id="productId" path="item.name" type="text"    class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="amount">Amount</label>
                <div class="col-lg-10">
                    <form:input id="amount" path="amount" type="text" class="form:input-large"/>
                </div>
            </div>
            <form:input name="type"  path="item.type" type="hidden" value="<%=String.valueOf(ItemType.BOOK) %>" />
            <div class="form-group">
                <label class="control-label col-lg-2"
                       for="publishing_house">Publishing house</label>
                <div class="col-lg-10">
                    <form:input id="publishing_house" path="publishing_house" type="text" class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="year">Year</label>
                <div class="col-lg-10">
                    <form:input id="year" path="year" type="text" class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2"
                       for="price">Price</label>
                <div class="col-lg-10">
                    <form:input id="price" path="price" type="number"  min="0" value="0" step="0.1"
                                class="form:input-large" required />
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
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>
</body>
</html>
