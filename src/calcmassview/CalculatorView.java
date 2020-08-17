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
import calcdatabase.DataBase;
import calcmasscontroller.ICalculatorController;
import calcmassmodel.ICalculatorModel;
import calcmassview.base.CalculatorPanelImpl;
import calcmassview.base.IKeyActionObserver;
import calcmassview.info.InfoPanel;
import calcmassview.settings.SettingsPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * ѕредставление приложени€
 * @author Sergei Lyashko
 */
@SuppressWarnings("serial")
public class CalculatorView extends JPanel implements IKeyActionSubject, ViewObserver {
    
    private final ICalculatorModel model;
    private final ICalculatorController controller;
    
    // коллекци€ компонентов
    private final ArrayList<JComponent> components;
    private CalculatorPanelImpl calculatorPanel;
    private SettingsPanel settingsPanel;
    private InfoPanel infoPanel;
    
    private final ArrayList<ViewObserver> observers;
    private final DataBase dataBase;
    
    private String profileAssortment, profileType, profileNumber, length, width;
    private String resultValue;
    
    public CalculatorView(ICalculatorModel model, ICalculatorController controller, DataBase dataBase){
        super(new GridLayout(1, 1));
        this.model = model;
        this.controller = controller;
        this.dataBase = dataBase;
        this.components = new ArrayList<>();
        //TODO
        observers = new ArrayList<>(); 
        
        createPanels();
        createAndShowGUI();
        registerObservers();
    }
    
    // создание панелей
    private void createPanels(){
        
        this.calculatorPanel = new CalculatorPanelImpl(components, dataBase);
        components.add(calculatorPanel);
        
        infoPanel = new InfoPanel(components);
        components.add(infoPanel);
        
        this.settingsPanel = new SettingsPanel(components);
        
        //
        this.setTabbedPane();        
    }
    
    /**
     * ƒобавление вкладок на панель
     */
    private void setTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab(" алькул€тор", calculatorPanel);       
        tabbedPane.addTab("Ќастройки", settingsPanel);
        tabbedPane.addTab("—правка", infoPanel);
        this.add(tabbedPane);
    }
    
    
    
    // основное окно
    private void createAndShowGUI(){
        JFrame appFrame = new JFrame(" алькул€тор масс");
        appFrame.setBounds(300, 300, 360, 220);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        appFrame.getContentPane().add(this, BorderLayout.CENTER);
        closeApp(appFrame);
        //отображение окна
        appFrame.setVisible(true);
    }
    
    // закрытие приложени€
    private void closeApp(JFrame appFrame){
        appFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("win open");// TEST
            }

            @Override
            public void windowClosing(WindowEvent e) {                
                settingsPanel.savePreference();
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
    
    
    // регистраци€ наблюдателей
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
        /*Detail calculatorData = generalPanel.getBasePanel().getData();
        this.profileAssortment = calculatorData.getAssortment();        
        this.profileType = calculatorData.getType();        
        this.profileNumber = calculatorData.getNumber();        
        this.length = calculatorData.getLength();        
        this.width = calculatorData.getWidth();
        */
    }
}
