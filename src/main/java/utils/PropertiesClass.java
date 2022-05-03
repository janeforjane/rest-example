package utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */


@Component
//@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.properties")
public class PropertiesClass {

    private Map<String,String> settings = new HashMap<>();

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }
}
