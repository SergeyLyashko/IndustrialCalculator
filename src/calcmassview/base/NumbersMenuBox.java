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
package calcmassview.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * Панель меню номеров профилей
 * @author Sergei Lyashko
 */
public class NumbersMenuBox extends JComboBox<String> implements ActionListener {
    
    private ICalculatorData calculatorData;
    
    private final BasePanel basePanel;
    private final String headerMenuName = "№ профиля";
    
    public NumbersMenuBox(BasePanel basePanel) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 100);        
        // пустое меню по-умолчанию
        Menu empty = new Menu();
        super.setModel(empty.createMenu(null, null));
        this.basePanel = basePanel;
    }
    
    public void setData(ICalculatorData data){
        this.calculatorData = data;
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        calculatorData.setNumber(selectedMenuItem);
        // активаци полей ввода значений
        actionFields(selectedMenuItem);
    }
    
    // активация полей ввода значений
    private void actionFields(String selectMenu){
        // если в меню выбран любой пункт, кроме заголовка
        if(!selectMenu.equals(headerMenuName)){
            basePanel.actionFields();
        }
    }
}
