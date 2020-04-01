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
     * @param assortment
     * @param type
     * @param number
     * @param length
     * @param width
     */
    public void setParametrs(String assortment, String type, String number, String length, String width);
        
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
