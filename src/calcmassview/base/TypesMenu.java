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
import calcdatabase.IDataBase;

/**
 * Выпадающее меню типов профилей
 * для выбранного сортамента
 * @author Sergei Lyashko
 */
public class TypesMenu extends JComboBox<String> implements ActionListener, FieldValueReceivable {   
    
    private final BasePanel basePanel;
    private String selectItem;
    private final IDataBase dataBase;
    
    public TypesMenu(BasePanel basePanel, IDataBase dataBase) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 60);
        this.basePanel = basePanel;
        this.dataBase = dataBase;
        setEmptyMenu();
    }
    
    // 
    private void setEmptyMenu(){
        Menu emptyMenu = new Menu();
        super.setModel(emptyMenu.createMenu(null));
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
        Menu menu = new Menu(dataBase);
        Menu numberProfileMenu = menu.createMenu(selectedAssortment, menuItem);
        basePanel.getNumbersMenu().setModel(numberProfileMenu);
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
