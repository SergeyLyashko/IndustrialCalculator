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
class DetailFactory extends AbstractDetailFactory {

    /**
     * �������� �������(������) ����������
     * @param profileAssortment
     * @param profileType
     * @param profileNumber
     * @param length
     * @param width
     */
    @Override
    protected AbstractDetail createDetail(String profileAssortment, String profileType, String profileNumber, String length, String width) {
        switch(profileAssortment){
            case "����":
                    return selectedProfile(profileAssortment, profileType, profileNumber, length, width);
            case "�������":
            case "������":
            case "�������":
                    return new SteelAssortmentDetail(profileAssortment, profileType, profileNumber, length, width);
            case "������":               
                    return selectedProfile(profileAssortment, profileType, profileNumber, length, width);
        }
        return null;
    }
    
    private AbstractDetail selectedProfile(String profileAssortment, String profileType, String profileNumber, String length, String width){
        switch (profileType){
            case "��������(����)":
                            return new SteelRiffledPlate(profileAssortment, profileType, profileNumber, length, width);
            case "�������������":
            case "��������������":
                            return new SteelSheet(profileAssortment, profileType, profileNumber, length, width);
            case "����":
                            return new SteelCircleDetail(profileAssortment, profileType, profileNumber, length, width);      
            case "�������":
                            return new SteelSquareDetail(profileAssortment, profileType, profileNumber, length, width);
            case "��������� ��������":
                            return new RubberSheet(profileAssortment, profileType, profileNumber, length, width);
        }
        return null;
    }
}
