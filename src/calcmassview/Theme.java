/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview;

import java.awt.Color;

/**
 *
 * @author Korvin
 */
public class Theme {
    
    private SettingsPanel sp;
    private ViewPanel vp;
    private InfoPanel ip;
    private ThemeChBox themeChBox;
    
    
    public void dark(){        
        sp.setBackground(Color.BLACK);
        sp.setForeground(Color.WHITE);
        themeChBox.setBackground(Color.BLACK);
        themeChBox.setForeground(Color.WHITE);
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
        themeChBox.setBackground(light);
        themeChBox.setForeground(Color.BLACK);
        vp.setBackground(light);
        vp.setForeground(Color.BLACK);
        vp.resultMarker.setForeground(Color.blue);
        ip.setBackground(light);
        ip.setForeground(Color.BLACK);
    }
}
