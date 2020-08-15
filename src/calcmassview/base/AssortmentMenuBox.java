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
 * ���� ����� ����������
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ValueReceiveble(getFieldValue = "")
@IDetailWidthState(haveWidth = false)
@ToolTips(getToolTipDescription = "")
public class AssortmentMenuBox extends JComboBox<String> implements CalculatorPanel, MenuBoxSelectable, ValueReceiveble, IDetailWidthState, ToolTips {
    
    private final String toolTipsText = "����� ���������� ������";
    private final String widthField = "����"; 
        
    private final DataBase dataBase;
    private final TypesMenuBox typesMenu;
    private String fieldValue;
    private final ActiveStateField activeStateField;
    private final ServiceInscription resetMarker;
    
    public AssortmentMenuBox(DataBase dataBase, ActiveStateField activeStateField, ServiceInscription resetMarker) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 20);
        this.dataBase = dataBase;
        this.activeStateField = activeStateField;
        this.resetMarker = resetMarker;
        // ������ ���� ��-���������
        Menu empty = new Menu(dataBase);
        super.setModel(empty.createMenu());
        // �������� ���� ����� �������
        typesMenu = new TypesMenuBox(dataBase, activeStateField, resetMarker); 
    }
    
    @Override
    public void actionMenuSelect(String selectedMenuItem) {
        this.fieldValue = selectedMenuItem;
        resetMenuBox();
        // �������� ���� ����� ��������
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
    
    // ���������� ���� ����� ��������
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