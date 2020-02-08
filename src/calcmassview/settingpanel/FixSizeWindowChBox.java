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
 * Фиксация размеров окна
 * @author Sergei Lyashko
 */
public class FixSizeWindowChBox extends AbstractSettingChBox {   
    
    public FixSizeWindowChBox(){
        super.setSelected(true);
        super.setSize(200, 20);
        super.setText("зафиксировать размер окна");             
        Theme.addTheme(this);
    }
    
    //TODO
    @Override
    public void actionChooser(ItemEvent e) {
        System.out.println("test Size Win");             
    }    
}
