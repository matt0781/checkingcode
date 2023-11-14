package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemSimulatedDateManager {
    private static Date date = new Date(); // default: actual date in real world
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getSystemSimulatedDate() {
        return date;
    }

    public static void setSystemSimulatedDate(String dateString) {
        while (true) {
            try {
                date = sdf.parse(dateString);
                break;
            } catch (Exception e) {
                System.err.println("Error parsing the date. Please enter a valid date in yyyy-MM-dd format.");
            }
        }
    }
}
