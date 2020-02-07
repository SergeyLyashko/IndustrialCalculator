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

import calcmassview.settingpanel.SettingsPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * �������� ������ ����������
 * 
 */
public final class ViewPanel extends JPanel {   
    
    private static final ViewPanel INSTANCE = new ViewPanel();
    
    // combo-boxes
    private final BaseMenuBox baseMenuBox, typeProfileMenuBox, nameProfileMenuBox;
    // ��������� ������
    private final ServiceMarker serviceMarker;
    // ���� ����� ��������
    private final LengthField lengthField, widthField;
    // ������ ����������
    private final ResultMarker resultMarker;
    //������ �� ���������� ����
    private String detailName, detailLength, detailWidth;
    
    /**
     * ��������-����� �������� �������� ������ ���� ����������
     * @return ������ �������� ������ View
     */
    public static final ViewPanel getInstance(){
        return INSTANCE;
    }
        
    private ViewPanel() {
        super();
        super.setBackground(Color.BLACK);
        
        // ���� ����������
        Theme.addTheme(this);
        
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
        MenuBoxModel baseMenuModel = MenuCreator.getInstance().getModel();
        baseMenuBox.setModel(baseMenuModel);
        
        // In working
        // ������ ��������� ������ ��� �������� ������ ����
        //CustomMenuFrame.setProfileModelList(baseMenuModel);
        
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
        updateView((String)baseMenuBox.getSelectedItem(), baseMenuBox);
        updateView((String)typeProfileMenuBox.getSelectedItem(), typeProfileMenuBox);
        
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
    
    // ��������� ��������� ������� � ����
    public void startPosition(){
        typeProfileMenuBox.setSelectedIndex(0);
        nameProfileMenuBox.setSelectedIndex(0);
    }

    // ���������� ������ � ���� 
    public void updateView(String menuName, JComboBox<String> menu){
        if(menu.equals(baseMenuBox)){
            MenuBoxModel typeMenuModel = MenuCreator.getInstance().getModel(menuName);
            typeProfileMenuBox.setModel(typeMenuModel);
        }
        if(menu.equals(typeProfileMenuBox)){
            String selectedAssortment = baseMenuBox.getSelect();
            MenuBoxModel numberMenuModel = MenuCreator.getInstance().getModel(selectedAssortment, menuName);
            nameProfileMenuBox.setModel(numberMenuModel);
        }
    }

    // TODO ��������� �����
    public void actionField(String menuName){
    // �����
        if(!menuName.equals("� �������")){
            lengthField.actionField();
            // ��������� ���� ������ ��� ������ "����" � "���������_��������"
            if(((String)baseMenuBox.getSelectedItem()).equals("����") ||
                    ((String)typeProfileMenuBox.getSelectedItem()).equals("��������� ��������")){
                widthField.actionField();
            }
        }        
    }
    
    public void setDetailName(String detailName){
        this.detailName = detailName;
    }
    
    public void setDetailWidth(String detailWidth){
        this.detailWidth = detailWidth;
    }
    
    public void setDetailLength(String detailLength){
        this.detailLength = detailLength;
    }
    
    // ���������� ������� � �������� ���� ����������
    private void addTab(){
        InfoPanel ip = new InfoPanel();
        SettingsPanel sp = new SettingsPanel();
        GeneralPanel.addToGeneralPanel("�����������", this);       
        GeneralPanel.addToGeneralPanel("���������", sp);
        GeneralPanel.addToGeneralPanel("�������", ip);
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
    
    public String getAssortmentName(){
        return (String)baseMenuBox.getSelectedItem();
    }
    
    public String getTypeDetailName(){
        return (String)typeProfileMenuBox.getSelectedItem();
    }
    
    // ������ ����������     
    public String getDetailName(){
        return detailName;
    }

    public String getDetailLength() {        
        return detailLength;
    }
    
    public String getDetailWidth(){
        return detailWidth;
    }
    
    public void setResultation(String value){        
        resultMarker.setResult(value);
        setServiceMarker("copy");        
    }
    
    public String getResultation(){
        return resultMarker.getText();
    }
}
