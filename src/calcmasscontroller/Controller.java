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
import calcmassview.View;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.AbstractMenuBox;

class Controller implements IObserver {
    
    private final View view;
    private AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    private AbstractField lengthField, widthField;
    private double mass;
    
    public static Controller newInstance(){
        return new Controller();
    }
    
    private Controller(){
        view = View.getInstance();
        //слушатель View
        view.addViewListener(new ViewListener());
    }
    
    //форматирование строки результата из Model для View
    private String resultFromModel(){
        String formatResult = new DecimalFormat("#.###").format(mass);      
        return formatResult;
    }    

    @Override
    public void update(double mass) {
        this.mass = mass;
        view.setResultation(resultFromModel());
    }
    
    private void createDetail(){
        Facade facade = new Facade(baseMenuBox, typeProfileMenuBox, numberProfileMenuBox, lengthField, widthField);
        facade.registerObserver(this);
        facade.createDetail();
    }
    
    // inner class
    private class ViewListener implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                try{
                    baseMenuBox = view.getBaseMenuBox();
                    typeProfileMenuBox = view.getTypeProfileMenuBox();
                    numberProfileMenuBox = view.getNumberProfileMenuBox();
                    lengthField = view.getLengthField();
                    widthField = view.getWidthField();
                    createDetail();
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
