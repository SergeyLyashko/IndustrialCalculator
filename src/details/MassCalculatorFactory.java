/*
 * Copyright 2020 Korvin.
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
package details;

import calcmassmodel.AbstractMassCalculator;
import calcmassmodel.AbstractCalculatorFactory;

public class MassCalculatorFactory extends AbstractCalculatorFactory {
    
    @Override
    protected AbstractMassCalculator createMassCalculator(String assortment, String type) {
        switch(assortment){
            case "Лист":
                            return selectedType(type);
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                            return new AssortmentMassCalculator();
            case "Другое":               
                            return selectedType(type);
            default:
                    System.out.println("tets null factory");
                    return null;
        }
    }
    
    private AbstractMassCalculator selectedType(String type){
        switch (type){
            case "рифленая(ромб)":
                            return new RiffledSteelSheetMassCalculator();
            case "тонколистовая":
            case "толстолистовая":
                            return new SheetSteelMassCalculator();
            case "Круг":
                            return new CircleSteelMassCalculator();
            case "Квадрат":
                            return new SquareSteelMassCalculator();
            case "Резиновая пластина":
                            return new SheetRubberMassCalculator();
            default:
                    System.out.println("tets null factory");
                    return null;
        }
    }
}
