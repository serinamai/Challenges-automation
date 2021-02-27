package Supports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
