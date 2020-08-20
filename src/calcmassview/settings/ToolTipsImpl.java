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

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;

/**
 * ¬сплывающие подсказки
 * @author Sergei Lyashko
 */
@ToolTips(getToolTipDescription = "")
public class ToolTipsImpl implements Serializable, ToolTips {
    
    private static final long serialVersionUID = 1L;

    private boolean state;
    private transient final List<JComponent> components;
    
    public ToolTipsImpl(List<JComponent> components){
        this.components = components;
        setToolTipsDescription();
    }
    
    // всплывающие подсказки
    private void setToolTipsDescription(){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(ToolTips.class))
                .forEach((JComponent component) -> {
                    String toolTipDescription = ((ToolTips)component).getToolTipDescription();
                    component.setToolTipText(toolTipDescription);
                });
    }
    
    @Override
    public String getToolTipDescription() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }

    public void oN() {
        this.state = true;
        actionCurrentState();
    }

    public void oFF() {
        this.state = false;
        actionCurrentState();
    }

    private void actionCurrentState() {
        ToolTipManager.sharedInstance().setEnabled(state);
    }
}
