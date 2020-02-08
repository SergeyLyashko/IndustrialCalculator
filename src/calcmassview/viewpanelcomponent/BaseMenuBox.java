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
package calcmassview.viewpanelcomponent;

import calcmassview.ViewPanel;
import java.awt.event.ActionEvent;

/**
 * ѕанель базового меню
 * @author Sergei Lyashko
 */
public class BaseMenuBox extends AbstractMenuBox {    
        
    private final ViewPanel viewPanel;
    private String selectMenuName;
    
    public BaseMenuBox(ViewPanel viewPanel){
        this.viewPanel = viewPanel;        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        AbstractMenuBox cb = (AbstractMenuBox)e.getSource();
        this.selectMenuName = (String)cb.getSelectedItem();
        //обновление меню типов профилей        
        viewPanel.updateView(selectMenuName, this);
        //установка начальных позиций меню
        viewPanel.startPosition();        
        //сброс параметров полей        
        viewPanel.reset();
    }
    
    /**
     * —троковое предстваление выбранного пункта меню
     * @return выбранный пункт меню
     */
    @Override
    public String getSelectMenuName(){
        return selectMenuName;
    }
}
