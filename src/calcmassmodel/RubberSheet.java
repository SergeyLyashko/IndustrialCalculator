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
public class RubberSheet extends AbstractDetail {
    
    private final String depthDetail;       //толщина детали  
    private final double lengthDetail;      // длина детали
    private double areaCut;                 //площадь сечения детали
    private final double density;           //плотность детали
    private double mass;                    //масса детали
    private final double widthDetail;       //ширина детали
    
    public RubberSheet(String depthDetail, double lengthDetail, double widthDetail){        
        this.depthDetail = depthDetail;
        this.lengthDetail = lengthDetail;
        this.widthDetail = widthDetail;
        density = DENSITY_RUBBER;        
        setAreaCut();
        setMass();
    }
    
    // сечение детали
    @Override
    protected void setAreaCut(){
        areaCut = widthDetail * super.getValueFromString(depthDetail);
    }
    
    // масса детали
    @Override
    protected void setMass(){
        mass = density * lengthDetail * areaCut;
    }
   
    
    @Override
    public double getMass(){
        return mass;
    }
}
