package edu.miu.cs.cs544.exercise17_2;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentsCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        this.studentService = applicationContext.getBean("studentService", StudentService.class);
        this.studentService.initializeStudent();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String studentIdStr = request.getParameter("studentid");

        long studentid = -1;
        Student student = null;

        if (studentIdStr != null && studentIdStr.matches("\\d+")) {
            studentid = Long.parseLong(studentIdStr);
            student = studentService.getStudent(studentid);
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }
}
