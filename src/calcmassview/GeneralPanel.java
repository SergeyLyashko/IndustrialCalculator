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

import calcmassview.basepanel.BasePanel;
import calcmassview.settingspanel.SettingsPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Основное окно с вкладками
 * @author Sergei Lyashko
 */
class GeneralPanel extends JPanel {      
        
    private JTabbedPane tabbedPane;  
    private BasePanel basePanel;
    private SettingsPanel settingsPanel;
    private InfoPanel infoPanel;
    
    public GeneralPanel() {
        // менеджер компоновки
        super(new GridLayout(1, 1));  
        create();
    }
    
    private void create(){
        this.basePanel = new BasePanel();
        this.settingsPanel = new SettingsPanel();
        this.infoPanel = new InfoPanel();
        addTabbedPane();
        createAndShowGUI();
    }
    
    // основное окно
    private void createAndShowGUI(){
        JFrame app = new JFrame("Калькулятор масс");
        app.setBounds(300, 300, 360, 220);
        app.setResizable(false);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.getContentPane().add(this, BorderLayout.CENTER);
        //отображение окна
        app.setVisible(true);
    }
    
    /**
     * Добавление вкладок на панель
     */
    private void addTabbedPane(){
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Калькулятор", basePanel);       
        tabbedPane.addTab("Настройки", settingsPanel);
        tabbedPane.addTab("Справка", infoPanel);
        this.add(tabbedPane);
    }
    
    public BasePanel getBasePanel(){
        return basePanel;
    }
}
