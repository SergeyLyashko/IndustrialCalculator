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
 * ����� ������������� ���������� ���� 8240-97
 * @author Sergei Lyashko
 */
public final class SquareSteelDetail extends AbstractDetail {
    
    private final String sideDetail;          //������� ������
    private final double lengthDetail;        //����� ������
    private final double density;             //��������� ������
    private double areaCut;             //������� ������� ������
    private double mass;                //����� ������
    
    public SquareSteelDetail(String sideDetail, double lengthDetail){        
        this.sideDetail = sideDetail;
        this.lengthDetail = lengthDetail;
        density = DENSITY_STEEL;
        setAreaCut();
        setMass();
    }
    
    // ������� ������
    @Override
    protected void setAreaCut(){
        double value = super.getValueFromString(sideDetail);
        areaCut = value * value;
    }
    
    // ����� ������
    @Override
    protected void setMass(){
        mass = density * lengthDetail * areaCut;
    }
    
    //����� ����������, ��������� ����� ������ �� ������
    @Override
    public double getMass(){
        return mass;
    }
}
