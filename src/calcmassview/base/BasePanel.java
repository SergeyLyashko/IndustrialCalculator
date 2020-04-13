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
package calcmassview.base;

import calcmassview.general.GeneralPanel;
import calcmassview.settings.Theme;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * основная панель с компонентами
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel {
    
    private final GeneralPanel panel;
    
    // combo-boxes
    private AssortmentMenu assortmentMenu;
    private TypeProfileMenu typeProfileMenu;
    private NumberProfileMenu numberProfileMenu;
    // сервисная строка
    private ServiceInfo serviceInfo;
    // поля ввода значений
    private LengthField lengthField;
    private WidthField widthField;
    // строка результата
    private ResultMarker resultMarker;
    // политика обхода фокуса
    private final ArrayList<Component> policy = new ArrayList<>();
    
    public BasePanel(GeneralPanel panel) {
        this.panel = panel;
        createComponents();
        createDecorations();
        // отключение автокомпоновки элементов
        super.setLayout(null);       
        // политика обхода фокуса
        super.setFocusCycleRoot(true);
        super.setFocusTraversalPolicy(new MyFocusTraversalPolicy(policy));
    }
    
    private void createDecorations(){
        Theme.addTheme(this);
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
        serviceInfo = new ServiceInfo(this);
    }
    
    private void createComponents(){
        // <Тип изделия>
        assortmentMenu = new AssortmentMenu(this);        
        // <Тип профиля>
        typeProfileMenu = new TypeProfileMenu(this);
        // <№ профиля>
        numberProfileMenu = new NumberProfileMenu(this);
        //текстовое поле Ширина (для листа)
        widthField = new WidthField(this);
        //текстовое поле Длина
        lengthField = new LengthField(this);
    }
    // добавление компонентов в политику обхода фокуса
    public void addPolicy(Component component){
        policy.add(component);
    }
    
    public GeneralPanel getGeneralPanel(){
        return panel;
    }
    
    /**
     * сброс значений при активации панели AssortmentMenu
     */
    public void reset(){        
        // сброс надписей
        resultMarker.reset();
        serviceInfo.reset();
        //сброс полей ввода
        widthField.close().close();
        lengthField.close().close();    
    }

    public AssortmentMenu getAssortmentMenu(){
        return assortmentMenu;
    }
    
    public TypeProfileMenu getTypeProfileMenu(){
        return typeProfileMenu;
    }
    
    public NumberProfileMenu getNumberProfileMenu(){
        return numberProfileMenu;
    }

    public LengthField getLengthField(){
        return lengthField;
    }

    public WidthField getWidthField(){
        return widthField;
    }

    public void setResultation(String value){
        resultMarker.setResult(value);
        setResultToSystemClipboard(value);
        serviceInfo.setMessage("результат скопирован в буфер обмена");
    }
    
    // сервисная строка
    public void setError(String message){
        resultMarker.setResult("error");
        serviceInfo.setErrorMessage(message);
    }
    
    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){                
        Toolkit.getDefaultToolkit()
            .getSystemClipboard()
            .setContents(new StringSelection(value), null);       
    }
}
