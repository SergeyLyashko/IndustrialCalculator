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

import java.awt.event.ItemEvent;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JViewport;
import calcmassview.base.Reset;

/**
 * Color theme checkbox
 * @author Sergei Lyashko
 */
@ToolTips(getToolTipDescription = "")
@ColorTheme()
class ColorThemeCheckBox extends JCheckBox implements CheckBoxSelectable, Serializable, ToolTips, ColorTheme {

    private static final long serialVersionUID = 1L;

    private ColorThemeImpl theme;
    private static final String THEME_TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private final String boxName = "темная тема оформления";
    
    public ColorThemeCheckBox(){
        super.setSelected(true);
        super.setSize(180, 20);
        super.setText(boxName);
        super.setLocation(15, 35);
    }
    
    @Override
    public void setComponents(ArrayList<JComponent> components){
        List<JComponent> componentsFiltred = componentsFilterForChangeTheme(components);
        createColorTheme(componentsFiltred);
    }
    
    private List<JComponent> componentsFilterForChangeTheme(ArrayList<JComponent> components){
        return components.stream()
                .filter((JComponent component) -> 
                        component.getClass().isAnnotationPresent(ColorTheme.class) || 
                        component.getClass().isAssignableFrom(JViewport.class) ||
                        component.getClass().isAssignableFrom(JLabel.class) ||
                        component.getClass().isAnnotationPresent(Reset.class))
                .collect(Collectors.toList());        
    }
    
    private void createColorTheme(List<JComponent> components){
        this.theme = new ColorThemeImpl(components);
        theme.doDark();
    }
    
    @Override
    public void actionChooser(ItemEvent event) {              
        switch(event.getStateChange()){
            case ItemEvent.SELECTED:
                theme.doDark();
                break;
            case ItemEvent.DESELECTED:
                theme.doLight();
                break;            
        } 
    } 

    @Override
    public String getToolTipDescription() {
        return THEME_TOOL_TIP_TEXT;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
