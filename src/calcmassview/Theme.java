/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Korvin
 */
public class Theme {       
    
    private static ArrayList<JComponent> componentList = new ArrayList<>();    
    
    private static Color colorBackGround, colorForeGround, colorMarker;    
    
    public static void addTheme(JComponent component){
        componentList.add(component);        
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
        for(int i=0; i<componentList.size(); i++){
            if(componentList.get(i)
                    .getClass()
                    .getName()
                    .equals(ResultMarker.class.getName()) || 
               componentList.get(i)
                    .getClass()
                    .getName()
                    .equals(ServiceMarker.class.getName())){
                componentList.get(i).setForeground(marker);
            }else{
                componentList.get(i).setBackground(backGround);
                componentList.get(i).setForeground(foreGround);
            }
        } 
    }
}
