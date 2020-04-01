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

import javax.swing.JFrame;

/**
 * Основное окно приложения
 * @author Sergei Lyashko
 */
public class WindowApp extends JFrame {
    
    public WindowApp(){
        create();
    }
    
    private void create(){
        super.setTitle("Калькулятор масс");
        super.setBounds(300, 300, 360, 220);        
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        //отображение окна
        super.setVisible(true);
    }
}
