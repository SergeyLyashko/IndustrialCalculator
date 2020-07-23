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

import calcmassview.general.ToolTipsInterface;
import calcmassview.general.ColorThemeInterface;
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
    
    private static final String THEME_TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private static final String TOOL_TIP_BOX_TEXT = "включение/отключение всплывающих подсказок";
    
    private final ToolTipsInterface toolTips;
    private final ColorThemeInterface theme;
    private ColorThemeCheckBox themeChBox;
    private ToolTipsChBox toolTipsChBox;    
    
    public SettingsPanel(ColorThemeInterface theme, ToolTipsInterface toolTips){
        this.theme = theme;
        this.toolTips = toolTips;
        addColorThemeBox();
        addToolTipBox();        
        super.setLayout(null);
    }
    
    // чек-бокс цветовой темы оформления
    private void addColorThemeBox(){        
        themeChBox = new ColorThemeCheckBox(theme);
        theme.componentChangeColor(themeChBox);        
        toolTips.setToolTips(themeChBox, THEME_TOOL_TIP_TEXT);
        themeChBox.addItemListener(this);
        super.add(themeChBox);              
    }
    
    // чек-бокс всплывающих подсказок
    private void addToolTipBox(){
        toolTipsChBox = new ToolTipsChBox(toolTips);
        theme.componentChangeColor(toolTipsChBox);        
        toolTips.setToolTips(toolTipsChBox, TOOL_TIP_BOX_TEXT);
        toolTipsChBox.addItemListener(this);
        super.add(toolTipsChBox); 
    }
    
    /**
     * Установка выбранных настроек
     * @param theme тема приложения
     */
    public void setPreference(ColorThemeInterface theme){        
        toolTips.currentState();
        theme.componentChangeColor(themeChBox);
        theme.componentChangeColor(toolTipsChBox);
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        Selectable sourse = (Selectable)e.getItemSelectable();
        sourse.actionChooser(e);
    }
}