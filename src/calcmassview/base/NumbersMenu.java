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

import calcmassview.settings.ToolTips;
import calcmassview.settings.ToolTipsInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JComboBox;

/**
 * Панель меню номеров профилей
 * @author Sergei Lyashko
 */
public class NumbersMenu extends JComboBox<String> implements ActionListener, FieldValueReceivable {  
    
    private String selectItem;
    private final BasePanel basePanel;
    private final String toolTipText = "выбор номера профиля детали";
    private final String headerProfilesMenu = "№ профиля";
    private final static String[] ASSORTMENT_WITH_WIDTH = {"Лист", "Резиновая пластина"};
    
    public NumbersMenu(BasePanel basePanel, ToolTipsInterface toolTips) {
        super.setSize(155, 25);
        super.setSelectedIndex(-1);
        super.setLocation(20, 100);
        this.basePanel = basePanel;
        addConent(toolTips);
    }
    
    // 
    private void addConent(ToolTipsInterface toolTips){
        Menu defaultMenu = new Menu();
        super.setModel(defaultMenu.createStartMenu(this));
        toolTips.setToolTips(this, toolTipText);
        basePanel.add(this);        
        basePanel.addPolicy(this);        
        addActionListener(this);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        resetAllFields();
        @SuppressWarnings("unchecked")
        String selectedMenuItem = ((JComboBox<String>)e.getSource()).getSelectedItem().toString();
        this.selectItem = selectedMenuItem;
        // активаци полей ввода значений
        actionFields(selectedMenuItem);
    }
    
    // активация полей ввода значений
    private void actionFields(String selectMenu){
        boolean detailHaveWidth = detailHaveWidth();        
        // если в меню выбран любой пункт, кроме заголовка
        if(!selectMenu.equals(headerProfilesMenu)){
            basePanel.getLengthField().activeField();         
            // если деталь имеет параметр ширина
            if(detailHaveWidth){
                boolean areaBoxOFF = basePanel.getDifficultAreaBox().isAreaBoxOFF();
                 // если чек-бокс площади выключен
                if(areaBoxOFF){
                    basePanel.getDifficultAreaBox().oFF();
                }else{
                    basePanel.getDifficultAreaBox().oN();
                }                
            }else{
                basePanel.getWidthField().deactiveField();
            }               
        }
    }
    
    /**
     * Проверка на наличие параметра ширина у детали
     * @return
     */
    public boolean detailHaveWidth(){
        Object assortmentMenuItem = basePanel.getAssortmentMenu().getSelectedItem();
        Object typeMenuItem = basePanel.getTypeProfileMenu().getSelectedItem();
        Object[] menuItem = {assortmentMenuItem, typeMenuItem};
        return Arrays.stream(ASSORTMENT_WITH_WIDTH).anyMatch((String element) -> {
                return Arrays.stream(menuItem).anyMatch((Object obj) -> element.equals(obj)); 
            });
    }
    
    // сброс полей ввода
    private void resetAllFields(){
        basePanel.reset();
    }

    @Override
    public String fieldValueStringReceive() {
        return this.selectItem;
    }
}
