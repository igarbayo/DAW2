<%@ page import="java.util.*" %>
<%
  HttpSession session = request.getSession(false);
  if (session != null) {
    List<String[]> carrito = (List<String[]>) session.getAttribute("carrito");
    int index = Integer.parseInt(request.getParameter("index"));
    carrito.remove(index);
    session.setAttribute("carrito", carrito);
  }
  response.sendRedirect("carrito.jsp");
%>
