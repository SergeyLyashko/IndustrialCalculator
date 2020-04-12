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
import java.awt.event.ActionEvent;

/**
 * Выпадающее меню типов профилей
 * для выбранного сортамента
 * @author Sergei Lyashko
 */
public class TypeProfileMenu extends AbstractMenu {   
    
    private final BasePanel panel;
    private MenuCreator creator;
    private String selectMenu;
    
    public TypeProfileMenu(BasePanel panel) {
        super(panel);
        this.panel = panel;
        create();
    }
    
    private void create(){
        panel.add(this);
        this.setLocation(20, 60);
        panel.addPolicy(this);
        creator = new MenuCreator();
        this.setModel(creator.getModel(null));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractMenu cb = (AbstractMenu)e.getSource();
        String currentMenu = (String)cb.getSelectedItem();
        // сброс значений
        super.resetAllValues();
        // обновление меню номеров профилей
        updateMenu(currentMenu);
        this.selectMenu = currentMenu;
    }
    
    private void updateMenu(String currentMenuItem){
        AbstractMenu baseMenuBox = panel.getBaseMenuBox();
        String selectedAssortment = baseMenuBox.value().getValue();
        MenuBoxModel numberMenuModel = creator.getModel(selectedAssortment, currentMenuItem);
        panel.getNumberProfileMenuBox().setModel(numberMenuModel);
    }
    
    
    @Override
    public ValueFieldReceivable value() {
        return () -> selectMenu;
    }
}
