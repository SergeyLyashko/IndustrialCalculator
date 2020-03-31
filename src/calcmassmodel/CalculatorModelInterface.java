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
