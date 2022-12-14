package com.epam.donetc.restaurant.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
//
//        String user = (String) session.getAttribute("current_user");
//        if (user == null){
//            //registration
//
//        }else {
//        }


//        TestCart testCart = (TestCart) session.getAttribute("cart");
//        String name = req.getParameter("name");
//        int quantity = Integer.parseInt(req.getParameter("quantity"));
//
//        if (testCart == null){
//            testCart = new TestCart();
//            testCart.setName(name);
//            testCart.setQuantity(quantity);
//
//
//        }
//        session.setAttribute("cart", testCart);
//
//        getServletContext().getRequestDispatcher("/WEB-INF/jsp/showCart.jsp").forward(req, resp);

//
//        Integer count = (Integer) session.getAttribute("count");
//        if (count == null){
//            session.setAttribute("count", 1);
//            count =1;
//        }else {
//            session.setAttribute("count", count +1);
//        }
//        resp.getWriter().println("<html>");
//        resp.getWriter().println("<h1>Your count is " + count + "</h1>");
//        resp.getWriter().println("</html>");

//        String name =  req.getParameter("name");
//        PrintWriter writer = resp.getWriter();
//
//        String surname = req.getParameter("sname");
//        writer.println(name+ surname);

//        resp.sendRedirect("/index.jsp");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
//        requestDispatcher.forward(req, resp);

    }
}
