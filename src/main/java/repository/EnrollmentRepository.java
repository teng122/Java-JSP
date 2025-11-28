package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Course;
import util.JPAUtil;
import java.util.List;

public class EnrollmentRepository {
    // Đếm số học viên của một khóa học
    public long countStudentsByCourse(Long courseId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT COUNT(s) FROM Course c JOIN c.students s WHERE c.id = :courseId";
            TypedQuery<Long> q = em.createQuery(jpql, Long.class);
            q.setParameter("courseId", courseId);
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }

    // Lấy danh sách khóa học sắp xếp theo số học viên
    public List<Course> findCoursesOrderByStudentCount() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Course c LEFT JOIN c.students s GROUP BY c.id ORDER BY COUNT(s) DESC";
            TypedQuery<Course> q = em.createQuery(jpql, Course.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
