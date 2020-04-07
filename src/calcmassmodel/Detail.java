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
package calcmassmodel;

import static java.lang.Math.PI;

/**
 * Получение массы детали
 * @author Sergei Lyashko
 */
class Detail {
    
    // Плотность стали марки Ст3 7,85e-6 кг/мм3 = 7850 кг/м3
    private static final double DENSITY_STEEL = 7.85e-6;
    // Плотность резины ГОСТ 7338-90 лист ТМКЩ 1.25e-7 кг/мм3 = 125 кг/м3
    private static final double DENSITY_RUBBER = 1.25e-6;
    // сообщение об ошибке
    private String message;
    // значение из Базы Данных
    private double valueFromDB;
    
    /**
     * значение из базы данных
     * @param profileAssortment наименование сортамента
     * @param profileType тип сортамента
     * @param profileNumber номер профиля
     * @return 
     */
    
    private double getValueFromDataBase(String profileAssortment, String profileType, String profileNumber){
         return new DataBaseQuery().getDataBaseValue(profileAssortment, profileType, profileNumber);
    }
    
    /**
     * Установка значения длины детали
     * @param value
     * @return 
     */
    private double getValue(String value) {
        try{
            return Double.parseDouble(value);
        }catch(NumberFormatException e){
            this.message = "ошибка! параметр задан неверно!";
        }
        return 0;
    }
    
    /**
     * Создание детали по параметрам
     * @param assortment наименование сортамента
     * @param type тип сортамента
     * @param number номер профиля
     * @param length длина детали
     * @param width ширина детали (при наличии)
     */
    public Massable setParametrs(String assortment, String type, String number, String length, String width) {
        this.valueFromDB = getValueFromDataBase(assortment, type, number);
        
        switch(assortment){
            case "Лист":
                            return selectedType(type, length, width);
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                            return () ->
                                    DENSITY_STEEL * valueFromDB * getValue(length) * 100;
            case "Другое":               
                            return selectedType(type, length, width);
        }
        return null;
    }
    
    private Massable selectedType(String type, String length, String width){
        switch (type){
            case "рифленая(ромб)":
                            return () ->
                                    (getValue(length) * getValue(width) / 1000000) * valueFromDB;
            case "тонколистовая":
            case "толстолистовая":
                            return () ->
                                    DENSITY_STEEL * getValue(length) * getValue(width) * valueFromDB;
            case "Круг":
                            return () ->
                                    DENSITY_STEEL * getValue(length) * (valueFromDB * valueFromDB) / 4 * PI;
            case "Квадрат":
                            return () -> 
                                DENSITY_STEEL * getValue(length) * valueFromDB * valueFromDB;
            case "Резиновая пластина":
                            return () -> 
                                    DENSITY_RUBBER * getValue(length) * getValue(width) * valueFromDB;  
        }
        return null;
    }

    public ErrorMessageInterface message() {
        return () -> message;
    }
}
