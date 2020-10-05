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
package calcmassmodel;

/**
 * Интерактор
 * @author Sergei Lyashko
 */
class MassGenerator {
    
    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private final InputDataService inputService;
    private final ValueReceiveService areaReceiver;
    private double mass;

    MassGenerator(InputDataService inputService, ValueReceiveService areaReceiver) {
        this.inputService = inputService;
        this.areaReceiver = areaReceiver;
    }
    
    void orderMass(AbstractCalculatorFactory calculatorFactory) {
        AbstractMassCalculator massCalculator = calculatorFactory.calculatorOrder(inputService);
        Detail newDetail = createDetail();
        if(newDetail != null){
            massCalculator.setDetail(newDetail);
            this.mass = massCalculator.calculationMass();
        }
    }
    
    double getMass(){
        return mass;
    }
    
    private Detail createDetail(){
        String assortment = inputService.getAssortment();
        String type = inputService.getType();
        String number = inputService.getNumber();
        double detailValue = areaReceiver.getDetailValue(assortment, type, number);
        double length = inputService.getLength();
        double width = inputService.getWidth();
        if(isValidValues(width, length)){
            return new Detail(length, width, detailValue);
        }
        return null;
    }
    
    private boolean isValidValues(double widthNum, double lengthNum){
        if(isValidNumber(widthNum) && isValidNumber(lengthNum)){
            double checkNum = MAX_NUMBER / lengthNum;
            if(checkNum < widthNum){
                //this.message = "ошибка! слишком большое число!";
                return false;
            }
            return true;
        }
        return false;
    }
    
    private boolean isValidNumber(double number){
        if(number > MAX_NUMBER){
                //this.message = "ошибка! слишком большое число!";
                return false;
        }
        if(number < 0){
                //this.message = "ошибка! отрицательное число!";
                return false;
        }
        return true;
    }

    String getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getServiceMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
