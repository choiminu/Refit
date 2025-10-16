package com.refit.user.domain.repository;

import com.refit.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        select
            u
        from
            User u
        where
            u.email = :email
    """)
    Optional<User> findUserByEmail(String email);

    @Query("""
        select
            case
                when count(u) > 0 then true
                else false
            end
        from
            User u
        where u.email = :email
    """)
    boolean existsUsersByEmail(String email);
}
