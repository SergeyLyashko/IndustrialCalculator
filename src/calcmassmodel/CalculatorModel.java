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

import java.util.ArrayList;
import calcmassview.ViewObserver;
import java.sql.Connection;

/**
 * создание детали
 * @author Sergei Lyashko
 */
public class CalculatorModel implements CalculatorModelInterface {
    
    private Detail detail;
    private final ArrayList<ViewObserver> observers;
    private double mass;
    private String serviceMessage;
    private DataBaseQuery dataBaseQuery;
    
    public CalculatorModel(){
        observers = new ArrayList<>();        
    }
    
    @Override
    public void createDetail(String assortment, String type, String number, String length, String width){
        detail = new Detail(assortment, type, number, length, width);
        detail.receiveData(dataBaseQuery);
        massChangedObservers();
    }
    
    @Override
    public void registerObserver(ViewObserver o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach((ViewObserver observer) -> {
            observer.massUpdate(mass);
            observer.errorMessageUpdate(serviceMessage);
        });
    }

    @Override
    public void displayError() {
        this.serviceMessage = detail.getErrorMessage();
    }
    
    // оповещение наблюдателей об изменениях
    private void massChangedObservers(){
        this.mass = detail.calculationMass();
        displayError();
        notifyObservers();
    }

    @Override
    public void setDataBaseConnect(Connection connection) {
        dataBaseQuery = new DataBaseQuery(connection);
    }
}
