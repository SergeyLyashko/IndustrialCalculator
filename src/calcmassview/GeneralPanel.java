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
package calcmassview;

import calcmassview.base.CalculatorPanelImpl;
import calcmassview.info.Info;
import calcmassview.info.InfoPanel;
import calcmassview.settings.SettingsPanel;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Korvin
 */
class GeneralPanel extends JPanel implements Serializable, BuildDataObserver {
    
    private static final long serialVersionUID = 1L;

    public GeneralPanel() {
        super(new GridLayout(1, 1));
    }
    
    List<JComponent> createComponentsApp(MenuListReceiveService menuListReceiver, Info info){
        JPanel calculatorPanel = createCalculatorPanel(menuListReceiver);
        JPanel infoPanel = createInfoPanel(info);
        JPanel settingsPanel = createSettingsPanel();
        return Stream.of(calculatorPanel, settingsPanel, infoPanel).collect(Collectors.toList());
    }
    
    private JPanel createCalculatorPanel(MenuListReceiveService menuListReceiver){
        CalculatorPanelImpl calculatorPanel = new CalculatorPanelImpl(menuListReceiver);
        calculatorPanel.registerObserver(this);
        return calculatorPanel;
    }
    
    private JPanel createInfoPanel(Info info){
        return new InfoPanel(info);
    }
    
    private JPanel createSettingsPanel(){
        return new SettingsPanel();
    }
    
    @Override
    public void updateData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
