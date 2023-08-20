package br.com.makersweb.domain.transaction;

import br.com.makersweb.domain.AggregateRoot;
import br.com.makersweb.domain.user.User;
import br.com.makersweb.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author aaristides
 */
public class Transaction extends AggregateRoot<TransactionID> {

    private BigDecimal amount;
    private User sender;
    private User receiver;
    private Instant createdAt;
    private Instant updatedAt;

    private Transaction(final TransactionID aTransactionID, final BigDecimal aAmount, final User aSender, final User aReceiver, final Instant aCreatedAt, final Instant aUpdatedAt) {
        super(aTransactionID);
        this.amount = aAmount;
        this.sender = aSender;
        this.receiver = aReceiver;
        this.createdAt = aCreatedAt;
        this.updatedAt = aUpdatedAt;
    }

    public static Transaction newTransaction(final BigDecimal aAmount, final User aSender, final User aReceiver) {
        final var id = TransactionID.unique();
        final var now = Instant.now();
        return new Transaction(id, aAmount, aSender, aReceiver, now, now);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new TransactionValidator(this, handler).validate();
    }

    public TransactionID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
