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
package calcmassview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 * Настройки приложения
 * @author Sergei Lyashko
 */
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String saveFileName = "save.calc";
    private Preference savedPreferences;
    private transient FileInputStream fileInputStream;
    
    private ArrayList<JComponent> components;
    
    /**
     * Сохранение настроек
     * @param components
     */
    public void save(ArrayList<JComponent> components){
        this.components = components;
        FileOutputStream newFile = createSaveFile();
        savePreferenceToFile(newFile);
    }
    
    private FileOutputStream createSaveFile() {
        try {
            return new FileOutputStream(saveFileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void savePreferenceToFile(FileOutputStream fileOutputStream) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(this);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isSaved(){
        return findFile();
    }
    
    private boolean findFile(){
        try {
            fileInputStream = new FileInputStream(saveFileName);
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }        
    }
    
    public ArrayList<JComponent> loadSavedComponents(){
        extractPreferences();
        return savedPreferences.getComponents();
    }
    
    // 
    private void extractPreferences(){
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            this.savedPreferences = (Preference)input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<JComponent> getComponents(){
        return components;
    }
}
