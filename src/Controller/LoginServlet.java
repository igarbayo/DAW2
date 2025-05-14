package Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.sql.Timestamp;

import Model.Database.DBConnection;
import Model.Database.OrderDAO;
import Model.Database.UserDAO;
import Model.Utilities.CD;
import Model.Utilities.Cart;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void init(ServletConfig config)
            throws ServletException {

        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // If it is a get request forward to doPost()
        doPost(request, response);
    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         /* Creates or gets the session */
         HttpSession session = request.getSession(true);

         /* Obtains the current mode for the login screen */
         String mode = request.getParameter("mode");

         if (mode == null) {
             mode = "default";
         }

         switch (mode) {

             case "default":

                 request.setAttribute("errorMessage", "");
                 request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
                 break;

             case "dataInput":

                 String email = request.getParameter("email");
                 String password = request.getParameter("password");

                 /* Checks if email and password are provided (flag 'required' is used in inputs, so their values should be always non-null) */
                 if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                     request.setAttribute("errorMessage", "El correo y la contraseña son obligatorios");
                     request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
                 }

                 /* Authenticate user */
                 boolean isAuthenticated = UserDAO.getInstance().authenticate(email, password);

                 if (isAuthenticated) {
                     /* User authenticated */

                     /* Obtains the session's cart */
                     Cart cart = (Cart) request.getSession().getAttribute("cart");

                     /* Removes all the CDs from the cart */
                     cart.emptyItems();

                     /* Gets the total price, and adds the entry to the orders table in the database */
                     double totalPrice = (double) session.getAttribute("totalPrice");
                     int orderId = OrderDAO.getInstance().saveOrder(email, totalPrice);
                     if (orderId != -1) { // the order was saved correctly

                         /* Sets up the variables, and forwards to the confirmation page */
                         request.setAttribute("username", UserDAO.getInstance().getUsernameByEmail(email)); // can be null (handled in the JSP)
                         request.setAttribute("email", email);
                         request.setAttribute("totalPrice", totalPrice);

                         /* Reformats the timestamp to a more legible prompt */
                         Timestamp purchaseDate = OrderDAO.getInstance().getOrderDateById(orderId);
                         SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                         String formattedPurchaseDate = purchaseDate != null ? dateFormat.format(purchaseDate) : null;
                         request.setAttribute("purchaseDate", formattedPurchaseDate); // can be null as well
                     }
                     request.setAttribute("orderId", orderId);
                     request.getRequestDispatcher("/View/Confirmation.jsp").forward(request, response);

                 } else {
                     /* Invalid credentials */
                     request.setAttribute("errorMessage", "El correo o contraseña no son correctos");
                     request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
                 }

                 break;
         }
     }
}
