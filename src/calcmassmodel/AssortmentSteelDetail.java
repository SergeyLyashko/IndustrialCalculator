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
 * �������� �������� ������������� ���� 8240-97
 * 
 */
public class AssortmentSteelDetail implements Massable {
    
    private final String detailLength;        //����� ������
    private double areaCut;                    //������� ������� ������
    /**
     * ����������� ��� ������� (�������, ������, �������)
     * @param detailName ������������ ������ ��� �� ������� ������
     * @param detailLength ����� ������
     */
    public AssortmentSteelDetail (String detailName, String detailLength){
        this.detailLength = detailLength;
        setAreaCut(detailName);
    }
    // ������� ������
    private void setAreaCut(String detailName){
        this.areaCut = Massable.getValueFromString(AreaCutData.getAreaCut(detailName)) * 100;
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