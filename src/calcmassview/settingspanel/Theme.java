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
package calcmassview.settingspanel;

import calcmassview.basepanel.ServiceInfo;
import calcmassview.basepanel.ResultMarker;
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
    
    private static Color colorBackGround, colorForeGround, colorMarker;    
    
    public static void addTheme(JComponent component){
        COMPONENT_LIST.add(component);        
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
                    .equals(ServiceInfo.class.getName())){
                COMPONENT_LIST.get(i).setForeground(marker);
            }else{
                COMPONENT_LIST.get(i).setBackground(backGround);
                COMPONENT_LIST.get(i).setForeground(foreGround);
            }
        } 
    }
}
