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


public class DetailFactoryImpl implements DetailFactory {
    
    private ValueReceiver receiver;
    private CalculatorInputDataImpl data;
    
    private double getDataBaseValue() {
        String assortment = data.getAssortment();
        String type = data.getType();
        String number = data.getNumber();
        return receiver.getValue(assortment, type, number);
    }

    @Override
    public Detail createDetail() {
        return newDetail();
    }
    
    private Detail newDetail(){
        String assortment = data.getAssortment();
        switch(assortment){
            case "Лист":
                            //return new Sheet(assortment, type, number);
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                            double area = getDataBaseValue();
                            double length = data.getLength();
                            return new AssortmentDetail(area, length);
            case "Другое":               
                            //return selectedType(type, length, valueOfArea);
        }
        return null;
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
