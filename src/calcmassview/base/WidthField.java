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

import calcmassview.settings.ToolTips;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;

/**
 * ���� ����� ������
 * @author Sergei Lyashko
 */
public class WidthField extends JFormattedTextField implements FocusListener, KeyListener, ValueReceivable {
    
    private final BasePanel basePanel;
    private transient String contentField;
    private final String text = "���� ����� ������ ������";
    private final String fieldName = "������";
    private final String emptyField = "";
    
    public WidthField(BasePanel basePanel, ToolTips toolTips){
        super.setSize(125, 25);
        super.setForeground(Color.GRAY);        
        super.setEditable(false);
        super.setBackground(Color.DARK_GRAY);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setText(fieldName);
        super.setLocation(190, 20);
        this.basePanel = basePanel;
        addContent(toolTips);
    }
    
    private void addContent(ToolTips toolTips){
        toolTips.setToolTips(this, text);
        basePanel.add(this);
        basePanel.addPolicy(this);
    }
    
    /**
     * ����������� (��������) ����
     * @return 
     */
    public IDeactivationField execute(){
        return () -> {
            setEditable(false);
            setBackground(Color.DARK_GRAY);
            setForeground(Color.GRAY);
            setText(fieldName);
            removeFocusListener(this);
            removeKeyListener(this);
        };
    }
    
    /**
     * ��������� ����
     * @return 
     */
    public IActivationField perform(){
        return () -> {
            setEditable(true);
            setBackground(Color.white);        
            addFocusListener(this);
            addKeyListener(this);
        };
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
        this.contentField = super.getText();
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
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public String receiveFieldString() {
        return this.contentField;
    }
}