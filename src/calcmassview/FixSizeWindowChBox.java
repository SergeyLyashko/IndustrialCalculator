/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview;

import java.awt.event.ItemEvent;

/**
 *
 * @author Korvin
 */
public class FixSizeWindowChBox extends SettingChBox {
    
    private final SettingsPanel setting;
    
    public FixSizeWindowChBox(SettingsPanel setting){
        super();
        this.setting = setting;
        super.setSelected(true);
        super.setSize(200, 20);
        super.setText("зафиксировать размер окна");
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        System.out.println("test Size Win");             
    }
    
    
}
