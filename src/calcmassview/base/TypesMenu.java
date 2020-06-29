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

import calcmassview.settings.ToolTips;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * Выпадающее меню типов профилей
 * для выбранного сортамента
 * @author Sergei Lyashko
 */
public class TypesMenu extends JComboBox<String> implements ActionListener, FieldValueReceivable {   
    
    private final BasePanel basePanel;
    private String selectItem;
    private final String text = "выбор типа профиля детали";
    
    public TypesMenu(BasePanel basePanel, ToolTips toolTips) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 60);
        this.basePanel = basePanel;
        addContent(toolTips);
    }
    
    // 
    private void addContent(ToolTips toolTips){
        Menu emptyMenu = new Menu();
        super.setModel(emptyMenu.createStartMenu(this));
        toolTips.setToolTips(this, text);
        basePanel.add(this);        
        basePanel.addPolicy(this);
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        resetAllValues();
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        this.selectItem = selectedMenuItem;
        // создание меню номеров профилей
        createNumberProfilesMenu(selectItem);
    }
    
    // обновление меню номеров профилей
    private void createNumberProfilesMenu(String menuItem){
        String selectedAssortment = basePanel.getAssortmentMenu().fieldValueStringReceive();
        Menu menu = new Menu(basePanel.getDataBase());
        Menu numberProfileMenu = menu.createMenu(selectedAssortment, menuItem);
        basePanel.getNumberProfileMenu().setModel(numberProfileMenu);
    }
    
    // сброс значений
    private void resetAllValues(){
        basePanel.reset();
    }

    @Override
    public String fieldValueStringReceive() {
        return this.selectItem;
    }
}
