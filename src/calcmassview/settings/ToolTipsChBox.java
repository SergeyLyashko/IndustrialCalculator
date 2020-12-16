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
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

/**
 * Всплывающие подсказки checkbox
 * @author Sergei Lyashko
 */
@ToolTips(getToolTipDescription = "")
@ColorTheme()
public class ToolTipsChBox extends JCheckBox implements CheckBoxSelectable, Serializable, ToolTips, ColorTheme {

    private static final long serialVersionUID = 1L;
    
    private static final String TOOL_TIP_BOX_TEXT = "включение/отключение всплывающих подсказок";
    private final String chBoxName = "включить всплывающие подсказки";
    private ToolTipsImpl toolTips;
    
    public ToolTipsChBox(){
        super.setSelected(true);
        super.setSize(320, 20);
        super.setLocation(15, 60);
        super.setText(chBoxName);
        super.setToolTipText(TOOL_TIP_BOX_TEXT);
    }
    
    @Override
    public void changeComponents(ArrayList<JComponent> components){
        List<JComponent> componentsFiltred = componentsFilterForToolTips(components);
        createToolTips(componentsFiltred);
    }
    
    private List<JComponent> componentsFilterForToolTips(ArrayList<JComponent> components){
        return components.stream()
                .filter((JComponent component) -> 
                        component.getClass().isAnnotationPresent(ToolTips.class))
                .collect(Collectors.toList());        
    }
    
    private void createToolTips(List<JComponent> components){
        this.toolTips = new ToolTipsImpl(components);
        toolTips.oN();
    }
    
    @Override
    public void actionChooser(ItemEvent event) {
        switch(event.getStateChange()){
            case ItemEvent.SELECTED:
                toolTips.oN();
                break;
            case ItemEvent.DESELECTED:
                toolTips.oFF();
                break;            
        }
    }
    
    @Override
    public String getToolTipDescription() {
        return TOOL_TIP_BOX_TEXT;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
