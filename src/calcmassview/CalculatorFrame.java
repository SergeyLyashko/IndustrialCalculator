/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
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

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import javax.swing.JFrame;

/**
 *
 * @author Korvin
 */
public class CalculatorFrame extends JFrame implements Serializable {
    
    private static final long serialVersionUID = 1L;    
    
    public CalculatorFrame(CalculatorView view){
        super("Industrial calculator");
        super.setBounds(300, 300, 360, 220);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.getContentPane().add(view, BorderLayout.CENTER);
        super.setVisible(true);
        savePreferencesForExit(view);
    }
    
    // закрытие приложения
    private void savePreferencesForExit(CalculatorView view){
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {                
                view.savePreferences();
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
    
}
