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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * ћеню типов сортамента
 * @author Sergei Lyashko
 */
public class AssortmentProfileMenu extends JComboBox<String> implements ActionListener, ValueReceivable {    
        
    private String selectItem;
    private final BasePanel basePanel;
    private final String text = "выбор сортамента детали";
    
    public AssortmentProfileMenu(BasePanel basePanel, ToolTips toolTips) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 20);
        this.basePanel = basePanel;
        super.setModel(new Menu().createMenu());
        addContent(toolTips);
    }
    
    private void addContent(ToolTips toolTips){
        toolTips.setToolTips(this, text);
        basePanel.add(this);        
        basePanel.addPolicy(this);
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        this.selectItem = selectedMenuItem;
        // создание меню типов профилей
        Menu newTypeProfilesMenu = new Menu().createMenu(selectItem);
        basePanel.getTypeProfileMenu().setModel(newTypeProfilesMenu);
        //сброс параметров полей        
        resetAllFields();        
    }
    
    private void resetAllFields(){
        setMenuStartPosition();
        basePanel.reset();
    }
    
    // установка начальных значений меню
    private void setMenuStartPosition(){
        basePanel.getTypeProfileMenu().setSelectedIndex(0);
        basePanel.getNumberProfileMenu().setSelectedIndex(0);
    }

    @Override
    public String receiveFieldString() {
        return this.selectItem;
    }
}
