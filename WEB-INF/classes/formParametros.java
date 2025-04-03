import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class formParametros extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<HEAD></HEAD>");
        out.println("<BODY>\n");
        out.println("<H1> Leyendo Texto </H1>\n");
        out.println("<UL>\n");
        out.println("<LI><B>Texto 1:</B> " + request.getParameter("txt1") + "\n");
        out.println("<LI><B>Texto 2:</B> " + request.getParameter("txt2") + "\n");
        out.println("</UL>\n");
        out.println("</BODY></HTML>");
    }
}
