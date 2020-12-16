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
package app;

import calcmassview.settings.CheckBoxSelectable;
import calcmassview.settings.ColorTheme;
import calcmassview.settings.ColorThemeCheckBox;
import calcmassview.settings.ToolTipsChBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Панель настроек
 * @author Sergei Lyashko
 */
@ColorTheme()
public class SettingsPanel extends JPanel implements ItemListener, Serializable, ColorTheme {

    private static final long serialVersionUID = 1L;

    public SettingsPanel() {
        super.setLayout(null);
    }
    
    public List<JComponent> createComponents(){
        JCheckBox themeCheckBox = createThemeCheckBox();
        JCheckBox toolTipsCheckBox = createToolTipsCheckBox();
        return Stream.of(themeCheckBox, toolTipsCheckBox).collect(Collectors.toList());
    }
    
    private JCheckBox createThemeCheckBox(){
        ColorThemeCheckBox themeChBox = new ColorThemeCheckBox();
        themeChBox.addItemListener(this);
        super.add(themeChBox);
        return themeChBox;
    }
    
    private JCheckBox createToolTipsCheckBox() {
        ToolTipsChBox toolTipsChBox = new ToolTipsChBox();
        toolTipsChBox.addItemListener(this);
        super.add(toolTipsChBox);
        return toolTipsChBox;
    }
    
    @Override
    public void itemStateChanged(ItemEvent event) {
        CheckBoxSelectable sourse = (CheckBoxSelectable)event.getItemSelectable();
        sourse.actionChooser(event);
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
    
    @Override
    public String getName(){
        return "Настройки";
    }    
}