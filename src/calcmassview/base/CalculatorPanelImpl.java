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
 * distributed under the License is distributed activate an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassview.base;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import calcdatabase.DataBase;
import calcmassview.settings.ColorTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import javax.swing.JComponent;

/**
 * основная панель с компонентами
 * @author Sergei Lyashko
 */
@SuppressWarnings("serial")
@ColorTheme()
public class CalculatorPanelImpl extends JPanel implements CalculatorPanel, ItemListener, ActionListener, IKeyActionObserver, ColorTheme {
    
    // коллекция компонентов
    private final ArrayList<JComponent> components;    
    // чек-бокс задания площади детали
    private DifficultAreaBox difficultAreaBox;
    // Данные
    private Detail calculatorData;    
    
    public CalculatorPanelImpl(ArrayList<JComponent> components, DataBase dataBase) {
        this.components =components;
        // добавление компонентов
        addComponents(dataBase);        
        // политика обхода фокусом
        focusPolicy();    
        // отключение автокомпоновки элементов
        super.setLayout(null);
        super.setFocusCycleRoot(true);
    }
    
    // создание компонентов окна приложения
    private void addComponents(DataBase dataBase){        
        // состояние активации полей ввода
        ActiveStateField activeStateField = new ActiveStateFieldImpl(components);
        // сброс маркеров
        ServiceInscription resetMarkers = new ServiceInscriptionImpl(components);
        // <Тип изделия>
        AssortmentMenuBox assortmentBox = new AssortmentMenuBox(dataBase, activeStateField, resetMarkers);
        assortmentBox.addActionListener(this);
        components.add(assortmentBox);
        // <Тип профиля>
        TypesMenuBox typesBox = assortmentBox.getTypesMenu();
        typesBox.addActionListener(this);
        components.add(typesBox);
        // <№ профиля>
        NumbersMenuBox numbersBox = typesBox.getNumbersMenu();
        numbersBox.addActionListener(this);
        components.add(numbersBox);
        //текстовое поле Ширина (для листа)
        WidthField widthField = new WidthField(resetMarkers);
        components.add(widthField);
        //текстовое поле Длина
        LengthField lengthField = new LengthField(resetMarkers);
        lengthField.registerObserver(this);
        components.add(lengthField);
        // текстовая строка результата
        ResultServiceInscription resultMarker = new ResultServiceInscription();
        components.add(resultMarker);
        // <Сервисная строка>
        MessageServiceInscription serviceInfo = new MessageServiceInscription();
        components.add(serviceInfo);
        // чек-бокс вычисления площади сложного периметра
        difficultAreaBox = new DifficultAreaBox(this);
        components.add(difficultAreaBox);
        //надпись мм для поля ширина
        Markmm widthMark = new Markmm(320, 22);
        components.add(widthMark);
        //надпись мм для поля длина      
        Markmm lengthMark = new Markmm(320, 62);
        components.add(lengthMark);
        
        addComponentToPanel();
        // начальное состояние полей ввода
        activeStateField.deactivate();
        
        // установка начального состояния селектора выбора площади
        //detailState = new AreaBoxOFFState(components);
    }
    // добавление комполнентов на панель
    private void addComponentToPanel(){
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(CalculatorPanel.class))
                .forEach(this::add);
    }
    
    // политика обхода фокусом
    private void focusPolicy(){
        MyFocusTraversalPolicy myFocusTraversalPolicy = new MyFocusTraversalPolicy(components);
        super.setFocusTraversalPolicy(myFocusTraversalPolicy);
    }
    
    /**
     *
     * @return
     */
    public Detail getData(){
        return calculatorData;
    }
    
    public void createData(){        
        calculatorData = new Detail(components);
        // TEST
        calculatorData.getAssortment();
        calculatorData.getType();
        calculatorData.getNumber();
        calculatorData.getWidth();
        calculatorData.getLength();
    }
        
    @Override
    public void itemStateChanged(ItemEvent event) {
        DifficultAreaBox source = (DifficultAreaBox) event.getItemSelectable();
        source.actionChooser(event);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        MenuBoxSelectable source = (MenuBoxSelectable) e.getSource();
        // выбор пунктов меню
        String selectedMenuItem = source.getSelectedMenuItem();
        source.actionMenuSelect(selectedMenuItem);
    }    

    @Override
    public void keyActionUpdate() {
        //System.out.println("base panel update");// TEST
        createData();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}