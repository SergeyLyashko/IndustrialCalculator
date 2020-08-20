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
 * distributed under the License is distributed activate an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassview.base;

import javax.swing.JComboBox;
import calcdatabase.DataBase;
import java.lang.annotation.Annotation;
import calcmassview.settings.ToolTips;

/**
 * Выпадающее меню типов профилей
 * для выбранного сортамента
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getFieldValue = "")
@DetailWidthState(haveWidth = false)
@ToolTips(getToolTipDescription = "")
public class TypesMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, DetailWidthState, ToolTips {

    private static final long serialVersionUID = 1L;
    
    private final String toolTipsText = "выбор типа профиля детали";
    private final String widthField = "Резиновая пластина";
    
    private final DataBase dataBase;
    private transient String selectedAssortment;
    private final NumbersMenuBox numbersMenu;
    private transient String fieldValue;
    private final StateField activeStateField;
    private final ServiceInscription resetMarker;
    
    public TypesMenuBox(DataBase dataBase, StateField activeStateField, ServiceInscription serviceResetMarker) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 60);
        this.dataBase = dataBase;
        this.activeStateField = activeStateField;
        this.resetMarker = serviceResetMarker;
        // пустое меню по-умолчанию
        Menu emptyMenu = new Menu();
        super.setModel(emptyMenu.createMenu(null));
        // создание меню номеров профиля
        numbersMenu = new NumbersMenuBox(activeStateField, serviceResetMarker);
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
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
     * @param selectedItem
     */
    public void setSelectedMenu(String selectedItem){
        this.selectedAssortment = selectedItem;
    }
    
    @Override
    public String getSelectedMenuItem(){
        return super.getSelectedItem().toString();
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        this.fieldValue = selectedMenuItem;
        resetMenuBox();
        // создание меню номеров профилей
        fillNumberProfilesMenu(selectedMenuItem);
    }
    
    private void resetMenuBox(){
        //System.out.println("type reset ");// TEST
        numbersMenu.setSelectedIndex(0);
        resetMarker.reset();
        activeStateField.deactivate();
    }
    
    @Override
    public String getFieldValue(){
        return fieldValue;
    }
    
    // обновление меню номеров профилей
    private void fillNumberProfilesMenu(String selectedMenuItem){
        Menu menu = new Menu(dataBase);
        Menu numberProfileMenu = menu.createMenu(selectedAssortment, selectedMenuItem);
        numbersMenu.setModel(numberProfileMenu);
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }

    @Override
    public boolean haveWidth() {
        return fieldValue.equals(widthField);
    }
}
