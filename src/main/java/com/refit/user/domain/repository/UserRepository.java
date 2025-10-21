package com.refit.user.domain.repository;

import com.refit.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User u set u.deleted = true where u.id = :id")
    void deleteById(Long id);

    @Query("select u from User u where u.id = :id and u.deleted = false")
    Optional<User> findById(Long id);

    @Query("select u from User u where u.email = :email and u.deleted = false")
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
