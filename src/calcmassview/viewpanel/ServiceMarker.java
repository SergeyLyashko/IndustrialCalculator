/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
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
package calcmassview.viewpanel;

import calcmassview.BasePanel;
import calcmassview.settingpanel.Theme;
import java.awt.Color;
import javax.swing.JLabel;

/**
 * информационна строка внизу основного окна
 * @author Sergei Lyashko
 */
public class ServiceMarker extends JLabel {
    
    private BasePanel basePanel;
    
    public ServiceMarker(BasePanel basePanel){
        super.setVisible(true);
        super.setHorizontalAlignment(CENTER);
        super.setSize(315, 15);
        this.basePanel = basePanel;
        this.setLocation(20, 140);
        basePanel.add(this);
        Theme.addTheme(this);
    }
    
    /**
     * —брос надписи служебной строки на панели View
     */
    public void resetServiceMarker(){
        super.setText(null);
    }
    
    /**
     * выбор служебной строки дл€ вывода на панель View в зависимости
     * от произошедшего событи€
     * @param eventStr строковое представление событи€
     */
    public void setMarker(String eventStr){
        String text;
        switch(eventStr){
            case "copy":                
                Theme.addThemeMarker(this);
                text = "результат скопирован в буфер обмена";
                break;
            case "error":
                super.setForeground(Color.red);
                text = "введено неверное значение";
                break;
            default:
                text = null;
                break;
        }
        super.setText(text);
    }
}
