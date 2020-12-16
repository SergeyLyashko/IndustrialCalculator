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
 * distributed under the License is distributed activate an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassview.base;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.annotation.Annotation;
import javax.swing.JFormattedTextField;
import calcmassview.settings.ToolTips;
import java.util.ArrayList;

/**
 * ���� ����� �����
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ToolTips(getToolTipDescription = "")
@ValueReceiveble(getCurrentMenuItem = "LengthField")
@StateField(activate = false, deactivate = true)
@SuppressWarnings("serial")
public class LengthField extends JFormattedTextField implements CalculatorPanel, FocusListener, StateField, ValueReceiveble, KeyListener, ToolTips, KeyActionSubject {
    
    private final String toolTipsText = "���� ����� ����� ������";
    
    private final String fieldName = "������� �����";
    private final String difficultAreaName = "������� �������";
    private final String emptyField = "";
    private String fieldValue;
    private final Reset resetMarker;
    
    private ArrayList<KeyActionObserver> observers;
    
    public LengthField(Reset resetMarker){
        super.setSize(125, 25);
        super.setEditable(false);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setText(fieldName);
        super.setLocation(190, 60);
        this.resetMarker = resetMarker;
        this.observers = new ArrayList<>();
        nonActiveFieldColor();
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    // ���� ����������� ����
    private void nonActiveFieldColor(){
        super.setForeground(Color.GRAY);
        super.setBackground(Color.DARK_GRAY);
    }
    
    /**
     * ��������� ���-����� ������� �������
     */
    public void difficultAreaStateON(){
        super.setText(difficultAreaName);
    }
    
    /**
     * ���������� ���-����� ������� �������
     */
    public void difficultAreaStateOFF(){
        super.setText(fieldName);
        super.setForeground(Color.GRAY);
    }
    
    @Override
    public boolean deactivate() {
        setEditable(false);              
        setText(fieldName);
        nonActiveFieldColor();
        removeFocusListener(this);
        removeKeyListener(this);
        return true;
    }

    @Override
    public boolean activate() {
        setEditable(true);
        setBackground(Color.white);        
        addFocusListener(this);
        addKeyListener(this);
        return true;
    }
    
    @Override
    public String getCurrentMenuItem(){
        return fieldValue;
    }
        
    /**
     * ������� ���� ��� ��������� ������ �� ���
     * @param e ��������� ������
     */
    @Override
    public void focusGained(FocusEvent e){
        super.setForeground(Color.BLACK);
        super.setText(emptyField);
        resetMarker.reset();
    }
    
    @Override
    public void focusLost(FocusEvent e){}

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.fieldValue = super.getText();            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            notifyObservers();
        }
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(KeyActionObserver::keyActionUpdate);
    }

    @Override
    public void registerObserver(KeyActionObserver keyActionObserver) {
        observers.add(keyActionObserver);
    }
}