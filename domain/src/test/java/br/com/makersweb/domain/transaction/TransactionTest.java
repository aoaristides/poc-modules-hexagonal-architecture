package br.com.makersweb.domain.transaction;

import br.com.makersweb.domain.user.User;
import br.com.makersweb.domain.user.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public class TransactionTest {

    @Test
    public void givenAValidParams_whenCallNewTransaction_thenInstantiateATransaction() {
        final var senderFirstName = "Anderson";
        final var senderLastName = "O. Aristides";
        final var senderDocument = "12345678909";
        final var senderMail = "contato@makersweb.com.br";
        final var senderBalance = BigDecimal.valueOf(10);
        final var senderPassword = "123456";
        final var senderIsActive = true;
        final var senderType = UserType.COMMON;

        final var receiverFirstName = "Joyce";
        final var receiverLastName = "Miranda";
        final var receiverDocument = "12345678999";
        final var receiverMail = "joyce@example.com";
        final var receiverBalance = BigDecimal.valueOf(50);
        final var receiverPassword = "123456789";
        final var receiverIsActive = true;
        final var receiverType = UserType.COMMON;

        final var expectedAmount = BigDecimal.valueOf(10);

        final var sender = User.newUser(senderFirstName, senderLastName, senderDocument, senderMail, senderPassword, senderBalance, senderType, senderIsActive);
        final var receiver = User.newUser(receiverFirstName, receiverLastName, receiverDocument, receiverMail, receiverPassword, receiverBalance, receiverType, receiverIsActive);

        Assertions.assertNotNull(sender);
        Assertions.assertNotNull(receiver);
        Assertions.assertNotNull(sender.getId());
        Assertions.assertNotNull(receiver.getId());

        final var transaction = Transaction.newTransaction(expectedAmount, sender, receiver);

        Assertions.assertNotNull(transaction);
        Assertions.assertNotNull(transaction.getId());
        Assertions.assertEquals(receiverBalance, transaction.getReceiver().getBalance());
    }

}
