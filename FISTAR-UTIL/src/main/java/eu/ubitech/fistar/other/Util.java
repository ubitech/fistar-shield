package eu.ubitech.fistar.other;

import eu.ubitech.fistar.database.DSHandler;
import eu.ubitech.fistar.entities.IDMRole;
import eu.ubitech.fistar.entities.User;
import eu.ubitech.fistar.idm.IDMHandler;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Christos Paraskeva
 */
public class Util {

    // Enumeration needed by Random Password Generation
    public static enum Mode {

        ALPHA, ALPHANUMERIC, NUMERIC, SYMBOL
    }

    public static String createAlgorithm(String content, String algorithm) {
        String encryptedContent = "";
        try {
            //Producing the SHA hash for the input
            MessageDigest m;
            m = MessageDigest.getInstance(algorithm);
            m.update(content.getBytes(), 0, content.length());
            encryptedContent = new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedContent;
    }

    /**
     *
     * Generates a new Random String for Password
     *
     * @param length
     * @param mode
     *
     * @return A String object
     *
     */
    public static String generateRandomString(int length, Mode mode) {

        StringBuilder buffer = new StringBuilder();
        String characters = "";

        switch (mode) {

            case ALPHA:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$";
                break;

            case NUMERIC:
                characters = "1234567890";
                break;

        }

        int charactersLength = characters.length();

        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    } // EoM generateRandomPassword

    //http://www.andygibson.net/blog/article/generate-test-data-with-datafactory/#more-1751
    public static void main(String[] args) {

        List<IDMRole> roles = getUserIDMRoles("DN=tes1");

        System.out.println("Role: " + roles.get(0).getRole());//" " + roles.get(1).getRole());

    }

    /**
     *
     * Returns the user role of a specific username
     *
     * @param username
     *
     * @return A String object
     *
     */
    public static String getUserRole(String username) {
        String userRole = null;
        Connection ds = DSHandler.INSTANCE.getDatasource();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = ds.prepareStatement("SELECT rolename FROM UserRole WHERE username = ?");
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                userRole = rs.getString("rolename");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        } finally {
            DSHandler.INSTANCE.closeDBStreams(ds, stm, rs);
        }
        return userRole;

    }

    /**
     *
     * Returns all the users
     *
     * @return A List of User object
     *
     */
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();;
        User user = null;
        Connection ds = DSHandler.INSTANCE.getDatasource();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = ds.prepareStatement("SELECT a.id, a.username, b.firstName, b.lastName, b.email FROM User a, Pseudonym b, UserRole c WHERE a.username = b.username AND c.uid = a.id AND c.rolename = 'user';");
            rs = stm.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setDN("CN=" + user.getFirstName() + " " + user.getLastName());
                List<IDMRole> idmRoles = getUserIDMRoles(user.getDN());
                user.setIDMUserRoles(idmRoles);

                String tempRoles = "";
                if (null != idmRoles && idmRoles.size() > 0) {
                    for (IDMRole tempRole : idmRoles) {
                        tempRoles += "<a href='deassignIDMRole?uID=" + user.getUserID() + "&role=" + tempRole.getRole() + "' title='Click to remove this role!' rel='tooltip' data-trigger='hover' data-placement='right' data-html='true'>" + tempRole.getRole() + "</a>, ";
                    }

                    user.setIdmRoles(tempRoles.substring(0, tempRoles.length() - 2));
                } else {
                    user.setIdmRoles(" - ");
                }

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        } finally {
            DSHandler.INSTANCE.closeDBStreams(ds, stm, rs);

        }

        return users;

    }

    /**
     *
     * Returns the user info of a specific username
     *
     * @param userID
     *
     * @return A User object
     *
     */
    public static User getUserByID(int userID) {
        User user = null;
        Connection ds = DSHandler.INSTANCE.getDatasource();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = ds.prepareStatement("SELECT a.id, a.username, b.firstName, b.lastName, b.email FROM User a, Pseudonym b WHERE a.username = b.username AND a.id = ?;");
            stm.setInt(1, userID);
            rs = stm.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setDN("CN=" + user.getFirstName() + " " + user.getLastName());
                user.setEmail(rs.getString("email"));
                user.setDN("CN=" + user.getFirstName() + " " + user.getLastName());
                List<IDMRole> idmRoles = getUserIDMRoles(user.getDN());
                user.setIDMUserRoles(idmRoles);
                String tempRoles = "";
                if (null != idmRoles && idmRoles.size() > 0) {
                    for (IDMRole tempRole : idmRoles) {
                        tempRoles += "<a href='deassignIDMRole?uID=" + user.getUserID() + "&role=" + tempRole.getRole() + "' title='Click to remove this role!' rel='tooltip' data-trigger='hover' data-placement='right' data-html='true'>" + tempRole.getRole() + "</a>, ";
                    }

                    user.setIdmRoles(tempRoles.substring(0, tempRoles.length() - 2));
                } else {
                    user.setIdmRoles(" - ");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        } finally {
            DSHandler.INSTANCE.closeDBStreams(ds, stm, rs);
        }
        return user;

    }

    /**
     *
     * Returns the IDM Roles from OpenIDM
     *
     *
     * @return A List of IDMRole object
     *
     */
    public static List<IDMRole> getIDMRoles() {
        List<IDMRole> roles = new ArrayList<>();
        IDMRole role = null;

        IDMHandler idm = new IDMHandler();
        JSONObject json = idm.getAllManagedRoles();
        JSONArray rolesArray = json.getJSONArray("result");

        for (int i = 0; i < rolesArray.length(); i++) {
            role = new IDMRole();
            JSONObject roleObj = rolesArray.getJSONObject(i);
            role.setRole(roleObj.getString("_id"));
            roles.add(role);
        }

        return roles;

    }

    /**
     *
     * Returns the IDM Roles from OpenIDM
     *
     * @param userDN
     *
     * @return A List of IDMRole object
     *
     */
    public static List<IDMRole> getUserIDMRoles(String userDN) {
        List<IDMRole> roles = new ArrayList<>();
        IDMRole role = null;

        IDMHandler idm = new IDMHandler();
        JSONObject jsonObj = idm.getManagedUser(userDN);

        JSONArray rolesArray = jsonObj.getJSONArray("roles");

        for (int i = 0; i < rolesArray.length(); i++) {
            if (!rolesArray.get(i).toString().contains("open")) {
                role = new IDMRole();
                role.setRole(rolesArray.get(i).toString());
                roles.add(role);
            }
        }

        return roles;

    }

    /**
     *
     * Deassigns an IDM Role of OpenIDM to User
     *
     * @param idmRole
     *
     * @param userDN
     *
     * @return A List of IDMRole object
     *
     */
    public static boolean deassignIDMRoleToUser(String idmRole, String userDN) {
        boolean idmRoleAssigned = false;

        IDMHandler idm = new IDMHandler();
        idmRoleAssigned = idm.unAssignRoleToUser(idmRole, userDN);

        return idmRoleAssigned;

    }

    /**
     *
     * Assigns an IDM Role of OpenIDM to User
     *
     * @param idmRole
     *
     * @param userDN
     *
     * @return A List of IDMRole object
     *
     */
    public static boolean assignIDMRoleToUser(String idmRole, String userDN) {
        boolean idmRoleAssigned = false;

        IDMHandler idm = new IDMHandler();
        idmRoleAssigned = idm.assignRoleToUser(idmRole, userDN);

        return idmRoleAssigned;

    }

    /**
     *
     * Creates a new IDM Role to OpenIDM
     *
     * @param idmRole
     *
     * @return A Boolean object
     *
     */
    public static boolean createNewRole(String idmRole) {
        boolean roleAdded = false;

        IDMHandler idm = new IDMHandler();
        roleAdded = idm.createManagedRole(idmRole);

        return roleAdded;

    }

    /**
     *
     * Creates a new user
     *
     * @param username
     * @param password
     *
     * @return A String object
     *
     */
    public static boolean createNewUser(String username, String password) {
        boolean userCreated = false;
        Connection ds = DSHandler.INSTANCE.getDatasource();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = ds.prepareStatement("INSERT INTO User (username, password) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, username);
            stm.setString(2, createAlgorithm(password, "SHA"));
            stm.executeUpdate();
            int userID = 0;
            rs = stm.getGeneratedKeys();
            if (rs.next() && (userID = rs.getInt(1)) <= 0) {
                Logger.getLogger(Util.class.getName()).log(Level.WARNING, "Could not retrieve primary key from table `User` for Record with name: {0}", username);
                return userCreated;
            }

            stm = ds.prepareStatement("INSERT INTO UserRole (username, rolename, uid) VALUES (?,?,?);");
            stm.setString(1, username);
            stm.setString(2, "user");
            stm.setInt(3, userID);
            stm.executeUpdate();

            userCreated = true;

        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return userCreated;
        } finally {
            DSHandler.INSTANCE.closeDBStreams(ds, stm, rs);
        }
        return userCreated;

    }

    /**
     *
     * Deletes an IDM Role to OpenIDM
     *
     * @param idmRole
     *
     * @return A Boolean object
     *
     */
    public static boolean deleteRole(String idmRole) {
        boolean roleDeleted = false;

        IDMHandler idm = new IDMHandler();
        roleDeleted = idm.deleteManagedRole(idmRole);

        return roleDeleted;

    }

    /**
     *
     * Sublists the IDM Roles of a specific user
     *
     * @param userIDMRoles
     *
     * @return A List of IDMRole object
     *
     */
    public static List<IDMRole> availableRoles(List<IDMRole> userIDMRoles) {
        List<IDMRole> newIDMRoles = new ArrayList<>();

        List<IDMRole> idmRoles = getIDMRoles();

        Map<String, IDMRole> mapOfUserRoles = new HashMap();
        for (IDMRole tempUserRole : userIDMRoles) {
            mapOfUserRoles.put(tempUserRole.getRole(), tempUserRole);
        }

        for (IDMRole tempRole : idmRoles) {
            if (!mapOfUserRoles.containsKey(tempRole.getRole())) {
                newIDMRoles.add(tempRole);
            }
        }

        return newIDMRoles;

    }

}
