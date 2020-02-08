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

import calcmassview.ViewPanel;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;

/**
 * ����� ���� ����� ������
 * @author Sergei Lyashko
 */
public class WidthField extends AbstractField {
    
    private static final WidthField INSTANCE = new WidthField();
    
    public static final WidthField getInstance(){
        return INSTANCE;
    }
    
    /**
     * ����������� ������
     */
    private WidthField(){
        //super();
        super.setText("������");
        super.setSize(125, 25);
        super.setForeground(Color.GRAY);        
        super.setEditable(false);
        super.setBackground(Color.DARK_GRAY);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
    }
    
    /**
     * ��������� ����
     */
    @Override
    public void actionField() {
        setEditable(true);
        setBackground(Color.white);        
        addFocusListener(this);
        addKeyListener(this);
    }
    
    /**
     * ����������� (��������) ����
     */
    @Override
    public void closeField(){        
        setEditable(false);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.GRAY);
        setText("������");
        removeFocusListener(this);
        removeKeyListener(this);
    }
    
    /**
     * ������� ���� ��� ��������� ������ �� ���
     * @param e ��������� ������
     */
    @Override
    public void focusGained(FocusEvent e){
        super.setForeground(Color.BLACK);
        super.setText("");
        new ResultMarker().resetResultMarker();
    }    
    
    /**
     * ������� � ���� "�����" ����� ������� "Enter"
     * @param e ������� ������� "Enter"
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.transferFocus();
        }
    }
    
    /**
     * ��������� �������� �� ���� ��� ������ ������
     * @param e ������� ������ ������ �� ����
     */
    @Override
    public void focusLost(FocusEvent e) {
        String text = super.getText();
        ViewPanel.getInstance().setDetailWidth(text);
    }
    
    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e) {}

    
}