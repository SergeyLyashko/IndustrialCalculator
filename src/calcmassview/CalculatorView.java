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
import calcmassview.base.CalculatorPanelImpl;
import calcmassview.base.IKeyActionObserver;
import calcmassview.info.InfoPanel;
import calcmassview.settings.ColorTheme;
import calcmassview.settings.SettingsPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import calcmasscontroller.CalculatorController;
import java.util.stream.Collectors;

/**
 * Представление приложения
 * @author Sergei Lyashko
 */
public class CalculatorView extends JPanel implements IKeyActionSubject, ViewObserver {

    private static final long serialVersionUID = 1L;
    
    private final CalculatorController controller;
    
    // коллекция компонентов
    private ArrayList<JComponent> components;
    
    private final ArrayList<ViewObserver> observers;
    
    private final Preference preference;
    
    //private String profileAssortment, profileType, profileNumber, length, width;
    private String resultValue;
    
    public CalculatorView(CalculatorController controller){
        super(new GridLayout(1, 1));
        this.controller = controller;
        new CalculatorFrame(this);
        this.preference = new Preference();
        //TODO
        observers = new ArrayList<>();        
        this.addToTabbedPane();
    }
    
    /**
     * Добавление вкладок на панель
     */
    private void addToTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        List<JPanel> panels = loadPanels();        
        panels.stream().forEach((JPanel panel) -> tabbedPane.addTab(panel.getName(), panel));
        this.add(tabbedPane);
    }
    
    private List<JPanel> loadPanels(){
        if(preference.isSaved()){
            this.components = preference.loadSavedComponents();
        }else{
            createComponents();
        }
        return extractPanels();
    }
    
    private List<JPanel> extractPanels(){
        return components.stream()
                    .filter((JComponent component) -> component.getClass().getSuperclass().isAssignableFrom(JPanel.class))
                    .filter((JComponent component) -> component.getClass().isAnnotationPresent(ColorTheme.class))
                    .map((JComponent component) -> (JPanel)component)
                    .collect(Collectors.toList());
    }
    
    // создание панелей
    private void createComponents(){
        components = new ArrayList<>();
        JPanel calculatorPanel = new CalculatorPanelImpl(components);
        components.add(calculatorPanel);
        JPanel infoPanel = new InfoPanel(components);            
        JPanel settingsPanel = new SettingsPanel(components);                                
        components.add(settingsPanel);
        components.add(infoPanel);
    }
    
    public void savePreferences() {
        preference.save(components);
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
