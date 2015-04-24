package eu.ubitech.fistar.entities;

import java.util.List;

/**
 *
 * @author smantzouratos
 */
public class User {

    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private String DN;
    private List<IDMRole> IDMUserRoles;
    private String idmRoles;
    private String email;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDN() {
        return DN;
    }

    public void setDN(String DN) {
        this.DN = DN;
    }

    public String getIdmRoles() {
        return idmRoles;
    }

    public void setIdmRoles(String idmRoles) {
        this.idmRoles = idmRoles;
    }

    public List<IDMRole> getIDMUserRoles() {
        return IDMUserRoles;
    }

    public void setIDMUserRoles(List<IDMRole> IDMUserRoles) {
        this.IDMUserRoles = IDMUserRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

}
