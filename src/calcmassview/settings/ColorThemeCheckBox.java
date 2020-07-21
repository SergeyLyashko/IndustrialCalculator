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

import java.awt.event.ItemEvent;
import java.io.Serializable;
import javax.swing.JCheckBox;

/**
 * Color theme checkbox
 * @author Sergei Lyashko
 */
class ColorThemeCheckBox extends JCheckBox implements Selectable, Serializable {

    private static final long serialVersionUID = 1L;

    private ColorThemeInterface theme;
    private final String toolTipText = "включить/отключить темную тему приложения";
    private final String boxName = "темная тема оформления";
    
    public ColorThemeCheckBox(SettingsPanel settingsPanel){
        super.setSelected(true);
        super.setSize(180, 20);
        super.setText(boxName);
        super.setLocation(15, 35);
        super.addItemListener(settingsPanel);
    }
    
    /**
     * Внешнее оформления
     * @param theme цветовая тема приложения
     * @param toolTips всплывающие подсказки
     */
    public void addVisualDecoration(ColorThemeInterface theme, ToolTipsInterface toolTips){
        this.theme = theme;
        theme.setColorTheme(this);
        toolTips.setToolTips(this, toolTipText);
    }
    
    @Override
    public void actionChooser(ItemEvent e) {              
        setTheme(e.getStateChange());
    } 
    
    // установка темы приложения
    private void setTheme(int stateChange){    
        switch(stateChange){
            case ItemEvent.SELECTED:
                theme.dark();
                break;
            case ItemEvent.DESELECTED:
                theme.light();
                break;            
        }        
    }
}
