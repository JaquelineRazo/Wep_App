package com.javamaster.controller;

import com.javamaster.exception.AuthException;
import com.javamaster.model.UsersModel;
import com.javamaster.service.UserServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author jaquelinerazo
 */
public class HomeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.equals("/main-page")) {
            String method = request.getMethod();
            System.out.println(method);
            if (request.getSession().getAttribute("user") == null) {
                request.setAttribute("errorMessage", "Tienes que autenticarte para usar la app");
                request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
            } else if(method.equals("GET")) {
                request.getRequestDispatcher("/WEB-INF/view/main-page.jsp").forward(request, response);
            } else {
                String date = request.getParameter("date");
                String imc = request.getParameter("imc");
                

                    UserServiceImpl.getInstance().record(date, imc);
                    
                    
                    
                    request.getRequestDispatcher("/WEB-INF/view/main-page.jsp").forward(request, response);
                    
            }
                
            
 
        } else if (uri.equals("/register")) {
            String method = request.getMethod();
            System.out.println(method);
            if (method.equals("GET")) {
                request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            } else {
                String name = request.getParameter("name");
                String age = request.getParameter("age");
                String sex = request.getParameter("sex");
                String height = request.getParameter("height");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                UserServiceImpl.getInstance().register(name, age, sex, height, email, password);
                response.sendRedirect("/login");
            }

        } else if (uri.equals("/login")) {
            String method = request.getMethod();
            System.out.println(method);
            if (method.equals("GET")) {
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            } else {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                try {
                    UsersModel user = UserServiceImpl.getInstance().auth(email, password);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("/main-page");
                } catch (AuthException ex) {
                    request.setAttribute("errorMessage", ex.getMessage());
                    request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
                }

            }
        } else {
            request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
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
