package br.com.makersweb.domain.user;

import br.com.makersweb.domain.AggregateRoot;
import br.com.makersweb.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author aaristides
 */
public class User extends AggregateRoot<UserID> implements Cloneable {

    private String firstName;
    private String lastName;
    private String document;
    private String mail;
    private String password;
    private BigDecimal balance;
    private UserType type;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private User(final UserID anId, final String aFirstName, final String aLastName, final String aDocument, final String aMail, final String aPassword, final BigDecimal aBalance, final UserType aType, final boolean isActive, final Instant createdAt, final Instant updatedAt, final Instant deletedAt) {
        super(anId);
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.document = aDocument;
        this.mail = aMail;
        this.password = aPassword;
        this.balance = aBalance;
        this.type = aType;
        this.active = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static User newUser(final String aFirstName, final String aLastName, final String aDocument, final String aMail, final String aPassword, final BigDecimal aBalance, final UserType aType, final boolean isActive) {
        final var id = UserID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new User(id, aFirstName, aLastName, aDocument, aMail, aPassword, aBalance, aType, isActive, now, now, deletedAt);
    }

    public static User clone(final User aUser) {
        return aUser.clone();
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    }

    public User activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public User deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public User update(final String aFirstName, final String aLastName, final String aDocument, final String aMail, final String aPassword, final BigDecimal aBalance, final UserType aType, final boolean isActive) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }

        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.document = aDocument;
        this.mail = aMail;
        this.password = aPassword;
        this.balance = aBalance;
        this.type = aType;
        this.updatedAt = Instant.now();
        return this;
    }

    public UserID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocument() {
        return document;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserType getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    protected User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
