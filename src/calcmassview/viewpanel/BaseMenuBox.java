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

import calcmassview.AbstractPanel;
import calcmassview.BasePanel;
import calcmassview.MenuCreator;
import java.awt.event.ActionEvent;

/**
 * Выпадающее меню типов сортамента
 * @author Sergei Lyashko
 */
public class BaseMenuBox extends AbstractMenuBox implements ValueReceivable {    
        
    private String selectMenu;
    private final AbstractPanel panel;
    private MenuCreator creator;
    
    public BaseMenuBox(AbstractPanel panel) {
        super(panel);
        this.panel = panel;
        create();
    }
    
    private void create(){
        panel.add(this);
        this.setLocation(20, 20);
        ((BasePanel)panel).addPolicy(this);
        creator = ((BasePanel)panel).getMenuCreator();
        this.setModel(creator.getModel());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        AbstractMenuBox cb = (AbstractMenuBox)e.getSource();
        String currentMenu = (String)cb.getSelectedItem();
        //обновление меню типов профилей        
        MenuBoxModel typeMenuModel = creator.getModel(currentMenu);
        ((BasePanel)panel).getTypeProfileMenuBox().setModel(typeMenuModel);
        //установка начальных позиций меню
        startPosition();
        //сброс параметров полей        
        ((BasePanel)panel).reset();
        this.selectMenu = currentMenu;
    }
    
    // установка начальных значений меню
    private void startPosition(){
        ((BasePanel)panel).getTypeProfileMenuBox().setSelectedIndex(0);
        ((BasePanel)panel).getNumberProfileMenuBox().setSelectedIndex(0);
    }
    
    @Override
    public String getValueOfField() {
        return selectMenu;
    }
}
