<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Utilities.Cart" %>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>Compra confirmada</title>
    <link rel="stylesheet" type="text/css" href="style/general_style.css">
</head>
<body>
<header><h1>Datos de la compra</h1></header>
<main>
    <c:choose>
        <c:when test="${orderId > 0}">
            <p class="order-info">Identificador del pedido: <b>${orderId}</b></p>
            <p class="order-info">Precio total: <b>${totalPrice}€</b></p>
            <p class="order-info">Email: <b>${email}</b></p>
            <c:if test="${username != null}">
                <p class="order-info">Nombre de usuario: <b>${username}</b></p>
            </c:if>
            <c:if test="${purchaseDate != null}">
                <p class="order-info">Fecha de compra: <b>${purchaseDate}</b></p>
            </c:if>
        </c:when>
        <c:otherwise>
            <p class="order-error">Error: No se ha podido tramitar el pedido.</p>
        </c:otherwise>
    </c:choose>

    <%-- Form to redirect to the main page --%>
    <form action="index.html">
        <input type="submit" value="Volver a la pantalla de inicio">
    </form>
</main>
<footer>
    <p>© 2024 Tienda de Música DAA</p>
</footer>
</body>
</html>
