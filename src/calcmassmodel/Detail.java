/*
 * Copyright 2019 Sergei Lyashko. Contacts: <9lLLLepuLLa@gmail.com>.
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
 * ���������� ����� ������
 * @author Sergei Lyashko
 */
class Detail implements Massable, ErrorMessageInterface {
    
    // ��������� ����� ����� ��3 7,85e-6 ��/��3 = 7850 ��/�3
    private static final double DENSITY_STEEL = 7.85e-6;
    // ��������� ������ ���� 7338-90 ���� ���� 1.25e-7 ��/��3 = 125 ��/�3
    private static final double DENSITY_RUBBER = 1.25e-6;
    // ��������� �� ������
    private String message;
    // �������� �� ���� ������
    private double valueFromDB;
    // ��������� ������
    private final String assortment, type, number, length, width;
    
    public Detail(String assortment, String type, String number, String length, String width){
        //this.valueFromDB = getValueFromDataBase(assortment, type, number);
        this.assortment = assortment;
        this.type = type;
        this.number = number;
        this.length = length;
        this.width = width;
    }
    
    public void receiveData(DataBaseQuery query){
        this.valueFromDB = query.getDataBaseValue(assortment, type, number);
    }
    
    /**
     * ��������� �������� �� ���� ������, � ����������� �� ����������
     * @param profileAssortment ������������ ����������
     * @param profileType ��� ����������
     * @param profileNumber ����� �������
     * @return ����� � ��������� ������
     */
    /*
    private double getValueFromDataBase(String profileAssortment, String profileType, String profileNumber){
         return new DataBaseQuery().getDataBaseValue(profileAssortment, profileType, profileNumber);
    }
    */
    /**
     * ��������� ��������� ��������
     * @param value ��������� ������������� ��������
     * @return �������� ������������� 
     */
    private double getValueOf(String value) {
        try{
            return Double.parseDouble(value);
        }catch(NumberFormatException e){
            this.message = "������! �������� ����� �������!";
        }
        return 0;
    }
    
    /**
     * ���������� ����� ������
     */
    @Override
    public double calculationMass() {
        switch(assortment){
            case "����":
                            return selectedType(type, length, width);
            case "�������":
            case "������":
            case "�������":
                            return DENSITY_STEEL * valueFromDB * getValueOf(length) * 100;
            case "������":               
                            return selectedType(type, length, width);
        }
        return 0;
    }
    
    private double selectedType(String type, String length, String width){
        switch (type){
            case "��������(����)":
                            return getValueOf(length) * getValueOf(width) / 1000000 * valueFromDB;
            case "�������������":
            case "��������������":
                            return DENSITY_STEEL * getValueOf(length) * getValueOf(width) * valueFromDB;
            case "����":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB) / 4 * PI;
            case "�������":
                            return DENSITY_STEEL * getValueOf(length) * valueFromDB * valueFromDB;
            case "��������� ��������":
                            return DENSITY_RUBBER * getValueOf(length) * getValueOf(width) * valueFromDB;  
        }
        return 0;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
