package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        if (req.getParameter("id") != null) {
            long id = Long.parseLong(req.getParameter("id"));
            Product product = productRepository.findById(id);
            wr.println("<p>id: "+ id + "</p>");
            wr.println("<p>name: "+ product.getName() +"</p>");
        } else if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            List<Product> products = productRepository.findAll();
            req.setAttribute("products", products);
            getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
//            wr.println("<table>");
//
//            wr.println("<tr>");
//            wr.println("<th>Id</th>");
//            wr.println("<th>Name</th>");
//            wr.println("</tr>");
//
//            for (Product product : productRepository.findAll()) {
//                wr.println("<tr>");
//                wr.println("<td>" + product.getId() + "</td>");
//                wr.println("<td><a href='" + getServletContext().getContextPath() + "/product?id=" + product.getId() + "'>" + product.getName() + "</a></td>");
//                wr.println("</tr>");
//            }
//
//            wr.println("</table>");
        }
    }
}
