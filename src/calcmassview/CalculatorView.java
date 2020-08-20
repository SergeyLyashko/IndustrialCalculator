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
package calcmassview;

import java.text.DecimalFormat;
import calcmasscontroller.ICalculatorController;
import calcmassmodel.ICalculatorModel;
import calcmassview.base.CalculatorPanelImpl;
import calcmassview.base.IKeyActionObserver;
import calcmassview.info.InfoPanel;
import calcmassview.settings.ColorTheme;
import calcmassview.settings.SettingsPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Представление приложения
 * @author Sergei Lyashko
 */
public class CalculatorView extends JPanel implements IKeyActionSubject, ViewObserver {

    private static final long serialVersionUID = 1L;
    
    private final ICalculatorModel model;
    private final ICalculatorController controller;
    
    // коллекция компонентов
    private final ArrayList<JComponent> components;
    private JPanel calculatorPanel;
    private JPanel settingsPanel;
    private JPanel infoPanel;
    private Preference preference;
    
    private List<JPanel> panels = new ArrayList<>();
    
    private final ArrayList<ViewObserver> observers;
    
    private String profileAssortment, profileType, profileNumber, length, width;
    private String resultValue;
    
    public CalculatorView(ICalculatorModel model, ICalculatorController controller){
        super(new GridLayout(1, 1));
        this.model = model;
        this.controller = controller;
        this.components = new ArrayList<>();
        //TODO
        observers = new ArrayList<>();        
        createPanels();
        createAndShowGUI();
        registerObservers();
    }
    
    // создание панелей
    private void createPanels(){        
        //
        preference = new Preference();
        if(preference.isSaved()){
            panels = preference.load();
        }else{        
            this.calculatorPanel = new CalculatorPanelImpl(components);
            this.infoPanel = new InfoPanel(components);
            components.add(calculatorPanel);
            this.settingsPanel = new SettingsPanel(components);            
            //components.add(settingsPanel);
            components.add(infoPanel);
        }
        components.stream()
                .filter((JComponent component) -> component.getClass().getSuperclass().isAssignableFrom(JPanel.class))
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(ColorTheme.class))
                .forEach((JComponent component) -> {
                    panels.add((JPanel)component);
                });
        this.setTabbedPane(panels);        
    }
    
    /**
     * Добавление вкладок на панель
     */
    private void setTabbedPane(List<JPanel> panels){        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        panels.stream().forEach((JPanel panel) -> tabbedPane.addTab(panel.getName(), panel));
        this.add(tabbedPane);
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
                preference.save(components);
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
        //System.out.println("general observers");// TEST
        observers.stream().forEach(ViewObserver::keyActionUpdate);
    }

    @Override
    public void registerObserver(IKeyActionObserver keyActionObserver) {
        
    }
    
    
    // регистрация наблюдателей
    private void registerObservers(){
        model.registerObserver(this);        
    }
    
    @Override
    public void massUpdate(double mass) {
        formatDoubleToString(mass);
        //generalPanel.getBasePanel().setResultation(resultValue);
    }
    
    @Override
    public void errorMessageUpdate(String message) {
        if(message != null){
            //generalPanel.getBasePanel().setError(message);
        }
    }
    
    //форматирование строки результата
    private void formatDoubleToString(double mass){
        this.resultValue = new DecimalFormat("#.###").format(mass);
    }
    
    @Override
    public void keyActionUpdate() {
        setParametrsToController();
    }
    
    // установка значений полей
    private void setParametrsToController(){
        /*getFieldsValue();
        boolean areaBoxOFF = generalPanel.getBasePanel().getDifficultAreaBox().isAreaBoxOFF();
        if(areaBoxOFF){
            controller.setFieldsValue(profileAssortment, profileType, profileNumber, length, width);
        }else{
            String area = this.length;
            controller.setFieldsValue(profileAssortment, profileType, profileNumber, area);
        }*/
    }
    
    // получение значений полей
    private void getFieldsValue(){
        /*Detail calculatorData = generalPanel.getBasePanel().getDetail();
        this.profileAssortment = calculatorData.getAssortment();        
        this.profileType = calculatorData.getType();        
        this.profileNumber = calculatorData.getNumber();        
        this.length = calculatorData.getLength();        
        this.width = calculatorData.getWidth();
        */
    }
}
