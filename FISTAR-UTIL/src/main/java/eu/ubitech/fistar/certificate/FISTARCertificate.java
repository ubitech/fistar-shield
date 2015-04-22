package eu.ubitech.fistar.certificate;

import java.io.InputStream;

/**
 *
 * @author Christos Paraskeva
 */
public class FISTARCertificate {

   InputStream certificate;
    String certificateName;
    String errorMessage;
    boolean isValid = false;

    public InputStream getCertificate() {
        return certificate;
    }

    public void setCertificate(InputStream certificate) {
        this.certificate = certificate;
        isValid = (null != certificate);
    }

    public String getCertificateFileName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public boolean isCertificateValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
