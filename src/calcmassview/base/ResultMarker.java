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
package calcmassview.base;

import calcmassview.settings.Theme;
import java.awt.Color;
import javax.swing.JLabel;

/**
 * строка вывода результата вычислений
 * @author Sergei Lyashko
 */
public class ResultMarker extends JLabel {
    
    public ResultMarker(BasePanel panel){
        super.setVisible(true);
        super.setText("0.0");
        super.setHorizontalAlignment(RIGHT);
        super.setSize(125, 25);
        this.setLocation(190, 100);
        panel.add(this);
        Theme.addTheme(this);
    }
    
    // установка значени€ в строку результата
    public void setResult(String result){
        String str;
        if(!result.equals("error")){
            StringBuilder s = new StringBuilder();
            str = s.append(result).append(" ").append("кг").toString();
        }else{
            super.setForeground(Color.red);
            str = result;
        }
        super.setText(str);
    }
    
    /**
     * —брос результата
     */
    public void reset(){        
        super.setText("0.0");
        Theme.addTheme(this);
    }
}
