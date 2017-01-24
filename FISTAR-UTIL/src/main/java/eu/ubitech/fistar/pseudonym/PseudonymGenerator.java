package eu.ubitech.fistar.pseudonym;

import java.util.Random;
import java.util.logging.Logger;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author Christos Paraskeva
 */
public final class PseudonymGenerator {

    private final int MAX_RANDOM_NUMBER = 100000;
    private static PseudonymGenerator pseudonymGenerator = null;
    private DataFactory dataFactory;
    private Random randomNumberGenerator;

    private PseudonymGenerator() {
        init();
    }

    /**
     * Initialize PseudonymGenerator
     *
     */
    private void init() {
        dataFactory = new DataFactory();
        randomNumberGenerator = new Random();
    }

    private String getRandomEmail() {
        String email = "";
        int randomNum = this.randomNumberGenerator.nextInt(MAX_RANDOM_NUMBER);
        for (int i = 0; i <= randomNum; i++) {
            email = this.dataFactory.getEmailAddress();
        }
        return email;
    }

    private String getRandomLastName() {
        String email = "";
        int randomNum = this.randomNumberGenerator.nextInt(MAX_RANDOM_NUMBER);
        for (int i = 0; i <= randomNum; i++) {
            email = this.dataFactory.getLastName();
        }
        return email;
    }

    private String getRandomFirstName() {
        String email = "";
        int randomNum = this.randomNumberGenerator.nextInt(MAX_RANDOM_NUMBER);
        for (int i = 0; i <= randomNum; i++) {
            email = this.dataFactory.getFirstName();
        }
        return email;
    }

    private String getRandomAddress() {
        String address = "";
        int randomNum = this.randomNumberGenerator.nextInt(MAX_RANDOM_NUMBER);
        for (int i = 0; i <= randomNum; i++) {
            address = this.dataFactory.getAddress();
        }
        return address;
    }

    //Public API
    public static synchronized PseudonymGenerator getInstance() {
        if (null == pseudonymGenerator) {
            pseudonymGenerator = new PseudonymGenerator();
            Logger.getLogger(PseudonymGenerator.class.getName()).info("PseudonymGenerator has been initialized!");
        }
        return pseudonymGenerator;
    }

    public Pseudonym getRandomPseudonym() {
        Pseudonym pseudonym;
        //TODO: fill with actual values
        /*String firstName = "";
         String lastName = "";
         String email = "";*/
        pseudonym = new Pseudonym(getRandomFirstName(), getRandomLastName(), getRandomEmail(), "");
        return pseudonym;
    }

    public static void main(String[] args) {

        PseudonymGenerator ps = PseudonymGenerator.getInstance();

        String randNum;

        for (int i = 0; i < 1000; i++) {
            randNum = String.valueOf( ps.randomNumberGenerator.nextInt(9)) + String.valueOf( ps.randomNumberGenerator.nextInt(9))+String.valueOf( ps.randomNumberGenerator.nextInt(9))+String.valueOf( ps.randomNumberGenerator.nextInt(9))+String.valueOf( ps.randomNumberGenerator.nextInt(9))+String.valueOf( ps.randomNumberGenerator.nextInt(9))+String.valueOf( ps.randomNumberGenerator.nextInt(9)) ;
            System.out.println("\"" + String.valueOf((i+1)) +"\"" + ","+ "\""+ps.getRandomFirstName()+"\""+  ","+"\""+  ps.getRandomLastName()+"\""+ "," + "\""+ps.getRandomAddress()+ "\""+ "," + "\""+"+44" + randNum + "\""+"," +"\""+ ps.getRandomEmail()+"\"");
        }

    }

}
