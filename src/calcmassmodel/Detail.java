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
 *
 * @author Sergei Lyashko
 */
public class Detail {
    
    private AbstractDetail detail;
    
    private final String assortment;
    private final String type;
    private final String name;
    private final double length;
    private final double width;
    
    private final double result;
    
    // ����������� ��-���������
    public Detail(){
        assortment = null;
        type = null;
        name = null;
        length = 0;
        width = 0;    
        result = 0;
    }
    
    // �����������
    public Detail(String assortment, String type, String name, double length, double width){
        this.assortment = assortment;
        this.type = type;
        this.name = name;
        this.length = length;
        this.width = width;
        result = createDetail();
    }
    
    // ����������� ���������� ���� ������
    private double createDetail(){
        switch(assortment){
            case "����":                        
                    return selectedProfile(type);
            case "�������":
            case "������":
            case "�������":
                    detail = new AssortmentSteelDetail(name, length);
                    return detail.getMass();
            case "������":                
                    return selectedProfile(type);
            default:
                    return 0;
        }
    }
    
    private double selectedProfile(String type){
        switch (type){
            case "��������(����)":
                    detail = new RiffledSteelPlate(name, length, width);
                    break;
            case "�������������":
            case "��������������":
                    detail = new SteelPlate(name, length, width);
                    break;
            case "����":
                    detail = new CircleSteelDetail(name, length);
                    break;
            case "�������":
                    detail = new SquareSteelDetail(name, length);
                    break;
            case "���������_��������":
                    detail = new RubberSheet(name, length, width);
                    break;
        }
        return detail.getMass();
    }
    
    //����� ����������, ��������� ����� ������ �� ������
    public double getResult(){
        return result;
    }
}