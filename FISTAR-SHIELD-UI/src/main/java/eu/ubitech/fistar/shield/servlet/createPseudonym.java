package eu.ubitech.fistar.shield.servlet;

import eu.ubitech.fistar.pseudonymizer.Pseudonym;
import eu.ubitech.fistar.pseudonymizer.PseudonymGenerator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author smantzouratos
 */
public class createPseudonym extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getUserPrincipal().getName();
        request.setAttribute("username", username);

        //Get next step
        String nextStep = (null == request.getParameter("nextStep") ? "step1" : request.getParameter("nextStep"));

        System.out.println("Next step is: " + nextStep);

        switch (nextStep) {

            case ("step1"): {
                request.getRequestDispatcher("createPseudonym_step1.jsp").forward(request, response);
                break;
            }

            //Create pseudonym
            case ("step2"): {
                Pseudonym pseudonym = PseudonymGenerator.getInstance().getRandomPseudonym();
                request.setAttribute("pseudonym", pseudonym);
                request.getRequestDispatcher("createPseudonym_step2.jsp").forward(request, response);
                break;
            }

            //Create certificate request
            case ("step3"): {

            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
