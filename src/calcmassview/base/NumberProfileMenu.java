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
package calcmassview.base;

import calcmassview.MenuCreator;
import calcmassview.settings.ToolTips;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * ������ ���� ������� ��������
 * @author Sergei Lyashko
 */
public class NumberProfileMenu extends JComboBox<String> implements ActionListener {  
    
    private String selectMenu;
    private final BasePanel panel;
    private MenuCreator creator;
    
    public NumberProfileMenu(BasePanel panel) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        this.panel = panel;
        create();
    }
    
    private void create(){
        ToolTips.addToolTips(this, "����� ������ ������� ������");
        panel.add(this);
        this.setLocation(20, 100);
        panel.addPolicy(this);
        creator = new MenuCreator();
        this.setModel(creator.getModel(null, null));
        addActionListener(this);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        resetAllValues();
        @SuppressWarnings("unchecked")
        String currentMenu = 
                ((JComboBox<String>)e.getSource())
                        .getSelectedItem()
                        .toString();
        // �������� ����� ����� ��������
        actionFields(currentMenu);
        // ��������� ������ � ���������� ���� 
        this.selectMenu = currentMenu;
    }
    
    // ��������� ����� ����� ��������
    //TODO ���������� ���
    private void actionFields(String selectMenu){
        if(!selectMenu.equals("� �������")){
            panel.getLengthField().field().action();
            if(panel.getAssortmentMenu().getSelectedItem().equals("����") ||
                    panel.getTypeProfileMenu().getSelectedItem().equals("��������� ��������")){
                panel.getWidthField().field().action();
            }
        }
    }

    public ValueFieldReceivable value() {
        return () -> selectMenu;
    }
    
    private void resetAllValues(){
        panel.reset();
    }
}
