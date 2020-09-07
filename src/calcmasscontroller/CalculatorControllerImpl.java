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

/**
 * Контроллер
 * Создание модели и представления
 * @author Sergei Lyashko
 */
public class CalculatorControllerImpl implements CalculatorController {

    private final ModelDispatcher model;
    
    CalculatorControllerImpl(ModelDispatcher model) {
        this.model = model;
    }

    @Override
    public OutputService getOutputData() {
        OutputService outputDataService = new DataSender();
        model.setOutputData(outputDataService);
        return outputDataService;
    }

    @Override
    public InputService getInputData() {
        InputService inputDataService = new DataReceiver();
        model.setInputData(inputDataService);
        return inputDataService;
    }
    
}
