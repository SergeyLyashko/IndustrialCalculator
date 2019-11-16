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
    
    //private static ResultMarker rm;
    private Color color;
    
    public static void addTheme(JComponent component){
        componentList.add(component);
    }     
    
    public void dark(){
        color = Color.BLACK;
        for(int i=0; i<componentList.size(); i++){
            componentList.get(i).setBackground(color);
            componentList.get(i).setForeground(Color.WHITE);        
        }        
    }
    
    public void light(){
        color = new Color(250, 236, 229);        
        for(int i=0; i<componentList.size(); i++){
            componentList.get(i).setBackground(color);
            componentList.get(i).setForeground(Color.BLACK);
        }        
    }
}
