package com.example.backendservice.intro;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class AuthorService {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public List<Author> findAll() {
        return em.createQuery("select a from Author a order by a.id", Author.class).getResultList();
    }

    public Author findById(Long id) {
        try {
            return em.createQuery("select a from Author a where a.id = :id", Author.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    public List<Author> search(AuthorSearchRequest searchRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> query = cb.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);
        query.select(root).where(cb.like(root.get(Author_.firstname), searchRequest.firstName()));
        return em.createQuery(query).getResultList();
    }
}
