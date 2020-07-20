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
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassview.base;

import calcmassview.settings.ToolTipsInterface;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;

/**
 * ѕоле ввода длины
 * @author Sergei Lyashko
 */
public class LengthField extends JFormattedTextField implements FocusListener, KeyListener, FieldValueReceivable, StateFieldInterface {
    
    private final BasePanel basePanel;
    private transient String contentFeld;
    private final String toolTipText = "поле ввода длины детали";
    private final String fieldName = "введите длину";
    private final String difficultAreaName = "введите площадь";
    private final String emptyField = "";
    
    public LengthField(BasePanel basePanel, ToolTipsInterface toolTips){
        super.setSize(125, 25);
        super.setForeground(Color.GRAY);        
        super.setEditable(false);
        super.setBackground(Color.DARK_GRAY);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setText(fieldName);
        super.setLocation(190, 60);
        this.basePanel = basePanel;
        addContent(toolTips);
    }
    
    // 
    private void addContent(ToolTipsInterface toolTips){
        toolTips.setToolTips(this, toolTipText);
        basePanel.add(this);
        basePanel.addPolicy(this);
    }
    
    /**
     *
     */
    public void difficultAreaStateON(){
        super.setText(difficultAreaName);
    }
    
    /**
     *
     */
    public void difficultAreaStateOFF(){
        super.setText(fieldName);
    }
    
    @Override
    public void deactiveField() {
        setEditable(false);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.GRAY);        
        setText(fieldName);
        removeFocusListener(this);
        removeKeyListener(this);
    }

    @Override
    public void activeField() {
        setEditable(true);
        setBackground(Color.white);        
        addFocusListener(this);
        addKeyListener(this);
    }
    
    /**
     * установка текста из пол€ в метод по нажатию клавиши
     * @param e нажатие клавиши "Enter"
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.contentFeld = super.getText();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            basePanel.getGeneralPanel().notifyObservers();
        }
    }
    
    /**
     * очистка пол€ при установке фокуса на нем
     * @param e установка фокуса
     */
    @Override
    public void focusGained(FocusEvent e){
        super.setForeground(Color.BLACK);
        super.setText(emptyField);
        basePanel.resetMarker();
    }
    
    @Override
    public void focusLost(FocusEvent e){}
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public String fieldValueStringReceive() {
        return this.contentFeld;
    }

    
}
