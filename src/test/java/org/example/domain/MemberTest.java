package org.example.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class MemberTest {

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .email("test@example.com")
                .name("Test User")
                .role("USER")
                .createdDate(LocalDateTime.now())
                .lastLoginDate(LocalDateTime.now())
                .build();
    }

    @Test
    public void testUpdateProfile() {
        String newName = "Updated User";
        String newEmail = "updated@example.com";

        member.updateProfile(newName, newEmail);

        assertEquals(newName, member.getName());
        assertEquals(newEmail, member.getEmail());
    }

    @Test
    public void testChangeRole() {
        String newRole = "ADMIN";

        member.changeRole(newRole);

        assertEquals(newRole, member.getRole());
    }

    @Test
    public void testCreatedDate() {
        assertNotNull(member.getCreatedDate());
    }

    @Test
    public void testLastLoginDate() {
        assertNotNull(member.getLastLoginDate());
    }
}

