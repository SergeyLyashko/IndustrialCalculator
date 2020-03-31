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

import calcmassview.viewpanel.CalculatorFocusTraversalPolicy;
import calcmassview.settingpanel.Theme;
import calcmassview.infopanel.InfoPanel;
import calcmassview.viewpanel.MenuBoxModel;
import calcmassview.viewpanel.FieldMarker;
import calcmassview.viewpanel.ServiceMarker;
import calcmassview.viewpanel.ResultMarker;
import calcmassview.viewpanel.LengthField;
import calcmassview.viewpanel.WidthField;
import calcmassview.viewpanel.TypeProfileMenuBox;
import calcmassview.viewpanel.NumberProfileMenuBox;
import calcmassview.viewpanel.BaseMenuBox;
import calcmassview.viewpanel.AbstractMenuBox;
import calcmassview.settingpanel.SettingsPanel;
import calcmassview.viewpanel.AbstractField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * основная панель приложения
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel {
    
    // статическое создание экземпляра класса
    private static final BasePanel INSTANCE = new BasePanel();
    
    // combo-boxes
    private final AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    // сервисная строка
    private final ServiceMarker serviceMarker;
    // поля ввода значений
    private final AbstractField lengthField, widthField;
    // строка результата
    private final ResultMarker resultMarker;
    
    /**
     * Синглтон-метод создания основной панели вида приложения
     * @return объект основной панели BasePanel
     */
    public static final BasePanel getInstance(){
        return INSTANCE;
    }
    
    // конструктор
    private BasePanel() {
        // цвет фона по умолчанию
        super.setBackground(Color.BLACK);        
        // тема оформления
        Theme.addTheme(this);
        
        // текстовая строка результата
        resultMarker = new ResultMarker();
        resultMarker.setLocation(190, 100);
        
        //текстовое поле Ширина (для листа)
        widthField = WidthField.getInstance();
        widthField.setLocation(190, 20);       
        //надпись мм для поля
        FieldMarker mmWf = new FieldMarker();
        mmWf.setText("мм");
        mmWf.setLocation(320, 22);
        
        //текстовое поле Длина
        lengthField = LengthField.getInstance();
        lengthField.setLocation(190, 60);        
        //надпись мм для поля
        FieldMarker mmLf = new FieldMarker();
        mmLf.setText("мм");
        mmLf.setLocation(320, 62);
        
        // <Тип изделия>
        baseMenuBox = BaseMenuBox.getInstance();
        baseMenuBox.setLocation(20, 20);       
        
        // создание модели меню из БД
        MenuBoxModel baseMenuModel = MenuCreator.getInstance().getModel();
        baseMenuBox.setModel(baseMenuModel);
        
        // In working
        // модель основного списка для создания своего меню
        //CustomMenuFrame.setProfileModelList(baseMenuModel);
        
        // <№ профиля>
        numberProfileMenuBox = NumberProfileMenuBox.getInstance();
        numberProfileMenuBox.setLocation(20, 100);
        numberProfileMenuBox.setModel(MenuCreator.getInstance().getModel("", ""));
        
        // <Тип профиля>
        typeProfileMenuBox = TypeProfileMenuBox.getInstance();
        typeProfileMenuBox.setLocation(20, 60);
        typeProfileMenuBox.setModel(MenuCreator.getInstance().getModel(""));
        
        // <Сервисная строка>
        serviceMarker = new ServiceMarker();
        serviceMarker.setLocation(20, 140);
        
        //добавление компонентов на панель в интерфейсе окна
        super.add(baseMenuBox);        
        super.add(typeProfileMenuBox);
        super.add(numberProfileMenuBox);
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
        policy.add(numberProfileMenuBox);
        policy.add(widthField);
        policy.add(lengthField);       
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new CalculatorFocusTraversalPolicy(policy));
    }
    
    /**
     * обновление списка в меню 
     * @param menuName строковое представление наименования пунка меню
     * @param source панель выпадающего меню
     */
    public void updateView(String menuName, AbstractMenuBox source){
        if(source.equals(baseMenuBox)){
            MenuBoxModel typeMenuModel = MenuCreator.getInstance().getModel(menuName);
            typeProfileMenuBox.setModel(typeMenuModel);
        }
        if(source.equals(typeProfileMenuBox)){
            String selectedAssortment = baseMenuBox.getStringValue();
            MenuBoxModel numberMenuModel = MenuCreator.getInstance().getModel(selectedAssortment, menuName);
            numberProfileMenuBox.setModel(numberMenuModel);
        }
    }
    
    // добавление вкладок в основное окно приложения
    private void addTab(){
        GeneralPanel.getInstance().addToGeneralPanel("Калькулятор", this);       
        GeneralPanel.getInstance().addToGeneralPanel("Настройки", SettingsPanel.getInstance());
        GeneralPanel.getInstance().addToGeneralPanel("Справка", InfoPanel.getInstance());
    }    
    
    /**
     * слушатель нажатия клавиши в поле "длина"
     * @param e событие нажатия клавиши
     */
    public void addViewListener(KeyListener e){
        lengthField.addKeyListener(e);        
    }
    
    /**
     * сброс значений при активации панели BaseMenuBox
     */
    public void reset(){        
        // сброс надписей
        resultMarker.resetResultMarker();
        serviceMarker.resetServiceMarker();
        //сброс полей ввода
        widthField.closeField();
        lengthField.closeField();        
    }
    
    // сервисная строка
    public void setServiceMarker(String eventStr){        
        serviceMarker.setMarker(eventStr);
    }
    
    public AbstractMenuBox getBaseMenuBox(){
        return baseMenuBox;
    }
    
    public AbstractMenuBox getTypeProfileMenuBox(){
        return typeProfileMenuBox;
    }
            
    public AbstractMenuBox getNumberProfileMenuBox(){
        return numberProfileMenuBox;
    }

    public AbstractField getLengthField() {        
        return lengthField;
    }
    
    public AbstractField getWidthField(){
        return widthField;
    }
    
    public void setResultation(String value){        
        resultMarker.setResult(value);
        setServiceMarker("copy");        
    }
    
    public String getResultation(){
        return resultMarker.getText();
    }
}
