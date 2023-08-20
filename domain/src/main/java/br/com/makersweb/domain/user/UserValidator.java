package br.com.makersweb.domain.user;

import br.com.makersweb.domain.validation.Error;
import br.com.makersweb.domain.validation.ValidationHandler;
import br.com.makersweb.domain.validation.Validator;

/**
 * @author aaristides
 */
public class UserValidator extends Validator {

    private final User user;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;

    public UserValidator(final User user, final ValidationHandler aHandler) {
        super(aHandler);
        this.user = user;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var firstName = this.user.getFirstName();
        final var lastName = this.user.getLastName();
        if (firstName == null || lastName == null) {
            this.validationHandler().append(new Error("'firstName' or 'lastName' should not be null"));
            return;
        }

        if (firstName.isBlank() || lastName.isBlank()) {
            this.validationHandler().append(new Error("'firstName' or 'lastName' should not be empty"));
            return;
        }

        final var lengthFirstName = firstName.trim().length();
        final var lengthLastName = lastName.trim().length();
        final var isMaxOrMinLengthFirstName = lengthFirstName > NAME_MAX_LENGTH || lengthFirstName < NAME_MIN_LENGTH;
        final var isMaxOrMinLengthLastName = lengthLastName > NAME_MAX_LENGTH || lengthLastName < NAME_MIN_LENGTH;
        if (isMaxOrMinLengthFirstName || isMaxOrMinLengthLastName) {
            this.validationHandler().append(new Error("'firstName' or 'lastName' must be between 3 and 255 characters"));
        }
    }

}
