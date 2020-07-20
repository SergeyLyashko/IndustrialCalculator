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

import calcmassview.settings.Theme;
import calcmassview.settings.ToolTips;
import calcmassview.settings.ToolTipsInterface;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

/**
 * Чек-бокс для установки сложного периметра
 * @author Sergei Lyashko
 */
public class DifficultAreaBox extends JCheckBox {
    
    private final String boxName = "сложный периметр";
    private final String toolTipText = "расчет массы детали по задаваемой площади детали";
    private final BasePanel basePanel;
    private final Theme theme;
    private boolean stateBoxOFF;
        
    public DifficultAreaBox(BasePanel basePanel, Theme theme, ToolTipsInterface toolTips){
        super.setSelected(false);
        super.setSize(140, 17);
        super.setLocation(187, 90);  
        super.setText(boxName);
        super.setFont(this.getFont().deriveFont(10f));
        this.basePanel = basePanel;
        this.theme = theme;
        this.stateBoxOFF = true;
        super.addItemListener(basePanel);
        addContent(toolTips);
    }
    
    // 
    private void addContent(ToolTipsInterface toolTips){
        toolTips.setToolTips(this, toolTipText);
        basePanel.add(this);
        theme.setColorTheme(this);
    }
    
    /**
     * Активация события на чек-боксе
     * @param e
     */
    public void actionChooser(ItemEvent e) {
        setStateDifficultArea(e.getStateChange());
    }
    
    // установка состояния в зависимости от чек-бокса 
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
    
    // чек-бокс включен
    public void oN(){
        boolean detailHaveWidth = basePanel.getNumberProfileMenu().detailHaveWidth();
        if(detailHaveWidth){
            basePanel.getWidthField().deactiveField();
            basePanel.getLengthField().difficultAreaStateON();
            basePanel.getWidthField().difficultAreaStateON();
        }
        stateBoxOFF = false;
    }
    
    // чек-бокс выключен
    public void oFF(){
        boolean detailHaveWidth = basePanel.getNumberProfileMenu().detailHaveWidth();
        if(detailHaveWidth){
            basePanel.getWidthField().activeField();  
            basePanel.getLengthField().difficultAreaStateOFF();
            basePanel.getWidthField().difficultAreaStateOFF();
        }
        stateBoxOFF = true;
    }    
    
    /**
     * Проверяет выключен ли чек-бокс
     * @return true если чек-бокс выключен
     */
    public boolean isAreaBoxOFF(){
        return stateBoxOFF;
    }    
}
