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

import static java.lang.Math.PI;

/**
 * Вычисление массы детали
 * @author Sergei Lyashko
 */
class Detail implements Massable, ErrorMessageInterface {
    
    // Плотность стали марки Ст3 7,85e-6 кг/мм3 = 7850 кг/м3
    private static final double DENSITY_STEEL = 7.85e-6;
    // Плотность резины ГОСТ 7338-90 лист ТМКЩ 1.25e-7 кг/мм3 = 125 кг/м3
    private static final double DENSITY_RUBBER = 1.25e-6;
    // сообщение об ошибке
    private String message;
    // значение из Базы Данных
    private double valueFromDB;
    // параметры детали
    private final String assortment, type, number, length, width;
    
    public Detail(String assortment, String type, String number, String length, String width){
        //this.valueFromDB = getValueFromDataBase(assortment, type, number);
        this.assortment = assortment;
        this.type = type;
        this.number = number;
        this.length = length;
        this.width = width;
    }
    
    public void receiveData(DataBaseQuery query){
        this.valueFromDB = query.getDataBaseValue(assortment, type, number);
    }
    
    /**
     * Получение значения из базы данных, в зависимости от параметров
     * @param profileAssortment наименование сортамента
     * @param profileType тип сортамента
     * @param profileNumber номер профиля
     * @return число с плавающей точкой
     */
    /*
    private double getValueFromDataBase(String profileAssortment, String profileType, String profileNumber){
         return new DataBaseQuery().getDataBaseValue(profileAssortment, profileType, profileNumber);
    }
    */
    /**
     * Получение числового значения
     * @param value Строковое представление значения
     * @return числовое представление 
     */
    private double getValueOf(String value) {
        try{
            return Double.parseDouble(value);
        }catch(NumberFormatException e){
            this.message = "ошибка! параметр задан неверно!";
        }
        return 0;
    }
    
    /**
     * Вычисление массы детали
     */
    @Override
    public double calculationMass() {
        switch(assortment){
            case "Лист":
                            return selectedType(type, length, width);
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                            return DENSITY_STEEL * valueFromDB * getValueOf(length) * 100;
            case "Другое":               
                            return selectedType(type, length, width);
        }
        return 0;
    }
    
    private double selectedType(String type, String length, String width){
        switch (type){
            case "рифленая(ромб)":
                            return getValueOf(length) * getValueOf(width) / 1000000 * valueFromDB;
            case "тонколистовая":
            case "толстолистовая":
                            return DENSITY_STEEL * getValueOf(length) * getValueOf(width) * valueFromDB;
            case "Круг":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB) / 4 * PI;
            case "Квадрат":
                            return DENSITY_STEEL * getValueOf(length) * valueFromDB * valueFromDB;
            case "Резиновая пластина":
                            return DENSITY_RUBBER * getValueOf(length) * getValueOf(width) * valueFromDB;  
        }
        return 0;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
