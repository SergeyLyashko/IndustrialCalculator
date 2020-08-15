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
import calcmassview.base.CalculatorPanelImpl;
import calcmassview.info.InfoPanel;
import calcmassview.settings.SettingsPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import calcdatabase.DataBase;
import calcmassview.base.IKeyActionObserver;
import javax.swing.JComponent;

/**
 * Основное окно с вкладками
 * @author Sergei Lyashko
 */
@SuppressWarnings("serial")
public class GeneralPanel extends JPanel implements IKeyActionSubject {      
        
    // коллекция компонентов
    private final ArrayList<JComponent> components;
    private CalculatorPanelImpl calculatorPanel;
    private SettingsPanel settingsPanel;
    private InfoPanel infoPanel;
    private final Preference preference;
    private final ArrayList<ViewObserver> observers;
    
    public GeneralPanel(DataBase dataBase) {
        super(new GridLayout(1, 1));
        this.components = new ArrayList<>();
        //TODO
        observers = new ArrayList<>();
        
        preference = new Preference();        
        //
        createPanels(dataBase);
        createAndShowGUI();
    }
    
    // создание панелей
    private void createPanels(DataBase dataBase){
        Preference saved = preference.load();
        
        this.calculatorPanel = new CalculatorPanelImpl(components, dataBase);
        components.add(calculatorPanel);
        
        infoPanel = new InfoPanel(components);
        components.add(infoPanel);
        
        if(saved != null){
            loadPreference(saved);
        }else{
            this.settingsPanel = new SettingsPanel(components);
        }        
        //
        addTabbedPane();        
    }
    
    /**
     * Добавление вкладок на панель
     */
    private void addTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Калькулятор", calculatorPanel);       
        tabbedPane.addTab("Настройки", settingsPanel);
        tabbedPane.addTab("Справка", infoPanel);
        this.add(tabbedPane);
    }
    
    
    // загрузка сохраненных настроек
    private void loadPreference(Preference savedPreference){
        this.settingsPanel = savedPreference.getSettingsPanel();
    }
    
    // сохранение настроек
    private void savePreference(){
        //preference.addComponent(settingsPanel);
        //preference.save();
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

    @Override
    public void registerObserver(ViewObserver ob) {
        //TODO написать создание массива наблюдателей если их больше 1
        observers.add(ob);
    }

    @Override
    public void notifyObservers() {
        System.out.println("general observers");// TEST
        observers.stream().forEach(ViewObserver::keyActionUpdate);
    }

    @Override
    public void registerObserver(IKeyActionObserver keyActionObserver) {
        
    }
}
