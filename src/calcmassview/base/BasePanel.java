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

import calcdatabase.DataBaseInterface;
import calcmassview.general.GeneralPanel;
import calcmassview.settings.ToolTipsInterface;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import calcmassview.settings.ColorThemeInterface;

/**
 * �������� ������ � ������������
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel implements ItemListener {
    
    // ��������� ���� ������
    private DataBaseInterface dataBase;
    // combo-boxes
    private AssortmentMenu assortmentMenu;
    private TypesMenu typeProfileMenu;
    private NumbersMenu numberProfileMenu;
    // ���� ����� ��������
    private LengthField lengthField;
    private WidthField widthField;    
    // �������� ������ ������
    private final MyFocusTraversalPolicy myFocusTraversalPolicy;
    private final ArrayList<Component> policy;
    // ������� ������
    private final GeneralPanel panel;
    // ��������� ������
    private ServiceInfo serviceInfo;
    // ������ ����������
    private ResultMarker resultMarker;
    // ���-���� ������� ������� ������
    private DifficultAreaBox difficultAreaBox;
    
    public BasePanel(GeneralPanel panel, ColorThemeInterface theme, ToolTipsInterface toolTips) {
        this.panel = panel;
        policy = new ArrayList<>();
        createComponents(theme, toolTips);
        createDecorations(theme);
        // ���������� �������������� ���������
        super.setLayout(null);       
        // �������� ������ ������
        super.setFocusCycleRoot(true);
        myFocusTraversalPolicy = new MyFocusTraversalPolicy(policy);
        super.setFocusTraversalPolicy(myFocusTraversalPolicy);
    }
    
    private void createDecorations(ColorThemeInterface theme){
        //������� �� ��� ����
        JLabel widthMark = new JLabel();
        widthMark.setVisible(true);
        widthMark.setSize(25, 20);
        widthMark.setForeground(Color.white);
        widthMark.setText("��");
        widthMark.setLocation(320, 22);
        this.add(widthMark);
        theme.setColorTheme(widthMark);
        
        //������� �� ��� ����
        JLabel lengthMark = new JLabel();
        lengthMark.setVisible(true);
        lengthMark.setSize(25, 20);
        lengthMark.setForeground(Color.white);
        lengthMark.setText("��");
        lengthMark.setLocation(320, 62);
        this.add(lengthMark);
        theme.setColorTheme(lengthMark);
    }
    
    private void createComponents(ColorThemeInterface theme, ToolTipsInterface toolTips){
        theme.setColorTheme(this);
        // <��� �������>
        assortmentMenu = new AssortmentMenu(this, toolTips);
        // <��� �������>
        typeProfileMenu = new TypesMenu(this, toolTips);
        // <� �������>
        numberProfileMenu = new NumbersMenu(this, toolTips);
        //��������� ���� ������ (��� �����)
        widthField = new WidthField(this, toolTips);
        //��������� ���� �����
        lengthField = new LengthField(this, toolTips);
        // ��������� ������ ����������
        resultMarker = new ResultMarker(this, theme);
        // <��������� ������>
        serviceInfo = new ServiceInfo(this, theme);        
        // ���-���� ���������� ������� �������� ���������
        difficultAreaBox = new DifficultAreaBox(this, theme, toolTips);
    }
    
    // ���������� ����������� � �������� ������ ������
    void addPolicy(Component component){
        policy.add(component);
    }
    
    /**
     * ������ �� ����������� ������� ������
     * @return ������� ������
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
        widthField.deactiveField();
        lengthField.deactiveField();    
    }
    
    /**
     * ����� �������� ��������� ������ � ������ ����������
     */
    void resetMarker(){
        // ����� ��������
        resultMarker.reset();
        serviceInfo.reset();
    }
    
    public DifficultAreaBox getDifficultAreaBox(){
        return difficultAreaBox;
    }

    public AssortmentMenu getAssortmentMenu(){
        return assortmentMenu;
    }
    
    public TypesMenu getTypeProfileMenu(){
        return typeProfileMenu;
    }
    
    public NumbersMenu getNumberProfileMenu(){
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
    
    /**
     * ��������� ����������� ���� ������
     * @param dataBase ��������� ���� ������
     */
    public void setDataBase(DataBaseInterface dataBase) {
        this.dataBase = dataBase;
    }
    
    /**
     * ������ ���������� ���� ������
     * @return ��������� ���� ������
     */
    public DataBaseInterface getDataBase(){
        return dataBase;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        DifficultAreaBox source = (DifficultAreaBox) event.getItemSelectable();
        source.actionChooser(event);
    }
}
