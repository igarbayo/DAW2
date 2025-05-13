<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Música para DAA</title>
    <link rel="stylesheet" type="text/css" href="style/general.css">
</head>
<body>
<%@ include file="View/header.jsp" %>
<main>
    <form id="carritoForm" method="post" action="cart">
        <div class="form-group">
            <label for="cd">CD:</label>
            <select name="cd" id="cd">
                <option>Yuan | The Guo Brothers | China | $14.95</option>
                <option>Drums of Passion | Babatunde Olatunji | Nigeria | $16.95</option>
                <option>Kaira | Tounami Diabate | Mali | $16.95</option>
                <option>The Lion is Loose | Eliades Ochoa | Cuba | $13.95</option>
                <option>Dance the Devil Away | Outback | Australia | $14.95</option>
                <option>Record of Changes | Samulnori | Korea | $12.95</option>
                <option>Djelika | Tounami Diabate | Mali | $14.95</option>
                <option>Rapture | Nusrat Fateh Ali Khan | Pakistan | $12.95</option>
                <option>Cesaria Evora | Cesaria Evora | Cape Verde | $16.95</option>
                <option>DAA | GSTIC | Spain | $50.00</option>
            </select>
        </div>
        <div class="form-group">
            <label for="ammount">Cantidad:</label>
            <input type="text" id="amount" name="ammount" value="1">
        </div>
        <!-- Campo oculto para el modo -->
        <input type="hidden" name="mode" value="add">
        <input type="submit" value="Agregar al carrito">
    </form>
    <form method="post" action="cart"> <!-- Ver el carrito en el momento -->
        <input type="hidden" name="mode" value="ignore">
        <input type="submit" name="viewCart" value="Ver contenido del carrito">
    </form>
    <form method="post" action="payment"> <!-- Página de ocnfirmación de pago -->
        <input type="submit" name="endAndPay" value="Finalizar y pagar">
    </form>
    <script>
    document.addEventListener("DOMContentLoaded", function () {
        const form = document.getElementById("carritoForm");
        const cantidadInput = document.getElementById("amount");

        form.addEventListener("submit", function (e) {
            const cantidad = parseInt(cantidadInput.value, 10);

            if (isNaN(cantidad) || cantidad <= 0) {
                e.preventDefault(); // Detiene el envío
                alert("La cantidad debe ser un número mayor que 0.");
                cantidadInput.focus();
            }
        });
    });
    </script>
</main>
<%@ include file="View/footer.jsp" %>
</body>
</html>
