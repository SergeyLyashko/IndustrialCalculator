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
package calcmassview.general;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Интерфейс цветовой темы
 * @author Sergei Lyashko
 */
public interface ColorThemeInterface {
    
    /**
     * Изменение цветовой темы оформления
     * @param marker компоненты класса JLabel
     */
    public void componentChangeColor(JLabel marker);
    
    /**
     * Изменнение цветовой темы оформления
     * @param component компоненты класса JComponent
     */
    public void componentChangeColor(JComponent component);
    
    /**
     * Установить темную тему оформления
     */
    public void doDark();
    
    /**
     * Установить светлую тему оформления
     */
    public void doLight();    
}
