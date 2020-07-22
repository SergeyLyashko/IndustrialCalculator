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
 * Меню типов сортамента
 * @author Sergei Lyashko
 */
public class AssortmentMenu extends JComboBox<String> implements ActionListener, FieldValueReceivable {    
        
    private String selectItem;
    private final BasePanel basePanel;
    
    public AssortmentMenu(BasePanel basePanel) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 20);
        this.basePanel = basePanel;
        addContent();
    }
    
    /**
     * Создание меню из базы данных
     */
    public void createMenuFromDataBase(){
        Menu menu = new Menu(basePanel.getDataBase());
        super.setModel(menu.createMenu());
    }
    
    // 
    private void addContent(){
        Menu defaulMenu = new Menu();
        super.setModel(defaulMenu.createStartMenu(this));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        this.selectItem = selectedMenuItem;
        // создание меню типов профилей
        createTypeProfilesMenu(selectItem);
        //сброс параметров полей        
        resetAllFields();        
    }
    
    // создание меню типов профилей
    private void createTypeProfilesMenu(String menuItem){
        Menu menu = new Menu(basePanel.getDataBase());
        Menu newTypeProfilesMenu = menu.createMenu(menuItem);
        basePanel.getTypesMenu().setModel(newTypeProfilesMenu);
    }
    
    // сброс полей ввода
    private void resetAllFields(){
        setMenuStartPosition();
        basePanel.reset();
    }
    
    // установка начальных значений меню
    private void setMenuStartPosition(){
        basePanel.getTypesMenu().setSelectedIndex(0);
        basePanel.getNumbersMenu().setSelectedIndex(0);
    }

    @Override
    public String fieldValueStringReceive() {
        return this.selectItem;
    }
}
