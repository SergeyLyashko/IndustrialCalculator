/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview.settingpanel;

import java.awt.event.ItemEvent;

/**
 *
 * @author Korvin
 */
public class PreferedProfileChBox extends AbstractSettingChBox {
    
    
    public PreferedProfileChBox(){
        super.setSize(220, 20);
        super.setText("часто используемые профили");
        super.setSelected(false);       
    }

    @Override
    public void actionChooser(ItemEvent e) {
        switch(e.getStateChange()){
            case ItemEvent.SELECTED:
                break;
            case ItemEvent.DESELECTED:                
                break;            
        } 
    }
}
