package com.refit.domain.content.repository;

import com.refit.domain.content.entity.Content;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContentRepository {

    private final EntityManager em;

    public Content save(Content content) {
        em.persist(content);
        return content;
    }

    public Content findById(Long id) {
        return em.find(Content.class, id);
    }


}
