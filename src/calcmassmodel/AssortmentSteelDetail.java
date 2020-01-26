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
 * Швеллеры стальные горячекатаные ГОСТ 8240-97
 * 
 */
public class AssortmentSteelDetail implements Massable {
    
    private final String detailLength;        //длина детали
    private double areaCut;                    //площадь сечения детали
    /**
     * Конструктор для деталей (Швеллер, Уголок, Двутавр)
     * @param profileAssortment
     * @param profileType
     * @param profileNumber
     * @param detailLength Длина детали
     */
    public AssortmentSteelDetail (String profileAssortment, String profileType, String profileNumber, String detailLength){
        this.detailLength = detailLength;
        setAreaCut(profileAssortment, profileType, profileNumber);
    }
    // сечение детали
    private void setAreaCut(String profileAssortment, String profileType, String profileNumber){
        this.areaCut = new QueryBD().getAreaCutBD(profileAssortment, profileType, profileNumber);      
    }
    
    private double getLength(){
        return Massable.getValueFromString(detailLength);
    }
    private double getAreaCut(){
        return areaCut;
    }
    /**
     * Вычисление массы детали
     * @return масса детали
     */
    @Override
    public double getMass(){
        return Massable.DENSITY_STEEL * getLength() * getAreaCut() * 100;
    }
}