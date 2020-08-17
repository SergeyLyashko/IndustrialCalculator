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
package calcmassview.settings;

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
import javax.swing.JComponent;

/**
 * Настройки приложения
 * @author Sergei Lyashko
 */
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String saveFileName = "save.calc";
    private Preference savedPreferences;
    
    private final List<JComponent> components;

    public Preference(List<JComponent> components) {
        this.components = components;
    }

    public Preference() {
       components = null;
    }
    
    public Preference load(){
        extractPreferences();
        return savedPreferences;
    }
    
    public List<JComponent> getComponents(){
        return Collections.unmodifiableList(components);
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
    
    // 
    private void extractPreferences(){
        try {
            FileInputStream file = new FileInputStream(saveFileName);
            ObjectInputStream input = new ObjectInputStream(file);
            this.savedPreferences = (Preference)input.readObject();
            input.close();
            System.out.println("saved: "+savedPreferences.toString());// TEST
        } catch (FileNotFoundException ex) {
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Preference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
