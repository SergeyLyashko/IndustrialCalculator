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

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * ������ ���� ��������
 * @author Sergei Lyashko
 */
public class NameProfileMenuBox extends BaseMenuBox {  
    
    private final ViewPanel viewPanel;
    
    public NameProfileMenuBox(ViewPanel viewPanel){
        super(viewPanel);
        this.viewPanel = viewPanel;        
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String menuName = (String)cb.getSelectedItem();
        // ����� ��������
        viewPanel.reset();
        // ��������� ����� ������ �������
        actionField(menuName);
        viewPanel.detailName = menuName;        
    }
    
    // ��������� �����
    private void actionField(String menuName){
    // �����
        if(!menuName.equals("�_�������")){
            viewPanel.lengthField.actionField();
            // ��������� ���� ������ ��� ������ "����" � "���������_��������"
            if(((String)viewPanel.baseMenuBox.getSelectedItem()).equals("����") ||
                    ((String)viewPanel.typeProfileMenuBox.getSelectedItem()).equals("���������_��������")){
                viewPanel.widthField.actionField();
            }
        }        
    }
    
}
