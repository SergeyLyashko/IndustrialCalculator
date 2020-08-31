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

import calcdatabase.DataBaseMenuReceiver;
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

/**
 * Представление приложения
 * @author Sergei Lyashko
 */
public class CalculatorViewImpl extends JPanel implements /*IKeyActionSubject,*/ CalculatorView {

    private static final long serialVersionUID = 1L;
    
    // коллекция компонентов
    private ArrayList<JComponent> components;
    
    private final Preference preference;
    
    private FieldsData data;
    private final DataBaseMenuReceiver receiver;
    
    public CalculatorViewImpl(DataBaseMenuReceiver receiver){
        super(new GridLayout(1, 1));
        this.receiver = receiver;
        new CalculatorFrame(this);
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
    
    /**
     * Добавление вкладок на панель
     */
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
        JPanel calculatorPanel = new CalculatorPanelImpl(components, receiver);
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
    public void showResult(double mass) {
        String formattedValue = formatDoubleToString(mass);
        
        //generalPanel.getBasePanel().setResultation(resultValue);
    }
    
    //форматирование строки результата
    private String formatDoubleToString(double value){
        return new DecimalFormat("#.###").format(value);
    }
    
    @Override
    public void showError(String message) {
        if(message != null){
            //generalPanel.getBasePanel().setError(message);
        }
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
    public FieldsData getData() {
        return data;
    }
}
