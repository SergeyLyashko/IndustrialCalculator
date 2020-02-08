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
package calcmassview.infopanel;

import calcmassview.settingpanel.Theme;
import calcmassview.viewpanel.FieldMarker;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * панель Информации о приложении (инструкция)
 * @author Sergei Lyashko
 */
public class InfoPanel extends JPanel {
    
    // статическое создание экземпляра класса
    private static final InfoPanel INSTANCE = new InfoPanel();
    
    /**
     * Метод получения экземпляра класса-синглтона
     * @return экземпляр класса
     */
    public static final InfoPanel getInstance(){
        return INSTANCE;
    }
    
    private final FieldMarker headerText;
    private final InfoText infoText;
    
    // конструктор
    private InfoPanel(){
        super(false);
        super.setBackground(Color.black);        
        Theme.addTheme(this);
        // заголовок
        headerText = new FieldMarker();
        headerText.setText("О программе");
        // текстовая панель
        infoText = new InfoText();       
        super.add(headerText);
        super.add(infoText);            
    }    
}
