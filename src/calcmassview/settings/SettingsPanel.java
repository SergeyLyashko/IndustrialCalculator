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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ѕанель настроек
 * @author Sergei Lyashko
 */
public class SettingsPanel extends JPanel implements ItemListener, Serializable {

    private static final long serialVersionUID = 1L;
    
    private Theme theme;
    private ToolTips toolTips;
    private ThemeChBox themeChBox;
    private ToolTipsChBox toolTipsChBox;
    private FixSizeWindowChBox fixSizeWindowChBox;
    
    public SettingsPanel(Theme theme, ToolTips toolTips){
        this.theme = theme;
        this.toolTips = toolTips;
        header();
        createCheckBoxes();
        super.setLayout(null);
    }
    
    /**
     * ”становка выбранных настроек
     * @param theme тема приложени€
     * @param toolTips всплывающие подсказки
     */
    public void addPreference(Theme theme, ToolTips toolTips){
        this.theme = theme;
        this.toolTips = toolTips;
        themeChBox.addState(theme, toolTips);
        toolTipsChBox.addState(theme, toolTips);
        fixSizeWindowChBox.addState(theme, toolTips);
        theme.setColorTheme(this);
        toolTips.currentState();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        Selectable sourse = (Selectable)e.getItemSelectable();
        sourse.actionChooser(e);
    }
    
    // создание чек-боксов
    private void createCheckBoxes(){
        themeChBox = new ThemeChBox(this);
        themeChBox.addState(theme, toolTips);
        super.add(themeChBox);
        
        toolTipsChBox = new ToolTipsChBox(this);
        toolTipsChBox.addState(theme, toolTips);
        super.add(toolTipsChBox);
        
        fixSizeWindowChBox = new FixSizeWindowChBox(this);
        fixSizeWindowChBox.addState(theme, toolTips);
        super.add(fixSizeWindowChBox);
        theme.setColorTheme(this);
    }
    
    // маркеры полей длина и ширина
    private void header(){
        JLabel infoText = new JLabel();
        theme.setColorTheme(infoText);
        infoText.setVisible(true);
        infoText.setText("Ќастройки");
        infoText.setSize(300, 20);
        infoText.setLocation(15, 10);       
        super.add(infoText);
    }
}