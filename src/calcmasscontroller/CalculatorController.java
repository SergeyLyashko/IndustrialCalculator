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
import calcdatabase.DataBaseInterface;
import calcmassmodel.CalculatorModel;
import calcmassmodel.CalculatorModelInterface;
import calcmassview.CalculatorView;

/**
 * Контроллер
 * Создание модели и представления
 * @author Sergei Lyashko
 */
public class CalculatorController implements CalculatorControllerInterface {
    
    private static CalculatorModelInterface model;
    private static CalculatorController controller;    
    private static DataBaseInterface dataBase;
    private static CalculatorView calculatorView;
    
    private CalculatorController(CalculatorModelInterface model){
        CalculatorController.model = model;
    }
    
    /**
     * Создание модели, контроллера, прдставления
     */
    public static void start(){
        dataBase = new DataBase(); 
        model = new CalculatorModel();
        controller = new CalculatorController(model);
        calculatorView = new CalculatorView(model, controller);
        loadContent();
    }
    
    private static void loadContent(){
        calculatorView.setDataBase(dataBase);
        calculatorView.losdMenu();
    }
    
    @Override
    public void setParametersFromView(String assortment, String type, String number, String length, String width) {
        model.setDataBase(dataBase);
        model.createDetail(assortment, type, number, length, width);        
    }
}
