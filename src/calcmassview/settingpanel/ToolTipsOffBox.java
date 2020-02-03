/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview.settingpanel;

import calcmassview.Theme;
import java.awt.event.ItemEvent;

/**
 *  Всплывающие подсказки
 *
 */
public class ToolTipsOffBox extends AbstractSettingChBox {   
    
    public ToolTipsOffBox(){
        super();        
        super.setSelected(true);
        super.setSize(250, 20);
        super.setText("отключить всплывающие подсказки");             
        Theme.addTheme(this);        
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        setToolTips(e.getStateChange());
    }
    
    private void setToolTips(int stateChange){
        switch(stateChange){
            case ItemEvent.SELECTED:
                
                break;
            case ItemEvent.DESELECTED:
                
                break;            
        }
    }    
}
