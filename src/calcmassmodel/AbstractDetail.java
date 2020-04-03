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
 * ����������� ����� ������
 * @author Korvin
 */
public abstract class AbstractDetail {
    
    // ��������� ����� ����� ��3 7,85e-6 ��/��3 = 7850 ��/�3
    final double DENSITY_STEEL = 7.85e-6;
    // ��������� ������ ���� 7338-90 ���� ���� 1.25e-7 ��/��3 = 125 ��/�3
    final double DENSITY_RUBBER = 1.25e-6;
    
    private double length;
    private double width;
    private double dataBaseValue;
    private String message;
    
    /**
     * ����������� ������
     * @param profileAssortment ������������ ����������
     * @param profileType ��� ���������� ����������
     * @param profileNumber ����� ������� ������
     * @param length ����� ������
     * @param width ������ ������ (���� ������)
     */
    public AbstractDetail(String profileAssortment, String profileType, String profileNumber, String length, String width){        
    }
    
    /**
     * �������� �� ���� ������
     * @param profileAssortment ������������ ����������
     * @param profileType ��� ����������
     * @param profileNumber ����� �������
     */
    protected final void setValueFromDataBase(String profileAssortment, String profileType, String profileNumber){
        this.dataBaseValue = new DataBaseQuery().getDataBaseValue(profileAssortment, profileType, profileNumber);
    }
    
    /**
     * ������ �������� �� ���� ������
     * @return �������� �� ���� ������
     */
    protected final double getValueFromDataBase(){
        return dataBaseValue;
    }
    
    /**
     * ��������� �������� ����� ������
     * @param length ��������� �������� �����
     */
    protected final void setLength(String length){
        try{
            this.length = getValueFromField(length);
        }catch(NumberFormatException e){
            this.message = "������! ����������� ������ ����� ������";
        }
    }
    
    /**
     * ��������� �������� ������ ������
     * @param width ��������� ������ ������
     */
    protected final void setWidth(String width){
        try{
            this.width = getValueFromField(width);
        }catch(NumberFormatException e){
            this.message = "������! ����������� ������ ������ ������";
        }
    }
    
    /**
     * �������������� ������ � �����
     * @param valueStr
     * @return ����� � ��������� ������
     */
    private double getValueFromField(String valueStr) throws NumberFormatException {
        return Double.parseDouble(valueStr);
    }
    
    /**
     * ������ ����� ������
     * @return ����� ������
     */
    protected final double getLength(){
        return length;
    }
    
    /**
     * ������ ������ ������
     * @return ������ ������
     */
    protected final double getWidth(){
        return width;
    }
    
    /**
     * ���������� ��������� ��������� �� ������
     * @return ��������� ������������� ���������
     */
    protected final String getErrorMessage() {
        return message;
    } 
    
    /**
     * ��������� ����� ������
     * @return �������� ����� ������
     */
    abstract double getMass();    
}
