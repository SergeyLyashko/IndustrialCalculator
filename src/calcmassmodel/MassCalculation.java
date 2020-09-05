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

import calcmassview.base.FieldsData;

/**
 * Интерактор
 * @author Sergei Lyashko
 */
class MassCalculation {
    
    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    // сообщение об ошибке
    private String message;
    
    private FieldsData data;
    private Detail newDetail;
    
    
    public MassCalculation(ValueReceiver valueReceiver){
        createDetail();
    }
    
    private void createDetail() {
        double width = data.getWidth();
        double length = data.getLength();
        if(isValidValues(width, length)){
            DetailFactoryImpl factory = new DetailFactoryImpl();
            newDetail = factory.createDetail();
        }        
    }
    
    public double getMass(){
        double mass = newDetail.calculationMass();
        return mass;
    }
    
    // проверка на переполнение
    private boolean isValidValues(double widthNum, double lengthNum){
        if(isValidNumber(widthNum) && isValidNumber(lengthNum)){
            double checkNum = MAX_NUMBER / lengthNum;
            if(checkNum > widthNum){
                this.message = "ошибка! слишком большое число!";
                return false;
            }
            return true;
        }
        return false;
    }
    
    
    private boolean isValidNumber(double number){
        if(number > MAX_NUMBER){
                this.message = "ошибка! слишком большое число!";
                return false;
        }
        if(number < 0){
                this.message = "ошибка! отрицательное число!";
                return false;
        }
        return true;
    }    

    public String getErrorMessage() {
        return message;
    }
}
