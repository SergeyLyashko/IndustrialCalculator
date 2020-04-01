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
 * ����������� ���������
 * @author Sergei Lyashko
 */
class ToolTipsOffBox extends AbstractSettingChBox {   
    
    public ToolTipsOffBox(){
        create();
    }
    
    private void create(){
        super.setSelected(true);
        super.setSize(250, 20);
        super.setText("��������� ����������� ���������");             
        Theme.addTheme(this);
    }
    
    @Override
    public void actionChooser(ItemEvent e) {
        setToolTips(e.getStateChange());
    }
    
    // ��������� ����������� ���������
    private void setToolTips(int stateChange){
        switch(stateChange){
            case ItemEvent.SELECTED:
                //TODO
                break;
            case ItemEvent.DESELECTED:
                //TODO
                break;            
        }
    }    
}
