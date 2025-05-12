<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Utilities.Cart" %>
<%@ page import="Model.Utilities.CD" %>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
    <TITLE>Carrito de compra</TITLE>
    <link rel="stylesheet" type="text/css" href="style/general.css">
</HEAD>
<BODY>
<header><H1>Carrito de compra</H1></header>
<main>
    <!-- Verifies if the cart is empty and prompts the message -->
    <c:choose>
        <c:when test="${empty cart.items}">
            <H2>No hay ningún CD en el carrito</H2>
        </c:when>
        <c:otherwise>
            <form action="cart" method="post">
                <table border="1">
                    <tr>
                        <th>Título</th>
                        <th>Artista</th>
                        <th>País</th>
                        <th>Precio</th>
                        <th>Seleccionar</th>
                    </tr>
                    <!-- Iterates over the items, showing them in a table -->
                    <c:forEach items="${cart.items}" var="item">
                        <tr>
                            <td>${item.title}</td>
                            <td>${item.artist}</td>
                            <td>${item.country}</td>
                            <td>${item.price}</td>
                            <td><input type="checkbox" name="delete" value="${item.title}"></td>
                        </tr>
                    </c:forEach>
                </table>
                <p class="price">Precio total: ${totalPrice}€</p>
                <input type="hidden" name="mode" value="delete">
                <input type="submit" name="deleteSelected" value="Eliminar seleccionados"><br>
            </form>
        </c:otherwise>
    </c:choose>
    
    <!-- Buttons to keep buying or end and go to payment -->
    <form action="index.html">
        <input type="submit" value="Seguir comprando">
    </form>
    <c:if test="${not empty cart.items}">
        <form action="payment" method="post">
            <input type="submit" value="Finalizar y pagar">
        </form>
    </c:if>
</main>
<footer>
    <p>© 2024 Tienda de Música DAA</p>
</footer>
</BODY>
</HTML>
