package eu.ubitech.fistar.pseudonymizer;

/**
 *
 * @author Christos Paraskeva
 */
public class Pseudonym {

    final String firstName;
    final String lastName;
    final String email;
    final String gender;
    final String pseudonymKey;

    public Pseudonym(String firstName, String lastName, String email, String gender, String pseudonymKey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
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
    
    

}
