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
package calcmassmodel;

public class DetailMassCalculationFactory extends DetailFactory {
    
    @Override
    public Detail createDetail(String assortment, String type) {
        switch(assortment){
            case "Лист":
                            //return new Sheet(assortment, type, number);
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                            return new AssortmentDetail();
            case "Другое":               
                            //return selectedType(type, length, valueOfArea);
            default:
                    System.out.println("tets null factory");
                    return null;
        }
    }
    
    /*
    private double selectedType(String type, String length, double valueOfArea){
        switch (type){
            case "рифленая(ромб)":
                            return valueOfArea / 1000000 * valueFromDB;
            case "тонколистовая":
            case "толстолистовая":
                            return DENSITY_STEEL * valueOfArea * valueFromDB;
            case "Круг":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB) / 4 * PI;
            case "Квадрат":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB);
            case "Резиновая пластина":
                            return DENSITY_RUBBER * valueOfArea * valueFromDB;  
        }
        return 0;
    }*/
    
}
