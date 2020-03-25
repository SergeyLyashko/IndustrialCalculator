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
 * Лист стальной по ГОСТ 
 * @author Sergei Lyashko
 */
class SteelSheet extends AbstractDetail {

    private final String detailLength;         // длина детали
    private double areaCut;                    //площадь сечения детали
        
    public SteelSheet(String profileAssortment, String profileType, String profileNumber, String length, String width) {
        super(profileAssortment, profileType, profileNumber, length, width);
        this.detailLength = length;
        setAreaCut(profileNumber, width);
    }

    // Площадь сечения детали
    private void setAreaCut(String detailDepth, String detailWidth){
        this.areaCut = getValueFromString(detailDepth) * getValueFromString(detailWidth);
    }
        
    @Override
    public double getMass() {
        return DENSITY_STEEL * getValueFromString(detailLength) * areaCut;
    }
}
