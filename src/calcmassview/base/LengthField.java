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

import calcmassview.ViewObserver;
import calcmassview.IKeyActionSubject;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.annotation.Annotation;
import javax.swing.JFormattedTextField;
import calcmassview.settings.ToolTips;

/**
 * Поле ввода длины
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ToolTips(getToolTipDescription = "")
@ValueReceiveble(getFieldValue = "LengthField")
@ActiveStateField(activate = false, deactivate = true)
@SuppressWarnings("serial")
public class LengthField extends JFormattedTextField implements CalculatorPanel, FocusListener, ActiveStateField, ValueReceiveble, KeyListener, IKeyActionSubject, ToolTips {
    
    private final String toolTipsText = "поле ввода длины детали";
    
    private final String fieldName = "введите длину";
    private final String difficultAreaName = "введите площадь";
    private final String emptyField = "";
    private String fieldValue;
    private IKeyActionObserver observer;
    private final ServiceInscription resetMarker;
    
    public LengthField(ServiceInscription resetMarker){
        super.setSize(125, 25);
        super.setEditable(false);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setText(fieldName);
        super.setLocation(190, 60);
        this.resetMarker = resetMarker;
        nonActiveFieldColor();
    }
    
    @Override
    public String getToolTipDescription(){
        return toolTipsText;
    }
    
    // цвет неактивного поля
    private void nonActiveFieldColor(){
        super.setForeground(Color.GRAY);
        super.setBackground(Color.DARK_GRAY);
    }
    
    /**
     * Включение чек-бокса сложной площади
     */
    public void difficultAreaStateON(){
        super.setText(difficultAreaName);
    }
    
    /**
     * Отключение чек-бокса сложной площади
     */
    public void difficultAreaStateOFF(){
        super.setText(fieldName);
        super.setForeground(Color.GRAY);
    }
    
    @Override
    public boolean deactivate() {
        //System.out.println("length OFF");// TEST
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
    public String getFieldValue(){
        return fieldValue;
    }
        
    /**
     * очистка поля при установке фокуса на нем
     * @param e установка фокуса
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
            //System.out.println("length press");// TEST
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            notifyObservers();
        }
    }

    @Override
    public void registerObserver(ViewObserver observer) {}

    @Override
    public void notifyObservers() {
        observer.keyActionUpdate();
    }

    @Override
    public void registerObserver(IKeyActionObserver keyActionObserver) {
        this.observer = keyActionObserver;
    }
}
