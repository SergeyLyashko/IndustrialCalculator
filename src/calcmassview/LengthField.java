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
package calcmassview;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;

/**
 * поле ввода длины
 * 
 */
public class LengthField extends JFormattedTextField implements FocusListener, KeyListener {
    
    private final ViewPanel viewPanel;
        
    public LengthField(ViewPanel basePanel){
        super();
        this.viewPanel = basePanel;
        super.setText("длина");
        super.setSize(125, 25);
        super.setForeground(Color.GRAY);        
        super.setEditable(false);
        super.setBackground(Color.DARK_GRAY);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);        
    }
    
    //активация поля
    public void actionField(){
        setEditable(true);
        setBackground(Color.white);        
        addFocusListener(this);
        addKeyListener(this);
    }
    
    //деактивация поля Длина
    protected void closeField(){        
        setEditable(false);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.GRAY);        
        setText("длина");
        removeFocusListener(this);
        removeKeyListener(this);
    }
    
    // очистка поля при фокусе
    @Override
    public void focusGained(FocusEvent e){
        super.setForeground(Color.BLACK);
        super.setText("");
        new ResultMarker().resetResultMarker();
    }   
    
    // установка текста из поля в метод по нажатию клавиши <Enter>
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String text = super.getText();
            viewPanel.setDetailLength(text);                   
        }
    }
    
    // копирование результата в буфер обмена при отпускании клавиши <Enter>
    @Override
    public void keyReleased(KeyEvent e) {
        String value = viewPanel.getResultation(); 
        setResultToSystemClipboard(value);
    }
    
    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){                
        Toolkit.getDefaultToolkit()
            .getSystemClipboard()
            .setContents(new StringSelection(value), null);       
    }    
    
    @Override
    public void focusLost(FocusEvent e){}

    @Override
    public void keyTyped(KeyEvent e) {}    
}
