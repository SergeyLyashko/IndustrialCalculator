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
import java.text.DecimalFormat;
import calcdatabase.DataBase;
import calcmasscontroller.ICalculatorController;
import calcmassmodel.ICalculatorModel;
import calcmassview.base.Detail;

/**
 * Представление приложения
 * @author Sergei Lyashko
 */
public class CalculatorView implements ViewObserver {
    
    private final ICalculatorModel model;
    private final ICalculatorController controller;
    private final GeneralPanel generalPanel;
    private String profileAssortment, profileType, profileNumber, length, width;
    private String resultValue;
    
    public CalculatorView(ICalculatorModel model, ICalculatorController controller, DataBase dataBase){
        this.model = model;
        this.controller = controller;
        this.generalPanel = new GeneralPanel(dataBase);
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
        //generalPanel.getBasePanel().setResultation(resultValue);
    }
    
    @Override
    public void errorMessageUpdate(String message) {
        if(message != null){
            //generalPanel.getBasePanel().setError(message);
        }
    }
    
    //форматирование строки результата
    private void formatDoubleToString(double mass){
        this.resultValue = new DecimalFormat("#.###").format(mass);
    }
    
    @Override
    public void keyActionUpdate() {
        setParametrsToController();
    }
    
    // установка значений полей
    private void setParametrsToController(){
        /*getFieldsValue();
        boolean areaBoxOFF = generalPanel.getBasePanel().getDifficultAreaBox().isAreaBoxOFF();
        if(areaBoxOFF){
            controller.setFieldsValue(profileAssortment, profileType, profileNumber, length, width);
        }else{
            String area = this.length;
            controller.setFieldsValue(profileAssortment, profileType, profileNumber, area);
        }*/
    }
    
    // получение значений полей
    private void getFieldsValue(){
        /*Detail calculatorData = generalPanel.getBasePanel().getData();
        this.profileAssortment = calculatorData.getAssortment();        
        this.profileType = calculatorData.getType();        
        this.profileNumber = calculatorData.getNumber();        
        this.length = calculatorData.getLength();        
        this.width = calculatorData.getWidth();
        */
    }
}
