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
public class PreferedProfileChBox extends SettingChBox {
    
    private final SettingsPanel setting;
    
    public PreferedProfileChBox(SettingsPanel setting){
        super();
        this.setting = setting;
        super.setSize(220, 20);
        super.setText("часто используемые профили");
        super.setSelected(false);                        
    }

    @Override
    protected void actionChooser(ItemEvent e) {
        System.out.println("test Profile");
    }
    
}
