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
import java.awt.event.ActionEvent;

/**
 * ������ ���� ������� ��������
 * @author Sergei Lyashko
 */
public class NumberProfileMenuBox extends AbstractMenuBox {  
    
    private String selectMenu;
    private final BasePanel basePanel;
    
    public NumberProfileMenuBox(BasePanel basePanel) {
        this.basePanel = basePanel;
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractMenuBox cb = (AbstractMenuBox)e.getSource();
        String currentMenu = (String)cb.getSelectedItem();
        // ����� ��������
        basePanel.reset();
        // �������� ����� ����� ��������
        actionFields(currentMenu);
        // ��������� ������ � ���������� ���� 
        this.selectMenu = currentMenu;
    }
    
    // ��������� ����� ����� ��������
    private void actionFields(String selectMenu){
        if(!selectMenu.equals("� �������")){
            basePanel.getLengthField().actionField();
            if(((String)basePanel.getBaseMenuBox().getSelectedItem()).equals("����") ||
                    ((String)basePanel.getTypeProfileMenuBox().getSelectedItem()).equals("��������� ��������")){
                basePanel.getWidthField().actionField();
            }
        }
    }

    @Override
    public String getStringValue() {
        return selectMenu;
    }
}
