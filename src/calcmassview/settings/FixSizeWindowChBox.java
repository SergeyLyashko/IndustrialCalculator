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
 * Фиксация размеров окна
 * @author Sergei Lyashko
 */
class FixSizeWindowChBox extends JCheckBox implements Selectable, Serializable {   

    private static final long serialVersionUID = 1L;
    
    private final SettingsPanel settingsPanel;
    private final String text = "фиксировать размер окна";
        
    public FixSizeWindowChBox(SettingsPanel panel){
        this.settingsPanel = panel;
        super.setSelected(true);
        super.setSize(350, 20);
        super.setLocation(15, 85);  
        super.setText("зафиксировать размер окна <in process>");
        super.addItemListener(settingsPanel);
    }
    
    /**
     * Внешнее оформления
     * @param theme цветовая тема приложения
     * @param toolTips всплывающие подсказки
     */
    public void addVisualDecoration(Theme theme, ToolTips toolTips){
        theme.setColorTheme(this);
        toolTips.setToolTips(this, text);
    }
    
    @Override
    public void actionChooser(ItemEvent e) {              
        setSizeWindow(e.getStateChange());
    }
    
    // TODO
    private void setSizeWindow(int stateChange){
        switch(stateChange){
            case ItemEvent.SELECTED:
                //TODO
                break;
            case ItemEvent.DESELECTED:
                //TODO
                break;            
        }
    }
}
