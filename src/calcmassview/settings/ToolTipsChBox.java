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
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

/**
 * Всплывающие подсказки checkbox
 * @author Sergei Lyashko
 */
@ToolTips(getToolTipDescription = "")
@ColorTheme()
class ToolTipsChBox extends JCheckBox implements CheckBoxSelectable, Serializable, ToolTips, ColorTheme {

    private static final long serialVersionUID = 1L;
    
    private static final String TOOL_TIP_BOX_TEXT = "включение/отключение всплывающих подсказок";
    private final String chBoxName = "включить всплывающие подсказки";
    private ToolTipsImpl toolTips;
    private transient final List<JComponent> components;
    
    public ToolTipsChBox(List<JComponent> components){
        super.setSelected(true);
        super.setSize(320, 20);
        super.setLocation(15, 60);
        super.setText(chBoxName);
        super.setToolTipText(TOOL_TIP_BOX_TEXT);
        this.components = components;
        components.add(this);
        actionToolTips();              
    }
    
    private void actionToolTips(){
        this.toolTips = new ToolTipsImpl(components);
        toolTips.oN();
    }
    
    @Override
    public void actionChooser(int stateChange) {
        switch(stateChange){
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
