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
package calcmassview.settings;

import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

/**
 * ToolTips checkbox
 * @author Sergei Lyashko
 */
class ToolTipsOffBox extends JCheckBox implements Selectable {
    
    private final SettingsPanel panel;
    
    public ToolTipsOffBox(SettingsPanel panel){
        this.panel = panel;
        create();
    }
    
    private void create(){
        Theme.addTheme(this);
        super.setSelected(true);
        super.setSize(250, 20);
        super.setLocation(15, 60);
        super.setText("отключить всплывающие подсказки");
        this.addItemListener(panel);
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        setToolTips(e.getStateChange());
    }
    
    // установка всплывающих подсказок
    private void setToolTips(int stateChange){
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
