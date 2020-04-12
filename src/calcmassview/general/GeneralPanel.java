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
package calcmassview.general;

import calcmassview.KeyActionObserver;
import calcmassview.base.BasePanel;
import calcmassview.info.InfoPanel;
import calcmassview.settings.SettingsPanel;
import calcmassview.settings.Theme;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Основное окно с вкладками
 * @author Sergei Lyashko
 */
public class GeneralPanel extends JPanel implements ISubject {      
        
    private JTabbedPane tabbedPane;  
    private BasePanel basePanel;
    private SettingsPanel settingsPanel;
    private InfoPanel infoPanel;
    private ArrayList<KeyActionObserver> observers;
    
    public GeneralPanel() {
        super(new GridLayout(1, 1));
        create();
    }
    
    private void create(){
        observers = new ArrayList<>();
        basePanel = new BasePanel(this);
        settingsPanel = new SettingsPanel();
        infoPanel = new InfoPanel();
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
        Theme.defaultTheme();
    }
    
    public BasePanel getBasePanel(){
        return basePanel;
    }

    @Override
    public void registerObserver(KeyActionObserver ob) {
        observers.add(ob);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(KeyActionObserver::keyActionUpdate);
    }
}
