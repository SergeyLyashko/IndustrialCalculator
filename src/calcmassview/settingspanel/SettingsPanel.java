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
package calcmassview.settingspanel;

import calcmassview.AbstractPanel;
import calcmassview.basepanel.FieldMarker;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * панель создани€ чек-боксов
 * @author Sergei Lyashko
 */
public class SettingsPanel extends AbstractPanel implements ItemListener{
    
    public SettingsPanel(){
        createFieldsMarker();
        createCheckBoxes();
        super.setLayout(null);
    }
    
    // создание чек-боксов
    private void createCheckBoxes(){
        new ThemeChBox(this);
        new ToolTipsOffBox(this);
        new FixSizeWindowChBox(this);
    }
    
    // маркеры полей длина и ширина
    private void createFieldsMarker(){
        FieldMarker infoText = new FieldMarker(this);
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