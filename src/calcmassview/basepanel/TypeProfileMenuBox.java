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
 * Выпадающее меню типов профилей
 * для выбранного сортамента
 * @author Sergei Lyashko
 */
public class TypeProfileMenuBox extends AbstractMenuBox implements ValueFieldReceivable {   
    
    private final BasePanel panel;
    private MenuCreator creator;
    private String selectMenu;
    
    public TypeProfileMenuBox(BasePanel panel) {
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
        AbstractMenuBox cb = (AbstractMenuBox)e.getSource();
        String currentMenu = (String)cb.getSelectedItem();
        // сброс значений
        panel.reset();
        // обновление меню номеров профилей
        ValueFieldReceivable baseMenuBox = (ValueFieldReceivable) panel.getBaseMenuBox();
        String selectedAssortment = baseMenuBox.getValueOfField();
        MenuBoxModel numberMenuModel = creator.getModel(selectedAssortment, currentMenu);
        panel.getNumberProfileMenuBox().setModel(numberMenuModel);
        this.selectMenu = currentMenu;
    }
    
    @Override
    public String getValueOfField() {
        return selectMenu;
    }
}
