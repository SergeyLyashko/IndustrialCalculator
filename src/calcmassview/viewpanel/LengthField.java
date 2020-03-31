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

import calcmassview.BasePanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * ���� ����� �����
 * @author Sergei Lyashko
 */
public class LengthField extends AbstractField {
    
    private static final LengthField INSTANCE = new LengthField();
    
    private String text;
    
    public static final LengthField getInstance(){
        return INSTANCE;
    }
    
    private LengthField(){
        super.setText("�����");            
    }
    
    /**
     * ����������� (��������) ����
     */
    @Override
    public void closeField(){        
        setEditable(false);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.GRAY);        
        setText("�����");
        removeFocusListener(this);
        removeKeyListener(this);
    }
    
    /**
     * ��������� ������ �� ���� � ����� �� ������� �������
     * @param e ������� ������� "Enter"
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.text = super.getText();
        }
    }
    
    public double getLengthValue(){
        return getValue(text);
    }
    
    /**
     * ����������� ���������� � ����� ������ ��� ���������� �������
     * @param e ���������� ������� "Enter" ����� �������
     */
    @Override
    public void keyReleased(KeyEvent e) {
        String value = BasePanel.getInstance().getResultation(); 
        setResultToSystemClipboard(value);
    }
    
    // ����� ����������� � ����� ������ ��� ������ ����������
    private void setResultToSystemClipboard(String value){                
        Toolkit.getDefaultToolkit()
            .getSystemClipboard()
            .setContents(new StringSelection(value), null);       
    }

    @Override
    public String getStringValue() {
        return text;
    }
}
