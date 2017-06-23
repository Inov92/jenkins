package org.protasov.ii.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestSettings {

    private String driverName; // имя WebDriver
    private String driverPath; // путь к WebDriver
    private String cssPath; //путь к *.css файлу с логинами и паролями

    public TestSettings(){
        Properties p = readSettings(this);
        driverName = p.getProperty("driverName");
        driverPath = p.getProperty("driverPath");
        cssPath = p.getProperty("cssPath");
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getCssPath() {
        return cssPath;
    }

    //метод чтения config.properties файла
    static Properties readSettings(TestSettings settings){

        Properties prop = new Properties();
        FileInputStream input;

        try {
            input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
            input.close();

        }catch (IOException e) {
            System.err.println("ERROR: CSS file not found!");
        }
        return prop;
    }
}
