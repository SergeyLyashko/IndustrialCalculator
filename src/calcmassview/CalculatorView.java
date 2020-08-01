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
import calcdatabase.IDataBase;
import calcmasscontroller.ICalculatorController;
import calcmassmodel.ICalculatorModel;

/**
 * ������������� ����������
 * @author Sergei Lyashko
 */
public class CalculatorView implements ViewObserver {
    
    private final ICalculatorModel model;
    private final ICalculatorController controller;
    private final GeneralPanel generalPanel;
    private String profileAssortment, profileType, profileNumber, length, width;
    private String resultValue;
    
    public CalculatorView(ICalculatorModel model, ICalculatorController controller, IDataBase dataBase){
        this.model = model;
        this.controller = controller;
        this.generalPanel = new GeneralPanel(dataBase);
        registerObservers();
    }
    
    // ����������� ������������
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
    
    //�������������� ������ ����������
    private void formatDoubleToString(double mass){
        this.resultValue = new DecimalFormat("#.###").format(mass);
    }
    
    @Override
    public void keyActionUpdate() {
        setParametrsToController();
    }
    
    // ��������� �������� �����
    private void setParametrsToController(){
        getFieldsValueFromView();
        boolean areaBoxOFF = generalPanel.getBasePanel().getDifficultAreaBox().isAreaBoxOFF();
        if(areaBoxOFF){
            controller.setFieldsValue(profileAssortment, profileType, profileNumber, length, width);
        }else{
            String area = this.length;
            controller.setFieldsValue(profileAssortment, profileType, profileNumber, area);
        }
    }
    
    // ��������� �������� �����
    private void getFieldsValueFromView(){
        this.profileAssortment = generalPanel
                        .getBasePanel()
                        .getAssortmentMenu()
                        .fieldValueStringReceive();
        this.profileType = generalPanel
                        .getBasePanel()
                        .getTypesMenu()
                        .fieldValueStringReceive();
        this.profileNumber = generalPanel
                        .getBasePanel()
                        .getNumbersMenu()
                        .fieldValueStringReceive();
        this.length = generalPanel
                    .getBasePanel()
                    .getLengthField()
                    .fieldValueStringReceive();
        this.width = generalPanel
                    .getBasePanel()
                    .getWidthField()
                    .fieldValueStringReceive();
    }
}
