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
public class CalculatorData {
    
    private String assortment;
    private String type;
    private String number;
    private String width;
    private String length;
    
    public void setAssortment(String assortment){
        this.assortment = assortment;
    }
    
    public String getAssortment(){
        return assortment;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return type;
    }
    
    public void setNumber(String number){
        this.number = number;
    }
    
    public String getNumber(){
        return number;
    }
    
    public void setWidth(String width){
        this.width = width;
    }
    
    public String getWidth(){
        return width;
    }
    
    public void setLength(String length){
        this.length = length;
    }
    
    public String getLength(){
        return length;
    }
    
}
