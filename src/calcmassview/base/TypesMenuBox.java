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
import javax.swing.JComboBox;
import java.lang.annotation.Annotation;
import calcmassview.settings.ToolTips;
import java.util.ArrayList;

/**
 * ���������� ���� ����� ��������
 * ��� ���������� ����������
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getCurrentMenuItem = "")
@DetailWidthState(haveWidth = false)
@ToolTips(getToolTipDescription = "")
public class TypesMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, DetailWidthState, ToolTips {

    private static final long serialVersionUID = 1L;
    
    private final String toolTipsText = "����� ���� ������� ������";
    private final String widthField = "��������� ��������";
    
    private transient String selectedAssortment;
    private final NumbersMenuBox numbersBox;
    private transient String menuItem;
    private final StateField activeStateField;
    private final Reset resetMarker;
    
    public TypesMenuBox(StateField activeStateField, Reset serviceResetMarker) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 60);
        this.activeStateField = activeStateField;
        this.resetMarker = serviceResetMarker;
        // ������ ���� ��-���������
        addEmptyMenu();
        // �������� ���� ������� �������
        numbersBox = new NumbersMenuBox(activeStateField, serviceResetMarker);
    }
    
    private void addEmptyMenu(){
        // ������ ���� ��-���������
        Menu menu = new Menu();
        Menu emptyMenu = menu.createMenu(this);
        super.setModel(emptyMenu);
    }
    
    @Override
    public ArrayList<String> receiveMenu(){
        DataBaseMenuReceiver receiver = new DataBaseDispatcher();
        //System.out.println("test type box receive menu: "+selectedAssortment);
        return receiver.getTypeMenu(selectedAssortment);
    }
    
    public void setSelectedMenu(String selectedItem){
        this.selectedAssortment = selectedItem;
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    public NumbersMenuBox getNumbersBox(){
        return numbersBox;
    }
    /*
    @Override
    public String getSelectedMenuItem(){
        return super.getSelectedItem().toString();
    }
    */
    
    @Override
    public String getCurrentMenuItem(){
        return super.getSelectedItem().toString();
        //return menuItem;
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        //System.out.println("test type box: "+selectedMenuItem);
        this.menuItem = selectedMenuItem;
        resetMenuBox();
        // �������� ���� ������� ��������
        fillNumberProfilesMenu(selectedMenuItem);
    }
    
    // ���������� ���� ������� ��������
    private void fillNumberProfilesMenu(String menuItem){
        Menu menu = new Menu();
        numbersBox.setSelectedMenu(selectedAssortment, menuItem);
        menu.createMenu(numbersBox);
        numbersBox.setModel(menu);
    }
    
    private void resetMenuBox(){
        numbersBox.setSelectedIndex(0);
        resetMarker.reset();
        activeStateField.deactivate();
    }
    
    

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }

    @Override
    public boolean haveWidth() {
        return menuItem.equals(widthField);
    }
}
