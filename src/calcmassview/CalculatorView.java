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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import calcmassview.basepanel.ValueFieldReceivable;

/**
 * Представление приложения
 * @author Sergei Lyashko
 */
public class CalculatorView implements MassObserver {
    
    private final CalculatorControllerInterface controller;
    private final GeneralPanel generalPanel;
    private String profileAssortment, profileType, profileNumber, length, width;
    private double mass;
    private String result;
    
    public CalculatorView(CalculatorModelInterface model, CalculatorControllerInterface controller){
        this.controller = controller;
        model.registerObserver(this);
        this.generalPanel = new GeneralPanel();
        addViewListener();
    }
    
    @Override
    public void update(double mass) {
        this.mass = mass;
        formatResult();
        generalPanel.getBasePanel().setResultation(result);
    }
    
    @Override
    public void updateErrorMessage(String message) {
        if(message != null){
            generalPanel.getBasePanel().setError(message);
        }
    }
    
    //форматирование строки результата
    private void formatResult(){
        this.result = new DecimalFormat("#.###").format(mass);
    }
    
    // слушатель нажатия клавиши
    private void addViewListener(){
        generalPanel.getBasePanel()
                .actionLengthField()
                .addKeyListener(new KeyListener() {
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
        controller.setValueFromView(profileAssortment, profileType, profileNumber, length, width);
    }
    
    // получение значений полей
    private void setFields(){
        this.profileAssortment = 
                ((ValueFieldReceivable) generalPanel
                    .getBasePanel()
                    .getBaseMenuBox())
                    .getValueOfField();
        this.profileType = 
                ((ValueFieldReceivable) generalPanel
                    .getBasePanel()
                    .getTypeProfileMenuBox())
                    .getValueOfField();
        this.profileNumber = 
                ((ValueFieldReceivable) generalPanel
                    .getBasePanel()
                    .getNumberProfileMenuBox())
                    .getValueOfField();
        this.length = 
                ((ValueFieldReceivable) generalPanel
                    .getBasePanel()
                    .actionLengthField())
                    .getValueOfField();
        this.width = 
                ((ValueFieldReceivable) generalPanel
                    .getBasePanel()
                    .actionWidthField())
                    .getValueOfField();
    }
}
