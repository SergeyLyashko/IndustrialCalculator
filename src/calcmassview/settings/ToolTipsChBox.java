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

import calcmassview.general.ToolTipsInterface;
import java.awt.event.ItemEvent;
import java.io.Serializable;
import javax.swing.JCheckBox;

/**
 * ¬сплывающие подсказки checkbox
 * @author Sergei Lyashko
 */
class ToolTipsChBox extends JCheckBox implements Selectable, Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String chBoxName = "включить всплывающие подсказки";
    private final ToolTipsInterface toolTips;
    
    public ToolTipsChBox(ToolTipsInterface toolTips){
        super.setSelected(true);
        super.setSize(320, 20);
        super.setLocation(15, 60);
        super.setText(chBoxName);
        this.toolTips = toolTips;
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        setToolTips(e.getStateChange());
    }
    
    // установка всплывающих подсказок
    private void setToolTips(int stateChange){
        switch(stateChange){
            case ItemEvent.SELECTED:
                toolTips.oN();
                break;
            case ItemEvent.DESELECTED:
                toolTips.oFF();
                break;            
        }
    }    
}
