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

import calcdatabase.DataBaseInterface;
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
    // ����������� ��������� �������� ���������� ��� ������������ �����
    private final double maxNumber = Double.MAX_VALUE;
    // ��������� �� ������
    private String message;
    // �������� �� ���� ������
    private double valueFromDB;
    // ��������� ������
    private final String assortment, type, number, length;
    // ������� ������
    private double area;
    
    public Detail(String assortment, String type, String number, String length, String width){
        this.assortment = assortment;
        this.type = type;
        this.number = number;
        this.length = length;
        calculationArea(width, length);
    }

    Detail(String assortment, String type, String number, String area) {
        this.assortment = assortment;
        this.type = type;
        this.number = number;
        this.length = null;
        calculationArea(area);
    }
    
    /**
     * ������ � ���� ������
     * @param dataBase ��������� ���� ������
     */
    public void executeQuery(DataBaseInterface dataBase) {
        this.valueFromDB = dataBase.query(assortment, type, number);
        //this.valueFromDB = dataBase.receiveValue();
    }
    
    /**
     * ��������� ��������� ��������
     * @param value ��������� ������������� ��������
     * @return �������� ������������� 
     */
    private double getValueOf(String value) {
        try{
            double valueNum = Double.parseDouble(value);
            if(valueNum > maxNumber){
                this.message = "������! ������� ������� �����!";
            }else if(valueNum < 0){
                this.message = "������! ������������� �����!";
            }else{
                return valueNum;
            }
        }catch(NumberFormatException e){
            this.message = "������! ��������� �������� �� �������� ������!";
        }
        return 0;
    }
    
    // �������� �� ������������
    private boolean isNotBigNumber(double widthNum, double lengthNum){
        double checkNum = maxNumber / lengthNum;
        return checkNum > widthNum;
    }
    
    // ���������� �������
    private void calculationArea(String width, String length){
        if(width != null){
            double widthNum = getValueOf(width);
            double lengthNum = getValueOf(length);
            if(isNotBigNumber(widthNum, lengthNum)){
                this.area = widthNum * lengthNum;
            }else{
                this.message = "������! ������� ������� �����!";
            }
        }
    }
    
    // �������� �������� �������� � ������������ �������
    private void calculationArea(String area){
        this.area = getValueOf(area);
    }
    
    
    /**
     * ���������� ����� ������
     */
    @Override
    public double calculationMass() {
        switch(assortment){
            case "����":
                            return selectedType(type, length);
            case "�������":
            case "������":
            case "�������":
                            return DENSITY_STEEL * valueFromDB * getValueOf(length) * 100;
            case "������":               
                            return selectedType(type, length);
        }
        return 0;
    }
    
    private double selectedType(String type, String length){
        switch (type){
            case "��������(����)":
                            return area / 1000000 * valueFromDB;
            case "�������������":
            case "��������������":
                            return DENSITY_STEEL * area * valueFromDB;
            case "����":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB) / 4 * PI;
            case "�������":
                            return DENSITY_STEEL * getValueOf(length) * (valueFromDB * valueFromDB);
            case "��������� ��������":
                            return DENSITY_RUBBER * area * valueFromDB;  
        }
        return 0;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }    
}
