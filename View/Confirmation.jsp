<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Utilities.Cart" %>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>Compra confirmada</title>
    <link rel="stylesheet" type="text/css" href="style/confirmar.css">
</head>
<body>
<header><h1>Datos de la compra</h1></header>
<main>
    <c:choose>
        <c:when test="${orderId > 0}">
            <table id="order-table" border="1" cellpadding="10" cellspacing="0">
                <thead>
                    <tr>
                        <th>Identificador del pedido</th>
                        <th>Precio total</th>
                        <th>Correo electrónico</th>
                        <th>Nombre de usuario</th>
                        <th>Fecha de compra</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${orderId}</td>
                        <td>${totalPrice} €</td>
                        <td>${email}</td>
                        <c:if test="${username != null}">
                            <td>${username}</td>
                        </c:if>
                        <c:if test="${purchaseDate != null}">
                            <td>${purchaseDate}</td>
                        </c:if>
                    </tr>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="order-error">Error: No se ha podido tramitar el pedido.</p>
        </c:otherwise>
    </c:choose>

    <%-- Form to redirect to the main page --%>
    <form action="index.jsp">
        <input type="submit" value="Volver a la pantalla de inicio">
    </form>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>
