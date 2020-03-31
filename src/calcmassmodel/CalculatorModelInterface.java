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
 * �����������
 * @author Korvin
 */
public interface CalculatorModelInterface {
    
    /**
     * ����������� ������������
     * @param baseMenuBox
     * @param typeProfileMenuBox
     * @param numberProfileMenuBox
     * @param lengthField
     * @param widthField
     */
    public void setParametrs(AbstractMenuBox baseMenuBox, AbstractMenuBox typeProfileMenuBox, AbstractMenuBox numberProfileMenuBox,
            AbstractField lengthField, AbstractField widthField);
        
    /**
     * ����������� ������������
     * @param o ��������� ���������� �����������
     */
    public void registerObserver(MassObserver o);

    /**
     * �������� �����������
     * @param o ��������� ���������� �����������
     */
    public void removeObserver(MassObserver o);
    
    /**
     * ���������� ������������ �� ��������� ���������
     */
    public void notifyObservers();
    
}
