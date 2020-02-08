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
package calcmassview.viewpanelcomponent;

import calcmassview.ViewPanel;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

/**
 * ����� ���� ����� ������
 * @author Sergei Lyashko
 */
public class WidthField extends LengthField {
    
    private final ViewPanel viewPanel;
    
    /**
     * ����������� ������
     * @param viewPanel �������� ������� ������
     */
    public WidthField(ViewPanel viewPanel){
        super(viewPanel);
        this.viewPanel = viewPanel;
        super.setText("������");
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
    
    @Override
    public void keyPressed(KeyEvent e){}
    
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
        viewPanel.setDetailWidth(text);
    }   
}