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

import calcmasscontroller.Observer;
import java.util.ArrayList;

/**
 * Фасад модели
 * реализует интерфейс Subject делающий рассылку вычисления массы
 * детали в зависимости от посутпивших к нему данных
 * @author Sergei Lyashko
 */
public class Facade implements Subject {
    
    private static final Facade INSTANCE = new Facade();
    
    public static Facade getInstance(){
        return INSTANCE;
    }
    
    private final ArrayList<Observer> observers;
    private double mass;
    
    private Facade(){
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i >= 0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for(int i=0; i<observers.size(); i++){
            Observer observer = observers.get(i);
            observer.update(mass);
        }
    }
    
    // оповещение наблюдателей о появлении новых данных
    private void massChanged(){
        notifyObservers();
    }
    
    /**
     * Создание детали с параметрами из View
     * @param profileAssortment наименование сортамента
     * @param profileType наименование типа дтали
     * @param profileNumber наименование детали
     * @param length длина детали
     * @param width ширина детали (когда она есть)
     */
    public void createDetail(String profileAssortment, String profileType, String profileNumber, String length, String width){
        new FactoryDetail().getCurrentDetail(profileAssortment, profileType, profileNumber, length, width);
    }
    
    /**
     * Установка поля массы детали
     * @param mass
     */
    public void setMass(double mass){
        this.mass = mass;
        massChanged();
    }
}
