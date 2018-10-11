package stackflow.Questions52736001;

import java.util.Properties;

public abstract class AbstractOptions {
    protected volatile Properties properties;

    public void setupProperties() {
        properties = new Properties();
    }

    public void setupProperties(Properties properties) {}

    public void setProperty(String key, String value) {
        if(properties.getProperty(key) == null) {
            //throw exception.
        }
        properties.setProperty(key, value);
    }

    public String getProperty(String key) {
        System.out.println(properties);
        return properties.getProperty(key);
    }

    public abstract void loadFromFile(String filename);
}