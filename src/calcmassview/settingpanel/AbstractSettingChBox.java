/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview.settingpanel;

import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

/**
 *
 * @author Korvin
 */
public abstract class AbstractSettingChBox extends JCheckBox { 
    
    public AbstractSettingChBox(){
        super();       
        super.setBackground(Color.BLACK);
        super.setForeground(Color.white);        
    }    
    
    /**
     *
     * @param e
     */
    public abstract void actionChooser(ItemEvent e);
}
