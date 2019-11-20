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

import java.awt.Color;
import javax.swing.JLabel;

/**
 * строка вывода результата вычислений
 * @author Sergei Lyashko
 */
public class ResultMarker extends JLabel {   
    
    public ResultMarker(){
        super();        
        super.setVisible(true);
        super.setText("0.0");
        super.setHorizontalAlignment(RIGHT);
        super.setSize(125, 25);
        super.setForeground(Color.green);
        Theme.addTheme(this);
    }
    
    // установка значения в строку результата
    protected void setValue(String value){
        StringBuilder s = new StringBuilder();
        if(!value.equals("error")){
            s.append(value).append(" "+"кг");
        }else{
            super.setForeground(Color.red);
            s.append(value);
        }
        super.setText(s.toString());
    }
    
    protected void resetResultMarker(){
        super.setText("0.0");              
        Theme.addThemeMarker(this);       
    }
}
