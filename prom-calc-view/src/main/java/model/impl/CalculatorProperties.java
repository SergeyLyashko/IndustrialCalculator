package model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import controller.Preference;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
/*
public class CalculatorProperties implements Preference {

    private static final String SAVED_FILE_NAME = "app.properties";
    private static final String DEFAULT_VALUE = "on";
    private static final String COLOR_MODE = "colorMode";
    private static final String TOOL_TIPS_MODE = "toolTipsMode";
    private UiComponent colorThemeBox;
    private UiComponent toolTipsBox;
    private Properties properties;

    @Autowired
    public void setProperties(Properties properties){
        this.properties = properties;
    }

    @Autowired
    @Qualifier("colorThemeBox")
    public void setColorThemeBox(UiComponent colorThemeBox){
        this.colorThemeBox = colorThemeBox;
    }

    @Autowired
    @Qualifier("toolTipsBox")
    public void setToolTipsBox(UiComponent toolTipsBox){
        this.toolTipsBox = toolTipsBox;
    }

    @Override
    public void loadProperties(){
        try {
            properties.load(new FileInputStream(SAVED_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPropertiesToApp(){
        String colorProperty = properties.getProperty(COLOR_MODE, DEFAULT_VALUE);
        String toolTipsProperty = properties.getProperty(TOOL_TIPS_MODE, DEFAULT_VALUE);
        // TODO установка настроек
    }

    @Override
    public List<UiComponent> loadComponents() {
        return null;
    }

    @Override
    public void saveComponents(List<UiComponent> components) {

    }

    @Override
    public boolean isSaved() {
        return false;
    }

    @Override
    public void save() {

    }
}*/
