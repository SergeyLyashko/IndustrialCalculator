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
import calcdatabase.DataBaseDispatcher;
import java.lang.annotation.Annotation;
import calcmassview.settings.ToolTips;
import calcdatabase.DataBaseMenuReceiver;
import java.util.ArrayList;

/**
 * Меню типов сортамента
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getCurrentMenuItem = "")
@DetailWidthState(haveWidth = false)
@ToolTips(getToolTipDescription = "")
public class AssortmentMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, DetailWidthState, ToolTips {

    private static final long serialVersionUID = 1L;
    
    private final String toolTipsText = "выбор сортамента детали";
    private final String widthField = "Лист"; 
        
    private final TypesMenuBox typesBox;
    private transient String menuItem;
    private final StateField activeStateField;
    private final Reset resetMarker;
    
    public AssortmentMenuBox(StateField activeStateField, Reset serviceResetMarker) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 20);
        this.activeStateField = activeStateField;
        this.resetMarker = serviceResetMarker;
        addEmptyMenu();
        // создание меню типов профиля
        typesBox = new TypesMenuBox(activeStateField, serviceResetMarker); 
    }
    
    private void addEmptyMenu(){
        // пустое меню по-умолчанию
        Menu menu = new Menu();
        Menu emptyMenu = menu.createMenu(this);
        super.setModel(emptyMenu);
    }
    
    @Override
    public ArrayList<String> receiveMenu(){
        DataBaseMenuReceiver receiver = new DataBaseDispatcher();
        return receiver.getAssortmentMenu();
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        this.menuItem = selectedMenuItem;
        System.out.println("test selectedMenuItem: "+menuItem);
        resetMenuBox();
        // создание меню типов профилей
        fillTypeProfilesMenu(selectedMenuItem);  
    }
    
    // заполнение меню типов профилей
    private void fillTypeProfilesMenu(String menuItem){
        Menu menu = new Menu();
        typesBox.setSelectedMenu(menuItem);
        Menu typesMenu = menu.createMenu(typesBox);
        typesBox.setModel(typesMenu);
    }
    
    private void resetMenuBox(){
        activeStateField.deactivate();
        typesBox.setSelectedIndex(0);
        resetMarker.reset();
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    @Override
    public String getCurrentMenuItem(){
        //System.out.println("test aas getCurrentMenuItem: "+menuItem);
        //return menuItem;
        return super.getSelectedItem().toString();
    }
    /*
    @Override
    public String getSelectedMenuItem(){
        return super.getSelectedItem().toString();
    }*/
    
    public TypesMenuBox getTypesBox(){
        return typesBox;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return getClass();
    }

    @Override
    public boolean haveWidth() {
        return menuItem.equals(widthField);
    }
}