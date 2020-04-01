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
 * Сталь листовая рифленая (ромбическая) ГОСТ 8568-77
 * @author Sergei Lyashko
 */
class SteelRiffledPlate extends AbstractDetail {

    private final String detailLength;        //длина детали
    private final String detailWidth;         //ширина детали
    private double valueFromDataBase;         // значения для расчета массы из БД
        
    public SteelRiffledPlate(String profileAssortment, String profileType, String profileNumber, String length, String width) {
        super(profileAssortment, profileType, profileNumber, length, width);
        this.detailLength = length;
        this.detailWidth = width;
        setValueFromDataBase(profileAssortment, profileType, profileNumber);
    }
    
    // значение из базы данных
    private void setValueFromDataBase(String profileAssortment, String profileType, String profileNumber){
        this.valueFromDataBase = new DataBaseQuery().getDataBaseValue(profileAssortment, profileType, profileNumber);      
    }

    @Override
    public double getMass() {
        return (getValueFromString(detailLength) * getValueFromString(detailWidth) / 1000000) * valueFromDataBase;
    }
}
