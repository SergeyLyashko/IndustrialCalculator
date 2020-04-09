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
package calcmassview.basepanel;

import calcmassview.MenuCreator;
import java.awt.event.ActionEvent;

/**
 * ������ ���� ������� ��������
 * @author Sergei Lyashko
 */
public class NumberProfileMenuBox extends AbstractMenuBox implements ValueFieldReceivable {  
    
    private String selectMenu;
    private final BasePanel panel;
    private MenuCreator creator;
    
    public NumberProfileMenuBox(BasePanel panel) {
        super(panel);
        this.panel = panel;
        create();
    }
    
    private void create(){
        panel.add(this);
        this.setLocation(20, 100);
        panel.addPolicy(this);
        creator = new MenuCreator();
        this.setModel(creator.getModel(null, null));
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractMenuBox cb = (AbstractMenuBox)e.getSource();
        String currentMenu = (String)cb.getSelectedItem();
        // ����� ��������
        panel.reset();
        // �������� ����� ����� ��������
        actionFields(currentMenu);
        // ��������� ������ � ���������� ���� 
        this.selectMenu = currentMenu;
    }
    
    // ��������� ����� ����� ��������
    //TODO ���������� ���
    private void actionFields(String selectMenu){
        if(!selectMenu.equals("� �������")){
            panel.actionLengthField().action();
            if(panel.getBaseMenuBox().getSelectedItem().equals("����") ||
                    panel.getTypeProfileMenuBox().getSelectedItem().equals("��������� ��������")){
                panel.actionWidthField().action();
            }
        }
    }

    @Override
    public String getValueOfField() {
        return selectMenu;
    }
}
