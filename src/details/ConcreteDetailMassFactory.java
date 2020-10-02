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

import calcmassmodel.AbstractDetailMass;
import calcmassmodel.AbstractDetailMassFactory;

public class ConcreteDetailMassFactory extends AbstractDetailMassFactory {
    
    @Override
    public AbstractDetailMass createDetailMass(String assortment, String type) {
        switch(assortment){
            case "����":
                            return selectedType(type);
            case "�������":
            case "������":
            case "�������":
                            return new AssortmentDetailMass();
            case "������":               
                            return selectedType(type);
            default:
                    System.out.println("tets null factory");
                    return null;
        }
    }
    
    private AbstractDetailMass selectedType(String type){
        switch (type){
            case "��������(����)":
                            return new RiffledSteelSheet();
            case "�������������":
            case "��������������":
                            return new SheetSteelDetailMass();
            case "����":
                            return new CircleSteelDetailMass();
            case "�������":
                            return new SquareSteelDetailMass();
            case "��������� ��������":
                            return new SheetRubberDetailMass();
            default:
                    System.out.println("tets null factory");
                    return null;
        }
    }
}
