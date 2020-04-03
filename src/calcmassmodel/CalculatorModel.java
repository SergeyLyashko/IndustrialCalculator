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

import calcmassview.MassObserver;
import java.util.ArrayList;

/**
 * создание детали
 * @author Sergei Lyashko
 */
public class CalculatorModel implements CalculatorModelInterface {
    
    private AbstractDetail orderDetail;
    private final ArrayList<MassObserver> observers;
    private double mass;
    private String serviceMessage;
    
    public CalculatorModel(){
        observers = new ArrayList<>();
    }
    
    @Override
    public void createDetail(String assortment, String type, String number, String length, String width){
        orderDetail = new DetailFactory().orderDetail(assortment, type, number, length, width);
        massChanged();
    }
    
    @Override
    public void registerObserver(MassObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(MassObserver o) {
        int i = observers.indexOf(o);
        if(i >= 0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for(int i=0; i<observers.size(); i++){
            MassObserver observer = observers.get(i);
            observer.update(mass);
            observer.updateErrorMessage(serviceMessage);
        }
    }

    @Override
    public void displayErrorMessage() {
        this.serviceMessage = orderDetail.getErrorMessage();
    }
    
    // оповещение наблюдателей об изменениях
    private void massChanged(){
        this.mass = orderDetail.getMass();
        displayErrorMessage();
        notifyObservers();
    }
}
