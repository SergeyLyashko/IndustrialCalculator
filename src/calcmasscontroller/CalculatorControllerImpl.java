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
package calcmasscontroller;

import calcdatabase.DataBase;
import calcmassmodel.CalculatorModelImpl;
import calcmassview.CalculatorView;
import calcmassmodel.CalculatorModel;

/**
 * Контроллер
 * Создание модели и представления
 * @author Sergei Lyashko
 */
public class CalculatorControllerImpl implements CalculatorController {
    
    private static CalculatorModel model;
    private static CalculatorControllerImpl controller;    
    
    private CalculatorControllerImpl(CalculatorModel model){
        CalculatorControllerImpl.model = model;
    }
    
    /**
     * запуск приложения
     */
    public static void startApp(){
        DataBase dataBase = new DataBase(); 
        model = new CalculatorModelImpl(dataBase);
        controller = new CalculatorControllerImpl(model);
        new CalculatorView(controller);
    }
    
    @Override
    public void setFieldsValue(String assortment, String type, String number, String length, String width) {
        model.createDetail(assortment, type, number, length, width);        
    }
        
    @Override
    public void setFieldsValue(String assortment, String type, String number, String area) {
        model.createDetail(assortment, type, number, area);        
    }
}
