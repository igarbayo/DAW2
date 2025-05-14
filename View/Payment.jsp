<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="Model.Utilities.Cart" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Confirmación de compra</title>
    <link rel="stylesheet" type="text/css" href="style/general.css">
</head>
<body>
<header><h1>Caja</h1></header>
<main>
    <p class="price">Total a pagar: <fmt:formatNumber value="${totalPrice}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</p>

    
    <c:choose>
        <c:when test="${totalPrice > 0}">
            <%-- Form to redirect to the login page --%>
            <form action="login" method="post">
                <input type="hidden" name="mode" value="default">
                <input type="submit" value="Finalizar el pedido">
            </form>
        </c:when>
        <c:otherwise>
            <%-- Form to redirect to home --%>
            <form action="index.jsp" method="get">
                <input type="submit" value="Volver a la tienda a comprar algo">
            </form>
        </c:otherwise>
    </c:choose>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>


