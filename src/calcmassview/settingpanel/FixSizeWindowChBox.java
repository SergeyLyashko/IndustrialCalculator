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
public class FixSizeWindowChBox extends AbstractSettingChBox {   
    
    public FixSizeWindowChBox(){
        super();        
        super.setSelected(true);
        super.setSize(200, 20);
        super.setText("зафиксировать размер окна");             
        Theme.addTheme(this);
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        System.out.println("test Size Win");             
    }    
}
