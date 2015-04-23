package eu.ubitech.fistar.certificate;

import eu.ubitech.fistar.ejbcarestclient.services.RESTClientProvider;
import eu.ubitech.fistar.ejbcarestclient.services.RESTClientService;
import eu.ubitech.fistar.other.Util;
import eu.ubitech.fistar.pseudonym.Pseudonym;
import eu.ubitech.fistar.database.DSHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christos Paraskeva
 */
public final class CertificateRequestor {

    private static CertificateRequestor certificateRequestor;
    private RESTClientService restClientService;

    //EJBCA Defaults params
    private String defaultCAName = "FISTARManagementCA";

    //Ensure that class cannot be ibstanciate
    private CertificateRequestor() {

    }

    //Public API
    public synchronized static CertificateRequestor getInstance() {
        if (null == certificateRequestor) {
            certificateRequestor = new CertificateRequestor();
            Logger.getLogger(CertificateRequestor.class.getName()).info("CertificateRequestor has been initialized!");
        }
        return certificateRequestor;
    }

    public String createCertificateRequest(Pseudonym pseudonym) {
        String pseudonymKey = Util.generateRandomString(12, Util.Mode.ALPHANUMERIC);
        pseudonym.setPseudonymKey(pseudonymKey);
        //Create new EndEntity
        boolean isEntityCreated = getRestClientService().createEndEntity(pseudonym.getUsername(), pseudonym.getPseudonymKey(), getDefaultCAName(), "CN=" + pseudonym.getFirstName() + " " + pseudonym.getLastName(), "FISTARUser", "ENDUSER", pseudonym.getEmail(), "P12");

        if (isEntityCreated && savePseudonym(pseudonym)) {
            Logger.getLogger(CertificateRequestor.class.getName()).log(Level.INFO, "Successfully created entitty for username: {0}", pseudonym.getUsername());
            return pseudonymKey;
        }
        return null;
    }

    private boolean savePseudonym(Pseudonym pseudonym) {

        Connection ds = DSHandler.INSTANCE.getDatasource();
        PreparedStatement stm = null;
        try {
            stm = ds.prepareStatement("INSERT INTO Pseudonym  (username,firstName,lastName,email,gender,pseudonymKey) VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE username=VALUES(username),firstName=VALUES(firstName),lastName=VALUES(lastName),email=VALUES(email),gender=VALUES(gender),pseudonymKey=VALUES(pseudonymKey)");
            stm.setString(1, pseudonym.getUsername());
            stm.setString(2, pseudonym.getFirstName());
            stm.setString(3, pseudonym.getLastName());
            stm.setString(4, pseudonym.getEmail());
            stm.setString(5, pseudonym.getGender());
            stm.setString(6, pseudonym.getPseudonymKey());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DSHandler.INSTANCE.closeDBStreams(ds, stm, null);

        }

        return true;

    }

    private Pseudonym getPseudonym(String username) {

        Connection ds = DSHandler.INSTANCE.getDatasource();
        PreparedStatement stm = null;
        ResultSet rs = null;
        Pseudonym pseudonym = null;
        try {
            stm = ds.prepareStatement("select * FROM Pseudonym  where username=?");
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                pseudonym = new Pseudonym(username, rs.getNString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("gender"), rs.getString("pseudonymKey"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DSHandler.INSTANCE.closeDBStreams(ds, stm, rs);

        }
        return pseudonym;
    }

    public FISTARCertificate generateCertificate(String username, String pseudonymCode) {
        FISTARCertificate fistarCertificate = new FISTARCertificate();
        Pseudonym pseudonym = getPseudonym(username);
        if (null == pseudonym) {
            Logger.getLogger(CertificateRequestor.class.getName()).severe("Could not find stored pseudonym for user: " + username);
        } else {
            if (!pseudonym.getPseudonymKey().endsWith(pseudonymCode)) {
                Logger.getLogger(CertificateRequestor.class.getName()).severe("Invalid pseudonym code: " + pseudonymCode + " for user: " + username);
            } else {
                //Generate certificate
                fistarCertificate.setCertificate(getRestClientService().createUserCertificate(username, pseudonymCode));
                fistarCertificate.setCertificateName(pseudonym.getUsername() + ".P12");
            }
        }
        return fistarCertificate;
    }

    private RESTClientService getRestClientService() {
        if (null == restClientService) {
            RESTClientProvider restClientProvider = new RESTClientProvider("http://localhost:9090/rest/ejbca");
            restClientService = new RESTClientService(restClientProvider);
        }
        Logger.getLogger(CertificateRequestor.class.getName()).info("Created new RESTClientService");
        return restClientService;
    }

    public void setDefaultCAName(String newCAName) {
        this.defaultCAName = newCAName;
    }

    public String getDefaultCAName() {
        return this.defaultCAName;
    }
}
