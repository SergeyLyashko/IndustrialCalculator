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

/**
 *
 * @author Korvin
 */
public class CalculatorData implements ICalculatorData {
    
    private String assortment;
    private String type;
    private String number;
    private String width;
    private String length;
    
    @Override
    public void setAssortment(String assortment){
        this.assortment = assortment;
    }
    
    @Override
    public String getAssortment(){
        System.out.println(assortment);// TEST
        return assortment;
    }
    
    @Override
    public void setType(String type){
        this.type = type;
    }
    
    @Override
    public String getType(){
        System.out.println(type);// TEST
        return type;
    }
    
    @Override
    public void setNumber(String number){
        this.number = number;
    }
    
    @Override
    public String getNumber(){
        System.out.println(number);// TEST
        return number;
    }
    
    @Override
    public void setWidth(String width){
        this.width = width;
    }
    
    @Override
    public String getWidth(){
        System.out.println(width);// TEST
        return width;
    }
    
    @Override
    public void setLength(String length){
        this.length = length;
    }
    
    @Override
    public String getLength(){
        System.out.println(length);// TEST
        return length;
    }
    
}
