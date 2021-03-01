package Supports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Utils {

    public String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public String getGlobalProperties(String property) throws IOException {
        String projectDir = System.getProperty("user.dir");
        String filePath = "/src/main/java/Properties/global.properties";
        FileReader reader = new FileReader(projectDir + filePath.replace("/", File.separator));
        Properties prop = new Properties();
        prop.load(reader);
        return prop.getProperty(property);
    }
}
