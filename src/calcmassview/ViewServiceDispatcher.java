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

import calcdatabase.ViewService;
import java.text.DecimalFormat;
import calcmassview.base.CalculatorPanelImpl;
import calcmassview.info.InfoPanel;
import calcmassview.settings.ColorTheme;
import calcmassview.settings.SettingsPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.util.stream.Collectors;
import calcmassview.base.FieldsData;
import calcmassview.info.Info;
import calcmasscontroller.ControllerService;

/**
 * Представление приложения
 * @author Sergei Lyashko
 */
public class ViewServiceDispatcher extends JPanel implements ViewService, BuildDataObserver {

    private static final long serialVersionUID = 1L;
    
    // коллекция компонентов
    //private ArrayList<JComponent> components;
    
    private final Preference preference;
    
    private MenuListReceiveService menuListReceiver;
    private final Info info;
    private final ControllerService controller;

    public ViewServiceDispatcher(Info info, ControllerService controller) {
        super(new GridLayout(1, 1));
        this.info = info;
        this.controller = controller;
        this.preference = new Preference();
    }
    
    private List<JPanel> loadPanels(){
        ArrayList<JComponent> components;
        if(preference.isSaved()){
            components = preference.loadSavedComponents();
        }else{
            components = createComponents();
        }
        return extractPanels(components);
    }
    
    // создание панелей
    private ArrayList<JComponent> createComponents(){
        ArrayList<JComponent> components = new ArrayList<>();
        JPanel calculatorPanel = new CalculatorPanelImpl(components, menuListReceiver);
        ((CalculatorPanelImpl)calculatorPanel).registerObserver(this);
        components.add(calculatorPanel);
        JPanel infoPanel = new InfoPanel(components, info);            
        JPanel settingsPanel = new SettingsPanel(components);                                
        components.add(settingsPanel);
        components.add(infoPanel);
        new CalculatorFrame(this);
        return components;
    }
    
    private List<JPanel> extractPanels(ArrayList<JComponent> components){
        return components.stream()
                    .filter((JComponent component) -> component.getClass().getSuperclass().isAssignableFrom(JPanel.class))
                    .filter((JComponent component) -> component.getClass().isAnnotationPresent(ColorTheme.class))
                    .map((JComponent component) -> (JPanel)component)
                    .collect(Collectors.toList());
    }
    
    @Override
    public void setMenuList(MenuListReceiveService menuListReceiver) {
        this.menuListReceiver = menuListReceiver;
        List<JPanel> panels = loadPanels();        
        this.addToTabbedPane(panels);
    }
    
    private void addToTabbedPane(List<JPanel> panels){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);             
        panels.stream().forEach((JPanel panel) -> tabbedPane.addTab(panel.getName(), panel));
        this.add(tabbedPane);
    }
    
    public void savePreferences() {
        //preference.save(components);
    }

    @Override
    public void updateData() {
        FieldsData data = receiveFieldsData();    
        controller.acceptData(new ViewDataServiceImpl(data));
        //test
        double calculationResult = controller.getCalculationResult();
    }
    
    private FieldsData receiveFieldsData(){
        JComponent get = components.stream()
                .filter((JComponent component) -> component.getClass().equals(CalculatorPanelImpl.class))
                .findFirst()
                .get();
        return ((CalculatorPanelImpl)get).getFieldsData();
    }
    
    //форматирование строки результата
    private String formatDoubleToString(double value){
        return new DecimalFormat("#.###").format(value);
    }
}
