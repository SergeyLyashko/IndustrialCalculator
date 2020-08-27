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

import calcmassmodel.CalculatorModelImpl;
import calcmassview.CalculatorView;
import calcmassmodel.CalculatorModel;
import calcmassview.ViewObserver;
import calcmassview.base.Detail;

/**
 * Контроллер
 * Создание модели и представления
 * @author Sergei Lyashko
 */
public class CalculatorControllerImpl implements CalculatorController {
    
    private static CalculatorModel model;
    private static CalculatorController controller;    
    private static ViewObserver view;
    
    private CalculatorControllerImpl(CalculatorModel model){
        CalculatorControllerImpl.model = model;
    }
    
    /**
     * запуск приложения
     */
    public static void startApp(){
        model = new CalculatorModelImpl();
        controller = new CalculatorControllerImpl(model);
        view = new CalculatorView(controller);
    }

    @Override
    public void setDetail(Detail detail) {
        model.setDetail(detail);
    }
    
    @Override
    public void setResult(double value){
        view.massUpdate(value);
    }
}
