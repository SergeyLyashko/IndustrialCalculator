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

import java.awt.Font;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

/**
 * ���-���� ��� ��������� �������� ���������
 * @author Sergei Lyashko
 */
public class DifficultAreaBox extends JCheckBox {
    
    private final String boxName = "������� ��������";
    private final BasePanel basePanel;
    private boolean stateBoxOFF;
        
    public DifficultAreaBox(BasePanel basePanel){
        super.setSelected(false);
        super.setSize(140, 17);
        super.setLocation(187, 90);  
        super.setText(boxName);
        Font deriveFont = super.getFont().deriveFont(10f);
        super.setFont(deriveFont);
        this.basePanel = basePanel;
        this.stateBoxOFF = true;
        super.addItemListener(basePanel);
    }
    
    /**
     * ��������� ������� �� ���-�����
     * @param e
     */
    public void actionChooser(ItemEvent e) {
        setStateDifficultArea(e.getStateChange());
    }
    
    // ��������� ��������� � ����������� �� ���-����� 
    private void setStateDifficultArea(int stateChange) {
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
    public void oN(){
        boolean detailHaveWidth = basePanel.getNumbersMenu().detailHaveWidth();
        if(detailHaveWidth){
            basePanel.getWidthField().deactiveField();
            basePanel.getLengthField().difficultAreaStateON();
            basePanel.getWidthField().difficultAreaStateON();
        }
        stateBoxOFF = false;
    }
    
    // ���-���� ��������
    public void oFF(){
        boolean detailHaveWidth = basePanel.getNumbersMenu().detailHaveWidth();
        if(detailHaveWidth){
            basePanel.getWidthField().activeField();  
            basePanel.getLengthField().difficultAreaStateOFF();
            basePanel.getWidthField().difficultAreaStateOFF();
        }
        stateBoxOFF = true;
    }    
    
    /**
     * ��������� �������� �� ���-����
     * @return true ���� ���-���� ��������
     */
    public boolean isAreaBoxOFF(){
        return stateBoxOFF;
    }    
}
