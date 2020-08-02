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
import calcmassview.general.ToolTipsInterface;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import calcmassview.general.ColorThemeInterface;
import calcdatabase.IDataBase;

/**
 * �������� ������ � ������������
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel implements ItemListener {
    
    // ��������� ��������� ������ ������
    private IDetailWidthState detailState;
    
    // ��������� ���� ������
    private IDataBase dataBase;
    
    // combo-boxes
    private AssortmentMenuBox assortmentMenu;
    private TypesMenuBox typesMenu;
    private NumbersMenuBox numbersMenu;
    
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
    
    private ICalculatorData iCalculatorData;
    
    public BasePanel(GeneralPanel panel, ColorThemeInterface theme, ToolTipsInterface toolTips, IDataBase dataBase) {
        this.panel = panel;
        this.theme = theme;
        this.toolTips = toolTips;
        // ���������� �����������
        addComponents(dataBase);
        // �������� ������ �������
        focusPolicy();    
        // ���������� �������������� ���������
        super.setLayout(null);
    }
    
    // �������� ����������� ���� ����������
    private void addComponents(IDataBase dataBase){
        
        // <��� �������>
        assortmentMenu = new AssortmentMenuBox(this, dataBase);
        String assortmentToolTipText = "����� ���������� ������";
        toolTips.setToolTips(assortmentMenu, assortmentToolTipText);
        this.add(assortmentMenu);
        policy.add(assortmentMenu);
        assortmentMenu.addActionListener(assortmentMenu);
        
        // <��� �������>
        typesMenu = assortmentMenu.getTypesMenu();
        String typesToolTipText = "����� ���� ������� ������";
        toolTips.setToolTips(typesMenu, typesToolTipText);
        this.add(typesMenu);
        policy.add(typesMenu);
        
        // <� �������>
        numbersMenu = typesMenu.getNumbersMenu();
        String numbersToolTipText = "����� ������ ������� ������";
        toolTips.setToolTips(numbersMenu, numbersToolTipText);
        this.add(numbersMenu);
        policy.add(numbersMenu);
        
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
        
        // ��������� ���������� ��������� ��������� ������ �������
        detailState = new AreaBoxOFFState(this);
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
    public void reset(){        
        resetMarker();
        //����� ����� �����
        widthField.deactiveField();
        lengthField.deactiveField();
        
    }
    
    public ICalculatorData getData(){
        return iCalculatorData;
    }
    
    /**
     * ����� �������� ��������� ������ � ������ ����������
     */
    public void resetMarker(){
        // ����� ��������
        theme.componentChangeColor(resultMarker);
        theme.componentChangeColor(serviceInfo);
        resultMarker.reset();
        serviceInfo.reset();
    }
    
    public DifficultAreaBox getDifficultAreaBox(){
        return difficultAreaBox;
    }
    
    public AssortmentMenuBox getAssortmentMenu(){
        return assortmentMenu;
    }
    
    public TypesMenuBox getTypesMenu(){
        return typesMenu;
    }
    
    public NumbersMenuBox getNumbersMenu(){
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
        resultMarker.show(value);
        serviceInfo.show("��������� ���������� � ����� ������");
    }
    
    /**
     * ��������� �� ������ � ��������� ������
     * @param message ��������� �� ������
     */
    public void setError(String message){
        resultMarker.show("error");
        serviceInfo.showError(message);
    }
    
    /**
     * ��������� ����������� ���� ������
     * @param dataBase ��������� ���� ������
     */
    public void setDataBase(IDataBase dataBase) {
        this.dataBase = dataBase;
    }
    
    /**
     * ������ ���������� ���� ������
     * @return ��������� ���� ������
     */
    public IDataBase getDataBase(){
        return dataBase;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        DifficultAreaBox source = (DifficultAreaBox) event.getItemSelectable();
        source.actionChooser(event);
    }
    
    /**
     *
     * @param detailState
     */
    public void setDetailState(IDetailWidthState detailState){
        this.detailState = detailState;
    }
    
    /**
     *
     */
    public void actionFields(){
        reset();
        iCalculatorData = assortmentMenu.getICalculatorData();
        if(assortmentMenu.haveWidth() || typesMenu.haveWidth()){
            detailState.haveWidth();
            widthField.setData(iCalculatorData);
            lengthField.setData(iCalculatorData);
        }else{
            detailState.haveNotWidth();
            lengthField.setData(iCalculatorData);
        }
    }
}
