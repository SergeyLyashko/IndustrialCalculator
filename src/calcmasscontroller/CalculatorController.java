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

import calcmassmodel.CalculatorModel;
import calcmassview.CalculatorView;
import calcmassview.base.FieldsData;

/**
 * Контроллер
 * Создание модели и представления
 * @author Sergei Lyashko
 */
public class CalculatorController {
    
    private final CalculatorModel model;
    private final CalculatorView view;

    CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }
    
    // ввод данных
    public void input(FieldsData data){
        
    }
    
    public void output(){
        
    }
}
