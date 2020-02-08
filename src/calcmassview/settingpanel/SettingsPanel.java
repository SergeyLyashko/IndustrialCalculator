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
package calcmassview.settingpanel;

import calcmassview.viewpanelcomponent.FieldMarker;
import calcmassview.Theme;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JPanel;

/**
 * панель настроек приложения
 * @author Sergei Lyashko
 */
public class SettingsPanel extends JPanel implements ItemListener{
    
    private final AbstractSettingChBox themeChooser, toolTipsOffBox, fixSizeWindow, preferedProfileList;   
    
    public SettingsPanel(){
        super(false);        
        super.setBackground(Color.black);     
        
        FieldMarker infoText = new FieldMarker();
        infoText.setText("Настройки");
        infoText.setSize(300, 20);
        infoText.setLocation(15, 10);       
        
        // тема оформления
        Theme.addTheme(this);
        themeChooser = new ThemeChBox();
        themeChooser.setLocation(15, 35);        
        themeChooser.addItemListener(this);
        
        // всплывающие подсказки
        toolTipsOffBox = new ToolTipsOffBox();
        toolTipsOffBox.setLocation(15, 60);
        toolTipsOffBox.addItemListener(this);
        
        // фиксация размера окна
        fixSizeWindow = new FixSizeWindowChBox();
        fixSizeWindow.setLocation(15, 85);               
        fixSizeWindow.addItemListener(this);
        
        // включение списка часто используемых профилей
        preferedProfileList = new PreferedProfileChBox();
        preferedProfileList.setLocation(15, 110);        
        preferedProfileList.addItemListener(this);        
        
        super.add(themeChooser);
        //super.add(toolTipsOffBox);
        //super.add(fixSizeWindow);
        //super.add(preferedProfileList);
        super.add(infoText);
        super.setLayout(null);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        AbstractSettingChBox source = (AbstractSettingChBox) e.getItemSelectable();
        source.actionChooser(e);     
    }
}