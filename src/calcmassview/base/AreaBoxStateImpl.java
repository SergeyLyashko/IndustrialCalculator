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
package calcmassview.base;

import calcmassview.settings.ColorTheme;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.lang.annotation.Annotation;
import javax.swing.JCheckBox;
import calcmassview.settings.ToolTips;

/**
 * ���-���� ��� ��������� �������� ���������
 * @author Sergei Lyashko
 */
@ToolTips(getToolTipDescription = "")
@ColorTheme()
@CalculatorPanel()
@AreaBoxState(isAreaBoxStateOFF = true)
public class AreaBoxStateImpl extends JCheckBox implements CalculatorPanel, ToolTips, ColorTheme, AreaBoxState{
    
    private final String toolTipsText = "������ ����� ������ �� ���������� ������� ������";
    private final String boxName = "������� ��������";
    private boolean stateOFF;
        
    public AreaBoxStateImpl(){
        super.setSelected(false);
        super.setSize(140, 17);
        super.setLocation(187, 90);  
        super.setText(boxName);
        Font deriveFont = super.getFont().deriveFont(10f);
        super.setFont(deriveFont);
        this.stateOFF = true;
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    /**
     * ��������� ������� �� ���-�����
     * @param e
     */
    public void actionChooser(ItemEvent e) {
        setState(e.getStateChange());
    }
    
    // ��������� ��������� � ����������� �� ���-����� 
    private void setState(int stateChange) {
        switch(stateChange){
            case ItemEvent.SELECTED:
                oN();
                break;
            case ItemEvent.DESELECTED:
                oFF();
                break;            
        }
    }
    
    // ���-���� �������
    private void oN(){
        stateOFF = false;     
    }
    
    // ���-���� ��������
    private void oFF(){
        stateOFF = true;        
    }    
    
    /**
     * ��������� �������� �� ���-����
     * @return true ���� ���-���� ��������
     */
    @Override
    public boolean isAreaBoxStateOFF(){
        return stateOFF;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
