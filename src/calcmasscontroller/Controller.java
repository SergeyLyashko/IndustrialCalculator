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

import calcmassmodel.Facade;
import calcmassview.ViewPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import calcmassmodel.ISubject;

public class Controller implements IObserver {
    
    private final ISubject model;
    private final ViewPanel view;
    private double mass;
    
    public static Controller newInstance(ISubject model, ViewPanel view){
        return new Controller(model, view);
    }
    
    private Controller(ISubject model, ViewPanel view){
        this.model = model;
        this.view = view;        
        //слушатели View
        view.addViewListener(new ViewListener());
        model.registerObserver(this);
    }
    
    //форматирование строки результата из Model для View
    private String resultFromModel(){
        //double valueFromModel = model.getResult();
        String formatResult = new DecimalFormat("#.###").format(mass);      
        return formatResult;
    }    

    @Override
    public void update(double mass) {
        this.mass = mass;
        view.setResultation(resultFromModel());
    }
    
    // inner class
    private class ViewListener implements KeyListener {
        
        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                try{
                    // создание детали
                    Facade.getInstance().createDetail(
                        // получение параметров из View
                        view.getAssortmentName(),
                        view.getTypeDetailName(),               
                        view.getDetailName(),
                        view.getDetailLength(),
                        view.getDetailWidth()
                    );
                    //получение результата из Model и установка во View
                    //view.setResultation(resultFromModel());                    
                    //model = null;
                }catch(NullPointerException ex){
                    //неопределенный результат
                    String err = "error";
                    view.setResultation(err);
                    view.setServiceMarker(err);
                }
            }
        }    
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {}
    } // end iner class
}
