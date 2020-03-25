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

import static java.lang.Math.PI;

/**
 * ����� ������������� ������� ���� 2590-88
 * @author Sergei Lyashko
 */
class SteelCircleDetail extends AbstractDetail {

    private final String detailLength;      //����� ������
    private double areaCut;                 //������� ������� ������
        
    public SteelCircleDetail(String profileAssortment, String profileType, String profileNumber, String length, String width) {
        super(profileAssortment, profileType, profileNumber, length, width);
        this.detailLength = length;
        setAreaCut(profileNumber);
    }
    
    // ������� ������� ������
    private void setAreaCut(String detailDiametr){
        double value = getValueFromString(detailDiametr);
        areaCut = (value * value) / 4 * PI;
    }
    
    @Override
    public double getMass() {
        return DENSITY_STEEL * getValueFromString(detailLength) * areaCut;
    }
}
