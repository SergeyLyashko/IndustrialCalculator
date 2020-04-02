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

import calcmassview.AbstractPanel;
import calcmassview.viewpanel.FieldMarker;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * панель настроек приложения
 * @author Sergei Lyashko
 */
public class SettingsPanel extends AbstractPanel implements ItemListener{
    
    // чек-боксы
    private AbstractSettingChBox 
            themeChooser,
            toolTipsOffBox,
            fixSizeWindow,
            preferedProfileList;   
    
    public SettingsPanel(){
        createFieldsMarker();
        createThemeCheckBox();
        //createToolTipsCheckBox();
        //createFixSizeWindowCheckBox();
        //createPreferedProfileCheckBox();
        super.setLayout(null);
    }
    
    // включение списка часто используемых профилей
    private void createPreferedProfileCheckBox(){
        preferedProfileList = new PreferedProfileChBox();
        preferedProfileList.setLocation(15, 110);        
        preferedProfileList.addItemListener(this);        
        super.add(preferedProfileList);
    }
    
    // фиксация размера окна
    private void createFixSizeWindowCheckBox(){
        fixSizeWindow = new FixSizeWindowChBox();
        fixSizeWindow.setLocation(15, 85);               
        fixSizeWindow.addItemListener(this);
        super.add(fixSizeWindow);
    }
    
    // чекбокс включения/отключения всплывающих подсказок
    private void createToolTipsCheckBox(){
        toolTipsOffBox = new ToolTipsOffBox();
        toolTipsOffBox.setLocation(15, 60);
        toolTipsOffBox.addItemListener(this);
        super.add(toolTipsOffBox);
    }
    
    // чекбокс выбора цветовой темы оформления
    private void createThemeCheckBox(){
        themeChooser = new ThemeChBox();
        themeChooser.setLocation(15, 35);        
        themeChooser.addItemListener(this);
        super.add(themeChooser);
    }
    
    // маркеры полей длина и ширина
    private void createFieldsMarker(){
        FieldMarker infoText = new FieldMarker(this);
        infoText.setText("Настройки");
        infoText.setSize(300, 20);
        infoText.setLocation(15, 10);       
        super.add(infoText);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        AbstractSettingChBox source = (AbstractSettingChBox) e.getItemSelectable();
        source.actionChooser(e);     
    }
}