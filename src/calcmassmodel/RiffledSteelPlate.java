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
public final class RiffledSteelPlate implements Massable {
    
    private final String detailLength;        //длина детали
    private final String detailWidth;         //ширина детали
    private double valueFromDataBase;         // значения для расчета массы из БД
    
    /**
     * Конструктор детали (Рифленый стальной лист)
     * @param detailDepth толщина детали
     * @param detailLength длина детали
     * @param detailWidth ширина детали
     */
    public RiffledSteelPlate(String detailDepth, String detailLength, String detailWidth){
        this.detailLength = detailLength;
        this.detailWidth = detailWidth;
        setValueFromDataBase(detailDepth);
    }
    private double getLength(){
        return Massable.getValueFromString(this.detailLength);
    }
    private double getWidth(){
        return Massable.getValueFromString(this.detailWidth);
    }
    private void setValueFromDataBase(String detailDepth){
        this.valueFromDataBase = Massable.getValueFromString(AreaCutData.getAreaCut(detailDepth));
    }
    private double getValueFromDataBase(){
        return valueFromDataBase;
    }
    /**
     * Вычисление массы детали
     * @return масса детали
     */
    @Override
    public double getMass(){
        return (getLength() * getWidth() / 1000000) * getValueFromDataBase();
    }
}
