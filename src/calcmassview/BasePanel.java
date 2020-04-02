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
import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.ValueReceivable;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

/**
 * основная панель приложения
 * @author Sergei Lyashko
 */
public class BasePanel extends AbstractPanel {
    
    // combo-boxes
    private AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    // сервисная строка
    private ServiceMarker serviceMarker;
    // поля ввода значений
    private AbstractField lengthField, widthField;
    // строка результата
    private ResultMarker resultMarker;
    // создание выпадающего меню в комбо-боксах
    private MenuCreator menuCreator;
    // политика обхода фокуса
    private final ArrayList<Component> policy = new ArrayList<>();
    
    public BasePanel() {
        createMenuApplication();
        createMenuDecorations();
        // отключение автокомпоновки элементов
        super.setLayout(null);       
        // политика обхода фокуса
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new CalculatorFocusTraversalPolicy(policy));
    }
    
    private void createMenuDecorations(){
        //надпись мм для поля
        FieldMarker mmWf = new FieldMarker(this);
        mmWf.setText("мм");
        mmWf.setLocation(320, 22);
        //надпись мм для поля
        FieldMarker mmLf = new FieldMarker(this);
        mmLf.setText("мм");
        mmLf.setLocation(320, 62);
        // текстовая строка результата
        resultMarker = new ResultMarker(this);
        // <Сервисная строка>
        serviceMarker = new ServiceMarker(this);
    }
    
    private void createMenuApplication(){
        // создание модели меню из БД
        menuCreator = new MenuCreator();
        // <Тип изделия>
        baseMenuBox = new BaseMenuBox(this);        
        // <Тип профиля>
        typeProfileMenuBox = new TypeProfileMenuBox(this);
        // <№ профиля>
        numberProfileMenuBox = new NumberProfileMenuBox(this);
        //текстовое поле Ширина (для листа)
        widthField = new WidthField(this);
        //текстовое поле Длина
        lengthField = new LengthField(this);
        //setValueOfFields();
    }
    // добавление компонентов в политику обхода фокуса
    public void addPolicy(Component component){
        policy.add(component);
    }
    
    public MenuCreator getMenuCreator(){
        return menuCreator;
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
        setResultToSystemClipboard(value);
        setServiceMarker("copy");        
    }
    
    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){                
        Toolkit.getDefaultToolkit()
            .getSystemClipboard()
            .setContents(new StringSelection(value), null);       
    }
}
