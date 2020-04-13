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
package calcmassview.base;

import calcmassview.general.GeneralPanel;
import calcmassview.settings.Theme;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * �������� ������ � ������������
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel {
    
    private final GeneralPanel panel;
    
    // combo-boxes
    private AssortmentMenu assortmentMenu;
    private TypeProfileMenu typeProfileMenu;
    private NumberProfileMenu numberProfileMenu;
    // ��������� ������
    private ServiceInfo serviceInfo;
    // ���� ����� ��������
    private LengthField lengthField;
    private WidthField widthField;
    // ������ ����������
    private ResultMarker resultMarker;
    // �������� ������ ������
    private final ArrayList<Component> policy = new ArrayList<>();
    
    public BasePanel(GeneralPanel panel) {
        this.panel = panel;
        createComponents();
        createDecorations();
        // ���������� �������������� ���������
        super.setLayout(null);       
        // �������� ������ ������
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new MyFocusTraversalPolicy(policy));
    }
    
    private void createDecorations(){
        Theme.addTheme(this);
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
        // <��� �������>
        assortmentMenu = new AssortmentMenu(this);        
        // <��� �������>
        typeProfileMenu = new TypeProfileMenu(this);
        // <� �������>
        numberProfileMenu = new NumberProfileMenu(this);
        //��������� ���� ������ (��� �����)
        widthField = new WidthField(this);
        //��������� ���� �����
        lengthField = new LengthField(this);
    }
    // ���������� ����������� � �������� ������ ������
    public void addPolicy(Component component){
        policy.add(component);
    }
    
    public GeneralPanel getGeneralPanel(){
        return panel;
    }
    
    /**
     * ����� �������� ��� ��������� ������ AssortmentMenu
     */
    public void reset(){        
        // ����� ��������
        resultMarker.reset();
        serviceInfo.reset();
        //����� ����� �����
        widthField.close().close();
        lengthField.close().close();    
    }

    public AssortmentMenu getAssortmentMenu(){
        return assortmentMenu;
    }
    
    public TypeProfileMenu getTypeProfileMenu(){
        return typeProfileMenu;
    }
    
    public NumberProfileMenu getNumberProfileMenu(){
        return numberProfileMenu;
    }

    public LengthField getLengthField(){
        return lengthField;
    }

    public WidthField getWidthField(){
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
