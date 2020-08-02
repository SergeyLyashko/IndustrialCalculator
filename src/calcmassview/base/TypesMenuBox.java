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
public class TypesMenuBox extends JComboBox<String> implements ActionListener {
    
    private ICalculatorData calculatorData;
    
    private final BasePanel basePanel;
    private final IDataBase dataBase;
    private boolean haveWidth;
    
    private String selectedItem;
    
    private final NumbersMenuBox numbersMenu;
    
    public TypesMenuBox(BasePanel basePanel, IDataBase dataBase) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 60);
        this.basePanel = basePanel;
        this.dataBase = dataBase;
        // пустое меню по-умолчанию
        Menu emptyMenu = new Menu();
        super.setModel(emptyMenu.createMenu(null));
        
        numbersMenu = new NumbersMenuBox(basePanel);
        numbersMenu.addActionListener(numbersMenu);        
    }
    
    /**
     *
     * @return
     */
    public NumbersMenuBox getNumbersMenu(){
        return numbersMenu;
    }
    
    /**
     *
     * @param data
     */
    public void setData(ICalculatorData data){
        this.calculatorData = data;
    }
    
    /**
     *
     * @param selectedItem
     */
    public void setSelectedMenu(String selectedItem){
        this.selectedItem = selectedItem;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        resetAllValues();
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        calculatorData.setType(selectedMenuItem);
        // создание меню номеров профилей
        fillNumberProfilesMenu(selectedMenuItem);
    }
    
    // обновление меню номеров профилей
    private void fillNumberProfilesMenu(String menuItem){
        String selectedAssortment = selectedItem;
        Menu menu = new Menu(dataBase);
        Menu numberProfileMenu = menu.createMenu(selectedAssortment, menuItem);
        numbersMenu.setData(calculatorData);
        numbersMenu.setModel(numberProfileMenu);
        haveWidth = menu.haveWidth();
        //System.out.println("have type width: "+haveWidth);// TEST
    }
    
    // сброс значений
    private void resetAllValues(){
        haveWidth = false;
        basePanel.reset();
    }
    
    /**
     *
     * @return
     */
    public boolean haveWidth(){
        return haveWidth;
    }
}
