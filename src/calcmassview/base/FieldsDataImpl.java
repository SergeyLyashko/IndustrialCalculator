/*
 * Copyright 2020 Korvin.
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

import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Korvin
 */
public class FieldsDataImpl implements FieldsData {
    
    private String assortment;
    private String type;
    private String number;
    private double width;
    private double length;
    private double area;
    
    private final ArrayList<JComponent> components;

    public FieldsDataImpl(ArrayList<JComponent> components) {                
        this.components = components;
        setFields();
    }
    
    private void setFields(){
        components.stream()
            .filter((JComponent component) -> component.getClass().isAnnotationPresent(ValueReceiveble.class))
            .forEach((JComponent element) -> {
                if(element.getClass().equals(AssortmentMenuBox.class)){
                    assortment = ((ValueReceiveble)element).getCurrentMenuItem();
                }
                if(element.getClass().equals(TypesMenuBox.class)){
                    type = ((ValueReceiveble)element).getCurrentMenuItem();
                }
                if(element.getClass().equals(NumbersMenuBox.class)){
                    number = ((ValueReceiveble)element).getCurrentMenuItem();
                }
                if(element.getClass().equals(WidthField.class)){
                    String value = ((ValueReceiveble)element).getCurrentMenuItem();
                    width = getNumberOf(value);
                }
                if(element.getClass().equals(LengthField.class)){
                    String value = ((ValueReceiveble)element).getCurrentMenuItem();
                    length = getNumberOf(value);
                }
            });
    }
    
    /**
     * Получение числового значения
     * @param value Строковое представление значения
     * @return числовое представление 
     */
    private double getNumberOf(String value) {
        try{
            return Double.parseDouble(value);
        }catch(NumberFormatException ex){
            //this.message = "ошибка! введенное значение не является числом!";
        }
        return 0;
    }
    
    @Override
    public String getAssortment(){
        System.out.println("test assort: "+assortment);// TEST
        return assortment;
    }
    
    @Override
    public String getType(){
        System.out.println(type);// TEST
        return type;
    }
    
    @Override
    public String getNumber(){
        System.out.println(number);// TEST
        return number;
    }
    
    @Override
    public double getWidth(){
        System.out.println(width);// TEST
        return width;
    }
    
    @Override
    public double getLength(){
        System.out.println(length);// TEST
        return length;
    }

    @Override
    public double getArea() {
        return area;
    }
}
