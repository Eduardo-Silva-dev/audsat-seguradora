package br.com.audsat.seguradora.model.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRoleTest {

    @Test
    public void testGetRole() {
        assertEquals("admin", UserRole.ADMIN.getRole());
        assertEquals("user", UserRole.USER.getRole());
    }
}
