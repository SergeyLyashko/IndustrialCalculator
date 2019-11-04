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

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * �������� ������ ����������
 * @author Sergei Lyashko
 */
public final class ViewPanel extends JPanel implements IViewController {   
    
    public static final ViewPanel VIEW_PANEL = new ViewPanel();
    
    // combo-boxes
    protected BaseMenuBox baseMenuBox, typeProfileMenuBox, nameProfileMenuBox;
    // ��������� ������
    private final ServiceMarker serviceMarker;
    // ���� ����� ��������
    protected LengthField lengthField, widthField;
    // ������ ����������
    protected ResultMarker resultMarker;
    //������ �� ���������� ����
    protected String detailName, detailLength, detailWidth;   
    
    public ViewPanel() {
        super();
        super.setBackground(Color.BLACK);       
        
        // ��������� ������ ����������
        resultMarker = new ResultMarker();
        resultMarker.setLocation(190, 100);
        
        //��������� ���� ������ (��� �����)
        widthField = new WidthField(this);
        widthField.setLocation(190, 20);        
        //������� ��
        Marker mmWf = new Marker();
        mmWf.setText("��");
        mmWf.setLocation(320, 22);
        
        //��������� ���� �����
        lengthField = new LengthField(this);
        lengthField.setLocation(190, 60);        
        //������� ��
        Marker mmLf = new Marker();
        mmLf.setText("��");
        mmLf.setLocation(320, 62);
        
        // <��� �������>
        baseMenuBox = new BaseMenuBox(this);
        baseMenuBox.setLocation(20, 20);       
        // �������� ������ ���� �� ��
        MenuBoxModel baseMenuModel = MenuData.createMenuModelFromData("baseMenu");
        baseMenuBox.setModel(baseMenuModel);        
        
        // <� �������>
        nameProfileMenuBox = new NameProfileMenuBox(this);
        nameProfileMenuBox.setLocation(20, 100);
        
        // <��� �������>
        typeProfileMenuBox = new TypeProfileMenuBox(this);
        typeProfileMenuBox.setLocation(20, 60);
        
        // <��������� ������>
        serviceMarker = new ServiceMarker();
        serviceMarker.setLocation(20, 140);
        
        // ���������� ���� ����        
        baseMenuBox.updateView((String)baseMenuBox.getSelectedItem());
        typeProfileMenuBox.updateView((String)typeProfileMenuBox.getSelectedItem());       
        
        //���������� ����������� �� ������ � ���������� ����
        super.add(baseMenuBox);        
        super.add(typeProfileMenuBox);
        super.add(nameProfileMenuBox);
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
        policy.add(nameProfileMenuBox);
        policy.add(widthField);
        policy.add(lengthField);       
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new CalculatorFocusTraversalPolicy(policy));
    }
    
    // ���������� ������� � �������� ���� ����������
    private void addTab(){
        GeneralPanel.addToGeneralPanel("�����������", this);
        GeneralPanel.addToGeneralPanel("���������", new SettingsPanel());
        GeneralPanel.addToGeneralPanel("Info", new InfoPanel());        
    }    
    
    // ��������� ����� <Enter> � ���� "�����"
    public void addViewListener(KeyListener e){
        lengthField.addKeyListener(e);        
    }
    
    // ����� �������� ��� ��������� ������ BaseMenuBox 
    protected void reset(){        
        // ����� ��������
        resultMarker.resetResultMarker();
        serviceMarker.resetServiceMarker();
        //����� ����� �����
        widthField.closeField();
        lengthField.closeField();        
        //��������� ��������� ��������
        detailName = null; 
        detailLength = null; 
        detailWidth = null;        
    }
    
    // ��������� ������
    public void setServiceMarker(String eventStr){        
        serviceMarker.setMarker(eventStr);
    }
    
    @Override
    public String getAssortmentName(){
        return (String)baseMenuBox.getSelectedItem();
    }
    
    @Override
    public String getTypeDetailName(){
        return (String)typeProfileMenuBox.getSelectedItem();
    }
    
    // ������ ����������     
    @Override 
    public String getDetailName(){
        return detailName;
    }

    @Override
    public String getDetailLength() {        
        return detailLength;
    }
    
    @Override
    public String getDetailWidth(){
        return detailWidth;
    }
    
    @Override
    public void setResultation(String value){        
        resultMarker.setValue(value);
        setServiceMarker("copy");
    }
}
