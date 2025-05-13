<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Utilities.Cart" %>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>Confirmación de compra</title>
    <link rel="stylesheet" type="text/css" href="style/login.css">
</head>
<body>
<main>
    <section class="login-box">
        <h1>Regístrate</h1>
        <hr class="title-divider">

        <form action="register" method="post">

            <div class="user-box">
                <input type="text" id="name" name="name" required>
                <label>Nombre completo</label>
            </div>
            <div class="user-box">
                <input type="text" id="email" name="email" required>
                <label>Correo electrónico</label>
            </div>
            <div class="user-box">
                <input type="password" id="password" name="password" required>
                <label>Contraseña</label>
            </div>
            <div class="user-box">
                <input type="text" id="cardType" name="cardType" required>
                <label>Tipo de tarjeta</label>
            </div>
            <div class="user-box">
                <input type="text" id="cardNumber" name="cardNumber" required>
                <label>Número de tarjeta</label>
            </div>

            <!-- Shows the error message when it is not empty -->
            <c:if test="${not empty errorMessage}">
                <p class="error-message">${errorMessage}</p>
            </c:if>

            <input type="hidden" name="mode" value="dataInput">
            <input type="submit" name="continue" class="continue" value="Continuar">

            <p>¿Ya tienes cuenta? <a class="register" href="login">Inicia sesión</a></p>

        </form>
    </section>
</main>
<footer>
    <p>© 2024 Tienda de Música DAA</p>
</footer>
</body>

<!-- Simple JScript to improve the responsivity of the text fields -->
<script>
    document.addEventListener('DOMContentLoaded', function () {

        const inputs = document.querySelectorAll('.user-box input');

        inputs.forEach(input => {

            input.value = "";
            input.parentElement.querySelector('label').style.top = '0';
            input.parentElement.querySelector('label').style.color = '#fff';
            input.parentElement.querySelector('label').style.fontSize = '20px';

            input.addEventListener('focus', function () {
                input.parentElement.querySelector('label').style.top = '-20px';
                input.parentElement.querySelector('label').style.color = '#a0e1f5';
                input.parentElement.querySelector('label').style.fontSize = '15px';
            });

            input.addEventListener('blur', function () {
                if (input.value === '') {
                    input.parentElement.querySelector('label').style.top = '0';
                    input.parentElement.querySelector('label').style.color = '#fff';
                    input.parentElement.querySelector('label').style.fontSize = '20px';
                } else {
                    input.parentElement.querySelector('label').style.top = '-20px';
                    input.parentElement.querySelector('label').style.color = '#a0e1f5';
                    input.parentElement.querySelector('label').style.fontSize = '15px';
                }
            });
        });
    });
</script>

</html>


