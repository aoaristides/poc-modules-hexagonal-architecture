package br.com.makersweb.domain.transaction;

import br.com.makersweb.domain.user.UserType;
import br.com.makersweb.domain.validation.Error;
import br.com.makersweb.domain.validation.ValidationHandler;
import br.com.makersweb.domain.validation.Validator;

/**
 * @author aaristides
 */
public class TransactionValidator extends Validator {

    private final Transaction transaction;

    public TransactionValidator(final Transaction aTransaction, final ValidationHandler aHandler) {
        super(aHandler);
        this.transaction = aTransaction;
    }

    @Override
    public void validate() {
        checkTransaction();
    }

    private void checkTransaction() {
        final var sender = this.transaction.getSender();
        final var amount = this.transaction.getAmount();

        if (UserType.MERCHANT.equals(sender.getType())) {
            this.validationHandler().append(new Error("Usuário do tipo Lojista não está autorizado a realizar transação."));
            return;
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            this.validationHandler().append(new Error("Saldo insuficiente."));
        }
    }
}
