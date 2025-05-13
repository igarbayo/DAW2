<%@ page import="tienda.Carrito" %>
<%@ page import="tienda.Item" %>
<%@ page import="tienda.CD" %>
<%@ page import="java.util.*" %>

<%
  List<String[]> carrito = (List<String[]>) session.getAttribute("carrito");

  if (carrito == null) {
%>
    <p>El carrito está vacío.</p>
<%
  } else {
%>
    <h2>Resumen de tu compra</h2>
    <table border="1">
      <tr><th>CD</th><th>Autor</th><th>País</th><th>Cantidad</th><th>Precio</th><th>Total</th></tr>
<%
    for (Item item : carrito) {
      String titulo = item.getCD().getTitulo();
      String autor = item.getCD().getAutor();
      String pais = item.getCD().getPais();
      double precio = item.getCD().getPrecio();
      int cantidad = item.getCantidad();
%>
      <tr>
        <td><%= titulo %></td>
        <td><%= autor %></td>
        <td><%= pais %></td>
        <td><%= cantidad %></td>
        <td>$<%= String.format("%.2f", precio) %></td>
        <td>$<%= String.format("%.2f", precio * cantidad) %></td>
      </tr>
<%
    }
%>
    </table>
    <p><strong>Importe total:</strong> $<%= String.format("%.2f", carrito.getTotal()) %></p>

    <form action="confirmar.jsp" method="post">
      <input type="submit" value="Pagar y volver al inicio">
    </form>
<%
  }
%>
