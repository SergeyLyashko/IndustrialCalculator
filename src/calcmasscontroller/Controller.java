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

import calcmassmodel.Detail;
import calcmassview.ViewPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class Controller {
    
    private Detail model;    
    private final ViewPanel view;
    
    public Controller(Detail model, ViewPanel view){
        this.model = model;
        this.view = view;        
        //слушатели View
        view.addViewListener(new ViewListener());  
    } 
    
    // преобразование данных из View в число
    private double getValueFromView(String value) throws NumberFormatException, NullPointerException {
            return Double.parseDouble(value);
    }
    
    //форматирование строки результата из Model для View
    private String formattingModelResult(){
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String formatResult = decimalFormat.format(model.getResult());        
        return formatResult;
    }
    
    // inner class
    class ViewListener implements KeyListener {
        
        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                
                // получение параметров из View
                String detaiAssortment = view.getAssortmentName();
                String detailType = view.getTypeDetailName();                
                String detailName = view.getDetailName();
                String detailLength = view.getDetailLength();
                String detailWidth = view.getDetailWidth();
                
                try{
                    double length = getValueFromView(detailLength);
                    double width = detailWidth == null ? 0 : getValueFromView(detailWidth);
                    if(length < 0 || width < 0){
                        throw new ValueLogicException();
                    }
                    
                    // создание детали
                    model = new Detail(detaiAssortment, detailType, detailName, length, width);                    
                    //получение результата из Model и установка во View
                    view.setResultation(formattingModelResult());                    
                    model = null;                    
                }catch(NumberFormatException | NullPointerException | ValueLogicException ex){
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
