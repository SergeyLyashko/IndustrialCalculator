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
import calcmassview.settings.ColorTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import javax.swing.JComponent;

/**
 * �������� ������ � ������������
 * @author Sergei Lyashko
 */
@ColorTheme()
@CalculatorPanel()
public class CalculatorPanelImpl extends JPanel implements CalculatorPanel, ItemListener, ActionListener, IKeyActionObserver, ColorTheme {

    private static final long serialVersionUID = 1L;
    
    // ��������� �����������
    private final ArrayList<JComponent> components;    
    // ������
    private transient Detail detail;    
    
    public CalculatorPanelImpl(ArrayList<JComponent> components) {
        this.components =components;
        // ���������� �����������
        addComponents();        
        // �������� ������ �������
        focusPolicy();    
        // ���������� �������������� ���������
        super.setLayout(null);
        super.setFocusCycleRoot(true);
    }
    
    // �������� ����������� ���� ����������
    private void addComponents(){        
        // ��������� ��������� ����� �����
        StateField stateField = new StateFieldImpl(components);
        // ����� ��������
        ServiceInscription serviceResetMarkers = new ServiceInscriptionImpl(components);
        // <��� �������>
        AssortmentMenuBox assortmentBox = new AssortmentMenuBox(stateField, serviceResetMarkers);
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
        WidthField widthField = new WidthField(serviceResetMarkers);
        components.add(widthField);
        //��������� ���� �����
        LengthField lengthField = new LengthField(serviceResetMarkers);
        lengthField.registerObserver(this);
        components.add(lengthField);
        // ��������� ������ ����������
        ResultServiceInscription resultMarker = new ResultServiceInscription();
        components.add(resultMarker);
        // <��������� ������>
        MessageServiceInscription serviceInfo = new MessageServiceInscription();
        components.add(serviceInfo);
        // ���-���� ���������� ������� �������� ���������
        AreaBoxStateImpl areaBoxSelector = new AreaBoxStateImpl();
        areaBoxSelector.addItemListener(this);
        components.add(areaBoxSelector);
        //������� �� ��� ���� ������
        Markmm widthMark = new Markmm(320, 22);
        components.add(widthMark);
        //������� �� ��� ���� �����      
        Markmm lengthMark = new Markmm(320, 62);
        components.add(lengthMark);
        //        
        addComponentToPanel();
        // ��������� ��������� ����� �����
        stateField.deactivate();
        
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
    
    @Override
    public String getName(){
        return "�����������";
    }
    
    /**
     *
     * @return
     */
    public Detail getDetail(){
        return detail;
    }
    
    public void createDetail(){        
        detail = new Detail(components);
        // TEST
        detail.getAssortment();
        detail.getType();
        detail.getNumber();
        detail.getWidth();
        detail.getLength();
    }
        
    @Override
    public void itemStateChanged(ItemEvent event) {
        AreaBoxStateImpl source = (AreaBoxStateImpl) event.getItemSelectable();
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
        createDetail();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}