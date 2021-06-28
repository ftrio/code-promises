package ringcentral.common; /*
 * @author : Shahnwaz
 *on 28-06-2021
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.asserts.SoftAssert;
import ringcentral.reportcommon.ExtentManager;
import ringcentral.reportcommon.ExtentTestManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class Helper {

    public static Properties properties;
    public static GlobalApiHelper globalApiHelper;
    public static SoftAssert softAssert;
    public static ExtentManager extentManager;
    public static ExtentTestManager extentTestManager;
    public static ResponseParser responseParser;


    public Helper(){
        properties=new Properties();
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream("properties\\application.properties");
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void intiateClass(){

        globalApiHelper=new GlobalApiHelper();
        responseParser=new ResponseParser();
        softAssert=new SoftAssert();
    }

    public String randomGenrator(int size){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public String randomGenratorNumber(int size){
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }




}
