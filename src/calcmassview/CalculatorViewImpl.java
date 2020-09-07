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

import calcmasscontroller.CalculatorController;
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
import calcmassview.base.CalculatorPanel;
import java.util.stream.Collectors;
import calcmassview.base.FieldsData;
import calcmassview.info.Info;
import calcmassmodel.OutputService;

/**
 * ѕредставление приложени€
 * @author Sergei Lyashko
 */
public class CalculatorViewImpl extends JPanel implements CalculatorView, IKeyActionSubject  {

    private static final long serialVersionUID = 1L;
    
    // коллекци€ компонентов
    private ArrayList<JComponent> components;
    
    private final Preference preference;
    
    private FieldsData data;
    private MenuListReceiver menuListReceiver;
    private final Info info;
    private final CalculatorController controller;

    public CalculatorViewImpl(Info info, CalculatorController controller) {
        super(new GridLayout(1, 1));
        this.info = info;
        this.controller = controller;
        this.preference = new Preference();
        List<JPanel> panels = loadPanels();
        this.addToTabbedPane(panels);
    }
    
    private List<JPanel> loadPanels(){
        if(preference.isSaved()){
            this.components = preference.loadSavedComponents();
        }else{
            createComponents();
        }
        return extractPanels();
    }
    
    private void addToTabbedPane(List<JPanel> panels){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);             
        panels.stream().forEach((JPanel panel) -> tabbedPane.addTab(panel.getName(), panel));
        this.add(tabbedPane);
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
        JPanel calculatorPanel = new CalculatorPanelImpl(components, menuListReceiver);
        components.add(calculatorPanel);
        JPanel infoPanel = new InfoPanel(components, info);            
        JPanel settingsPanel = new SettingsPanel(components);                                
        components.add(settingsPanel);
        components.add(infoPanel);
        new CalculatorFrame(this);
    }
    
    @Override
    public void setMenuListReceiver(MenuListReceiver menuListReceiver) {
        this.menuListReceiver = menuListReceiver;
    }
    
    public void savePreferences() {
        preference.save(components);
    }
    
    private void showResult() {
        OutputService outputData = controller.getOutputData();
        double detailMass = outputData.getDetailMass();
        String formattedValue = formatDoubleToString(detailMass);
    }
    
    //форматирование строки результата
    private String formatDoubleToString(double value){
        return new DecimalFormat("#.###").format(value);
    }
    
    private void showError() {
        OutputService outputData = controller.getOutputData();
        String errorMessage = outputData.getErrorMessage();
    }
    
    public void keyActionUpdate() {
        setParametrsToController();
    }
    
    // установка значений полей
    private void setParametrsToController(){
        receiveFieldsData();
        //controller.setDetail(data);
    }
    
    private void receiveFieldsData(){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(CalculatorPanel.class))
                .forEach((JComponent component) -> {
                    this.data = ((CalculatorPanelImpl)component).getFieldsData();
                        });
    }

    @Override
    public void registerObserver(CalculatorViewImpl viewObserver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerObserver(IKeyActionObserver keyActionObserver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
