package stackflow.Questions52736001;


import java.util.Properties;

public class EngineOptions extends AbstractOptions{
    //values
    private String debugEnabled;
    private String debugAvgLoadtime;
    private String showShaderUsed;
    private String mainLanguage;

    //keys
    public static final String DEBUGENABLED_KEY = "debugEnabled";
    public static final String DEBUGAVGLOADTIME_KEY = "debugAvgLoadtime";
    public static final String SHOWSHADERUSED_KEY = "showShaderUsed";
    public static final String MAINLANGUAGE_KEY = "mainLanguage";

//    public String getProperty(String key) {
//        return properties.getProperty(key);
//    }

    public void loadFromFile(String filename) {
//        OptionReader loader = new OptionReader(filename);
        //load properties
        debugEnabled = "language_IT";//loader.getProperty(DEBUGENABLED_KEY);
        debugAvgLoadtime = "1";//loader.getProperty(DEBUGAVGLOADTIME_KEY);
        showShaderUsed = "1";//loader.getProperty(SHOWSHADERUSED_KEY);
        mainLanguage = "1";//loader.getProperty(MAINLANGUAGE_KEY);

        properties.put(DEBUGENABLED_KEY, debugEnabled);
        properties.put(DEBUGAVGLOADTIME_KEY, debugAvgLoadtime);
        properties.put(SHOWSHADERUSED_KEY, showShaderUsed);
        properties.put(MAINLANGUAGE_KEY, mainLanguage);
    }
}
