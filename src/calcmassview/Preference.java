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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JComponent;

/**
 * Настройки приложения
 * @author Sergei Lyashko
 */
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final String SAVED_FILE_NAME = "save.calc";    
    private static transient FileInputStream fileInputStream;
    
    private static List<JComponent> components;
    
    /**
     * Сохранение настроек
     * @param savedComponents
     */
    public void save(List<JComponent> savedComponents){
        components = savedComponents.stream().collect(Collectors.toList());
        FileOutputStream newFile = createSaveFile();
        savePreferenceToFile(newFile);
    }
    
    private FileOutputStream createSaveFile() {
        try {
            return new FileOutputStream(SAVED_FILE_NAME);
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
    
    public static boolean isSaved(){
        try {
            readSavedFile();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }
    
    private static FileInputStream readSavedFile() throws FileNotFoundException{
        return new FileInputStream(SAVED_FILE_NAME);
    }
    
    public static List<JComponent> loadSavedComponents(){
        readComponentsFromFile();
        return Collections.unmodifiableList(components);
    }
    
    // 
    private static void readComponentsFromFile(){
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            Preference readObject = (Preference) input.readObject();
            components = readObject.getComponents();
            input.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<JComponent> getComponents(){
        return Collections.unmodifiableList(components);
    }
}
