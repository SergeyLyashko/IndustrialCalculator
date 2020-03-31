/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassmodel;

import calcmassview.MassObserver;

/**
 *
 * @author Korvin
 */
public interface CalculatorModelInterface {
    
        
    public double getMass();
    
    /**
     * Регистрация наблюдателей
     * @param o экземпляр интерфейса Наблюдатель
     */
    public void registerObserver(MassObserver o);

    /**
     * Удаление наблюдателя
     * @param o экземпляр интерфейса Наблюдатель
     */
    public void removeObserver(MassObserver o);
    
    /**
     * оповещение наблюдателей об изменении состояния
     */
    public void notifyObservers();
    
}
