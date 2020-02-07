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
 * ������� ��� �������� �������� �������
 * @author Sergei Lyashko
 */
public class FactoryDetail {
    
    /**
     * �������� �������(������) ����������
     * @param profileAssortment
     * @param profileType
     * @param profileNumber
     * @param length
     * @param width
     * @return ������ ���������� Massable
     */
    public Massable getCurrentDetail(String profileAssortment, String profileType, String profileNumber, String length, String width){
        Massable detail = null;
        switch(profileAssortment){
            case "����":
                    return selectedProfile(profileAssortment, profileType, profileNumber, length, width);
            case "�������":
            case "������":
            case "�������":
                    return new AssortmentSteelDetail(profileAssortment, profileType, profileNumber, length);
            case "������":               
                    return selectedProfile(profileAssortment, profileType, profileNumber, length, width);
        }
        return detail;
    }
    
    private Massable selectedProfile(String profileAssortment, String profileType, String profileNumber, String length, String width){
        Massable detail = null;
        switch (profileType){
            case "��������(����)":
                            return new RiffledSteelPlate(profileAssortment, profileType, profileNumber, length, width);
            case "�������������":
            case "��������������":
                            return new SteelPlate(profileNumber, length, width);
            case "����":
                            return new CircleSteelDetail(profileNumber, length);                                    
            case "�������":
                            return new SquareSteelDetail(profileNumber, length);                                    
            case "��������� ��������":
                            return new RubberSheet(profileNumber, length, width);
        }
        return detail;
    }    
}
