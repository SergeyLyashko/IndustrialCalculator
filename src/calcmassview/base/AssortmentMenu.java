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
 * Меню типов сортамента
 * @author Sergei Lyashko
 */
public class AssortmentMenu extends AbstractMenu {    
        
    private String selectMenu;
    private final BasePanel panel;
    private MenuCreator creator;
    
    public AssortmentMenu(BasePanel panel) {
        super(panel);
        this.panel = panel;
        create();
    }
    
    private void create(){
        panel.add(this);
        this.setLocation(20, 20);
        panel.addPolicy(this);
        creator = new MenuCreator();
        this.setModel(creator.getModel());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        AbstractMenu cb = (AbstractMenu)e.getSource();
        String currentMenu = (String)cb.getSelectedItem();
        //обновление меню типов профилей        
        MenuBoxModel typeMenuModel = creator.getModel(currentMenu);
        panel.getTypeProfileMenuBox().setModel(typeMenuModel);
        //установка начальных позиций меню
        setStartPosition();
        //сброс параметров полей        
        super.resetAllValues();
        this.selectMenu = currentMenu;
    }
    
    // установка начальных значений меню
    private void setStartPosition(){
        panel.getTypeProfileMenuBox().setSelectedIndex(0);
        panel.getNumberProfileMenuBox().setSelectedIndex(0);
    }
    
    @Override
    public ValueFieldReceivable value() {
        return () -> selectMenu;
    }
}
