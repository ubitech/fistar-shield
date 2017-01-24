package eu.ubitech.fistar.pseudonym;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author ermis
 */
public class Test {

    static final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
    static final long FIVE_SECONDS_IN_MILLIS = 5000;
    static Random randomNumberGenerator = new Random();

    ;

    public static void main(String[] args) {
        randomNumberGenerator = new Random();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();

        for (int i = 0; i < 100; i++) {
            System.out.println("\"" + String.valueOf((i + 1)) + "\"" + "," + "\"" + dateFormat.format(date = new Date(date.getTime() + (FIVE_SECONDS_IN_MILLIS))) + "\"" + "," + "\"" +  String.valueOf(randomNumberGenerator.nextInt(100)) + "\"");
        }

    }

}
