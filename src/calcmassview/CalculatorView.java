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
package calcmassview;

import calcmassview.general.GeneralPanel;
import calcmasscontroller.CalculatorControllerInterface;
import calcmassmodel.CalculatorModelInterface;
import java.text.DecimalFormat;

/**
 * Представление приложения
 * @author Sergei Lyashko
 */
public class CalculatorView implements ViewObserver {
    
    private final CalculatorModelInterface model;
    private final CalculatorControllerInterface controller;
    private final GeneralPanel generalPanel;
    private String profileAssortment, profileType, profileNumber, length, width;
    private String resultValue;
    
    public CalculatorView(CalculatorModelInterface model, CalculatorControllerInterface controller){
        this.model = model;
        this.controller = controller;
        this.generalPanel = new GeneralPanel();
        registerObservers();
    }
    
    // регистрация наблюдателей
    private void registerObservers(){
        model.registerObserver(this);        
        generalPanel.registerObserver(this);
    }
    
    @Override
    public void massUpdate(double mass) {
        formatDoubleToString(mass);
        generalPanel.getBasePanel().setResultation(resultValue);
    }
    
    @Override
    public void errorMessageUpdate(String message) {
        if(message != null){
            generalPanel.getBasePanel().setError(message);
        }
    }
    
    //форматирование строки результата
    private void formatDoubleToString(double mass){
        this.resultValue = new DecimalFormat("#.###").format(mass);
    }
    
    @Override
    public void keyActionUpdate() {
        setParametrs();
    }
    
    private void setParametrs(){
        getFieldsValueFromView();
        controller.setParametersFromView(profileAssortment, profileType, profileNumber, length, width);
    }
    
    // получение значений полей
    private void getFieldsValueFromView(){
        this.profileAssortment = generalPanel
                        .getBasePanel()
                        .getAssortmentMenu()
                        .value()
                        .receive();
        this.profileType = generalPanel
                        .getBasePanel()
                        .getTypeProfileMenu()
                        .value()
                        .receive();
        this.profileNumber = generalPanel
                        .getBasePanel()
                        .getNumberProfileMenu()
                        .value()
                        .receive();
        this.length = generalPanel
                    .getBasePanel()
                    .getLengthField()
                    .value()
                    .receive();
        this.width = generalPanel
                    .getBasePanel()
                    .getWidthField()
                    .value()
                    .receive();
    }
}
