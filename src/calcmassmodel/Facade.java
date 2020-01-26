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
 * �����, ������� ������� �� ��������� ��������� �� View
 * @author Sergei Lyashko
 */
public class Facade {
    
    private static final Facade INSTANCE = new Facade();
    private Massable detail;
    /**
     * �������� ������������� ���������� ������
     * @return singleton
     */
    public static Facade getInstance(){
        return INSTANCE;
    }
    private void setDetail(Massable detail){
        this.detail = detail;
    }
    /**
     * ���������� ����� ������ �� ������� �� View
     * @return ����� ������
     */
    public double getResult(){
        return detail.getMass();
    }
    /**
     * �������� ������ � ����������� �� View
     * @param profileAssortment ������������ ����������
     * @param profileType ������������ ���� �����
     * @param profileNumber ������������ ������
     * @param length ����� ������
     * @param width ������ ������ (����� ��� ����)
     */
    public void createDetail(String profileAssortment, String profileType, String profileNumber, String length, String width){
        Massable currentDetail = new FactoryDetail()
                .getCurrentDetail(profileAssortment, profileType, profileNumber, length, width);
        setDetail(currentDetail);
    }
}
