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
package calcmassmodel;


public class CalculatorInputDataImpl implements CalculatorInputData {
    
    private String assortment;
    private String type;
    private String number;
    private double length;
    private double width;


    @Override
    public void setAssortment(String assortment) {
        this.assortment = assortment;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }
    
    public String getAssortment(){
        return assortment;
    }
    
    public String getType(){
        return type;
    }
    
    public String getNumber(){
        return number;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getLength(){
        return length;
    }
}
