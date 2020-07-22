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

import calcdatabase.DataBaseInterface;
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
    // максимально возможное значение введенного или вычисляемого числа
    private final double maxNumber = Double.MAX_VALUE;
    // сообщение об ошибке
    private String message;
    // значение из Базы Данных
    private double valueFromDB;
    // параметры детали
    private final String assortment, type, number, length;
    // площадь детали
    private double area;
    
    public Detail(String assortment, String type, String number, String length, String width){
        this.assortment = assortment;
        this.type = type;
        this.number = number;
        this.length = length;
        calculationArea(width, length);
    }

    Detail(String assortment, String type, String number, String area) {
        this.assortment = assortment;
        this.type = type;
        this.number = number;
        this.length = null;
        calculationArea(area);
    }
    
    /**
     * Запрос в базу данных
     * @param dataBase интерфейс базы данных
     */
    public void executeQuery(DataBaseInterface dataBase) {
        this.valueFromDB = dataBase.query(assortment, type, number);
        //this.valueFromDB = dataBase.receiveValue();
    }
    
    /**
     * Получение числового значения
     * @param value Строковое представление значения
     * @return числовое представление 
     */
    private double getValueOf(String value) {
        try{
            double valueNum = Double.parseDouble(value);
            if(valueNum > maxNumber){
                this.message = "ошибка! слишком большое число!";
            }else if(valueNum < 0){
                this.message = "ошибка! отрицательное число!";
            }else{
                return valueNum;
            }
        }catch(NumberFormatException e){
            this.message = "ошибка! введенное значение не является числом!";
        }
        return 0;
    }
    
    // проверка на переполнение
    private boolean isNotBigNumber(double widthNum, double lengthNum){
        double checkNum = maxNumber / lengthNum;
        return checkNum > widthNum;
    }
    
    // вычисление площади
    private void calculationArea(String width, String length){
        if(width != null){
            double widthNum = getValueOf(width);
            double lengthNum = getValueOf(length);
            if(isNotBigNumber(widthNum, lengthNum)){
                this.area = widthNum * lengthNum;
            }else{
                this.message = "ошибка! слишком большое число!";
            }
        }
    }
    
    // числовое значение заданной в конструкторе площади
    private void calculationArea(String area){
        this.area = getValueOf(area);
    }
    
    
    /**
     * Вычисление массы детали
     */
    @Override
    public double calculationMass() {
        switch(assortment){
            case "Лист":
                            return selectedType(type, length);
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                            return DENSITY_STEEL * valueFromDB * getValueOf(length) * 100;
            case "Другое":               
                            return selectedType(type, length);
        }
        return 0;
    }
    
    private double selectedType(String type, String length){
        switch (type){
            case "рифленая(ромб)":
                            return area / 1000000 * valueFromDB;
            case "тонколистовая":
            case "толстолистовая":
                            return DENSITY_STEEL * area * valueFromDB;
            case "Круг":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB) / 4 * PI;
            case "Квадрат":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB);
            case "Резиновая пластина":
                            return DENSITY_RUBBER * area * valueFromDB;  
        }
        return 0;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }    
}
