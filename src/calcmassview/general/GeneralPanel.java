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
import calcmassview.settings.ToolTipsInterface;
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
        
    private BasePanel basePanel;
    private SettingsPanel settingsPanel;
    private InfoPanel infoPanel;
    private final Preference preference;
    private final ArrayList<ViewObserver> observers;
    private Theme theme;
    private ToolTipsInterface toolTips;
    
    public GeneralPanel() {
        super(new GridLayout(1, 1));
        observers = new ArrayList<>();
        preference = new Preference();
        createPanels();
        createAndShowGUI();
    }
    
    // создание панелей
    private void createPanels(){
        Preference saved = preference.load();        
        if(saved != null){
            this.settingsPanel = loadPreference(saved);
        }else{
            this.settingsPanel = newPreference();
        }
        this.toolTips = settingsPanel.getToolTips();
        this.basePanel = new BasePanel(this, theme, toolTips);
        this.infoPanel = new InfoPanel(theme);
        addTabbedPane(basePanel, settingsPanel, infoPanel);        
    }
    
    // загрузка сохраненных настроек
    private SettingsPanel loadPreference(Preference saved){
        theme = saved.getTheme();
        theme.setThemeChangedCompontnts();
        toolTips = saved.getToolTips();
        toolTips.currentState();
        SettingsPanel panel = saved.getSettingsPanel();
        panel.addPreference(theme, toolTips);
        return panel;
    }
    
    // сохранение настроек
    private void savePreference(){
        preference.addComponent(settingsPanel, theme, toolTips);
        preference.save();
    }
    
    // новые настройки оформления
    private SettingsPanel newPreference(){
        theme = new Theme();
        theme.dark();        
        return new SettingsPanel(theme);
    }
    
    // основное окно
    private void createAndShowGUI(){
        JFrame appFrame = new JFrame("Калькулятор масс");
        appFrame.setBounds(300, 300, 360, 220);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        appFrame.getContentPane().add(this, BorderLayout.CENTER);
        closeApp(appFrame);
        //отображение окна
        appFrame.setVisible(true);
    }
    
    // закрытие приложения
    private void closeApp(JFrame appFrame){
        appFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {                
                savePreference();
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
    private void addTabbedPane(BasePanel basePanel, SettingsPanel settingsPanel, InfoPanel infoPanel){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
