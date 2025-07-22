package com.refit.domain.user.repository;

import com.refit.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User signup(User user) {
        em.persist(user);
        return user;
    }

    public boolean existsByEmail(String email) {
        String query = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
        Long count = em.createQuery(query, Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

}
