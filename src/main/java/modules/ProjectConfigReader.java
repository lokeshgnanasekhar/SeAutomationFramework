package modules;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Lokesh_GnanaSekhar on 6/21/2017.
 */
public class ProjectConfigReader {

    Properties properties;
    InputStream input;

    public ProjectConfigReader(){
        properties = new Properties();
        try {
            input = new FileInputStream("../resources/ProjectConfig.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getBrowserName(){
        return properties.getProperty("BROWSER_NAME");
    }


}
