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

import calcmassview.base.ServiceInscription;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JViewport;

/**
 * Тема оформления окна приложения
 * @author Sergei Lyashko
 */
public class ColorThemeImpl implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final List<JComponent> components;
    private Color colorBackGround, colorForeGround, colorMarker, colorResultMarker;

    public ColorThemeImpl(List<JComponent> components) {
        this.components = components;
    }
        
    public void doDark(){
        colorBackGround = Color.BLACK;
        colorForeGround = Color.WHITE;
        colorMarker = Color.WHITE;
        colorResultMarker = Color.GREEN;
        actionTheme(components);               
    }
    
    public void doLight(){
        colorBackGround = new Color(250, 236, 229);
        colorForeGround = Color.BLACK;
        colorMarker = Color.BLACK;
        colorResultMarker = Color.BLUE;
        actionTheme(components);                     
    }
    
    // активация выбранной темы
    public void actionTheme(List<JComponent> components){
        // TEST
        /*components.stream().forEach((JComponent component) ->{
            System.out.println("collect theme test: "+component.toString());
        });
        System.out.println("color: "+colorBackGround.toString());*/
        serviceInscriptionPaint(components);
        titleMarkerPaint(components);
        selectedComponentPaint(components);
    }
    
    private void titleMarkerPaint(List<JComponent> components){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAssignableFrom(JLabel.class))
                .forEach((JComponent component) -> {
                    component.setForeground(colorMarker);
                });
    }
    
    private void serviceInscriptionPaint(List<JComponent> components){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(ServiceInscription.class))
                .forEach((JComponent component) -> {
                    component.setForeground(colorResultMarker);
                });
    }

    private void selectedComponentPaint(List<JComponent> components) {
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(ColorTheme.class) || component.getClass().isAssignableFrom(JViewport.class))
                .forEach((JComponent component) -> {
                    component.setBackground(colorBackGround);
                    component.setForeground(colorForeGround);
                });
    }
}
