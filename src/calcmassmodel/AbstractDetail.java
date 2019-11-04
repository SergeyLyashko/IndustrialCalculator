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
 * ����������� ����� ������� ��� ������������ � �������� ����� ������� ��� ������ ����������
 * @author Sergey Lyashko
 */
public abstract class AbstractDetail {
    
    // ��������� ����� ����� ��3 7,85e-6 ��/��3 = 7850 ��/�3
    protected static final double DENSITY_STEEL = 7.85e-6;
    // ��������� ������ ���� 7338-90 ���� ���� 1.25e-7 ��/��3 = 125 ��/�3
    protected static final double DENSITY_RUBBER = 1.25e-6;   
    
    // �������������� �������� �� ��
    protected double getValueFromString(String valueStr) {
        try{
            return Double.parseDouble(valueStr);
        }catch(NumberFormatException | NullPointerException e){
            System.err.println("������ �������� � ��: " + e);
            return 0;
        }
    }
    
    protected abstract void setMass();
    protected abstract void setAreaCut();
    protected abstract double getMass();
}