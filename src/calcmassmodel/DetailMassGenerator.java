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

import details.ConcreteDetailMassFactory;


/**
 * Интерактор
 * @author Sergei Lyashko
 */
class DetailMassGenerator {
    
    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    // сообщение об ошибке
    
    private final DetailValueReceiver areaReceiver;
    private final InputService inputService;
    private final OutputService outputDataService;
    
    public DetailMassGenerator(DetailValueReceiver areaReceiver, InputService inputDataService, OutputService outputDataService){
        this.areaReceiver = areaReceiver;
        this.inputService = inputDataService;
        this.outputDataService = outputDataService;
    }
    
    void calculationOrder() {
        if(isValidInputData()){
            AbstractDetailMassFactory detailMassFactory = new ConcreteDetailMassFactory();
            AbstractDetailMass order = detailMassFactory.order(inputService, areaReceiver);
            //test
            System.out.println("mass: "+order.getMass());
        }else{
            //test
            System.out.println("mass not");
        } 
    }
    
    private boolean isValidInputData(){
        double width = inputService.getWidth();
        double length = inputService.getLength();
        return isValidFieldsValues(width, length);
    }
    
    // проверка на переполнение
    private boolean isValidFieldsValues(double widthNum, double lengthNum){
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
    
}
