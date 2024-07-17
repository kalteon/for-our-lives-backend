package org.example.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email" ,unique = true, nullable = false)
    private String email;

    @Column(name = "name", unique = true)
    private String name;

    private String role;

    private LocalDateTime createdDate;

    private LocalDateTime lastLoginDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastLoginDate = LocalDateTime.now();
    }

    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void changeRole(String newRole) {
        this.role = newRole;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(Object name) {
        this.name = name.toString();
    }
}

