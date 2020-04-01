/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview.settingpanel;

import calcmassview.viewpanel.ServiceMarker;
import calcmassview.viewpanel.ResultMarker;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Тема оформления окна приложения
 * @author Korvin
 */
public class Theme {       
    
    private static final ArrayList<JComponent> COMPONENT_LIST = new ArrayList<>();    
    
    private static Color colorBackGround, colorForeGround, colorMarker;    
    
    public static void addTheme(JComponent component){
        COMPONENT_LIST.add(component);        
    }
    
    public static void addThemeBox(boolean select){
        colorMarker = select ? Color.GREEN: Color.BLUE;
    }
    
    public static void addThemeMarker(JLabel marker){        
        marker.setForeground(colorMarker);        
    }
    
    public void dark(){
        colorBackGround = Color.BLACK;
        colorForeGround = Color.WHITE;
        colorMarker = Color.GREEN;
        setColorTheme(colorBackGround, colorForeGround, colorMarker);               
    }
    
    public void light(){
        colorBackGround = new Color(250, 236, 229);
        colorForeGround = Color.BLACK;
        colorMarker = Color.BLUE;
        setColorTheme(colorBackGround, colorForeGround, colorMarker);                     
    }
    
    private void setColorTheme(Color backGround, Color foreGround, Color marker){
        for(int i=0; i<COMPONENT_LIST.size(); i++){
            if(COMPONENT_LIST.get(i)
                    .getClass()
                    .getName()
                    .equals(ResultMarker.class.getName()) || 
               COMPONENT_LIST.get(i)
                    .getClass()
                    .getName()
                    .equals(ServiceMarker.class.getName())){
                COMPONENT_LIST.get(i).setForeground(marker);
            }else{
                COMPONENT_LIST.get(i).setBackground(backGround);
                COMPONENT_LIST.get(i).setForeground(foreGround);
            }
        } 
    }
}
