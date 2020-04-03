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

import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

/**
 * ����������� ����� ���-������
 * @author Sergei Lyashko
 */
public abstract class AbstractSettingChBox extends JCheckBox { 
    
    public AbstractSettingChBox(){
        super.setBackground(Color.BLACK);
        super.setForeground(Color.white);
        Theme.addTheme(this);
    }    
    
    /**
     * Select/deselect ���-�����
     * @param e ������� select ��� deselect
     */
    public abstract void actionChooser(ItemEvent e);
}
