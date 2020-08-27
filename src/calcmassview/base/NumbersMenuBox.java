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

import calcdatabase.DataBaseDispatcher;
import calcdatabase.DataBaseMenuReceiver;
import java.lang.annotation.Annotation;
import javax.swing.JComboBox;
import calcmassview.settings.ToolTips;
import java.util.ArrayList;

/**
 * Панель меню номеров профилей
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getCurrentMenuItem = "")
@ToolTips(getToolTipDescription = "")
public class NumbersMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, ToolTips {

    private static final long serialVersionUID = 1L;
    
    private final String toolTipsText = "выбор номера профиля детали";
    private transient String menuItem;
    private final String headerMenuName = "№ профиля";
    
    private final StateField activeStateField;
    private final Reset resetMarker;
    private String assortment;
    private String type;
        
    public NumbersMenuBox(StateField activeStateField, Reset serviceResetMarker) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 100);        
        // пустое меню по-умолчанию
        addEmptyMenu();
        this.activeStateField = activeStateField;
        this.resetMarker = serviceResetMarker;
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
        return receiver.getNumberMenu(assortment, type);
    }
    
    public void setSelectedMenu(String selectedAssortment, String menuItem) {
        this.assortment = selectedAssortment;
        this.type = menuItem;
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        this.menuItem = selectedMenuItem;
        resetMenuBox();
        actionFields(selectedMenuItem);
    }
    
    private void resetMenuBox() {
        resetMarker.reset();
    }
    
    // активация полей ввода значений
    private void actionFields(String selectMenu){
        // если в меню выбран любой пункт, кроме заголовка
        if(!selectMenu.equals(headerMenuName)){
            activeStateField.activate();
        }
    }
    
    @Override
    public String getCurrentMenuItem(){
        return super.getSelectedItem().toString();
        //return menuItem;
    }
    /*
    @Override
    public String getSelectedMenuItem(){
        return super.getSelectedItem().toString();
    }*/
    
    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
