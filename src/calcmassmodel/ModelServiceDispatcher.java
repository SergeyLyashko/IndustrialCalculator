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
 * 
 * @author Sergei Lyashko
 */
public class ModelServiceDispatcher implements ModelService {
    
    private ValueReceiveService areaReceiver;
    private InputDataService inputService;
    private MassGenerator massGenerator;
    private AbstractCalculatorFactory massFactory;

    @Override
    public void acceptData(InputDataService inputService) {
        this.inputService = inputService;
    }   
    
    @Override
    public void acceptDataFromDataBase(ValueReceiveService areaReceiver) {
        this.areaReceiver = areaReceiver;
    }

    @Override
    public void acceptRealization(AbstractCalculatorFactory massFactory) {
        this.massFactory = massFactory;
    }

    @Override
    public double calculationResult() {
        this.massGenerator = new MassGenerator(inputService, areaReceiver);
        massGenerator.orderMass(massFactory); 
        return massGenerator.getMass();
    }

    @Override
    public String getErrorMessage() {
        return massGenerator.getError();
    }

    @Override
    public String getServiceMessage() {
        return massGenerator.getServiceMessage();
    }
}
