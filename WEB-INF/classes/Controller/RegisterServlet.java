package Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Database.OrderDAO;
import Model.Database.UserDAO;
import Model.Utilities.CD;
import Model.Utilities.Cart;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;

//@WebServlet("/registro") 
public class RegisterServlet extends HttpServlet {

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

         /* Obtains the current mode for the register screen */
         String mode = request.getParameter("mode");

         if (mode == null) {
             mode = "default";
         }

         switch (mode) {

             case "default":

                 request.setAttribute("errorMessage", "");
                 request.getRequestDispatcher("/View/Register.jsp").forward(request, response);
                 break;

             case "dataInput":

                 String name = request.getParameter("name");
                 String email = request.getParameter("email");
                 String password = request.getParameter("password");
                 String cardType = request.getParameter("cardType");
                 String cardNumber = request.getParameter("cardNumber");

                 /* Checks if all the fields are fulfilled (flag 'required' is used in inputs, so their values should be always non-null) */
                 if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty() ||
                         cardType == null|| cardType.isEmpty() || cardNumber == null || cardNumber.isEmpty()) {
                     request.setAttribute("errorMessage", "Todos los campos son obligatorios");
                     request.getRequestDispatcher("/View/Register.jsp").forward(request, response);
                 }

                 /* Checks if user is already registered */
                 boolean isRegistered = UserDAO.getInstance().isEmailRegistered(email);

                 if (isRegistered) {
                     /* User is already registered in the database */
                     request.setAttribute("errorMessage", "Ese email ya está registrado");
                     request.getRequestDispatcher("/View/Register.jsp").forward(request, response);
                 } else {
                     /* Email is available, user registration can proceed */
                     if (UserDAO.getInstance().registerUser(name, email, password, cardType, cardNumber)) {
                         /* Registration successful */

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
                         request.setAttribute("errorMessage", "No se ha podido completar el registro");
                         request.getRequestDispatcher("/View/Register.jsp").forward(request, response);
                     }
                 }

                 break;
         }
    }
}