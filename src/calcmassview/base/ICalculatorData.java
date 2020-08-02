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
public interface ICalculatorData {
    
    public void setAssortment(String assortment);
    
    public String getAssortment();
    
    public void setType(String type);
    
    public String getType();
    
    public void setNumber(String number);
    
    public String getNumber();
    
    public void setWidth(String width);
    
    public String getWidth();
    
    public void setLength(String length);
    
    public String getLength();
    
}
