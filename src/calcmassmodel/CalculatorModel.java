/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassmodel;

import calcmassview.MassObserver;
import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.AbstractMenuBox;
import java.util.ArrayList;

/**
 * Модель
 * @author Korvin
 */
public class CalculatorModel implements CalculatorModelInterface {
    
    private final ArrayList<MassObserver> observers;
    private final Facade facade;
    private double mass;
    
    public CalculatorModel(){
        observers = new ArrayList<>();
        facade = new Facade();
    }
    
    public void addParametrs(AbstractMenuBox baseMenuBox, AbstractMenuBox typeProfileMenuBox, AbstractMenuBox numberProfileMenuBox,
            AbstractField lengthField, AbstractField widthField){
        facade.addParametrs(baseMenuBox, typeProfileMenuBox, numberProfileMenuBox, lengthField, widthField);
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

    @Override
    public double getMass() {
        return mass;
    }
    
    // оповещение наблюдателей о появлении новых данных
    private void massChanged(){
        this.mass = facade.getDetail().getMass();
        notifyObservers();
    }
}
