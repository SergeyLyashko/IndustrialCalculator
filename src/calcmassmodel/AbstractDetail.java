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
 * абстрактный класс деталей для наследования и создания новых классов для разных материалов
 * @author Sergey Lyashko
 */
public abstract class AbstractDetail {
    
    // Плотность стали марки Ст3 7,85e-6 кг/мм3 = 7850 кг/м3
    protected static final double DENSITY_STEEL = 7.85e-6;
    // Плотность резины ГОСТ 7338-90 лист ТМКЩ 1.25e-7 кг/мм3 = 125 кг/м3
    protected static final double DENSITY_RUBBER = 1.25e-6;   
    
    // преобразование значений из БД
    protected double getValueFromString(String valueStr) {
        try{
            return Double.parseDouble(valueStr);
        }catch(NumberFormatException | NullPointerException e){
            System.err.println("Ошибка значения в БД: " + e);
            return 0;
        }
    }
    
    protected abstract void setMass();
    protected abstract void setAreaCut();
    protected abstract double getMass();
}