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

import calcmassview.settingpanel.SettingsPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * основная панель приложения
 * 
 */
public final class ViewPanel extends JPanel {   
    
    /**
     *
     */
    public static final ViewPanel VIEW_PANEL = new ViewPanel();
    
    // combo-boxes
    private final BaseMenuBox baseMenuBox, typeProfileMenuBox, nameProfileMenuBox;
    // сервисная строка
    private final ServiceMarker serviceMarker;
    // поля ввода значений
    private final LengthField lengthField, widthField;
    // строка результата
    private final ResultMarker resultMarker;
    //данные из выпадающих меню
    private String detailName, detailLength, detailWidth;   
        
    public ViewPanel() {
        super();
        super.setBackground(Color.BLACK);
        
        // тема оформления
        Theme.addTheme(this);
        
        // текстовая строка результата
        resultMarker = new ResultMarker();
        resultMarker.setLocation(190, 100);
        
        //текстовое поле Ширина (для листа)
        widthField = new WidthField(this);
        widthField.setLocation(190, 20);        
        //надпись мм
        Marker mmWf = new Marker();
        mmWf.setText("мм");
        mmWf.setLocation(320, 22);
        
        //текстовое поле Длина
        lengthField = new LengthField(this);
        lengthField.setLocation(190, 60);        
        //надпись мм
        Marker mmLf = new Marker();
        mmLf.setText("мм");
        mmLf.setLocation(320, 62);
        
        // <Тип изделия>
        baseMenuBox = new BaseMenuBox(this);
        baseMenuBox.setLocation(20, 20);       
        // создание модели меню из БД
        MenuBoxModel baseMenuModel = MenuData.createMenuModelFromData("baseMenu");
        baseMenuBox.setModel(baseMenuModel);
        // модель основного списка для создания своего меню
        CustomMenuFrame.setProfileModelList(baseMenuModel);
        
        //baseMenu = new ToolTips("выберите сортамент");
        //ToolTips.setToolTipComponent(baseMenuBox, "выберите сортамент");
        
        // <№ профиля>
        nameProfileMenuBox = new NameProfileMenuBox(this);
        nameProfileMenuBox.setLocation(20, 100);
        
        // <Тип профиля>
        typeProfileMenuBox = new TypeProfileMenuBox(this);
        typeProfileMenuBox.setLocation(20, 60);
        
        // <Сервисная строка>
        serviceMarker = new ServiceMarker();
        serviceMarker.setLocation(20, 140);
        
        // обновление вида меню        
        updateView((String)baseMenuBox.getSelectedItem(), baseMenuBox);
        updateView((String)typeProfileMenuBox.getSelectedItem(), typeProfileMenuBox);
        
        //добавление компонентов на панель в интерфейсе окна
        super.add(baseMenuBox);        
        super.add(typeProfileMenuBox);
        super.add(nameProfileMenuBox);
        super.add(lengthField);
        super.add(widthField);
        super.add(serviceMarker);
        super.add(mmLf);
        super.add(mmWf);
        super.add(resultMarker);
        // отключение автокомпоновки элементов
        super.setLayout(null);       
        // инициализация вкладок
        this.addTab();    
        
        // политика обхода фокуса
        ArrayList<Component> policy = new ArrayList<>();
        policy.add(baseMenuBox);
        policy.add(typeProfileMenuBox);
        policy.add(nameProfileMenuBox);
        policy.add(widthField);
        policy.add(lengthField);       
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new CalculatorFocusTraversalPolicy(policy));
    }
    
    // установка начальных позиций в меню
    public void startPosition(){
        typeProfileMenuBox.setSelectedIndex(0);
        nameProfileMenuBox.setSelectedIndex(0);
    }
    
    // обновление списка в меню 
    public void updateView(String menuName, JComboBox<String> menu){
        MenuBoxModel model = MenuData.createMenuModelFromData(menuName);        
        if(menu.equals(baseMenuBox)){
            typeProfileMenuBox.setModel(model);
        }
        if(menu.equals(typeProfileMenuBox)){
            nameProfileMenuBox.setModel(model);
        }
    }
    
    // активация полей
    public void actionField(String menuName){
    // длина
        if(!menuName.equals("№_профиля")){
            lengthField.actionField();
            // активация поля ширина для детали "Лист" и "Резиновая_пластина"
            if(((String)baseMenuBox.getSelectedItem()).equals("Лист") ||
                    ((String)typeProfileMenuBox.getSelectedItem()).equals("Резиновая_пластина")){
                widthField.actionField();
            }
        }        
    }
    
    public void setDetailName(String detailName){
        this.detailName = detailName;
    }
    
    public void setDetailWidth(String detailWidth){
        this.detailWidth = detailWidth;
    }
    
    public void setDetailLength(String detailLength){
        this.detailLength = detailLength;
    }
    
    // добавление вкладок в основное окно приложения
    private void addTab(){
        InfoPanel ip = new InfoPanel();
        SettingsPanel sp = new SettingsPanel();
        GeneralPanel.addToGeneralPanel("Калькулятор", this);       
        GeneralPanel.addToGeneralPanel("Настройки", sp);
        GeneralPanel.addToGeneralPanel("Справка", ip);
    }    
    
    // слушатель ввода <Enter> в поле "длина"
    public void addViewListener(KeyListener e){
        lengthField.addKeyListener(e);        
    }
    
    // сброс значений при активации панели BaseMenuBox 
    protected void reset(){        
        // сброс надписей
        resultMarker.resetResultMarker();
        serviceMarker.resetServiceMarker();
        //сброс полей ввода
        widthField.closeField();
        lengthField.closeField();        
        //установка начальных значений
        detailName = null; 
        detailLength = null; 
        detailWidth = null;        
    }
    
    // сервисная строка
    public void setServiceMarker(String eventStr){        
        serviceMarker.setMarker(eventStr);
    }
    
    public String getAssortmentName(){
        return (String)baseMenuBox.getSelectedItem();
    }
    
    public String getTypeDetailName(){
        return (String)typeProfileMenuBox.getSelectedItem();
    }
    
    // методы интерфейса     
    public String getDetailName(){
        return detailName;
    }

    public String getDetailLength() {        
        return detailLength;
    }
    
    public String getDetailWidth(){
        return detailWidth;
    }
    
    public void setResultation(String value){        
        resultMarker.setResult(value);
        setServiceMarker("copy");        
    }
    
    public String getResultation(){
        return resultMarker.getText();
    }
}
