/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview;

import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

/**
 *
 * @author Korvin
 */
public abstract class SettingChBox extends JCheckBox {   
    
    public SettingChBox(){
        super();       
        super.setBackground(Color.BLACK);
        super.setForeground(Color.white);                
    }    
    
    protected abstract void actionChooser(ItemEvent e);
    
    

    
    
    
}
