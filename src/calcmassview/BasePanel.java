/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
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
package calcmassview;

import calcmassview.viewpanel.CalculatorFocusTraversalPolicy;
import calcmassview.settingpanel.Theme;
import calcmassview.infopanel.InfoPanel;
import calcmassview.viewpanel.MenuBoxModel;
import calcmassview.viewpanel.FieldMarker;
import calcmassview.viewpanel.ServiceMarker;
import calcmassview.viewpanel.ResultMarker;
import calcmassview.viewpanel.LengthField;
import calcmassview.viewpanel.WidthField;
import calcmassview.viewpanel.TypeProfileMenuBox;
import calcmassview.viewpanel.NumberProfileMenuBox;
import calcmassview.viewpanel.BaseMenuBox;
import calcmassview.viewpanel.AbstractMenuBox;
import calcmassview.settingpanel.SettingsPanel;
import calcmassview.viewpanel.AbstractField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * �������� ������ ����������
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel {
    
    // ����������� �������� ���������� ������
    private static final BasePanel INSTANCE = new BasePanel();
    
    // combo-boxes
    private final AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    // ��������� ������
    private final ServiceMarker serviceMarker;
    // ���� ����� ��������
    private final AbstractField lengthField, widthField;
    // ������ ����������
    private final ResultMarker resultMarker;
    
    /**
     * ��������-����� �������� �������� ������ ���� ����������
     * @return ������ �������� ������ BasePanel
     */
    public static final BasePanel getInstance(){
        return INSTANCE;
    }
    
    // �����������
    private BasePanel() {
        // ���� ���� �� ���������
        super.setBackground(Color.BLACK);        
        // ���� ����������
        Theme.addTheme(this);
        
        // ��������� ������ ����������
        resultMarker = new ResultMarker();
        resultMarker.setLocation(190, 100);
        
        //��������� ���� ������ (��� �����)
        widthField = WidthField.getInstance();
        widthField.setLocation(190, 20);       
        //������� �� ��� ����
        FieldMarker mmWf = new FieldMarker();
        mmWf.setText("��");
        mmWf.setLocation(320, 22);
        
        //��������� ���� �����
        lengthField = LengthField.getInstance();
        lengthField.setLocation(190, 60);        
        //������� �� ��� ����
        FieldMarker mmLf = new FieldMarker();
        mmLf.setText("��");
        mmLf.setLocation(320, 62);
        
        // <��� �������>
        baseMenuBox = BaseMenuBox.getInstance();
        baseMenuBox.setLocation(20, 20);       
        
        // �������� ������ ���� �� ��
        MenuBoxModel baseMenuModel = MenuCreator.getInstance().getModel();
        baseMenuBox.setModel(baseMenuModel);
        
        // In working
        // ������ ��������� ������ ��� �������� ������ ����
        //CustomMenuFrame.setProfileModelList(baseMenuModel);
        
        // <� �������>
        numberProfileMenuBox = NumberProfileMenuBox.getInstance();
        numberProfileMenuBox.setLocation(20, 100);
        numberProfileMenuBox.setModel(MenuCreator.getInstance().getModel("", ""));
        
        // <��� �������>
        typeProfileMenuBox = TypeProfileMenuBox.getInstance();
        typeProfileMenuBox.setLocation(20, 60);
        typeProfileMenuBox.setModel(MenuCreator.getInstance().getModel(""));
        
        // <��������� ������>
        serviceMarker = new ServiceMarker();
        serviceMarker.setLocation(20, 140);
        
        //���������� ����������� �� ������ � ���������� ����
        super.add(baseMenuBox);        
        super.add(typeProfileMenuBox);
        super.add(numberProfileMenuBox);
        super.add(lengthField);
        super.add(widthField);
        super.add(serviceMarker);
        super.add(mmLf);
        super.add(mmWf);
        super.add(resultMarker);
        
        // ���������� �������������� ���������
        super.setLayout(null);       
        // ������������� �������
        this.addTab();    
        
        // �������� ������ ������
        ArrayList<Component> policy = new ArrayList<>();
        policy.add(baseMenuBox);
        policy.add(typeProfileMenuBox);
        policy.add(numberProfileMenuBox);
        policy.add(widthField);
        policy.add(lengthField);       
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new CalculatorFocusTraversalPolicy(policy));
    }
    
    /**
     * ���������� ������ � ���� 
     * @param menuName ��������� ������������� ������������ ����� ����
     * @param source ������ ����������� ����
     */
    public void updateView(String menuName, AbstractMenuBox source){
        if(source.equals(baseMenuBox)){
            MenuBoxModel typeMenuModel = MenuCreator.getInstance().getModel(menuName);
            typeProfileMenuBox.setModel(typeMenuModel);
        }
        if(source.equals(typeProfileMenuBox)){
            String selectedAssortment = baseMenuBox.getStringValue();
            MenuBoxModel numberMenuModel = MenuCreator.getInstance().getModel(selectedAssortment, menuName);
            numberProfileMenuBox.setModel(numberMenuModel);
        }
    }
    
    // ���������� ������� � �������� ���� ����������
    private void addTab(){
        GeneralPanel.getInstance().addToGeneralPanel("�����������", this);       
        GeneralPanel.getInstance().addToGeneralPanel("���������", SettingsPanel.getInstance());
        GeneralPanel.getInstance().addToGeneralPanel("�������", InfoPanel.getInstance());
    }    
    
    /**
     * ��������� ������� ������� � ���� "�����"
     * @param e ������� ������� �������
     */
    public void addViewListener(KeyListener e){
        lengthField.addKeyListener(e);        
    }
    
    /**
     * ����� �������� ��� ��������� ������ BaseMenuBox
     */
    public void reset(){        
        // ����� ��������
        resultMarker.resetResultMarker();
        serviceMarker.resetServiceMarker();
        //����� ����� �����
        widthField.closeField();
        lengthField.closeField();        
    }
    
    // ��������� ������
    public void setServiceMarker(String eventStr){        
        serviceMarker.setMarker(eventStr);
    }
    
    public AbstractMenuBox getBaseMenuBox(){
        return baseMenuBox;
    }
    
    public AbstractMenuBox getTypeProfileMenuBox(){
        return typeProfileMenuBox;
    }
            
    public AbstractMenuBox getNumberProfileMenuBox(){
        return numberProfileMenuBox;
    }

    public AbstractField getLengthField() {        
        return lengthField;
    }
    
    public AbstractField getWidthField(){
        return widthField;
    }
    
    public void setResultation(String value){        
        resultMarker.setResult(value);
        setServiceMarker("copy");        
    }
    
    public String getResultation(){
        return resultMarker.getText();
    }
}
