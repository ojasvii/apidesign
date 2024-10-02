package com.restapibase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    private Properties properties = new Properties();
    private String propertiesFilePath;

    // Constructor to initialize the properties file path
    public PropertiesLoader(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
        loadProperties();
    }

    // Load properties from the specified file
    private void loadProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get a property value by key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    //Get selected properties as a map
    public Map<String,String> getSelectedProperties(List<String> keys){
    	Map<String, String> selectedProps = new HashMap<>();
    	for(String key : keys) {
    		if(properties.containsKey(key)) {
    			selectedProps.put(key, properties.getProperty(key));
    		}
    	}
    	return selectedProps;
    }

    // Get all properties as a Map
    public Map<String, String> getAllProperties() {
        Map<String, String> allProps = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
        	allProps.put(name, properties.getProperty(name));
        }
        return allProps;
    }
}
