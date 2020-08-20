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
 * Меню типов сортамента
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getFieldValue = "")
@DetailWidthState(haveWidth = false)
@ToolTips(getToolTipDescription = "")
public class AssortmentMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, DetailWidthState, ToolTips {

    private static final long serialVersionUID = 1L;
    
    private final String toolTipsText = "выбор сортамента детали";
    private final String widthField = "Лист"; 
        
    private final DataBase dataBase;
    private final TypesMenuBox typesMenu;
    private transient String fieldValue;
    private final StateField activeStateField;
    private final ServiceInscription resetMarker;
    
    public AssortmentMenuBox(StateField activeStateField, ServiceInscription serviceResetMarker) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 20);
        this.dataBase = new DataBase();
        this.activeStateField = activeStateField;
        this.resetMarker = serviceResetMarker;
        // пустое меню по-умолчанию
        Menu empty = new Menu(dataBase);
        super.setModel(empty.createMenu());
        // создание меню типов профиля
        typesMenu = new TypesMenuBox(dataBase, activeStateField, serviceResetMarker); 
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        this.fieldValue = selectedMenuItem;
        resetMenuBox();
        // создание меню типов профилей
        fillTypeProfilesMenu(selectedMenuItem);  
    }
    
    private void resetMenuBox(){
        //System.out.println("assort reset ");// TEST
        activeStateField.deactivate();
        typesMenu.setSelectedIndex(0);
        resetMarker.reset();
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    @Override
    public String getFieldValue(){
        return fieldValue;
    }
    
    // заполнение меню типов профилей
    private void fillTypeProfilesMenu(String menuItem){
        Menu menu = new Menu(dataBase);
        Menu newTypeProfilesMenu = menu.createMenu(menuItem);
        typesMenu.setSelectedMenu(menuItem);
        typesMenu.setModel(newTypeProfilesMenu);
    }
    
    @Override
    public String getSelectedMenuItem(){
        return super.getSelectedItem().toString();
    }
    
    /**
     *
     * @return
     */
    public TypesMenuBox getTypesMenu(){
        return typesMenu;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return getClass();
    }

    @Override
    public boolean haveWidth() {
        return fieldValue.equals(widthField);
    }
}