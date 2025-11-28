package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Course;
import model.Student;
import repository.CourseRepository;
import repository.StudentRepository;
import util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@WebServlet("/enroll")
public class EnrollmentServlet extends HttpServlet {
    private StudentRepository studentRepo = new StudentRepository();
    private CourseRepository courseRepo = new CourseRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Hiển thị form nhập ID học viên và tên môn học nếu không có tham số
        req.getRequestDispatcher("/jsp/enrollment-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long studentId = Long.parseLong(req.getParameter("studentId"));
        String courseTitle = req.getParameter("courseTitle");
        Student student = studentRepo.findById(studentId);

        // Tìm course theo title
        List<Course> courses = courseRepo.findAll();
        Course course = null;
        for (Course c : courses) {
            if (c.getTitle().equalsIgnoreCase(courseTitle)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            req.setAttribute("error", "Không tìm thấy khóa học!");
            req.getRequestDispatcher("/jsp/enrollment-form.jsp").forward(req, resp);
            return;
        }
        // Kiểm tra lại enrollment bằng cách truy vấn trực tiếp DB để tránh cache hoặc lỗi equals/hashCode
        EntityManager em = JPAUtil.getEntityManager();
        boolean alreadyEnrolled = false;
        try {
            Student freshStudent = em.find(Student.class, studentId);
            Course freshCourse = em.find(Course.class, course.getId());
            if (freshStudent != null && freshCourse != null && freshStudent.getCourses().contains(freshCourse)) {
                alreadyEnrolled = true;
            }
        } finally {
            em.close();
        }
        if (alreadyEnrolled) {
            req.setAttribute("message", "SV đã đăng ký môn này rồi!");
            req.getRequestDispatcher("/jsp/enrollment-form.jsp").forward(req, resp);
            return;
        }
        EntityManager em2 = JPAUtil.getEntityManager();
        try {
            em2.getTransaction().begin();
            student = em2.merge(student);
            course = em2.merge(course);
            student.getCourses().add(course);
            em2.getTransaction().commit();
        } finally {
            em2.close();
        }
        req.setAttribute("message", "Đăng ký thành công!");
        req.getRequestDispatcher("/jsp/enrollment-form.jsp").forward(req, resp);
    }
}
