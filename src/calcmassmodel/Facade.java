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
 * ����� 
 * 
 */
public class Facade implements Massable {
    
    private static final Facade INSTANCE = new Facade();
    
    private String assortment;
    private String type;
    private String name;
    private String length;
    private String width;
    private double mass;
    /**
     * �������� ������������� ���������� ������
     * @return singleton
     */
    public static Facade getInstance(){
        return INSTANCE;
    }  
    
    private void setAssortmentName(String assortment) {
        this.assortment = assortment;
    }
    private String getAssortment(){
        return assortment;
    }    

    private void setTypeName(String type) {
        this.type = type;
    }
    private String getType(){
        return type;
    }

    private void setDetailName(String detailName) {
        this.name = detailName;
    }
    private String getDetailName(){
        return name;
    }

    public void setDetailLength(String value) {
        this.length = value;
    }
    private String getLength(){
        return length;
    }

    private void setDetailWidth(String value) {
        this.width = value;
    }
    private String getWidth(){
        return width;
    }
    private void setMass(double mass){
        this.mass = mass;
    }
    
    @Override
    public double getMass() {
        return mass;
    }
    /**
     * �������� ������ � ����������� �� View
     * @param assortment ������������ ����������
     * @param type ������������ ���� �����
     * @param name ������������ ������
     * @param length ����� ������
     * @param width ������ ������ (���� ����)
     */
    public void createDetail(String assortment, String type, String name, String length, String width){
        this.setAssortmentName(assortment);
        this.setTypeName(type);
        this.setDetailName(name);
        this.setDetailLength(length);
        this.setDetailWidth(width);
        double value;
        switch(getAssortment()){
            case "����":
                    value = selectedProfile(getType());
                    this.setMass(value);
                    break;
            case "�������":
            case "������":
            case "�������":
                        this.setMass(
                        new AssortmentSteelDetail(
                                getDetailName(),
                                getLength())
                                .getMass());
                        break;
            case "������":               
                    value = selectedProfile(getType());
                    this.setMass(value);
                    break;
        }
    }
    
    private double selectedProfile(String type){
        switch (type){
            case "��������(����)":
                            return new RiffledSteelPlate(
                                    getDetailName(),
                                    getLength(),
                                    getWidth())
                                    .getMass();
            case "�������������":
            case "��������������":
                            return new SteelPlate(
                                    getDetailName(),
                                    getLength(),
                                    getWidth())
                                    .getMass();
            case "����":
                            return new CircleSteelDetail(
                                    getDetailName(),
                                    getLength())
                                    .getMass();
            case "�������":
                            return new SquareSteelDetail(
                                    getDetailName(),
                                    getLength())
                                    .getMass();
            case "���������_��������":
                            return new RubberSheet(
                                    getDetailName(),
                                    getLength(),
                                    getWidth())
                                    .getMass();
        }
        return 0;
    }    
}