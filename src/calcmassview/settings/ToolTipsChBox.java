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
import javax.swing.JComponent;
import javax.swing.ToolTipManager;

/**
 * ¬сплывающие подсказки checkbox
 * @author Sergei Lyashko
 */
public class ToolTipsChBox extends JCheckBox implements Selectable {
    
    private final SettingsPanel settingsPanel;
    
    public ToolTipsChBox(SettingsPanel panel){
        super.setSelected(true);
        super.setSize(320, 20);
        super.setLocation(15, 60);
        super.setText("включить всплывающие подсказки");
        this.settingsPanel = panel;
        addContent();
    }
    
    public ToolTipsChBox(){
        this.settingsPanel = null;
    }
    
    private void addContent(){
        Theme.addTheme(this);
        addToolTips(this, "включение/отключение всплывающих подсказок");
        this.addItemListener(settingsPanel);
    }
    
    /**
     *
     * @param component
     * @param text
     */
    public static void addToolTips(JComponent component, String text){
        component.setToolTipText(text);
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        setToolTips(e.getStateChange());
    }
    
    // установка всплывающих подсказок
    private void setToolTips(int stateChange){
        switch(stateChange){
            case ItemEvent.SELECTED:
                ToolTipManager.sharedInstance().setEnabled(true);
                break;
            case ItemEvent.DESELECTED:
                ToolTipManager.sharedInstance().setEnabled(false);
                break;            
        }
    }    
}
