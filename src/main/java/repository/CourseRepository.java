package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Course;
import util.JPAUtil;
import java.util.List;

public class CourseRepository {
    public void save(Course course) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Course> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Course> q = em.createQuery("SELECT c FROM Course c", Course.class);
        return q.getResultList();
    }

    public Course findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Course.class, id);
    }

    public void update(Course course) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(course);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
