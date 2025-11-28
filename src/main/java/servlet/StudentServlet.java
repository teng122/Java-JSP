package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Student;
import repository.StudentRepository;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentRepository repo = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Student student = repo.findById(Long.parseLong(id));
            req.setAttribute("student", student);
            req.getRequestDispatcher("/jsp/student-detail.jsp").forward(req, resp);
        } else {
            List<Student> students = repo.findAll();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/jsp/student-list.jsp").forward(req, resp);
        }
    }

    // Chức năng thêm học viên đã bị loại bỏ
}
