/*
 * Copyright 2019 Sergei Lyashko. Contacts: <9lLLLepuLLa@gmail.com>.
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
package calcmassview.settings;

import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;

/**
 * ����������� ���������
 * @author Sergei Lyashko
 */
public class ToolTips implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private boolean state;
    
    /**
     * ��������� �������� ���������
     */
    public void currentState(){
        ToolTipManager.sharedInstance().setEnabled(state);
    }
        
    /**
     * ��������� ����������� ���������
     * @param component
     * @param text
     */
    public void setToolTips(JComponent component, String text){
        component.setToolTipText(text);
    }
        
    /**
     * ��������� ����������� ���������
     */
    public void oN(){
        this.state = true;
        ToolTipManager.sharedInstance().setEnabled(state);
    }
    
    /**
     * ���������� ����������� ���������
     */
    public void oFF(){
        this.state = false;
        ToolTipManager.sharedInstance().setEnabled(state);
    }
}
