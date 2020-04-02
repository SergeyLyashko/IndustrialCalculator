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
import calcmassview.viewpanel.ValueReceivable;
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
        addViewListener();
    }
    
    // добавление вкладок в основное окно приложения
    private void addTab(){
        generalPanel.addPanel("Калькулятор", basePanel);       
        generalPanel.addPanel("Настройки", settingsPanel);
        generalPanel.addPanel("Справка", infoPanel);
    }

    @Override
    public void update(double mass) {
        this.mass = mass;
        formatResult();
        basePanel.setResultation(result);
    }
    
    //форматирование строки результата
    private void formatResult(){
        this.result = new DecimalFormat("#.###").format(mass);
    }
    
    // слушатель нажатия клавиши
    private void addViewListener(){
        basePanel.getLengthField().addKeyListener(new KeyListener() {
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
        });
    }
    
    private void setParametrs(){
        setFields();
        controller.setParametrs(profileAssortment, profileType, profileNumber, length, width);
    }
    
    private void setFields(){
        this.profileAssortment = ((ValueReceivable) basePanel.getBaseMenuBox()).getValueOfField();
        this.profileType = ((ValueReceivable) basePanel.getTypeProfileMenuBox()).getValueOfField();
        this.profileNumber = ((ValueReceivable) basePanel.getNumberProfileMenuBox()).getValueOfField();
        this.length = ((ValueReceivable) basePanel.getLengthField()).getValueOfField();
        this.width = ((ValueReceivable) basePanel.getWidthField()).getValueOfField();
    }
}
