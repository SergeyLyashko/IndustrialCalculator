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
package calcmassview;

import app.AppPanel;
import calcdatabase.ViewService;
import java.text.DecimalFormat;
import calcmassview.settings.ColorTheme;
import java.util.List;
import javax.swing.JComponent;
import calcmassview.base.FieldsData;
import calcmasscontroller.ControllerService;

/**
 * View Dispatcher.
 * Create application view.
 * If preferences app is not saved,
 * then create view with default settings, 
 * else create app with saved settings.
 * 
 */
public class ViewDispatcher implements ViewService, BuildDataObserver {
    
    //private final Preference preference;
    
    private MenuListReceiveService menuListReceiver;
    private final Info info;
    private final ControllerService controller;

    public ViewDispatcher(Info info, ControllerService controller) {
        this.info = info;
        this.controller = controller;
    }
    
    @Override
    public void setMenuList(MenuListReceiveService menuListReceiver) {
        this.menuListReceiver = menuListReceiver;
        // TODO
        createAppView();        
    }
    
    // TODO проверить сохранение и загрузку настроек
    private void createAppView(){
        List<JComponent> components;
        AppPanel appPanel = new AppPanel(menuListReceiver, info);
        if(Preference.isSaved()){
            components = Preference.loadSavedComponents();
        }else{
            components = appPanel.createComponentsApp();         
        }
        appPanel.addComponents(components);
    }

    /*
    public void savePreferences() {
        //preference.save(components);
    }*/

    @Override
    public void updateData() {
        /*FieldsData data = receiveFieldsData();    
        controller.acceptData(new ViewDataServiceImpl(data));
        */
        //test
        double calculationResult = controller.getCalculationResult();
    }
    /*
    private FieldsData receiveFieldsData(){
        JComponent get = components.stream()
                .filter((JComponent component) -> component.getClass().equals(CalculatorPanelImpl.class))
                .findFirst()
                .get();
        return ((CalculatorPanelImpl)get).getFieldsData();
    }*/
    
    //форматирование строки результата
    private String formatDoubleToString(double value){
        return new DecimalFormat("#.###").format(value);
    }
}
