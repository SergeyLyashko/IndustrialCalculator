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

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * основная панель приложения
 * @author Sergei Lyashko
 */
public final class ViewPanel extends JPanel implements IViewController {   
    
    public static final ViewPanel VIEW_PANEL = new ViewPanel();
    
    // combo-boxes
    protected BaseMenuBox baseMenuBox, typeProfileMenuBox, nameProfileMenuBox;
    // сервисная строка
    private final ServiceMarker serviceMarker;
    // поля ввода значений
    protected LengthField lengthField, widthField;
    // строка результата
    protected ResultMarker resultMarker;
    //данные из выпадающих меню
    protected String detailName, detailLength, detailWidth;   
    
    public ViewPanel() {
        super();
        super.setBackground(Color.BLACK);       
        
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
        baseMenuBox.updateView((String)baseMenuBox.getSelectedItem());
        typeProfileMenuBox.updateView((String)typeProfileMenuBox.getSelectedItem());       
        
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
    
    // добавление вкладок в основное окно приложения
    private void addTab(){
        GeneralPanel.addToGeneralPanel("Калькулятор", this);
        GeneralPanel.addToGeneralPanel("Настройки", new SettingsPanel());
        GeneralPanel.addToGeneralPanel("Info", new InfoPanel());        
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
    
    @Override
    public String getAssortmentName(){
        return (String)baseMenuBox.getSelectedItem();
    }
    
    @Override
    public String getTypeDetailName(){
        return (String)typeProfileMenuBox.getSelectedItem();
    }
    
    // методы интерфейса     
    @Override 
    public String getDetailName(){
        return detailName;
    }

    @Override
    public String getDetailLength() {        
        return detailLength;
    }
    
    @Override
    public String getDetailWidth(){
        return detailWidth;
    }
    
    @Override
    public void setResultation(String value){        
        resultMarker.setValue(value);
        setServiceMarker("copy");
    }
}
