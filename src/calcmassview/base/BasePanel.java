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
import calcmassview.settings.ToolTipsInterface;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import calcmassview.settings.ColorThemeInterface;

/**
 * основная панель с компонентами
 * @author Sergei Lyashko
 */
public class BasePanel extends JPanel implements ItemListener {
    
    // интерфейс базы данных
    private DataBaseInterface dataBase;
    // combo-boxes
    private AssortmentMenu assortmentMenu;
    private TypesMenu typeProfileMenu;
    private NumbersMenu numberProfileMenu;
    // поля ввода значений
    private LengthField lengthField;
    private WidthField widthField;    
    // политика обхода фокуса
    private final MyFocusTraversalPolicy myFocusTraversalPolicy;
    private final ArrayList<Component> policy;
    // гравная панель
    private final GeneralPanel panel;
    // сервисная строка
    private ServiceInfo serviceInfo;
    // строка результата
    private ResultMarker resultMarker;
    // чек-бокс задания площади детали
    private DifficultAreaBox difficultAreaBox;
    
    public BasePanel(GeneralPanel panel, ColorThemeInterface theme, ToolTipsInterface toolTips) {
        this.panel = panel;
        policy = new ArrayList<>();
        createComponents(theme, toolTips);
        createDecorations(theme);
        // отключение автокомпоновки элементов
        super.setLayout(null);       
        // политика обхода фокуса
        super.setFocusCycleRoot(true);
        myFocusTraversalPolicy = new MyFocusTraversalPolicy(policy);
        super.setFocusTraversalPolicy(myFocusTraversalPolicy);
    }
    
    private void createDecorations(ColorThemeInterface theme){
        //надпись мм для поля
        JLabel widthMark = new JLabel();
        widthMark.setVisible(true);
        widthMark.setSize(25, 20);
        widthMark.setForeground(Color.white);
        widthMark.setText("мм");
        widthMark.setLocation(320, 22);
        this.add(widthMark);
        theme.setColorTheme(widthMark);
        
        //надпись мм для поля
        JLabel lengthMark = new JLabel();
        lengthMark.setVisible(true);
        lengthMark.setSize(25, 20);
        lengthMark.setForeground(Color.white);
        lengthMark.setText("мм");
        lengthMark.setLocation(320, 62);
        this.add(lengthMark);
        theme.setColorTheme(lengthMark);
    }
    
    private void createComponents(ColorThemeInterface theme, ToolTipsInterface toolTips){
        theme.setColorTheme(this);
        // <Тип изделия>
        assortmentMenu = new AssortmentMenu(this, toolTips);
        // <Тип профиля>
        typeProfileMenu = new TypesMenu(this, toolTips);
        // <№ профиля>
        numberProfileMenu = new NumbersMenu(this, toolTips);
        //текстовое поле Ширина (для листа)
        widthField = new WidthField(this, toolTips);
        //текстовое поле Длина
        lengthField = new LengthField(this, toolTips);
        // текстовая строка результата
        resultMarker = new ResultMarker(this, theme);
        // <Сервисная строка>
        serviceInfo = new ServiceInfo(this, theme);        
        // чек-бокс вычисления площади сложного периметра
        difficultAreaBox = new DifficultAreaBox(this, theme, toolTips);
    }
    
    // добавление компонентов в политику обхода фокуса
    void addPolicy(Component component){
        policy.add(component);
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
        resultMarker.reset();
        serviceInfo.reset();
    }
    
    public DifficultAreaBox getDifficultAreaBox(){
        return difficultAreaBox;
    }

    public AssortmentMenu getAssortmentMenu(){
        return assortmentMenu;
    }
    
    public TypesMenu getTypeProfileMenu(){
        return typeProfileMenu;
    }
    
    public NumbersMenu getNumberProfileMenu(){
        return numberProfileMenu;
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
