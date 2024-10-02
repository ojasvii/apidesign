package com.restapibase;

public class Config {
    private static final String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/config.properties";
    private static PropertiesLoader propertiesLoader = new PropertiesLoader(PROPERTIES_FILE_PATH);

    public static PropertiesLoader getPropertiesLoader() {
        return propertiesLoader;
    }
}

