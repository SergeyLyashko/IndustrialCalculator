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
package calcdatabase;

import java.util.ArrayList;

/**
 * ��������� ���� ������
 * @author Sergei Lyashko
 */
public interface IDataBase {
    
    /**
     * �������� ������� � ���� ������
     * @param assortment ������������ ����������
     * @param type ��� �������
     * @param number ����� �������
     * @return 
     */
    public double query(String assortment, String type, String number);    
    
    /**
     * ��������� ���� �� ���� ������ � ������� ���������� �������
     * @param assortment
     * @param type
     * @param number
     * @return ������ ��������� ��������
     */
    public ArrayList<String> receiveMenu(String assortment, String type, String number);
}
