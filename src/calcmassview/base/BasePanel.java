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

import calcdatabase.DataBaseInterface;
import calcmassview.general.GeneralPanel;
import calcmassview.general.ToolTipsInterface;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import calcmassview.general.ColorThemeInterface;

/**
 * основная панель с компонентами
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel implements ItemListener {
    
    // интерфейс базы данных
    private DataBaseInterface dataBase;
    
    // combo-boxes
    private AssortmentMenu assortmentMenu;
    private TypesMenu typesMenu;
    private NumbersMenu numbersMenu;
    
    // поля ввода значений
    private LengthField lengthField;
    private WidthField widthField;  
    
    // политика обхода фокусом
    private MyFocusTraversalPolicy myFocusTraversalPolicy;
    private final ArrayList<Component> policy = new ArrayList<>();
    
    // гравная панель
    private final GeneralPanel panel;
    
    // сервисная строка
    private ServiceInfo serviceInfo;
    
    // строка результата
    private ResultMarker resultMarker;
    
    // чек-бокс задания площади детали
    private DifficultAreaBox difficultAreaBox;
    
    private final ColorThemeInterface theme;
    private final ToolTipsInterface toolTips;
    
    public BasePanel(GeneralPanel panel, ColorThemeInterface theme, ToolTipsInterface toolTips) {
        this.panel = panel;
        this.theme = theme;
        this.toolTips = toolTips;
        // добавление компонентов
        addComponents();
        // политика обхода фокусом
        focusPolicy();    
        // отключение автокомпоновки элементов
        super.setLayout(null);
    }
    
    // создание компонентов окна приложения
    private void addComponents(){
        // <Тип изделия>
        assortmentMenu = new AssortmentMenu(this);
        String assortmentToolTipText = "выбор сортамента детали";
        toolTips.setToolTips(assortmentMenu, assortmentToolTipText);
        this.add(assortmentMenu);
        policy.add(assortmentMenu);
        assortmentMenu.addActionListener(assortmentMenu);
        
        // <Тип профиля>
        typesMenu = new TypesMenu(this);
        String typesToolTipText = "выбор типа профиля детали";
        toolTips.setToolTips(typesMenu, typesToolTipText);
        this.add(typesMenu);
        policy.add(typesMenu);
        typesMenu.addActionListener(typesMenu);
        
        // <№ профиля>
        numbersMenu = new NumbersMenu(this);
        String numbersToolTipText = "выбор номера профиля детали";
        toolTips.setToolTips(numbersMenu, numbersToolTipText);
        this.add(numbersMenu);
        policy.add(numbersMenu);
        numbersMenu.addActionListener(numbersMenu);
        
        //текстовое поле Ширина (для листа)
        widthField = new WidthField(this);
        String widthToolTipText = "поле ввода ширины детали";
        toolTips.setToolTips(widthField, widthToolTipText);
        this.add(widthField);
        policy.add(widthField);
        
        //текстовое поле Длина
        lengthField = new LengthField(this);
        String lengthToolTipText = "поле ввода длины детали";
        toolTips.setToolTips(lengthField, lengthToolTipText);
        this.add(lengthField);
        policy.add(lengthField);
        
        // текстовая строка результата
        resultMarker = new ResultMarker();
        theme.componentChangeColor(resultMarker);
        this.add(resultMarker);
        
        // <Сервисная строка>
        serviceInfo = new ServiceInfo();
        theme.componentChangeColor(serviceInfo);
        this.add(serviceInfo);
        
        // чек-бокс вычисления площади сложного периметра
        difficultAreaBox = new DifficultAreaBox(this);
        String areaBoxToolTipText = "расчет массы детали по задаваемой площади детали";
        toolTips.setToolTips(difficultAreaBox, areaBoxToolTipText);
        theme.componentChangeColor(difficultAreaBox);
        this.add(difficultAreaBox);
        
        //надпись мм для поля ширина
        Markmm widthMark = new Markmm(320, 22);
        this.add(widthMark);
        theme.componentChangeColor(widthMark);
        
        //надпись мм для поля длина      
        Markmm lengthMark = new Markmm(320, 62);
        this.add(lengthMark);
        theme.componentChangeColor(lengthMark);
    }
    
    // политика обхода фокусом
    private void focusPolicy(){        
        super.setFocusCycleRoot(true);
        myFocusTraversalPolicy = new MyFocusTraversalPolicy(policy);
        super.setFocusTraversalPolicy(myFocusTraversalPolicy);
    }
        
    /**
     * ссылка на конструктор главной панели
     * @return главная панель
     */
    public GeneralPanel getGeneralPanel(){
        return panel;
    }
    
    /**
     * сброс значений полей
     */
    void reset(){        
        resetMarker();
        //сброс полей ввода
        widthField.deactiveField();
        lengthField.deactiveField();    
    }
    
    /**
     * сброс значений сервисной строки и строки результата
     */
    void resetMarker(){
        // сброс надписей
        theme.componentChangeColor(resultMarker);
        theme.componentChangeColor(serviceInfo);
        resultMarker.reset();
        serviceInfo.reset();
    }
    
    public DifficultAreaBox getDifficultAreaBox(){
        return difficultAreaBox;
    }

    public AssortmentMenu getAssortmentMenu(){
        return assortmentMenu;
    }
    
    public TypesMenu getTypesMenu(){
        return typesMenu;
    }
    
    public NumbersMenu getNumbersMenu(){
        return numbersMenu;
    }

    public LengthField getLengthField(){
        return lengthField;
    }

    public WidthField getWidthField(){
        return widthField;
    }

    /**
     * Установка результата в строке результата
     * @param value результ вычислений
     */
    public void setResultation(String value){
        resultMarker.setResult(value);
        setResultToSystemClipboard(value);
        serviceInfo.setMessage("результат скопирован в буфер обмена");
    }
    
    /**
     * Сообщение об ошибке в сервисной строке
     * @param message сообщение об ошибке
     */
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
    
    /**
     * Установка интерфейчас базы данных
     * @param dataBase интерфейс базы данных
     */
    public void setDataBase(DataBaseInterface dataBase) {
        this.dataBase = dataBase;
    }
    
    /**
     * Запрос интерфейса базы данных
     * @return интерфейс базы данных
     */
    public DataBaseInterface getDataBase(){
        return dataBase;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        DifficultAreaBox source = (DifficultAreaBox) event.getItemSelectable();
        source.actionChooser(event);
    }
}
