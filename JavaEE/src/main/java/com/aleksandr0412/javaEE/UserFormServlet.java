package com.aleksandr0412.javaEE;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserFormServlet", urlPatterns = "/form2result.html")
public class UserFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String secondName = req.getParameter("second_name");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String birthDate = req.getParameter("birth_date");
        String city = req.getParameter("city");
        out.println("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"/>" +
                "</head><body>" + "<table style=\"border:1px solid black\">" +
                "<tr><td>Фамилия Имя Отчество</td><td>" + secondName + " " +
                firstName + " " + lastName + "</td</tr>" +
                "<tr><td>Дата рождения</td><td>" + birthDate + "</td</tr>" +
                "<tr><td>Город проживания</td><td>" + city + "</td</tr>" + "</table></body></html>");
        out.close();
    }
}