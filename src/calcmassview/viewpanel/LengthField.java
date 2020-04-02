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
package calcmassview.viewpanel;

import calcmassview.AbstractPanel;
import calcmassview.BasePanel;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * поле ввода длины
 * @author Sergei Lyashko
 */
public class LengthField extends AbstractField implements ValueReceivable {
    
    private final AbstractPanel panel;
    private String text;
    
    public LengthField(AbstractPanel panel){
        super(panel);
        this.panel = panel;
        create();
    }
    
    private void create(){
        panel.add(this);
        super.setText("длина");
        this.setLocation(190, 60);
        ((BasePanel)panel).addPolicy(this);
    }
    
    /**
     * деактивация (закрытие) поля
     */
    @Override
    public void closeField(){        
        setEditable(false);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.GRAY);        
        setText("длина");
        removeFocusListener(this);
        removeKeyListener(this);
    }
    
    /**
     * установка текста из поля в метод по нажатию клавиши
     * @param e нажатие клавиши "Enter"
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.text = super.getText();
        }
    }
    
    @Override
    public String getValueOfField() {
        return text;
    }
}
