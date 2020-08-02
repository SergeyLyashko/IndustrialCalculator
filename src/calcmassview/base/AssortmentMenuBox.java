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
 * ���� ����� ����������
 * @author Sergei Lyashko
 */
public class AssortmentMenuBox extends JComboBox<String> implements ActionListener {
    
    private ICalculatorData calculatorData;
        
    private final BasePanel basePanel;
    private final IDataBase dataBase;
    
    private final TypesMenuBox typesMenu;
    private boolean haveWidth;
    
    public AssortmentMenuBox(BasePanel basePanel, IDataBase dataBase) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 20);
        this.basePanel = basePanel;
        this.dataBase = dataBase;
        // ������ ���� ��-���������
        Menu empty = new Menu(dataBase);
        super.setModel(empty.createMenu());
        // �������� ���� ����� �������
        typesMenu = new TypesMenuBox(basePanel, dataBase); 
        typesMenu.addActionListener(typesMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // �������� ������
        createData();
        //����� ���������� �����        
        resetAllFields();        
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        calculatorData.setAssortment(selectedMenuItem);
        // �������� ���� ����� ��������
        fillTypeProfilesMenu(selectedMenuItem);         
    }
    
    private void createData(){
        calculatorData = new CalculatorData();
        typesMenu.setData(calculatorData);        
    }
    
    /**
     *
     * @return
     */
    public ICalculatorData getICalculatorData(){
        return calculatorData;
    }
    
    // ���������� ���� ����� ��������
    private void fillTypeProfilesMenu(String menuItem){
        Menu menu = new Menu(dataBase);
        Menu newTypeProfilesMenu = menu.createMenu(menuItem);
        typesMenu.setSelectedMenu(menuItem);
        typesMenu.setModel(newTypeProfilesMenu);
        haveWidth = menu.haveWidth();
        //System.out.println("have width: "+haveWidth);// TEST
    }
    
    /**
     *
     * @return
     */
    public TypesMenuBox getTypesMenu(){
        return typesMenu;
    }
    
    // ����� ����� �����
    private void resetAllFields(){
        haveWidth = false;
        typesMenu.setSelectedIndex(0);
        typesMenu.getNumbersMenu().setSelectedIndex(0);
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
