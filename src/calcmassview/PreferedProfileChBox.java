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
public class PreferedProfileChBox extends AbstractSettingChBox {    
    
    public PreferedProfileChBox(){
        super();        
        super.setSize(220, 20);
        super.setText("часто используемые профили");
        super.setSelected(false);       
        Theme.addTheme(this);
    }

    @Override
    protected void actionChooser(ItemEvent e) {
        System.out.println("test Profile");
    }
    
}
