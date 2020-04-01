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
import calcmassview.viewpanel.AbstractField;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * �������� ������ ����������
 * @author Sergei Lyashko
 */
public class BasePanel extends AbstractPanel {
    
    // combo-boxes
    private final AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    // ��������� ������
    private final ServiceMarker serviceMarker;
    // ���� ����� ��������
    private final AbstractField lengthField, widthField;
    // ������ ����������
    private final ResultMarker resultMarker;
    
    public BasePanel() {
        // ��������� ������ ����������
        resultMarker = new ResultMarker();
        resultMarker.setLocation(190, 100);
        
        //��������� ���� ������ (��� �����)
        widthField = new WidthField(this);
        widthField.setLocation(190, 20);       
        //������� �� ��� ����
        FieldMarker mmWf = new FieldMarker();
        mmWf.setText("��");
        mmWf.setLocation(320, 22);
        
        //��������� ���� �����
        lengthField = new LengthField(this);
        lengthField.setLocation(190, 60);        
        //������� �� ��� ����
        FieldMarker mmLf = new FieldMarker();
        mmLf.setText("��");
        mmLf.setLocation(320, 62);
        
        // <��� �������>
        baseMenuBox = new BaseMenuBox(this);
        baseMenuBox.setLocation(20, 20);       
        
        // �������� ������ ���� �� ��
        MenuBoxModel baseMenuModel = MenuCreator.getInstance().getModel();
        baseMenuBox.setModel(baseMenuModel);
        
        // In working
        // ������ ��������� ������ ��� �������� ������ ����
        //CustomMenuFrame.setProfileModelList(baseMenuModel);
        
        // <� �������>
        numberProfileMenuBox = new NumberProfileMenuBox(this);
        numberProfileMenuBox.setLocation(20, 100);
        numberProfileMenuBox.setModel(MenuCreator.getInstance().getModel("", ""));
        
        // <��� �������>
        typeProfileMenuBox = new TypeProfileMenuBox(this);
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
