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
package calcmassview;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JPanel;

/**
 * панель настроек программы
 * @author Sergei Lyashko
 */
public class SettingsPanel extends JPanel implements ItemListener{
    
    private final SettingChBox themeChooser, fixSizeWindow, preferedProfileList;
    
    private final Theme theme;
    
    public SettingsPanel(Theme theme){
        super(false);        
        super.setBackground(Color.black);
        
        this.theme = theme;
        
        Marker infoText = new Marker();
        infoText.setText("Настройки");
        infoText.setSize(300, 20);
        infoText.setLocation(15, 10);
        
        // тема оформления
        themeChooser = new ThemeChBox(theme);
        themeChooser.setLocation(15, 40);        
        themeChooser.addItemListener(this);        
        
        // фиксация размера окна
        fixSizeWindow = new FixSizeWindowChBox(this);
        fixSizeWindow.setLocation(15, 70);               
        fixSizeWindow.addItemListener(this);
        
        // включение списка часто используемых профилей
        preferedProfileList = new PreferedProfileChBox(this);
        preferedProfileList.setLocation(15, 100);        
        preferedProfileList.addItemListener(this);
        
        super.add(preferedProfileList);
        super.add(fixSizeWindow);
        super.add(themeChooser);
        super.add(infoText);
        super.setLayout(null);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        SettingChBox source = (SettingChBox) e.getItemSelectable();
        source.actionChooser(e);     
    }
}
