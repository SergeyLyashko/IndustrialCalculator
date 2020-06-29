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
 * ����������� ��������� checkbox
 * @author Sergei Lyashko
 */
public class ToolTipsChBox extends JCheckBox implements Selectable, Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String text = "���������/���������� ����������� ���������";
    private final String chBoxName = "�������� ����������� ���������";
    private ToolTips toolTips;
    
    public ToolTipsChBox(SettingsPanel settingsPanel){
        super.setSelected(true);
        super.setSize(320, 20);
        super.setLocation(15, 60);
        super.setText(chBoxName);
        super.addItemListener(settingsPanel);
    }
    
    /**
     * ������� ����������
     * @param theme �������� ���� ����������
     * @param toolTips ����������� ���������
     */
    public void addVisualDecoration(Theme theme, ToolTips toolTips){
        this.toolTips = toolTips;
        theme.setColorTheme(this);
        toolTips.setToolTips(this, text);        
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        setToolTips(e.getStateChange());
    }
    
    // ��������� ����������� ���������
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