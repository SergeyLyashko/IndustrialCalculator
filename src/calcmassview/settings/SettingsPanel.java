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

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * панель создани€ чек-боксов
 * @author Sergei Lyashko
 */
public class SettingsPanel extends JPanel implements ItemListener{
    
    public SettingsPanel(){
        Theme.addTheme(this);
        header();
        createCheckBoxes();
        super.setLayout(null);
    }
    
    // создание чек-боксов
    private void createCheckBoxes(){
        super.add(new ThemeChBox(this));
        super.add(new ToolTipsOffBox(this));
        super.add(new FixSizeWindowChBox(this));
    }
    
    // маркеры полей длина и ширина
    private void header(){
        JLabel infoText = new JLabel();
        Theme.addTheme(infoText);
        infoText.setVisible(true);
        infoText.setText("Ќастройки");
        infoText.setSize(300, 20);
        infoText.setLocation(15, 10);       
        super.add(infoText);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Selectable sourse = (Selectable)e.getItemSelectable();
        sourse.actionChooser(e);
    }
}