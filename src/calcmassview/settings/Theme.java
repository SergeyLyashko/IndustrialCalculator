/*
 * Copyright 2019 Sergei Lyashko. Contacts: <9lLLLepuLLa@gmail.com>.
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
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Тема оформления окна приложения
 * @author Sergei Lyashko
 */
class Theme implements Serializable, ColorThemeInterface {
    
    private static final long serialVersionUID = 1L;
    
    private transient ArrayList<JComponent> componentList = new ArrayList<>();
    private transient ArrayList<JLabel> markerList = new ArrayList<>();
    private Color colorBackGround, colorForeGround, colorMarker, colorResultMarker;
    
    /**
     * Установка темы оформления для выбранной надписи
     * @param marker надпись в приложении
     */
    @Override
    public void setColorTheme(JLabel marker){
        if(marker.getClass().equals(ServiceInfo.class) || marker.getClass().equals(ResultMarker.class)){
            marker.setForeground(colorResultMarker);
        }else{
            marker.setForeground(colorMarker);
        }
        markerList.add(marker);
    }
    
    /**
     * Установка темы оформления для выбранного компонента
     * @param component компонент для установки темы оформления
     */
    @Override
    public void setColorTheme(JComponent component) {
        if(componentList == null){
            actionComponents();
        }
        component.setBackground(colorBackGround);
        component.setForeground(colorForeGround);
        componentList.add(component);
    }
    
    /**
     * Темная тема оформления
     */
    @Override
    public void dark(){
        colorBackGround = Color.BLACK;
        colorForeGround = Color.WHITE;
        colorMarker = Color.WHITE;
        colorResultMarker = Color.GREEN;
        actionTheme();               
    }
    
    /**
     * Светлая тема оформления
     */
    @Override
    public void light(){
        colorBackGround = new Color(250, 236, 229);
        colorForeGround = Color.BLACK;
        colorMarker = Color.BLACK;
        colorResultMarker = Color.BLUE;
        actionTheme();                     
    }
    
    /**
     * Компоненты для изменения темы оформления
     */
    private void actionComponents(){
        this.markerList = new ArrayList<>();
        this.componentList = new ArrayList<>();
    }
    
    // активация выбранной темы
    private void actionTheme(){
        componentList.stream()
            .forEach((JComponent component) -> {
                component.setBackground(colorBackGround);
                component.setForeground(colorForeGround);
            });
        
        markerList.stream()
            .forEach((JLabel marker) -> marker.setForeground(colorMarker));
        
        markerList.stream()
            .filter((JLabel marker) -> 
                marker.getClass().equals(ServiceInfo.class) ||
                marker.getClass().equals(ResultMarker.class))
            .forEach((JLabel marker) -> marker.setForeground(colorResultMarker));
    }
}
