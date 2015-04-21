package eu.ubitech.fistar.other;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fluttercode.datafactory.ContentDataValues;
import org.fluttercode.datafactory.impl.DataFactory;

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
    
    public static void main(String[] args){
//        DataFactory df = new DataFactory();
//        	for (int i = 0; i < 1000000; i++) {
//			String name = df.getEmailAddress();
//			//System.out.println(name);
//		}
        
        System.out.println(createAlgorithm("!test!","SHA"));
        
    }
}
