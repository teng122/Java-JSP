package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Course;
import repository.CourseRepository;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    private CourseRepository repo = new CourseRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Course course = repo.findById(Long.parseLong(id));
            req.setAttribute("course", course);
            req.getRequestDispatcher("/jsp/course-detail.jsp").forward(req, resp);
        } else {
            List<Course> courses = repo.findAll();
            req.setAttribute("courses", courses);
            req.getRequestDispatcher("/jsp/course-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        repo.save(course);
        resp.sendRedirect("courses");
    }
}
