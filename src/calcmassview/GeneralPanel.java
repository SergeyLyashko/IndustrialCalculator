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

import static calcmassview.AppFrame.APP_FRAME;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * �������� ������ ����
 * @author Sergei Lyashko
 */
public class GeneralPanel extends JPanel {      
       
    public static final GeneralPanel GENERAL_PANEL = new GeneralPanel();
    
    private static JTabbedPane tabbedPane;    
    
    public GeneralPanel() {
        // �������� ����������
        super(new GridLayout(1, 1));        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);       
           
        //���������� ������� � ������
        super.add(tabbedPane);
        APP_FRAME.add(this);        
    }
    
    // ���������� � ������ �������
    public static void addToGeneralPanel(String namePanel, JPanel panel){        
        tabbedPane.addTab(namePanel, panel);        
    }    
}
