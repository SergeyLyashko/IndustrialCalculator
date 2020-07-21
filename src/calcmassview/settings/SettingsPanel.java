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
import javax.swing.JPanel;

/**
 * Панель настроек
 * @author Sergei Lyashko
 */
public class SettingsPanel extends JPanel implements ItemListener, Serializable {

    private static final long serialVersionUID = 1L;
    
    private final ToolTipsInterface toolTips;
    private final ColorThemeInterface theme;
    private ColorThemeCheckBox themeChBox;
    private ToolTipsChBox toolTipsChBox;
    
    public SettingsPanel(){
        toolTips = new ToolTips();
        toolTips.oN();
        theme = new Theme();
        theme.dark(); 
        createCheckBoxes();
        super.setLayout(null);
    }
    
    public ToolTipsInterface getToolTips(){
        return toolTips;
    }
    
    public ColorThemeInterface getTheme(){
        return theme;
    }
    
    // создание чек-боксов
    private void createCheckBoxes(){
        themeChBox = new ColorThemeCheckBox(this);
        themeChBox.addVisualDecoration(theme, toolTips);
        super.add(themeChBox);        
        toolTipsChBox = new ToolTipsChBox(this);
        toolTipsChBox.addVisualDecoration(theme, toolTips);
        super.add(toolTipsChBox);        
        theme.setColorTheme(this);
    }
    
    /**
     * Установка выбранных настроек
     * @param theme тема приложения
     * @param toolTips всплывающие подсказки
     */
    public void addPreference(ColorThemeInterface theme, ToolTipsInterface toolTips){
        themeChBox.addVisualDecoration(theme, toolTips);
        toolTipsChBox.addVisualDecoration(theme, toolTips);
        theme.setColorTheme(this);
        toolTips.currentState();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        Selectable sourse = (Selectable)e.getItemSelectable();
        sourse.actionChooser(e);
    }
}