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
package calcmassview;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * �������� ������ ���� ����������
 * @author Sergei Lyashko
 */
public class GeneralPanel extends JPanel {      
    
    // ����������� �������� ��������� ������
    private static final GeneralPanel INSTANCE = new GeneralPanel();
    
    /**
     * ����� ��������� ���������� ������-���������
     * @return ��������� ������
     */
    public static final GeneralPanel getInstance(){
        return INSTANCE;
    }
    
    private final JTabbedPane tabbedPane;    
    
    private GeneralPanel() {
        // �������� ����������
        super(new GridLayout(1, 1));        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        //���������� ������� � ������
        super.add(tabbedPane);
        AppFrame.getInstance().add(this);  
    }
    
    /**
     * ����� ����������� ������ � �������� ������ �������
     * @param namePanel ������������ ������
     * @param panel ����������� ������
     */
    public void addToGeneralPanel(String namePanel, JPanel panel){        
        tabbedPane.addTab(namePanel, panel);        
    }    
}
