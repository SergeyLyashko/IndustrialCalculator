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
package calcmasscontroller;

import calcmassmodel.CalculatorModel;
import calcmassmodel.CalculatorModelInterface;
import calcmassview.CalculatorView;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Контроллер
 * @author Sergei Lyashko
 */
public class CalculatorController implements CalculatorControllerInterface {
    
    private CalculatorModelInterface model;
    
    public CalculatorController(CalculatorModelInterface model){
        this.model = model;
        //createAndShowGUI();
        CalculatorView calculatorView = new CalculatorView(model, this);
        calculatorView.create();
        calculatorView.createAndShowGUI();
    }   
    /*
    private void createAndShowGUI(){
        JFrame app = new JFrame("Калькулятор масс");
        //app.setBounds(300, 300, 360, 220);
        app.setSize(360, 220);
        app.setResizable(false);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //CalculatorView calculatorView = new CalculatorView(model, this);
        //calculatorView.create();
        app.getContentPane().add(calculatorView, BorderLayout.CENTER);
        //отображение окна
        app.setVisible(true);
    }
    */
    @Override
    public void setValueFromView(String assortment, String type, String number, String length, String width) {
        model.createDetail(assortment, type, number, length, width);
    }
}
