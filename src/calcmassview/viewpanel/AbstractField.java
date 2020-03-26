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
package calcmassview.viewpanel;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;

/**
 * Абстрактный класс для полей ввода значений
 * @author Sergei Lyashko
 */
public abstract class AbstractField extends JFormattedTextField implements FocusListener, KeyListener {
    
    public AbstractField(){
        super.setSize(125, 25);
        super.setForeground(Color.GRAY);        
        super.setEditable(false);
        super.setBackground(Color.DARK_GRAY);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
    }
    
    public double getValue(String valueStr) {
        try{
            return Double.parseDouble(valueStr);
        }catch(NumberFormatException e){
            System.err.println("Ошибка преобразования значения: " + e);
        }catch (NullPointerException ex){
            System.err.println("Нулевой указатель: " + ex);
        }
        return 0;
    }
    
    /**
     * активация поля
     */
    public final void actionField(){
        setEditable(true);
        setBackground(Color.white);        
        addFocusListener(this);
        addKeyListener(this);
    }
    
    /**
     * очистка поля при установке фокуса на нем
     * @param e установка фокуса
     */
    @Override
    public void focusGained(FocusEvent e){
        super.setForeground(Color.BLACK);
        super.setText("");
        new ResultMarker().resetResultMarker();
    }
    
    @Override
    public void focusLost(FocusEvent e){}

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e) {}
    
    /**
     * деактивация (закрытие) поля
     */
    public abstract void closeField();

    public abstract String getStringValue();
}
