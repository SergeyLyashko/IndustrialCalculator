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
package calcmassview.base;

import calcmassview.settings.Theme;
import java.awt.Color;
import javax.swing.JLabel;

/**
 * информационна строка внизу основного окна
 * @author Sergei Lyashko
 */
public class ServiceInfo extends JLabel {
    
    private final BasePanel basePanel;
    
    public ServiceInfo(BasePanel basePanel){
        super.setVisible(true);
        super.setHorizontalAlignment(CENTER);
        super.setSize(315, 15);
        this.basePanel = basePanel;
        addContent();
    }
    
    private void addContent(){
        this.setLocation(20, 140);
        basePanel.add(this);
        Theme.addTheme(this);
    }
    
    /**
     * Сброс надписи служебной строки на панели View
     */
    public void reset(){
        super.setText(null);
        super.setForeground(Theme.getColorResultMarker());
    }
    
    /**
     * вывод служебной строки на панель View
     * @param message
     */
    public void setMessage(String message){
        super.setText(message);
    }
    
    /**
     *
     * @param message
     */
    public void setErrorMessage(String message){
        super.setForeground(Color.RED);        
        super.setText(message);
    }       
}
