package votingapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {
    @Id
    private String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
