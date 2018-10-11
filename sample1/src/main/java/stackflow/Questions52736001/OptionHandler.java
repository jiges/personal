package stackflow.Questions52736001;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class OptionHandler {
    private static HashMap<Integer, AbstractOptions> optionList;
    private static HashMap<Integer, String> optionFilename;
    private static CountDownLatch sync;

    static {
        setupOptions();
    }

    //OptionFIleID's (Starting from 101 to 199)
    public static final int GRAPHIC_OPTION_ID = 101;
    public static final int ENGINE_OPTION_ID = 102;
    public static final int CURRENT_LANGUAGE_ID = 103;

    public static final int GRAPHIC_OPTION_TYPE = 201;
    public static final int ENGINE_OPTION_TYPE = 202;
    public static final int CURRENT_LANGUAGE_TYPE = 203;

    public static void setupOptions() {
        optionList = new HashMap<Integer, AbstractOptions>();
        optionFilename = new HashMap<Integer, String>();
        //initialize
        sync = new CountDownLatch(1);
    }

    public static void addOptionFile(int id, AbstractOptions options, String filename) {
        options.setupProperties();
        optionList.put(id, options);
        optionFilename.put(id, filename);
    }

    public static String getProperty(String optionKey, int optionFileID) {
        try {
            //await when the property is not ready yet
            sync.await();
        } catch (InterruptedException e) {
            //log("thread was interrupted")
            Thread.currentThread().interrupt();
        }
        return optionList.get(optionFileID).getProperty(optionKey);
    }

    public static void loadOptionListFromFile(int id, int type) {
        System.out.println(optionFilename.get(id));
//        if(type == GRAPHIC_OPTION_TYPE)
//            GraphicOptions.loadFromFile(optionFilename.get(id));
        if(type == ENGINE_OPTION_TYPE)
            optionList.get(id).loadFromFile(optionFilename.get(id));
//        if(type == CURRENT_LANGUAGE_TYPE)
//            CurrentLanguage.loadFromFile(optionFilename.get(id));
        //Notify other threads that the property is ready
        sync.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            String currentLang = OptionHandler.getProperty(EngineOptions.MAINLANGUAGE_KEY, OptionHandler.ENGINE_OPTION_ID);
            System.out.println(currentLang);
        }).start();
        Thread.sleep(3000);
        OptionHandler.addOptionFile(OptionHandler.ENGINE_OPTION_ID, new EngineOptions(), "EngineOptions");
        OptionHandler.loadOptionListFromFile(OptionHandler.ENGINE_OPTION_ID, OptionHandler.ENGINE_OPTION_TYPE);
    }
}