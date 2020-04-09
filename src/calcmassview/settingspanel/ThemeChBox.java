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
package calcmassview.settingspanel;

import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

/**
 * Color theme checkbox
 * @author Sergei Lyashko
 */
class ThemeChBox extends JCheckBox implements Selectable {

    private Theme theme;
    private final SettingsPanel panel;
    
    public ThemeChBox(SettingsPanel panel){
        super.setBackground(Color.BLACK);
        super.setForeground(Color.white);
        super.setSelected(true);
        super.setSize(180, 20);
        super.setText("темная тема оформления");
        super.setLocation(15, 35);
        this.panel = panel;
        create();
    }
    
    private void create(){
        Theme.addTheme(this);
        this.addItemListener(panel);
        panel.add(this);
    }
    
    // смена темы оформления
    @Override
    public void actionChooser(ItemEvent e) {              
        setTheme(e.getStateChange());
    } 
    
    private void setTheme(int stateChange){    
        switch(stateChange){
            case ItemEvent.SELECTED:
                theme = new Theme();
                theme.dark();
                break;
            case ItemEvent.DESELECTED:
                theme = new Theme();
                theme.light();
                break;            
        }        
    }
}
