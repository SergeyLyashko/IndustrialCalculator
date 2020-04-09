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
import calcmassview.viewpanel.FieldMarker;
import calcmassview.viewpanel.ServiceInfo;
import calcmassview.viewpanel.ResultMarker;
import calcmassview.viewpanel.LengthField;
import calcmassview.viewpanel.WidthField;
import calcmassview.viewpanel.TypeProfileMenuBox;
import calcmassview.viewpanel.NumberProfileMenuBox;
import calcmassview.viewpanel.BaseMenuBox;
import calcmassview.viewpanel.AbstractMenuBox;
import calcmassview.viewpanel.AbstractField;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

/**
 * �������� ������ ����������
 * @author Sergei Lyashko
 */
public class BasePanel extends AbstractPanel {
    
    // combo-boxes
    private AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    // ��������� ������
    private ServiceInfo serviceInfo;
    // ���� ����� ��������
    private AbstractField lengthField, widthField;
    // ������ ����������
    private ResultMarker resultMarker;
    // �������� ����������� ���� � �����-������
    private MenuCreator menuCreator;
    // �������� ������ ������
    private final ArrayList<Component> policy = new ArrayList<>();
    
    public BasePanel() {
        createComponents();
        createDecorations();
        // ���������� �������������� ���������
        super.setLayout(null);       
        // �������� ������ ������
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new CalculatorFocusTraversalPolicy(policy));
    }
    
    private void createDecorations(){
        //������� �� ��� ����
        FieldMarker mmWf = new FieldMarker(this);
        mmWf.setText("��");
        mmWf.setLocation(320, 22);
        //������� �� ��� ����
        FieldMarker mmLf = new FieldMarker(this);
        mmLf.setText("��");
        mmLf.setLocation(320, 62);
        // ��������� ������ ����������
        resultMarker = new ResultMarker(this);
        // <��������� ������>
        serviceInfo = new ServiceInfo(this);
    }
    
    private void createComponents(){
        // �������� ������ ���� �� ��
        //menuCreator = new MenuCreator();
        // <��� �������>
        baseMenuBox = new BaseMenuBox(this);        
        // <��� �������>
        typeProfileMenuBox = new TypeProfileMenuBox(this);
        // <� �������>
        numberProfileMenuBox = new NumberProfileMenuBox(this);
        //��������� ���� ������ (��� �����)
        widthField = new WidthField(this);
        //��������� ���� �����
        lengthField = new LengthField(this);
    }
    // ���������� ����������� � �������� ������ ������
    public void addPolicy(Component component){
        policy.add(component);
    }
    /*
    public MenuCreator getMenuCreator(){
        return menuCreator;
    }
    */
    /**
     * ����� �������� ��� ��������� ������ BaseMenuBox
     */
    public void reset(){        
        // ����� ��������
        resultMarker.resetResultMarker();
        serviceInfo.resetServiceMarker();
        //����� ����� �����
        widthField.closeField();
        lengthField.closeField();        
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
        setResultToSystemClipboard(value);
        serviceInfo.setMessage("��������� ���������� � ����� ������");
    }
    
    // ��������� ������
    public void setError(String message){
        resultMarker.setResult("error");
        serviceInfo.setErrorMessage(message);
    }
    
    // ����� ����������� � ����� ������ ��� ������ ����������
    private void setResultToSystemClipboard(String value){                
        Toolkit.getDefaultToolkit()
            .getSystemClipboard()
            .setContents(new StringSelection(value), null);       
    }
}
