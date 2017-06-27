package modules;


import org.apache.log4j.Logger;

/**
 * Created by Lokesh_GnanaSekhar on 6/26/2017.
 */
public class Log {

    // Initialize Log4j logs
    public static Logger Log = Logger.getLogger(Log.class.getName());//


    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String testcaseName) {
        Log.info("**********************************************************************************************");
        Log.info("-------------------  " + testcaseName.toUpperCase()+"-STARTED" + "   -------------------------");
        Log.info("**********************************************************************************************");
    }

    //This is to print log for the ending of the test case
    public static void endTestCase(String testcaseName) {
        Log.info("***********************   " + testcaseName.toUpperCase()+"- ENDED" + "  ************************");
    }

    // Need to create these methods, so that they can be called
    public static void info(String message) {
        Log.info(message);

    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }


}
