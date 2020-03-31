/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcmassmodel;

import calcmassview.MassObserver;
import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.AbstractMenuBox;

/**
 * Наблюдатель
 * @author Korvin
 */
public interface CalculatorModelInterface {
    
    /**
     * Регистрация наблюдателей
     * @param baseMenuBox
     * @param typeProfileMenuBox
     * @param numberProfileMenuBox
     * @param lengthField
     * @param widthField
     */
    public void setParametrs(AbstractMenuBox baseMenuBox, AbstractMenuBox typeProfileMenuBox, AbstractMenuBox numberProfileMenuBox,
            AbstractField lengthField, AbstractField widthField);
        
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
