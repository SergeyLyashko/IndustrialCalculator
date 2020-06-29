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
 * Интерфейс Модели
 * @author Sergei Lyashko
 */
public interface CalculatorModelInterface {
        
    /**
     * Регистрация наблюдателей
     * @param o экземпляр интерфейса Наблюдатель
     */
    public void registerObserver(ViewObserver o);

    /**
     * оповещение наблюдателей об изменении состояния
     */
    public void notifyObservers();
    
    /**
     * вывод сервисного сообщения об ошибке
     */
    public void displayError();
    
    /**
     * Создание детали по параметрам
     * @param assortment наименование сортамента
     * @param type тип сортамента
     * @param number номер профиля
     * @param length длина детали
     * @param width ширина детали (при наличии)
     */
    public void createDetail(String assortment, String type, String number, String length, String width);    

    /**
     * Создание детали заданной площади
     * @param assortment наименование сортамента
     * @param type тип сортамента
     * @param number номер профиля
     * @param area площадь детали
     */
    public void createDetail(String assortment, String type, String number, String area);
    
    /**
     * 
     * @param dataBase интерфейс базы данных
     */
    public void setDataBase(DataBaseInterface dataBase);
}
