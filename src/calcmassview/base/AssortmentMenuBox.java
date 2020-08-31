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

import calcdatabase.DataBase;
import calcdatabase.DataBaseMenuReceiver;
import javax.swing.JComboBox;
import java.lang.annotation.Annotation;
import calcmassview.settings.ToolTips;
import java.util.ArrayList;

/**
 * ���� ����� ����������
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getCurrentMenuItem = "")
@WidthFieldState(isWidthValid = false)
@ToolTips(getToolTipDescription = "")
public class AssortmentMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, WidthFieldState, ToolTips {

    private static final long serialVersionUID = 1L;
    
    private final String toolTipsText = "����� ���������� ������";
    private final String widthField = "����"; 
        
    private final TypesMenuBox typesBox;
    private transient String menuItem;
    private final StateField activeStateField;
    private final Reset resetMarker;
    
    private final DataBaseMenuReceiver receiver;
    
    public AssortmentMenuBox(StateField activeStateField, Reset serviceResetMarker, DataBaseMenuReceiver receiver) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 20);
        this.activeStateField = activeStateField;
        this.resetMarker = serviceResetMarker;
        this.receiver = receiver;
        addEmptyMenu();
        // �������� ���� ����� �������
        typesBox = new TypesMenuBox(activeStateField, serviceResetMarker, receiver); 
    }
    
    private void addEmptyMenu(){
        // ������ ���� ��-���������
        Menu menu = new Menu();
        Menu emptyMenu = menu.createMenu(this);
        super.setModel(emptyMenu);
    }
    
    @Override
    public ArrayList<String> receiveMenu(){
        return receiver.getAssortmentMenu();
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        this.menuItem = selectedMenuItem;
        resetMenuBox();
        // �������� ���� ����� ��������
        fillTypeProfilesMenu(selectedMenuItem);  
    }
    
    // ���������� ���� ����� ��������
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
        return super.getSelectedItem().toString();
    }
    
    public TypesMenuBox getTypesBox(){
        return typesBox;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return getClass();
    }

    @Override
    public boolean isWidthValid() {
        return menuItem.equals(widthField);
    }
}