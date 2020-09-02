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
package calcmassview.info;

import calcmassview.settings.ColorTheme;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * 
 * @author Sergei Lyashko
 */
@ColorTheme()
public class InfoPanel extends JPanel implements Serializable, ColorTheme {
    
    private static final long serialVersionUID = 1L;
    
    private final JLabel informationText;
    private final ArrayList<JComponent> components;
    
    public InfoPanel(ArrayList<JComponent> components, Info info){
        super.setLayout(new BorderLayout());        
        this.informationText = (JLabel) info;
        this.components = components;
        addComponents();
    }
    
    private void addComponents(){        
        setInfoTextParameters(informationText);
        components.add(informationText);
        
        JScrollPane scrollPane = new JScrollPane(informationText);
        setScrollPaneParameters(scrollPane);
        components.add(scrollPane.getViewport());
        
        super.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void setInfoTextParameters(JLabel htmlText){
        Dimension dimension = new Dimension(250, 510);
        htmlText.setPreferredSize(dimension);
    }
    
    private void setScrollPaneParameters(JScrollPane scrollPane){
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
    
    @Override
    public String getName(){
        return "Справка";
    }
}
