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

import calcmassview.Menu;
import calcmassview.settings.ToolTipsChBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * Меню типов сортамента
 * @author Sergei Lyashko
 */
public class AssortmentProfileMenu extends JComboBox<String> implements ActionListener {    
        
    private String selectMenu;
    private final BasePanel basePanel;
    private final Menu menuCreator;
    
    public AssortmentProfileMenu(BasePanel basePanel) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        this.basePanel = basePanel;
        menuCreator = new Menu();
        addContent();
    }
    
    private void addContent(){
        ToolTipsChBox.addToolTips(this, "выбор сортамента");
        basePanel.add(this);
        this.setLocation(20, 20);
        basePanel.addPolicy(this);
        this.setModel(menuCreator.createMenu());
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        @SuppressWarnings("unchecked")
        String currentMenu = ((JComboBox<String>)e.getSource())
                .getSelectedItem()
                .toString();
        this.selectMenu = currentMenu;
        //обновление меню типов профилей        
        MenuModel typeMenuModel = menuCreator.createMenu(selectMenu);
        basePanel.getTypeProfileMenu().setModel(typeMenuModel);
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
    
    public ValueReceivable value() {
        return () -> selectMenu;
    }
}
