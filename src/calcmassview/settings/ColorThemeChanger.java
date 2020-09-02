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

import calcmassview.base.Markmm;
import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JViewport;
import calcmassview.base.Reset;

/**
 * App color theme
 * @author Sergei Lyashko
 */
public class ColorThemeChanger implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final List<JComponent> components;
    private Color backGround, foreGround, marker, serviceString;

    public ColorThemeChanger(List<JComponent> components) {
        this.components = components;
    }
        
    public void doDark(){
        backGround = Color.BLACK;
        foreGround = Color.WHITE;
        marker = Color.WHITE;
        serviceString = Color.GREEN;
        actionTheme(components);               
    }
    
    public void doLight(){
        backGround = new Color(250, 236, 229);
        foreGround = Color.BLACK;
        marker = Color.BLACK;
        serviceString = Color.BLUE;
        actionTheme(components);                     
    }
    
    // активация выбранной темы
    public void actionTheme(List<JComponent> components){
        serviceStringPaint(components);
        titleMarkerPaint(components);
        selectableComponentPaint(components);
    }
    
    private void titleMarkerPaint(List<JComponent> components){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAssignableFrom(Markmm.class))
                .forEach((JComponent component) -> {
                    component.setForeground(marker);
                });
    }
    
    private void serviceStringPaint(List<JComponent> components){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(Reset.class))
                .forEach((JComponent component) -> {
                    component.setForeground(serviceString);
                });
    }

    private void selectableComponentPaint(List<JComponent> components) {
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(ColorTheme.class) || component.getClass().isAssignableFrom(JViewport.class))
                .forEach((JComponent component) -> {
                    component.setBackground(backGround);
                    component.setForeground(foreGround);
                });
    }
}
