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

import java.awt.Color;
import javax.swing.JLabel;

/**
 * информационна строка внизу основного окна
 * @author Sergei Lyashko
 */
public class ServiceInfo extends JLabel {
   
    public ServiceInfo(){
        super.setLocation(20, 140);
        super.setVisible(true);
        super.setHorizontalAlignment(CENTER);
        super.setSize(315, 15);
    }
    
    /**
     * —брос надписи служебной строки на панели View
     */
    public void reset(){
        super.setText(null);
    }
    
    /**
     * вывод служебной строки на панель
     * @param message
     */
    public void show(String message){
        super.setText(message);
    }
    
    /**
     * вывод сообщени€ об ошибке на панель View
     * @param error
     */
    public void showError(String error){
        super.setForeground(Color.RED);        
        super.setText(error);
    }       
}
