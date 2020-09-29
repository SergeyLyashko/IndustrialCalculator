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

import calcmassmodel.ModelDispatcher;
import calcmassmodel.InputService;
import calcmassmodel.OutputService;
import calcmassview.ViewDispatcher;

/**
 * Контроллер
 * Создание модели и представления
 * @author Sergei Lyashko
 */
public class CalculatorControllerImpl implements CalculatorController {

    private final ModelDispatcher model;
    private InputService inputDataService;
    
    CalculatorControllerImpl(ModelDispatcher model) {
        this.model = model;
    }

    @Override
    public OutputService getOutputData() {
        OutputService outputDataService = new SendData();
        model.setOutputData(outputDataService);
        return outputDataService;
    }

    @Override
    public void setInputData(ViewDispatcher viewData) {
        inputDataService = new ReceiveData(viewData);
        model.setInputData(inputDataService);
    }

    @Override
    public void startCalc() {
        model.calcOrderStart();
    }
    
}
