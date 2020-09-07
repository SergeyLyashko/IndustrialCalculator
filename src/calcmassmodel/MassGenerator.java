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


/**
 * ����������
 * @author Sergei Lyashko
 */
class MassGenerator {
    
    // ����������� ��������� �������� ���������� ��� ������������ �����
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    // ��������� �� ������
    private String message;
    
    private MassableDetail newDetail;   
    private final DetailAreaReceiver detailArea;
    private final Detail inputData;
    
    public MassGenerator(DetailAreaReceiver detailArea, Detail data){
        this.detailArea = detailArea;
        this.inputData = data;
        createDetail();
    }
    
    private void createDetail() {
        double width = inputData.getDetailWidth();
        double length = inputData.getDetailLength();
        if(isValidFieldsValues(width, length)){
            //CalculationMassFactory factory = new CalculationMassFactory(detailArea, inputData);
            //newDetail = factory.createDetail();
        }        
    }
    
    // �������� �� ������������
    private boolean isValidFieldsValues(double widthNum, double lengthNum){
        if(isValidNumber(widthNum) && isValidNumber(lengthNum)){
            double checkNum = MAX_NUMBER / lengthNum;
            if(checkNum > widthNum){
                this.message = "������! ������� ������� �����!";
                return false;
            }
            return true;
        }
        return false;
    }
    
    
    private boolean isValidNumber(double number){
        if(number > MAX_NUMBER){
                this.message = "������! ������� ������� �����!";
                return false;
        }
        if(number < 0){
                this.message = "������! ������������� �����!";
                return false;
        }
        return true;
    }    
}
