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
 * Резиновые пластины ТМКЩ ГОСТ 7338-90
 * @author Korvin
 */
public class RubberSheet implements Massable {
    
    private final String detailLength;      // длина детали
    private double areaCut;                 //площадь сечения детали
    
    /**
     * Конструктор детали Резиновая пластина
     * @param detailDepth значение номера профиля из БД - толщина детали
     * @param detailLength длина детали
     * @param detailWidth ширина детали
     */
    public RubberSheet(String detailDepth, String detailLength, String detailWidth){        
        this.detailLength = detailLength;
        setAreaCut(detailDepth, detailWidth);
    }
    // сечение детали
    private void setAreaCut(String detailDepth, String detailWidth){
        this.areaCut = Massable.getValueFromString(detailDepth) * Massable.getValueFromString(detailWidth);
    }
    private double getAreaCut(){
        return areaCut;
    }
    private double getLength(){
        return Massable.getValueFromString(detailLength);
    }
    /**
     *  Вычисление массы детали
     * @return массу детали
     */
    @Override
    public double getMass(){
        return Massable.DENSITY_RUBBER * getLength() * getAreaCut();
    }
}
