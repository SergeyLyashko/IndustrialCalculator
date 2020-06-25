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
import calcmassview.ViewObserver;

/**
 * ��������� ������
 * @author Sergei Lyashko
 */
public interface CalculatorModelInterface {
        
    /**
     * ����������� ������������
     * @param o ��������� ���������� �����������
     */
    public void registerObserver(ViewObserver o);

    /**
     * ���������� ������������ �� ��������� ���������
     */
    public void notifyObservers();
    
    /**
     * ����� ���������� ��������� �� ������
     */
    public void displayError();
    
    /**
     * �������� ������ �� ����������
     * @param assortment ������������ ����������
     * @param type ��� ����������
     * @param number ����� �������
     * @param length ����� ������
     * @param width ������ ������ (��� �������)
     */
    public void createDetail(String assortment, String type, String number, String length, String width);    

    /**
     * 
     * @param dataBase ��������� ���� ������
     */
    public void setDataBase(DataBaseInterface dataBase);
}
