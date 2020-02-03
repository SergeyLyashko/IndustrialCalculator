/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview.settingpanel;

import calcmassview.Theme;
import java.awt.event.ItemEvent;

/**
 *
 * @author Korvin
 */
public class ThemeChBox extends AbstractSettingChBox {

    private Theme theme;
    
    public ThemeChBox(){
        super();        
        super.setSelected(true);
        super.setSize(180, 20);
        super.setText("темная тема оформления");        
        Theme.addTheme(this);
        setTheme(super.isSelected());
    }
    
    // смена темы оформления
    @Override
    public void actionChooser(ItemEvent e) {              
        setTheme(e.getStateChange());
    } 
    
    private void setTheme(int stateChange){    
        switch(stateChange){
            case ItemEvent.SELECTED:
                theme = new Theme();
                theme.dark();
                break;
            case ItemEvent.DESELECTED:
                theme = new Theme();
                theme.light();
                break;            
        }        
    }
    
    private void setTheme(boolean select){
        Theme.addThemeBox(select);
    }
}
