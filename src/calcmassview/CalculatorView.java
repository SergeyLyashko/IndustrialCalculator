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
import calcmassview.base.CalculatorPanel;
import calcmassview.base.Detail;
import java.util.stream.Collectors;

/**
 * ������������� ����������
 * @author Sergei Lyashko
 */
public class CalculatorView extends JPanel implements IKeyActionSubject, ViewObserver {

    private static final long serialVersionUID = 1L;
    
    private final CalculatorController controller;
    
    // ��������� �����������
    private ArrayList<JComponent> components;
    
    private final ArrayList<ViewObserver> observers;
    
    private final Preference preference;
    
    private Detail detail;
    
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
     * ���������� ������� �� ������
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
    
    // �������� �������
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
        //TODO �������� �������� ������� ������������ ���� �� ������ 1
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
        String formattedValue = formatDoubleToString(mass);
        
        //generalPanel.getBasePanel().setResultation(resultValue);
    }
    
    //�������������� ������ ����������
    private String formatDoubleToString(double value){
        return new DecimalFormat("#.###").format(value);
    }
    
    @Override
    public void errorMessageUpdate(String message) {
        if(message != null){
            //generalPanel.getBasePanel().setError(message);
        }
    }
    
    
    
    @Override
    public void keyActionUpdate() {
        setParametrsToController();
    }
    
    // ��������� �������� �����
    private void setParametrsToController(){
        receiveDetail();
        controller.setDetail(detail);
    }
    
    private void receiveDetail(){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(CalculatorPanel.class))
                .forEach((JComponent component) -> {
                    this.detail = ((CalculatorPanelImpl)component).getDetail();
                        });
    }
}
