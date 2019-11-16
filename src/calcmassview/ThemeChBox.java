/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview;

import java.awt.Color;
import java.awt.event.ItemEvent;

/**
 *
 * @author Korvin
 */
public class ThemeChBox extends SettingChBox {
    
    private SettingsPanel sp;
    private ViewPanel vp;
    private InfoPanel ip;
    
    public ThemeChBox(ViewPanel vp, SettingsPanel sp, InfoPanel ip){
        super();        
        this.vp = vp;
        this.sp = sp;
        this.ip = ip;
        super.setSelected(true);
        super.setSize(180, 20);
        super.setText("темная тема оформления");
        
    }
    
    // смена темы оформления
    @Override
    public void actionChooser(ItemEvent e) {
        //System.out.println("test Size Win");        
        setTheme(e.getStateChange());
        //setTheme(newTheme);                
    } 
    
    private void setTheme(int stateChange){      
        switch(stateChange){
            case ItemEvent.SELECTED:
                dark();
                break;
            case ItemEvent.DESELECTED:
                light();
                break;
            //default:
                //return null;
        }        
    }
    
    private void dark(){        
        sp.setBackground(Color.BLACK);
        sp.setForeground(Color.WHITE);
        super.setBackground(Color.BLACK);
        super.setForeground(Color.WHITE);
        vp.setBackground(Color.BLACK);
        vp.setForeground(Color.WHITE);
        vp.resultMarker.setForeground(Color.green);
        ip.setBackground(Color.BLACK);
        ip.setForeground(Color.WHITE);
    }
    
    private void light(){
        Color light = new Color(250, 236, 229);
        
        sp.setBackground(light);
        sp.setForeground(Color.BLACK);
        super.setBackground(light);
        super.setForeground(Color.BLACK);
        vp.setBackground(light);
        vp.setForeground(Color.BLACK);
        vp.resultMarker.setForeground(Color.blue);
        ip.setBackground(light);
        ip.setForeground(Color.BLACK);
    }
}
