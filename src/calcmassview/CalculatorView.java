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

import calcmassview.general.GeneralPanel;
import calcmasscontroller.CalculatorControllerInterface;
import calcmassmodel.CalculatorModelInterface;
import java.text.DecimalFormat;

/**
 * ѕредставление приложени€
 * @author Sergei Lyashko
 */
public class CalculatorView implements MassObserver, KeyActionObserver {
    
    private final CalculatorModelInterface model;
    private final CalculatorControllerInterface controller;
    private GeneralPanel generalPanel;
    private String profileAssortment, profileType, profileNumber, length, width;
    private double mass;
    private String result;
    
    public CalculatorView(CalculatorModelInterface model, CalculatorControllerInterface controller){
        this.model = model;
        this.controller = controller;
        create();
    }
    
    private void create(){
        model.registerObserver(this);
        this.generalPanel = new GeneralPanel();
        generalPanel.registerObserver(this);
    }
    
    @Override
    public void updateMass(double mass) {
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
    
    @Override
    public void keyActionUpdate() {
        setParametrs();
    }
    
    private void setParametrs(){
        setFields();
        controller.setParametersDetail(profileAssortment, profileType, profileNumber, length, width);
    }
    
    // получение значений полей
    private void setFields(){
        this.profileAssortment = generalPanel
                        .getBasePanel()
                        .getAssortmentMenu()
                        .value()
                        .getValue();
        this.profileType = generalPanel
                        .getBasePanel()
                        .getTypeProfileMenu()
                        .value()
                        .getValue();
        this.profileNumber = generalPanel
                        .getBasePanel()
                        .getNumberProfileMenu()
                        .value()
                        .getValue();
        this.length = generalPanel
                    .getBasePanel()
                    .getLengthField()
                    .value()
                    .getValue();
        this.width = generalPanel
                    .getBasePanel()
                    .getWidthField()
                    .value()
                    .getValue();
    }
}
