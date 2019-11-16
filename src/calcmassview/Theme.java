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
    
    //private ViewPanel vp;
    private SettingsPanel sp;    
    //private InfoPanel ip;
    //private ThemeChBox themeChBox;
    
    //private final Theme theme = new Theme(vp, sp, ip, themeChBox);
    
    /*public Theme(ViewPanel vp, SettingsPanel sp, InfoPanel ip, ThemeChBox themeChBox){        
        this.vp = vp;
        this.sp = sp;
        this.ip = ip;
        this.themeChBox = themeChBox;        
    }*/
        
    public Theme(){        
        setSp(sp);       
    }
    
    private void setSp(SettingsPanel sp){
        this.sp = sp;
    }
    
    private SettingsPanel getSp(){
        return sp;
    }
    
    /**
     *
     * @return
     */
    public Theme addTheme(){        
        return new Theme();
    }    
    
    public void dark(){
        sp = getSp();
        sp.setBackground(Color.BLACK);
        sp.setForeground(Color.WHITE);
        /*themeChBox.setBackground(Color.BLACK);
        themeChBox.setForeground(Color.WHITE);
        vp.setBackground(Color.BLACK);
        vp.setForeground(Color.WHITE);
        vp.resultMarker.setForeground(Color.green);
        ip.setBackground(Color.BLACK);
        ip.setForeground(Color.WHITE);*/
    }
    
    public void light(){
        Color light = new Color(250, 236, 229);
        this.sp = getSp();
        sp.setBackground(light);
        sp.setForeground(Color.BLACK);
        /*themeChBox.setBackground(light);
        themeChBox.setForeground(Color.BLACK);
        vp.setBackground(light);
        vp.setForeground(Color.BLACK);
        vp.resultMarker.setForeground(Color.blue);
        ip.setBackground(light);
        ip.setForeground(Color.BLACK);*/
    }
}
