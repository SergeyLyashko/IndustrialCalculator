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

import java.lang.annotation.Annotation;
import javax.swing.JComboBox;
import calcmassview.settings.ToolTips;

/**
 * Панель меню номеров профилей
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getFieldValue = "")
@ToolTips(getToolTipDescription = "")
public class NumbersMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, ToolTips {
    
    private final String toolTipsText = "выбор номера профиля детали";
    private String fieldValue;
    private final String headerMenuName = "№ профиля";
    
    private final ActiveStateField activeStateField;
    private final ServiceInscription resetMarker;
        
    public NumbersMenuBox(ActiveStateField activeStateField, ServiceInscription resetMarker) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 100);        
        // пустое меню по-умолчанию
        Menu empty = new Menu();
        super.setModel(empty.createMenu(null, null));
        this.activeStateField = activeStateField;
        this.resetMarker = resetMarker;
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        this.fieldValue = selectedMenuItem;
        resetMenuBox();
        actionFields(selectedMenuItem);
    }
    
    private void resetMenuBox() {
        //System.out.println("numbers reset ");// TEST        
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
    public String getFieldValue(){
        return fieldValue;
    }
    
    @Override
    public String getSelectedMenuItem(){
        return super.getSelectedItem().toString();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
