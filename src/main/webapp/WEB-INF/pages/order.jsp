<%--
  Created by IntelliJ IDEA.
  User: olly0418
  Date: 11/10/2020
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Cart</h1>
            <p>All the selected products in your cart</p>
        </div>
    </div>
</section>
<section class="container" ng-app="cartApp">
    <div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
        <div>
            <a class="btn btn-danger pull-left"
               ng-click="clearCart()"> <span
                    class="glyphicon glyphicon-remove-sign"></span> Clear Cart
            </a> <a href="#" class="btn btn-success pull-right"> <span
                class="glyphicon-shopping-cart glyphicon"></span> Check out
        </a>
        </div>
        <table class="table table-hover">
            <tr>
                <th>Product</th>
                <th>Unit price</th>
                <th>Qauntity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <tr ng-repeat="item in cart.cartItems">
                <td>{{item.product.productId}}-{{item.product.name}}</td>
                <td>{{item.product.unitPrice}}</td>
                <td>{{item.quantity}}</td>
                <td>{{item.totalPrice}}</td>
                <td><a href="#" class="label label-danger" ngclick="removeFromCart(item.product.productId)"> <span
                        class="glyphicon glyphicon-remove" /></span> Remove
                </a></td>
            </tr>
            <tr>
                <th></th>
                <th></th>
                <th>Grand Total</th>
                <th>{{cart.grandTotal}}</th>
                <th></th>
            </tr>
        </table>
        <a href="<spring:url value="/books" />" class="btn btndefault">
            <span class="glyphicon-hand-left glyphicon"></span>
            Continue shopping
        </a>
    </div>
</section>
</body>
</html>
</html>
