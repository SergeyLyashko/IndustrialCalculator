/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
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

import calcmassmodel.CalculatorModel;
import calcmassmodel.CalculatorModelInterface;
import calcmassview.CalculatorView;
import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.AbstractMenuBox;

/**
 *
 * @author Sergei Lyashko
 */
public class CalculatorController implements CalculatorControllerInterface {
    
    private static CalculatorModelInterface model;
    
    public static void start(){
        model = new CalculatorModel();
        new CalculatorController(model);
    }
    
    private CalculatorController(CalculatorModelInterface model){
        // �������� View
        new CalculatorView(model, this);
    }
    
    @Override
    public void setParametrs(AbstractMenuBox baseMenuBox, AbstractMenuBox typeProfileMenuBox, AbstractMenuBox numberProfileMenuBox,
            AbstractField lengthField, AbstractField widthField) {
        model.setParametrs(baseMenuBox, typeProfileMenuBox, numberProfileMenuBox, lengthField, widthField);
    }
}
