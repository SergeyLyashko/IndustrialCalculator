/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
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
package calcmassview.basepanel;

import calcmassview.AbstractPanel;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;

/**
 * Поле ввода ширины
 * @author Sergei Lyashko
 */
public class WidthField extends JFormattedTextField implements FocusListener, KeyListener, ValueFieldReceivable, ICloseField {
    
    private final AbstractPanel panel;
    private String text;
    
    public WidthField(AbstractPanel panel){
        super.setSize(125, 25);
        super.setForeground(Color.GRAY);        
        super.setEditable(false);
        super.setBackground(Color.DARK_GRAY);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        this.panel = panel;
        create();
    }
    
    private void create(){
        panel.add(this);
        this.setLocation(190, 20);   
        super.setText("ширина");
        ((BasePanel)panel).addPolicy(this);
    }
    
    /**
     * деактивация (закрытие) поля
     */
    @Override
    public void close(){        
        setEditable(false);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.GRAY);
        setText("ширина");
        removeFocusListener(this);
        removeKeyListener(this);
    }
    
    /**
     * активация поля
     */
    @Override
    public final void action(){
        setEditable(true);
        setBackground(Color.white);        
        addFocusListener(this);
        addKeyListener(this);
    }
    
    /**
     * переход в поле "длина" после нажатия "Enter"
     * @param e нажатие клавиши "Enter"
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.transferFocus();
        }
    }
    
    /**
     * установка значения из поля при потери фокуса
     * @param e событие потери фокуса на поле
     */
    @Override
    public void focusLost(FocusEvent e) {
        this.text = super.getText();
    }

    @Override
    public String getValueOfField() {
        return  text;
    }
    
    /**
     * очистка поля при установке фокуса на нем
     * @param e установка фокуса
     */
    @Override
    public void focusGained(FocusEvent e){
        super.setForeground(Color.BLACK);
        super.setText("");
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e){}
}