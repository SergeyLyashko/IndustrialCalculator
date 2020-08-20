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
import javax.swing.JCheckBox;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 * Color theme checkbox
 * @author Sergei Lyashko
 */
@ToolTips(getToolTipDescription = "")
@ColorTheme()
class ColorThemeCheckBox extends JCheckBox implements CheckBoxSelectable, Serializable, ToolTips, ColorTheme {

    private static final long serialVersionUID = 1L;

    private ColorThemeImpl theme;
    private static final String THEME_TOOL_TIP_TEXT = "��������/��������� ������ ���� ����������";
    private final String boxName = "������ ���� ����������";
    private List<JComponent> components;
    
    public ColorThemeCheckBox(List<JComponent> components){
        super.setSelected(true);
        super.setSize(180, 20);
        super.setText(boxName);
        super.setLocation(15, 35);
        this.components = components;
        components.add(this);
        createColorTheme();
        theme.doDark();
    }
    
    public void actionTheme(List<JComponent> components){
        this.components = components;
        theme.actionTheme(components);
    }
    
    private void createColorTheme(){
        this.theme = new ColorThemeImpl(components);        
    }
    
    @Override
    public void actionChooser(int stateChange) {              
        switch(stateChange){
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
