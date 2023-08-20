package br.com.makersweb.domain.transaction;

import br.com.makersweb.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class TransactionID extends Identifier {

    private final String value;

    private TransactionID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static TransactionID unique() {
        return TransactionID.from(UUID.randomUUID());
    }

    public static TransactionID from(final String anId) {
        return new TransactionID(anId);
    }

    public static TransactionID from(final UUID anId) {
        return new TransactionID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TransactionID that = (TransactionID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
