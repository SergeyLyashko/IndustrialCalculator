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
import calcmassview.general.ToolTipsInterface;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import calcmassview.general.ColorThemeInterface;

/**
 * �������� ������ � ������������
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel implements ItemListener {
    
    // ��������� ���� ������
    private DataBaseInterface dataBase;
    
    // combo-boxes
    private AssortmentMenu assortmentMenu;
    private TypesMenu typesMenu;
    private NumbersMenu numbersMenu;
    
    // ���� ����� ��������
    private LengthField lengthField;
    private WidthField widthField;  
    
    // �������� ������ �������
    private MyFocusTraversalPolicy myFocusTraversalPolicy;
    private final ArrayList<Component> policy = new ArrayList<>();
    
    // ������� ������
    private final GeneralPanel panel;
    
    // ��������� ������
    private ServiceInfo serviceInfo;
    
    // ������ ����������
    private ResultMarker resultMarker;
    
    // ���-���� ������� ������� ������
    private DifficultAreaBox difficultAreaBox;
    
    private final ColorThemeInterface theme;
    private final ToolTipsInterface toolTips;
    
    public BasePanel(GeneralPanel panel, ColorThemeInterface theme, ToolTipsInterface toolTips) {
        this.panel = panel;
        this.theme = theme;
        this.toolTips = toolTips;
        // ���������� �����������
        addComponents();
        // �������� ������ �������
        focusPolicy();    
        // ���������� �������������� ���������
        super.setLayout(null);
    }
    
    // �������� ����������� ���� ����������
    private void addComponents(){
        // <��� �������>
        assortmentMenu = new AssortmentMenu(this);
        String assortmentToolTipText = "����� ���������� ������";
        toolTips.setToolTips(assortmentMenu, assortmentToolTipText);
        this.add(assortmentMenu);
        policy.add(assortmentMenu);
        assortmentMenu.addActionListener(assortmentMenu);
        
        // <��� �������>
        typesMenu = new TypesMenu(this);
        String typesToolTipText = "����� ���� ������� ������";
        toolTips.setToolTips(typesMenu, typesToolTipText);
        this.add(typesMenu);
        policy.add(typesMenu);
        typesMenu.addActionListener(typesMenu);
        
        // <� �������>
        numbersMenu = new NumbersMenu(this);
        String numbersToolTipText = "����� ������ ������� ������";
        toolTips.setToolTips(numbersMenu, numbersToolTipText);
        this.add(numbersMenu);
        policy.add(numbersMenu);
        numbersMenu.addActionListener(numbersMenu);
        
        //��������� ���� ������ (��� �����)
        widthField = new WidthField(this);
        String widthToolTipText = "���� ����� ������ ������";
        toolTips.setToolTips(widthField, widthToolTipText);
        this.add(widthField);
        policy.add(widthField);
        
        //��������� ���� �����
        lengthField = new LengthField(this);
        String lengthToolTipText = "���� ����� ����� ������";
        toolTips.setToolTips(lengthField, lengthToolTipText);
        this.add(lengthField);
        policy.add(lengthField);
        
        // ��������� ������ ����������
        resultMarker = new ResultMarker();
        theme.componentChangeColor(resultMarker);
        this.add(resultMarker);
        
        // <��������� ������>
        serviceInfo = new ServiceInfo();
        theme.componentChangeColor(serviceInfo);
        this.add(serviceInfo);
        
        // ���-���� ���������� ������� �������� ���������
        difficultAreaBox = new DifficultAreaBox(this);
        String areaBoxToolTipText = "������ ����� ������ �� ���������� ������� ������";
        toolTips.setToolTips(difficultAreaBox, areaBoxToolTipText);
        theme.componentChangeColor(difficultAreaBox);
        this.add(difficultAreaBox);
        
        //������� �� ��� ���� ������
        Markmm widthMark = new Markmm(320, 22);
        this.add(widthMark);
        theme.componentChangeColor(widthMark);
        
        //������� �� ��� ���� �����      
        Markmm lengthMark = new Markmm(320, 62);
        this.add(lengthMark);
        theme.componentChangeColor(lengthMark);
    }
    
    // �������� ������ �������
    private void focusPolicy(){        
        super.setFocusCycleRoot(true);
        myFocusTraversalPolicy = new MyFocusTraversalPolicy(policy);
        super.setFocusTraversalPolicy(myFocusTraversalPolicy);
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
        theme.componentChangeColor(resultMarker);
        theme.componentChangeColor(serviceInfo);
        resultMarker.reset();
        serviceInfo.reset();
    }
    
    public DifficultAreaBox getDifficultAreaBox(){
        return difficultAreaBox;
    }

    public AssortmentMenu getAssortmentMenu(){
        return assortmentMenu;
    }
    
    public TypesMenu getTypesMenu(){
        return typesMenu;
    }
    
    public NumbersMenu getNumbersMenu(){
        return numbersMenu;
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
