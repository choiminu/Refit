package com.refit.domain.user.entity;

import com.refit.domain.user.entity.type.Role;
import com.refit.domain.user.entity.vo.Nickname;
import com.refit.domain.user.entity.vo.Point;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Embedded
    private Nickname nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Point point;

    private LocalDateTime createdDate;

    public String getNickname() {
        return nickname.getNickname();
    }

}
