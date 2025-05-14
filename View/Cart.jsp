<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="Model.Utilities.Cart" %>
<%@ page import="Model.Utilities.CD" %>
<%@ page session="false" %>

<!DOCTYPE html>
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
                        <th>Cantidad</th>
                        <th>Seleccionar</th>
                    </tr>
                    <!-- Iterates over the items, showing them in a table -->
                    <c:forEach items="${cart.items}" var="entry">
    <tr>
        <td>${entry.key.title}</td>
        <td>${entry.key.artist}</td>
        <td>${entry.key.country}</td>
        <td>${entry.key.price}</td>
        <td>${entry.value}</td>
        <td><input type="checkbox" name="delete" value="${entry.key.title}"></td>
    </tr>
</c:forEach>

                </table>
                <p class="price">Precio total: <fmt:formatNumber value="${totalPrice}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</p>
                <input type="hidden" name="mode" value="delete">
                <input type="submit" name="deleteSelected" value="Eliminar seleccionados"><br>
            </form>
        </c:otherwise>
    </c:choose>
    
    <!-- Buttons to keep buying or end and go to payment -->
    <form action="index.jsp">
        <input type="submit" value="Seguir comprando">
    </form>
    <c:if test="${not empty cart.items}">
        <form action="payment" method="post">
            <input type="submit" value="Finalizar y pagar">
        </form>
    </c:if>
</main>
<%@ include file="footer.jsp" %>

<script>
document.addEventListener("DOMContentLoaded", function () {
    const deleteForm = document.querySelector('form[action="cart"]');
    deleteForm.addEventListener("submit", function (e) {
        const checkboxes = deleteForm.querySelectorAll('input[type="checkbox"][name="delete"]');
        const algunoSeleccionado = Array.from(checkboxes).some(checkbox => checkbox.checked);

        if (!algunoSeleccionado) {
            e.preventDefault(); // Evita que se envíe el formulario
            alert("Debe seleccionar al menos un CD para eliminar.");
        }
    });
});
</script>

</BODY>
</HTML>
