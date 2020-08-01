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
 * Интерфейс базы данных
 * @author Sergei Lyashko
 */
public interface IDataBase {
    
    /**
     * Отправка запроса в базу данных
     * @param assortment наименование сортамента
     * @param type тип профиля
     * @param number номер профиля
     * @return 
     */
    public double query(String assortment, String type, String number);    
    
    /**
     * Получение меню из базы данных в формате списочного массива
     * @param assortment
     * @param type
     * @param number
     * @return список строковых значений
     */
    public ArrayList<String> receiveMenu(String assortment, String type, String number);
}
