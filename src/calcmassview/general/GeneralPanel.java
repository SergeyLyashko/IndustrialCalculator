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
package calcmassview.general;

import calcmassview.ViewObserver;
import calcmassview.base.BasePanel;
import calcmassview.info.InfoPanel;
import calcmassview.settings.SettingsPanel;
import calcmassview.settings.Theme;
import calcmassview.settings.ToolTips;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Основное окно с вкладками
 * @author Sergei Lyashko
 */
public class GeneralPanel extends JPanel implements KeyActionSubjectInterface {      
        
    private JTabbedPane tabbedPane;  
    private BasePanel basePanel;
    private SettingsPanel settingsPanel;
    private InfoPanel infoPanel;
    private JFrame appFrame;
    private final SavedPreference preference;
    private final ArrayList<ViewObserver> observers;
    private Theme theme;
    private ToolTips toolTips;
    
    public GeneralPanel() {
        super(new GridLayout(1, 1));
        observers = new ArrayList<>();
        preference = new SavedPreference();
        addContent();
    }
    
    // создание панелей
    private void addContent(){
        SavedPreference open = preference.load();
        if(open != null){
            theme = open.getTheme();
            theme.action();
            toolTips = open.getToolTips();
            toolTips.currentState();
            settingsPanel = open.getSettingsPanel();
            settingsPanel.addPreference(theme, toolTips);
        }else{
            theme = new Theme();
            theme.dark();
            toolTips = new ToolTips();
            toolTips.oN();
            settingsPanel = new SettingsPanel(theme, toolTips);
        }
        basePanel = new BasePanel(this, theme, toolTips);
        infoPanel = new InfoPanel(theme);
        addTabbedPane();
        createAndShowGUI();
    }
    
    // основное окно
    private void createAndShowGUI(){
        appFrame = new JFrame("Калькулятор масс");
        appFrame.setBounds(300, 300, 360, 220);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        appFrame.getContentPane().add(this, BorderLayout.CENTER);
        closeApp();
        //отображение окна
        appFrame.setVisible(true);
    }
    
    // закрытие приложения
    private void closeApp(){
        appFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {                
                preference.addComponent(settingsPanel, theme, toolTips);
                preference.save();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
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
    
    /**
     * 
     * @return основная панель приложения
     */
    public BasePanel getBasePanel(){
        return basePanel;
    }

    @Override
    public void registerObserver(ViewObserver ob) {
        observers.add(ob);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(ViewObserver::keyActionUpdate);
    }
}
