package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Student;
import util.JPAUtil;
import java.util.List;

public class StudentRepository {
    public void save(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Student> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s", Student.class);
        return q.getResultList();
    }

    public Student findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Student.class, id);
    }

    public void update(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
