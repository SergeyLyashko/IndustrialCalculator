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
import calcmassview.settings.ToolTips;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * Выпадающее меню типов профилей
 * для выбранного сортамента
 * @author Sergei Lyashko
 */
public class TypeProfileMenu extends JComboBox<String> implements ActionListener {   
    
    private final BasePanel basePanel;
    private final Menu menu;
    private String selectedMenuItem;
    private final ToolTips toolTips;
    private final String text = "выбор типа профиля детали";
    
    public TypeProfileMenu(BasePanel basePanel, ToolTips toolTips) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        this.basePanel = basePanel;
        this.toolTips = toolTips;
        menu = new Menu();
        addContent();
    }
    
    private void addContent(){
        toolTips.setToolTips(this, text);
        basePanel.add(this);
        this.setLocation(20, 60);
        basePanel.addPolicy(this);
        this.setModel(menu.createMenu(null));
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        resetAllValues();
        @SuppressWarnings("unchecked")
        String selectedItem = 
                ((JComboBox<String>)e.getSource())
                .getSelectedItem()
                .toString();
        this.selectedMenuItem = selectedItem;
        // обновление меню номеров профилей
        updateMenu(selectedMenuItem);
    }
    
    private void updateMenu(String currentMenuItem){
        String selectedAssortment = 
                basePanel.getAssortmentMenu()
                        .value()
                        .receive();
        MenuModel numberMenuModel = menu.createMenu(selectedAssortment, currentMenuItem);
        basePanel.getNumberProfileMenu().setModel(numberMenuModel);
    }
    
    public ValueReceivable value() {
        return () -> selectedMenuItem;
    }
    
    private void resetAllValues(){
        basePanel.reset();
    }
}
