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

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;

/**
 * ���� ����� �����
 * @author Sergei Lyashko
 */
public class LengthField extends JFormattedTextField implements FocusListener, KeyListener, StateFieldInterface {
    
    private ICalculatorData calculatorData;
    
    private final BasePanel basePanel;
    private final String fieldName = "������� �����";
    private final String difficultAreaName = "������� �������";
    private final String emptyField = "";
    
    public LengthField(BasePanel basePanel){
        super.setSize(125, 25);
        super.setEditable(false);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setText(fieldName);
        super.setLocation(190, 60);
        this.basePanel = basePanel;
        nonActiveFieldColor();
    }
    
    /**
     *
     * @param data
     */
    public void setData(ICalculatorData data){
        this.calculatorData = data;
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
    public void deactiveField() {
        setEditable(false);              
        setText(fieldName);
        nonActiveFieldColor();
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
     * ��������� ������ �� ���� � ����� �� ������� �������
     * @param e ������� ������� "Enter"
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            calculatorData.setLength(super.getText());
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            //TODO
            basePanel.getGeneralPanel().notifyObservers();
        }
    }
    
    /**
     * ������� ���� ��� ��������� ������ �� ���
     * @param e ��������� ������
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
}
