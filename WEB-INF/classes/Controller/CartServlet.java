package Controller;

import Model.Utilities.CD;
import Model.Utilities.Cart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.StringTokenizer;



//@WebServlet("/cart")
public class CartServlet extends HttpServlet {

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

        /* Creates or gets the cart for the given session */
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        /* Obtains data from the form */
        String mode = request.getParameter("mode");

        switch (mode) {

            /* Adds the product to the cart */
            case "add":

                String product = request.getParameter("cd");
                int ammount = Integer.parseInt(request.getParameter("ammount"));

                /* Retrieves data from the CD */
                StringTokenizer tokenizer = new StringTokenizer(product, "|");
                String title = tokenizer.nextToken().trim();
                String artist = tokenizer.nextToken().trim();
                String country = tokenizer.nextToken().trim();
                String priceAsString = tokenizer.nextToken().replace('$', ' ').trim();
                double price = Double.parseDouble(priceAsString);

                /* Creates the CD object with the previous data */
                CD cd = new CD(title, artist, country, price);

                /* Adds the CD to the cart itself */
                cart.addItem(cd, ammount);

                break;

            /* Erases the items chosen by the user */
            case "delete":

                String[] removedCDs = request.getParameterValues("delete");

                if (removedCDs != null && removedCDs.length > 0) {

                    /* Iterates over the titles, deleting those selected */
                    for (String titleIterator : removedCDs) {
                        cart.deleteItemByTitle(titleIterator);
                    }

                }
                break;
        }

        /* Updates the session's cart */
        session.setAttribute("cart", cart);

        /* Calculates the total price for the above cart */
        double totalPrice = cart.calcTotalPrice();

        /* Establishes the attributes that will be used for the JSP. The object 'cart' contains all the CDs the user pretends to buy */
        request.setAttribute("cart", cart);
        request.setAttribute("totalPrice", totalPrice);

        /* Obtains the RequestDispatcher for 'Cart.jsp'. Allows to send the request to another resource inside the server (JSP in this case) */
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Cart.jsp");

        /* Forwards the data from the request and the response to the JSP, that can now access the cart and generate an HTML response */
        dispatcher.forward(request, response);

    }
}
