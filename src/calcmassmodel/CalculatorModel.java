/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassmodel;

import calcmassview.MassObserver;
import java.util.ArrayList;

/**
 * Модель
 * @author Sergei Lyashko
 */
public class CalculatorModel implements CalculatorModelInterface {
    
    private final ArrayList<MassObserver> observers;
    private final CalculatorFacade facade;
    private double mass;
    
    public CalculatorModel(){
        observers = new ArrayList<>();
        facade = new CalculatorFacade();
    }
    
    @Override
    public void setParametrs(String assortment, String type, String number, String length, String width){
        facade.setParametrs(assortment, type, number, length, width);
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
        }
    }

    // оповещение наблюдателей о вычислении массы
    private void massChanged(){
        this.mass = facade.getDetail().getMass();
        notifyObservers();
    }
}
