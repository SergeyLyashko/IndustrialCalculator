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
import javax.swing.JCheckBox;

/**
 * Фиксация размеров окна
 * @author Sergei Lyashko
 */
class FixSizeWindowChBox extends JCheckBox implements Selectable {   
    
    private final SettingsPanel settingsPanel;
    
    public FixSizeWindowChBox(SettingsPanel panel){
        this.settingsPanel = panel;
        super.setSelected(true);
        super.setSize(350, 20);
        super.setLocation(15, 85);  
        super.setText("зафиксировать размер окна <in process>");
        addContent();
    }
    
    private void addContent(){
        Theme.addTheme(this);
        this.addItemListener(settingsPanel);
    }
    
    @Override
    public void actionChooser(ItemEvent e) {              
        setSizeWindow(e.getStateChange());
    }
    
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
