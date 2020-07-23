/*
 * Copyright 2019 Sergei Lyashko. Contacts: <9lLLLepuLLa@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassview.general;

import calcmassview.settings.SettingsPanel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Настройки приложения
 * @author Sergei Lyashko
 */
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String saveFileName = "save.ser";
    private SettingsPanel settingsPanel;
    private ColorThemeInterface theme;
    private ToolTipsInterface toolTips;
    
    public void addComponent(SettingsPanel settingsPanel, ColorThemeInterface theme, ToolTipsInterface toolTips){
        this.settingsPanel = settingsPanel;
        this.theme = theme;
        this.toolTips = toolTips;
    }
    
    /**
     *
     * @return
     */
    public SettingsPanel getSettingsPanel(){
        return settingsPanel;
    }
    
    /**
     *
     * @return
     */
    public ColorThemeInterface getTheme(){
        return theme;
    }
    
    public ToolTipsInterface getToolTips(){
        return toolTips;
    }
    
    /**
     * Сохранение настроек
     */
    public void save(){
        try {
            FileOutputStream file = new FileOutputStream(saveFileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Загрузка настроек приложения
     * @return сохраненные настройки
     */
    public Preference load(){
        try {
            FileInputStream file = new FileInputStream(saveFileName);
            ObjectInputStream input = new ObjectInputStream(file);
            Preference preference = (Preference)input.readObject();
            input.close();
            return preference;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
