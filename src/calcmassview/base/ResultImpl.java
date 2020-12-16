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
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.lang.annotation.Annotation;
import javax.swing.JLabel;

/**
 * ������ ������ ���������� ����������
 * @author Sergei Lyashko
 */
@CalculatorPanel()
@Reset(reset = true)
public class ResultImpl extends JLabel implements CalculatorPanel, Reset, Result {
    
    private final String defaultView = "0.0";
    private final String dimensionKg = "��";
    
    public ResultImpl(){
        super.setLocation(190, 105);
        super.setVisible(true);
        super.setText(defaultView);
        super.setHorizontalAlignment(RIGHT);
        super.setSize(125, 25);
    }
    
    /**
     * ����� �������� �� ������
     * @param result
     */
    @Override
    public void show(String result){
        if(result.equals("error")){
            super.setForeground(Color.red);
            super.setText(result);
        }else{
            String str = new StringBuilder().append(result).append(" ").append(dimensionKg).toString();
            super.setText(str);
            setResultToSystemClipboard(result);
        }
    }
    
    // ����� ����������� � ����� ������ ��� ������ ����������
    private void setResultToSystemClipboard(String value){                
        Toolkit.getDefaultToolkit()
            .getSystemClipboard()
            .setContents(new StringSelection(value), null);       
    }
    
    /**
     * ����� ����������
     * @return 
     */
    @Override
    public boolean reset(){        
        super.setText(defaultView);
        //System.out.println("result reset");// TEST
        return true;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return getClass();
    }
}