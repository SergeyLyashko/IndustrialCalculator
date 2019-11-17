/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassview;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Korvin
 */
public class Theme {       
    
    private static ArrayList<JComponent> componentList = new ArrayList<JComponent>();    
    
    private static Color color, colorResultMarker;    
    
    public static void addTheme(JComponent component){
        componentList.add(component);
        
    }
    
    public static void addThemeBox(boolean select){
        colorResultMarker = select ? Color.GREEN: Color.BLUE;
    }
    
    public static void addThemeRM(ResultMarker rm){        
        rm.setForeground(colorResultMarker);        
    }
    
    public void dark(){
        color = Color.BLACK;
        colorResultMarker = Color.GREEN;
        for(int i=0; i<componentList.size(); i++){
            if(componentList.get(i).getClass().getName().equals(ResultMarker.class.getName())){
                componentList.get(i).setForeground(colorResultMarker);
            }else{
                componentList.get(i).setBackground(color);
                componentList.get(i).setForeground(Color.WHITE);
            }
        }        
    }
    
    public void light(){
        color = new Color(250, 236, 229);
        colorResultMarker = Color.BLUE;        
        for(int i=0; i<componentList.size(); i++){
            if(componentList.get(i).getClass().getName().equals(ResultMarker.class.getName())){
                componentList.get(i).setForeground(colorResultMarker);
            }else{
                componentList.get(i).setBackground(color);
                componentList.get(i).setForeground(Color.BLACK);
            }
        }              
    }
}
