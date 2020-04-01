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

import calcmasscontroller.CalculatorControllerInterface;
import calcmassmodel.CalculatorModelInterface;
import calcmassview.settingpanel.SettingsPanel;
import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.AbstractMenuBox;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

/**
 * View
 * @author Sergei Lyashko
 */
public class CalculatorView implements MassObserver {
    
    private final CalculatorControllerInterface controller;
    private final CalculatorModelInterface model;
    private GeneralPanel generalPanel;
    private BasePanel basePanel;
    private SettingsPanel settingsPanel;
    private InfoPanel infoPanel;
    private AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    private AbstractField lengthField, widthField;
    private String profileAssortment, profileType, profileNumber, length, width;
    private double mass;
    private String result;
    
    public CalculatorView(CalculatorModelInterface model, CalculatorControllerInterface controller){
        this.controller = controller;
        this.model = model;
        create();
    }
    
    private void create(){
        this.generalPanel = new GeneralPanel();
        this.basePanel = new BasePanel();
        this.settingsPanel = new SettingsPanel();
        this.infoPanel = new InfoPanel();
        addTab();
        model.registerObserver(this);
        addViewListener(new ViewListener());
    }
    
    // ���������� ������� � �������� ���� ����������
    private void addTab(){
        generalPanel.addPanel("�����������", basePanel);       
        generalPanel.addPanel("���������", settingsPanel);
        generalPanel.addPanel("�������", infoPanel);
    }

    @Override
    public void update(double mass) {
        this.mass = mass;
        formatResult();
        basePanel.setResultation(result);
    }
    
    //�������������� ������ ����������
    private void formatResult(){
        this.result = new DecimalFormat("#.###").format(mass);
        System.out.println("test result: "+result);
    }
    
    private void addViewListener(KeyListener e){
        basePanel.addViewListener(e);
    }
    
    // inner class
    private class ViewListener implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                setParametrs();
            }
        }    
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {}
    } // end iner class
    
    public void setParametrs(){
        setFields();
        setValueOfFields();
        controller.setParametrs(profileAssortment, profileType, profileNumber, length, width);
    }
    
    private void setFields(){
        this.baseMenuBox = basePanel.getBaseMenuBox();
        this.typeProfileMenuBox = basePanel.getTypeProfileMenuBox();
        this.numberProfileMenuBox = basePanel.getNumberProfileMenuBox();
        this.lengthField = basePanel.getLengthField();
        this.widthField = basePanel.getWidthField();        
    } 
    
    private void setValueOfFields(){
        this.profileAssortment = baseMenuBox.getStringValue();
        this.profileType = typeProfileMenuBox.getStringValue();
        this.profileNumber = numberProfileMenuBox.getStringValue();
        this.length = lengthField.getStringValue();
        this.width = widthField.getStringValue();
    }
}
