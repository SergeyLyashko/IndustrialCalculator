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

import calcmassview.general.GeneralPanel;
import calcmassview.settings.Theme;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * �������� ������ � ������������
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel {
    
    private final GeneralPanel panel;
    // combo-boxes
    private AssortmentProfileMenu assortmentMenu;
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
        //������� �� ��� ����
        JLabel widthMark = new JLabel();
        widthMark.setVisible(true);
        widthMark.setSize(25, 20);
        widthMark.setForeground(Color.white);
        widthMark.setText("��");
        widthMark.setLocation(320, 22);
        this.add(widthMark);
        Theme.addTheme(widthMark);
        //������� �� ��� ����
        JLabel lengthMark = new JLabel();
        lengthMark.setVisible(true);
        lengthMark.setSize(25, 20);
        lengthMark.setForeground(Color.white);
        lengthMark.setText("��");
        lengthMark.setLocation(320, 62);
        this.add(lengthMark);
        Theme.addTheme(lengthMark);
    }
    
    private void createComponents(){
        Theme.addTheme(this);
        // <��� �������>
        assortmentMenu = new AssortmentProfileMenu(this);        
        // <��� �������>
        typeProfileMenu = new TypeProfileMenu(this);
        // <� �������>
        numberProfileMenu = new NumberProfileMenu(this);
        //��������� ���� ������ (��� �����)
        widthField = new WidthField(this);
        //��������� ���� �����
        lengthField = new LengthField(this);
        // ��������� ������ ����������
        resultMarker = new ResultMarker(this);
        // <��������� ������>
        serviceInfo = new ServiceInfo(this);
    }
    
    // ���������� ����������� � �������� ������ ������
    void addPolicy(Component component){
        policy.add(component);
    }
    
    /**
     *
     * @return
     */
    public GeneralPanel getGeneralPanel(){
        return panel;
    }
    
    /**
     * ����� �������� �����
     */
    void reset(){        
        resetMarker();
        //����� ����� �����
        widthField.execute().deactivation();
        lengthField.execute().deactivation();    
    }
    
    void resetMarker(){
        // ����� ��������
        resultMarker.reset();
        serviceInfo.reset();
    }

    public AssortmentProfileMenu getAssortmentMenu(){
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

    /**
     * ��������� ���������� � ������ ����������
     * @param value ������� ����������
     */
    public void setResultation(String value){
        resultMarker.setResult(value);
        setResultToSystemClipboard(value);
        serviceInfo.setMessage("��������� ���������� � ����� ������");
    }
    
    /**
     * ��������� �� ������ � ��������� ������
     * @param message ��������� �� ������
     */
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
