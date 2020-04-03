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
package calcmassview.settingpanel;

import java.awt.event.ItemEvent;

/**
 * Color theme
 * @author Sergei Lyashko
 */
class ThemeChBox extends AbstractSettingChBox {

    private Theme theme;
    
    public ThemeChBox(){
        create();
    }
    
    private void create(){
        super.setSelected(true);
        super.setSize(180, 20);
        super.setText("темная тема оформления");      
        setTheme(super.isSelected());
    }
    
    // смена темы оформления
    @Override
    public void actionChooser(ItemEvent e) {              
        setTheme(e.getStateChange());
    } 
    
    private void setTheme(int stateChange){    
        switch(stateChange){
            case ItemEvent.SELECTED:
                theme = new Theme();
                theme.dark();
                break;
            case ItemEvent.DESELECTED:
                theme = new Theme();
                theme.light();
                break;            
        }        
    }
    
    private void setTheme(boolean select){
        Theme.addThemeBox(select);
    }
}
