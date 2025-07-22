package com.refit.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Random;
import lombok.Getter;

@Getter
@Embeddable
public class Nickname {

    private static final int MAX_LENGTH = 1000;
    private static final String[] nicknamePool = {
            "초코냥이", "코딩곰돌이", "하늘토끼", "깜찍펭귄", "열정캥거루",
            "잉여개발자", "햇살물개", "무지개너굴", "코알라개발자", "호기심사자",
            "푸른여우", "어슬렁곰", "지각판다", "멍때리는참새", "밤하늘너구리",
            "스프링기린", "마시멜로돌고래", "디버깅다람쥐", "리팩토링햄스터", "테스트고양이"
    };

    @Column(unique = true)
    private String nickname;

    public Nickname() {
        this.nickname = generateNickname();
    }

    public Nickname(String nickname) {
        this.nickname = nickname;
    }

    private int generateIndex() {
        return new Random().nextInt(nicknamePool.length);
    }

    public String generateNickname() {
        return nicknamePool[generateIndex()] + new Random().nextInt(MAX_LENGTH);
    }

}
