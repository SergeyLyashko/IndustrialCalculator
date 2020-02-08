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
 * ���� �������� �� ���� 
 * @author Sergei Lyashko
 */
public class SteelPlate implements Massable {
    
    private final String detailLength;            // ����� ������
    private double areaCut;                    //������� ������� ������
    
    /**
     * ����������� ������ �������� ����
     * @param detailDepth �������� ������ ������� �� �� - ������� �����
     * @param detailLength ����� �����
     * @param detailWidth ������ �����
     */
    public SteelPlate(String detailDepth, String detailLength, String detailWidth){        
        this.detailLength = detailLength;
        setAreaCut(detailDepth, detailWidth);
    }
    // ������� ������
    private void setAreaCut(String detailDepth, String detailWidth){
        this.areaCut = Massable.getValueFromString(detailDepth) * Massable.getValueFromString(detailWidth);
    }
    private double getLength(){
        return Massable.getValueFromString(detailLength);
    }
    private double getAreaCut(){
        return areaCut;
    }
    /**
     * ���������� ����� ������
     * @return ����� ������
     */
    @Override
    public double getMass(){
        return Massable.DENSITY_STEEL * getLength() * getAreaCut();
    }
}
