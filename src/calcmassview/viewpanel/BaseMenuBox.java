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

import calcmassview.View;
import java.awt.event.ActionEvent;

/**
 * ������ �������� ����
 * @author Sergei Lyashko
 */
public class BaseMenuBox extends AbstractMenuBox {    
        
    private String selectMenu;
    
    // ����������� �������� ���������� ������
    private static final BaseMenuBox INSTANCE = new BaseMenuBox();
    
    /**
     * ��������-����� �������� ������ ����������� ����
     * @return ������ "������� ���� �����������"
     */
    public static final BaseMenuBox getInstance(){
        return INSTANCE;
    }
    
    private BaseMenuBox() {}
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        AbstractMenuBox cb = (AbstractMenuBox)e.getSource();
        String currentMenu = (String)cb.getSelectedItem();
        //���������� ���� ����� ��������        
        View.getInstance().updateView(currentMenu, this);
        //��������� ��������� ������� ����
        startPosition();
        //����� ���������� �����        
        View.getInstance().reset();
        this.selectMenu = currentMenu;
    }
    
    // ��������� ��������� �������� ����
    private void startPosition(){
        TypeProfileMenuBox.getInstance().setSelectedIndex(0);
        NumberProfileMenuBox.getInstance().setSelectedIndex(0);
    }
    
    @Override
    public String getStringValue() {
        return selectMenu;
    }
}
