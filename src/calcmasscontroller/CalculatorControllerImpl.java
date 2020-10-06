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

import calcmassmodel.InputDataService;
import calcmassmodel.ModelService;

/**
 * Контроллер
 * Создание модели и представления
 * @author Sergei Lyashko
 */
public class CalculatorControllerImpl implements ControllerService {

    private final ModelService modelService;
    
    CalculatorControllerImpl(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public void acceptData(ViewDataReceiveService viewService) {
        InputDataService dataService = new InputServiceImpl(viewService);
        modelService.acceptData(dataService);
    }
    
    @Override
    public double getCalculationResult() {
        return modelService.calculationResult();
    }

    @Override
    public String getErrorMessage() {
        return modelService.getErrorMessage();
    }

    @Override
    public String getServiceMessage() {
        return modelService.getServiceMessage();
    }
}
