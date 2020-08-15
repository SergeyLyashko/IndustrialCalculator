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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import calcdatabase.DataBase;
import calcmassview.settings.ColorTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import javax.swing.JComponent;

/**
 * �������� ������ � ������������
 * @author Sergei Lyashko
 */
@SuppressWarnings("serial")
@ColorTheme()
public class CalculatorPanelImpl extends JPanel implements CalculatorPanel, ItemListener, ActionListener, IKeyActionObserver, ColorTheme {
    
    // ��������� �����������
    private final ArrayList<JComponent> components;    
    // ���-���� ������� ������� ������
    private DifficultAreaBox difficultAreaBox;
    // ������
    private Detail calculatorData;    
    
    public CalculatorPanelImpl(ArrayList<JComponent> components, DataBase dataBase) {
        this.components =components;
        // ���������� �����������
        addComponents(dataBase);        
        // �������� ������ �������
        focusPolicy();    
        // ���������� �������������� ���������
        super.setLayout(null);
        super.setFocusCycleRoot(true);
    }
    
    // �������� ����������� ���� ����������
    private void addComponents(DataBase dataBase){        
        // ��������� ��������� ����� �����
        ActiveStateField activeStateField = new ActiveStateFieldImpl(components);
        // ����� ��������
        ServiceInscription resetMarkers = new ServiceInscriptionImpl(components);
        // <��� �������>
        AssortmentMenuBox assortmentBox = new AssortmentMenuBox(dataBase, activeStateField, resetMarkers);
        assortmentBox.addActionListener(this);
        components.add(assortmentBox);
        // <��� �������>
        TypesMenuBox typesBox = assortmentBox.getTypesMenu();
        typesBox.addActionListener(this);
        components.add(typesBox);
        // <� �������>
        NumbersMenuBox numbersBox = typesBox.getNumbersMenu();
        numbersBox.addActionListener(this);
        components.add(numbersBox);
        //��������� ���� ������ (��� �����)
        WidthField widthField = new WidthField(resetMarkers);
        components.add(widthField);
        //��������� ���� �����
        LengthField lengthField = new LengthField(resetMarkers);
        lengthField.registerObserver(this);
        components.add(lengthField);
        // ��������� ������ ����������
        ResultServiceInscription resultMarker = new ResultServiceInscription();
        components.add(resultMarker);
        // <��������� ������>
        MessageServiceInscription serviceInfo = new MessageServiceInscription();
        components.add(serviceInfo);
        // ���-���� ���������� ������� �������� ���������
        difficultAreaBox = new DifficultAreaBox(this);
        components.add(difficultAreaBox);
        //������� �� ��� ���� ������
        Markmm widthMark = new Markmm(320, 22);
        components.add(widthMark);
        //������� �� ��� ���� �����      
        Markmm lengthMark = new Markmm(320, 62);
        components.add(lengthMark);
        
        addComponentToPanel();
        // ��������� ��������� ����� �����
        activeStateField.deactivate();
        
        // ��������� ���������� ��������� ��������� ������ �������
        //detailState = new AreaBoxOFFState(components);
    }
    // ���������� ������������ �� ������
    private void addComponentToPanel(){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(CalculatorPanel.class))
                .forEach(this::add);
    }
    
    // �������� ������ �������
    private void focusPolicy(){
        MyFocusTraversalPolicy myFocusTraversalPolicy = new MyFocusTraversalPolicy(components);
        super.setFocusTraversalPolicy(myFocusTraversalPolicy);
    }
    
    /**
     *
     * @return
     */
    public Detail getData(){
        return calculatorData;
    }
    
    public void createData(){        
        calculatorData = new Detail(components);
        // TEST
        calculatorData.getAssortment();
        calculatorData.getType();
        calculatorData.getNumber();
        calculatorData.getWidth();
        calculatorData.getLength();
    }
        
    @Override
    public void itemStateChanged(ItemEvent event) {
        DifficultAreaBox source = (DifficultAreaBox) event.getItemSelectable();
        source.actionChooser(event);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        MenuBoxSelectable source = (MenuBoxSelectable) e.getSource();
        // ����� ������� ����
        String selectedMenuItem = source.getSelectedMenuItem();
        source.actionMenuSelect(selectedMenuItem);
    }    

    @Override
    public void keyActionUpdate() {
        //System.out.println("base panel update");// TEST
        createData();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}