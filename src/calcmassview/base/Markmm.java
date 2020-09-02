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
package calcmassview.base;

import calcmassview.settings.ColorTheme;
import java.awt.Color;
import java.lang.annotation.Annotation;
import javax.swing.JLabel;

/**
 * Маркер поля
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@ColorTheme()
public class Markmm extends JLabel implements CalculatorPanel, ColorTheme{
    
    public Markmm(int xDestination, int yDestination){
        super.setVisible(true);
        super.setSize(25, 20);
        super.setForeground(Color.white);
        super.setText("мм");
        super.setLocation(xDestination, yDestination);
    }    

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
