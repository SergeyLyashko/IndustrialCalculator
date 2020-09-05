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
package calcmasscontroller;

import calcdatabase.DataBaseDispatcherImpl;
import calcmassmodel.CalculatorModel;
import calcmassmodel.CalculatorModelImpl;
import calcmassview.CalculatorViewImpl;
import javax.swing.SwingUtilities;
import calcmassview.CalculatorView;
import calcmassview.info.Info;
import calcmassview.info.InfoImpl;
import calcmassview.MenuListReceiver;
import calcmassmodel.ValueReceiver;
import calcdatabase.DataBaseDispatcher;

/**
 * Внедрение зависимостей
 * @author Sergei Lyashko
 */
public class Main { 
    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataBaseDispatcher dispatcher = new DataBaseDispatcherImpl();
            
            ValueReceiver valueReceiver = dispatcher.getValueReceiver();
            CalculatorModel model = new CalculatorModelImpl(valueReceiver);
            
            MenuListReceiver menuList = dispatcher.getMenuList();
            Info info = new InfoImpl();
            CalculatorView view = new CalculatorViewImpl(menuList, info);
            
            CalculatorController calculatorController = new CalculatorController(model, view);
            
                });
    }
}
