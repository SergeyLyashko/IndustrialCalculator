/*
 * Copyright 2020 Korvin.
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
package app;

import calcmassview.MenuListReceiveService;
import calcmassview.base.CalculatorPanelImpl;
import calcmassview.Info;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * General panel of app.
 * Create panels.
 * @author Korvin
 */
public class AppPanel extends JPanel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private final MenuListReceiveService menuListReceiver;
    private final Info info;
    private List<JComponent> allComponents;

    public AppPanel(MenuListReceiveService menuListReceiver, Info info) {
        super(new GridLayout(1, 1));
        this.menuListReceiver = menuListReceiver;
        this.info = info;
        allComponents = new ArrayList<>();
    }
    
    public void addComponents(List<JComponent> componentsApp){
        allComponents = componentsApp.stream().collect(Collectors.toList());
        new AppFrame(this);
    }
    
    public List<JComponent> createComponentsApp(){
        //JPanel calculatorPanel = new CalculatorPanelImpl(menuListReceiver);
        JPanel infoPanel = createInfo(info);
        JPanel settingsPanel = createSettings();
        List<JPanel> componentsApp = Stream.of(/*calculatorPanel,*/ settingsPanel, infoPanel).collect(Collectors.toList());
        addToTabbedPane(componentsApp);
        return Collections.unmodifiableList(allComponents);
    }
    
    private JPanel createSettings(){
        SettingsPanel settingsPanel = new SettingsPanel();
        List<JComponent> settingsPanelComponents = settingsPanel.createComponents();
        collectComponents(settingsPanelComponents);
        return settingsPanel;
    }
    
    private JPanel createInfo(Info info){
        InfoPanel infoPanel = new InfoPanel(info);
        List<JComponent> infoPanelComponents = infoPanel.createComponents();
        collectComponents(infoPanelComponents);
        return infoPanel;
    }
    
    private void collectComponents(List<JComponent> components){
        components.stream().forEach((JComponent component) -> allComponents.add(component));
    }
    
    private void addToTabbedPane(List<JPanel> panels){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);             
        panels.stream().forEach((JPanel panel) -> tabbedPane.addTab(panel.getName(), panel));
        this.add(tabbedPane);
    }
}
