/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassview.settings;

import calcmassview.base.ServiceInfo;
import calcmassview.base.ResultMarker;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Тема оформления окна приложения
 * @author Sergei Lyashko
 */
public class Theme {       
    
    private static final ArrayList<JComponent> COMPONENT_LIST = new ArrayList<>();
    private static final ArrayList<JLabel> MARKER_LIST = new ArrayList<>();
    
    private static Color colorBackGround, colorForeGround, colorMarker, colorResultMarker;    
    
    public static void addTheme(JComponent component){
        COMPONENT_LIST.add(component);
    }
    
    public static void addTheme(JLabel marker){
        MARKER_LIST.add(marker);
    }
    
    public static void defaultTheme(){
        new Theme().dark();
    }
    
    private void setColorTheme(Color colorBackGround, Color colorForeGround, Color colorMarker, Color colorResultMarker) {
        COMPONENT_LIST.stream()
            .forEach((JComponent component) -> {
                component.setBackground(colorBackGround);
                component.setForeground(colorForeGround);
            });
        
        MARKER_LIST.stream()
            .forEach((JLabel marker) -> marker.setForeground(colorMarker));
        
        MARKER_LIST.stream()
            .filter((JLabel marker) -> 
                marker.getClass().equals(ServiceInfo.class) ||
                marker.getClass().equals(ResultMarker.class))
            .forEach((JLabel marker) -> marker.setForeground(colorResultMarker));
    }
    
    public void dark(){
        colorBackGround = Color.BLACK;
        colorForeGround = Color.WHITE;
        colorMarker = Color.WHITE;
        colorResultMarker = Color.GREEN;
        setColorTheme(colorBackGround, colorForeGround, colorMarker, colorResultMarker);               
    }
    
    public void light(){
        colorBackGround = new Color(250, 236, 229);
        colorForeGround = Color.BLACK;
        colorMarker = Color.BLACK;
        colorResultMarker = Color.BLUE;
        setColorTheme(colorBackGround, colorForeGround, colorMarker, colorResultMarker);                     
    }
}
