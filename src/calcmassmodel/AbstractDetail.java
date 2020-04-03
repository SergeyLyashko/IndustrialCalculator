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

/**
 * Абстрактный класс детали
 * @author Korvin
 */
public abstract class AbstractDetail {
    
    // Плотность стали марки Ст3 7,85e-6 кг/мм3 = 7850 кг/м3
    final double DENSITY_STEEL = 7.85e-6;
    // Плотность резины ГОСТ 7338-90 лист ТМКЩ 1.25e-7 кг/мм3 = 125 кг/м3
    final double DENSITY_RUBBER = 1.25e-6;
    
    private double length;
    private double width;
    private double dataBaseValue;
    private String message;
    
    /**
     * Абстрактная деталь
     * @param profileAssortment наименование сортамента
     * @param profileType тип выбранного сортамента
     * @param profileNumber номер профиля детали
     * @param length длина детали
     * @param width ширина детали (если задана)
     */
    public AbstractDetail(String profileAssortment, String profileType, String profileNumber, String length, String width){        
    }
    
    /**
     * значение из базы данных
     * @param profileAssortment наименование сортамента
     * @param profileType тип сортамента
     * @param profileNumber номер профиля
     */
    protected final void setValueFromDataBase(String profileAssortment, String profileType, String profileNumber){
        this.dataBaseValue = new DataBaseQuery().getDataBaseValue(profileAssortment, profileType, profileNumber);
    }
    
    /**
     * Запрос значения из базы данных
     * @return значение из базы данных
     */
    protected final double getValueFromDataBase(){
        return dataBaseValue;
    }
    
    /**
     * Установка значения длины детали
     * @param length строковое значение длины
     */
    protected final void setLength(String length){
        try{
            this.length = getValueFromField(length);
        }catch(NumberFormatException e){
            this.message = "ошибка! неправильно задана длина детали";
        }
    }
    
    /**
     * Установка значения ширины детали
     * @param width строковое знание ширины
     */
    protected final void setWidth(String width){
        try{
            this.width = getValueFromField(width);
        }catch(NumberFormatException e){
            this.message = "ошибка! неправильно задана ширина детали";
        }
    }
    
    /**
     * преобразование строки в число
     * @param valueStr
     * @return число с плавающей точкой
     */
    private double getValueFromField(String valueStr) throws NumberFormatException {
        return Double.parseDouble(valueStr);
    }
    
    /**
     * Запрос длины детали
     * @return длина детали
     */
    protected final double getLength(){
        return length;
    }
    
    /**
     * Запрос ширины детали
     * @return ширина детали
     */
    protected final double getWidth(){
        return width;
    }
    
    /**
     * Возвращает сервисное сообщение об ошибке
     * @return строковое представление сообщения
     */
    protected final String getErrorMessage() {
        return message;
    } 
    
    /**
     * получение массы детали
     * @return значение массы детали
     */
    abstract double getMass();    
}
