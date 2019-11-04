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
 * Ўвеллеры стальные гор€чекатаные √ќ—“ 8240-97
 * 
 * @author Sergei Lyashko
 */
public class AssortmentSteelDetail extends AbstractDetail {
    
    private final String nameDetail;   //наименование профил€ детали
    private final double lengthDetail;        //длина детали
    private double areaCut;                    //площадь сечени€ детали
    private final double density;             //плотность детали
    private double mass;                    //масса детали    
    
    public AssortmentSteelDetail (String nameDetail, double lengthDetail){
        this.nameDetail = nameDetail;
        this.lengthDetail = lengthDetail;
        density = DENSITY_STEEL;        
        setAreaCut();
        setMass();
    }
    
    // сечение детали
    @Override
    protected void setAreaCut(){
        areaCut = super.getValueFromString(AreaCutData.getAreaCut(nameDetail)) * 100;
    }
    
    // масса детали
    @Override
    protected void setMass(){
        mass = density * lengthDetail * areaCut;
    }
   
    //метод интерфейса, получение массы детали из модели
    @Override
    public double getMass(){
        return mass;
    }
}