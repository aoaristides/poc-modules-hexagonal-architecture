package br.com.makersweb.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public class UserTest {

    @Test
    public void givenAValidParams_whenCallNewUser_thenInstantiateAUser() {
        final var expectedFirstName = "Anderson";
        final var expectedLastName = "O. Aristides";
        final var expectedDocument = "12345678909";
        final var expectedMail = "contato@makersweb.com.br";
        final var expectedBalance = BigDecimal.valueOf(10);
        final var expectedPassword = "123456";
        final var expectedIsActive = true;
        final var expectedType = UserType.COMMON;

        final var actualUser = User.newUser(expectedFirstName, expectedLastName, expectedDocument, expectedMail, expectedPassword, expectedBalance, expectedType, expectedIsActive);

        Assertions.assertNotNull(actualUser);
        Assertions.assertNotNull(actualUser.getId());
        Assertions.assertEquals(expectedFirstName, actualUser.getFirstName());
        Assertions.assertEquals(expectedLastName, actualUser.getLastName());
        Assertions.assertEquals(expectedDocument, actualUser.getDocument());
        Assertions.assertEquals(expectedIsActive, actualUser.isActive());
        Assertions.assertNotNull(actualUser.getCreatedAt());
        Assertions.assertNotNull(actualUser.getUpdatedAt());
        Assertions.assertNull(actualUser.getDeletedAt());
    }

}
