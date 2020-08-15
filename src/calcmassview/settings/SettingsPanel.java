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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Панель настроек
 * @author Sergei Lyashko
 */
@ColorTheme()
public class SettingsPanel extends JPanel implements ItemListener, Serializable, ColorTheme {

    private static final long serialVersionUID = 1L;
    
    private ColorThemeCheckBox themeChBox;
    private ToolTipsChBox toolTipsChBox;    
    private final ArrayList<JComponent> components;
    
    public SettingsPanel(ArrayList<JComponent> components){
        this.components = components;
        components.add(this);
        addToolTipBox();
        addColorThemeBox();
        super.setLayout(null);
    }
    
    // чек-бокс цветовой темы оформления
    private void addColorThemeBox(){        
        themeChBox = new ColorThemeCheckBox(components);
        themeChBox.addItemListener(this);
        super.add(themeChBox);              
    }
    
    // чек-бокс всплывающих подсказок
    private void addToolTipBox(){
        toolTipsChBox = new ToolTipsChBox(components);
        components.add(toolTipsChBox);
        toolTipsChBox.addItemListener(this);
        super.add(toolTipsChBox); 
    }
    
    @Override
    public void itemStateChanged(ItemEvent event) {
        CheckBoxSelectable sourse = (CheckBoxSelectable)event.getItemSelectable();
        sourse.actionChooser(event.getStateChange());
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}