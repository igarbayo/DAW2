package Controller;

import Model.Utilities.Cart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    public void init(ServletConfig config)
            throws ServletException {

        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        /* If it is a get request forward to doPost() */
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Creates or gets the session */
        HttpSession session = request.getSession(true);

        /* Obtains the session's cart */
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        /* Calculates the total price */
        double totalPrice = cart.calcTotalPrice();

        /* Establishes attributes for the usage of the JSP */
        request.setAttribute("totalPrice", totalPrice);
        session.setAttribute("totalPrice", totalPrice); // The amount totalPrice is required in later JSPs (so session scoped)

        /* Redirects to the JSP page */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Payment.jsp");
        dispatcher.forward(request, response);

    }
}
