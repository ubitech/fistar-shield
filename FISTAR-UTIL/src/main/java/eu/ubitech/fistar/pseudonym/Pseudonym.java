package eu.ubitech.fistar.pseudonym;

/**
 *
 * @author Christos Paraskeva
 */
public class Pseudonym {

    String username;
    final String firstName;
    final String lastName;
    final String email;
    final String gender;
    String pseudonymKey;

    public Pseudonym(String firstName, String lastName, String email, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Pseudonym(String username, String firstName, String lastName, String email, String gender, String pseudonymKey) {
        this(firstName, lastName, email, gender);
        this.username = username;
        this.pseudonymKey = pseudonymKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPseudonymKey() {
        return pseudonymKey;
    }

    public String setPseudonymKey(String key) {
        return this.pseudonymKey = key;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String key) {
        return this.username = key;
    }

}
