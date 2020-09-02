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

import calcdatabase.DataBaseValueReceiver;


/**
 * 
 * @author Sergei Lyashko
 */
public class CalculatorModelImpl implements CalculatorModel {
    
    private final MassCalculation massCalculation;
    

    public CalculatorModelImpl(DataBaseValueReceiver valueReceiver) {
        
        massCalculation = new MassCalculation(valueReceiver);
    }
    
    @Override
    public double getCalculationResult() {
        return massCalculation.getMass();
    }

    @Override
    public String getError() {
        return massCalculation.getErrorMessage();
    }


    @Override
    public CalculatorInputData receiveData() {
        return new CalculatorInputDataImpl();
    }
}