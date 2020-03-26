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

import java.util.ArrayList;
import calcmasscontroller.IObserver;
import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.AbstractMenuBox;

/**
 * Фасад модели
 * реализует интерфейс ISubject делающий рассылку вычисления массы
 * детали в зависимости от посутпивших к нему данных
 * @author Sergei Lyashko
 */
public class Facade implements ISubject {
    
    private final ArrayList<IObserver> observers;
    private double mass;
    private final AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    private final AbstractField lengthField, widthField;
    private String profileAssortment, profileType, profileNumber, length, width;
    private DetailFactory detailFactory;
    private AbstractDetail detail;
    
    public Facade(AbstractMenuBox baseMenuBox, AbstractMenuBox typeProfileMenuBox, AbstractMenuBox numberProfileMenuBox,
            AbstractField lengthField, AbstractField widthField){
        observers = new ArrayList<>();
        this.baseMenuBox = baseMenuBox;
        this.typeProfileMenuBox = typeProfileMenuBox;
        this.numberProfileMenuBox = numberProfileMenuBox;
        this.lengthField = lengthField;
        this.widthField = widthField;        
    }
    
    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        int i = observers.indexOf(o);
        if(i >= 0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for(int i=0; i<observers.size(); i++){
            IObserver observer = observers.get(i);
            observer.update(mass);
        }
    }
    
    /**
     * Создание детали
     */
    public void createDetail(){
        setValueOfFields();
        detailFactory = new DetailFactory();
        detail = detailFactory.createDetail(profileAssortment, profileType, profileNumber, length, width);
        setMass();
    }
    
    private void setValueOfFields(){
        this.profileAssortment = baseMenuBox.getStringValue();
        this.profileType = typeProfileMenuBox.getStringValue();
        this.profileNumber = numberProfileMenuBox.getStringValue();
        this.length = lengthField.getStringValue();
        this.width = widthField.getStringValue();
    }
        
    /**
     * Установка поля массы детали
     * @param mass
     */
    private void setMass(){
        this.mass = detail.getMass();
        System.out.println("test facade set mass: "+mass);
        massChanged();
    }
    
    // оповещение наблюдателей о появлении новых данных
    private void massChanged(){
        notifyObservers();
    }
}
