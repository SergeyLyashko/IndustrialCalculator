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
 * Панель меню номеров профилей
 * @author Sergei Lyashko
 */
public class NumberProfileMenu extends JComboBox<String> implements ActionListener, ValueReceivable {  
    
    private String selectItem;
    private final BasePanel basePanel;
    private final String text = "выбор номера профиля детали";
    
    public NumberProfileMenu(BasePanel basePanel, ToolTips toolTips) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 100);
        this.basePanel = basePanel;
        addConent(toolTips);
    }
    
    private void addConent(ToolTips toolTips){
        Menu emptyMenu = new Menu(basePanel.getDataBase());
        super.setModel(emptyMenu.addHeaderInMenu(this));
        toolTips.setToolTips(this, text);
        basePanel.add(this);        
        basePanel.addPolicy(this);        
        addActionListener(this);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        resetAllFields();
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        this.selectItem = selectedMenuItem;
        // активаци полей ввода значений
        actionFields(selectedMenuItem);
    }
    
    // активация полей ввода значений
    private void actionFields(String selectMenu){
        if(!selectMenu.equals("№ профиля")){
            basePanel.getLengthField().perform().activation();
            if(basePanel.getAssortmentMenu().getSelectedItem().equals("Лист") ||
                    basePanel.getTypeProfileMenu().getSelectedItem().equals("Резиновая пластина")){
                basePanel.getWidthField().perform().activation();
            }
        }
    }

    private void resetAllFields(){
        basePanel.reset();
    }

    @Override
    public String receiveFieldString() {
        return this.selectItem;
    }
}
