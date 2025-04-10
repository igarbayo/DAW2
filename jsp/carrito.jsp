<%@ page import="java.util.*, javax.servlet.http.*" %>
<%
  HttpSession session = request.getSession(true);
  List<String[]> carrito = (List<String[]>) session.getAttribute("carrito");
  if (carrito == null) {
    carrito = new ArrayList<>();
  }

  String cd = request.getParameter("cd");
  String cantidad = request.getParameter("cantidad");

  if (cd != null && cantidad != null) {
    carrito.add(new String[]{cd, cantidad});
    session.setAttribute("carrito", carrito);
  }
%>

<h2>Carrito de la compra</h2>
<table border="1">
  <tr><th>CD</th><th>Cantidad</th><th>Precio</th><th>Eliminar</th></tr>
  <%
    double total = 0;
    for (int i = 0; i < carrito.size(); i++) {
      String[] item = carrito.get(i);
      String[] partes = item[0].split("\\|");
      double precio = Double.parseDouble(partes[3].replace("$", "").trim());
      int cant = Integer.parseInt(item[1]);
      total += precio * cant;
  %>
    <tr>
      <td><%= item[0] %></td>
      <td><%= item[1] %></td>
      <td><%= precio * cant %></td>
      <td><a href="eliminar.jsp?index=<%= i %>">Eliminar</a></td>
    </tr>
  <% } %>
</table>
<p>Total: $<%= String.format("%.2f", total) %></p>

<form action="resumen.jsp" method="post">
  <input type="submit" value="Finalizar Compra">
</form>
