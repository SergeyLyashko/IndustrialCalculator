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
 * »нтерфейс базы данных
 * @author Sergei Lyashko
 */
public interface DataBaseInterface {
    
    /**
     * ќтправка запроса в базу данных
     * @param assortment наименование сортамента
     * @param type тип профил€
     * @param number номер профил€
     */
    public void query(String assortment, String type, String number);
    
    /**
     * ѕолучение значени€ из базы данных
     * @return значение из базы данных в формате числа с плавающей точкой
     */
    public double receiveValue();
    
    /**
     * ѕолучение меню из базы данных в формате списочного массива
     * @return список строковых значений
     */
    public ArrayList<String> receiveMenuList();
}
