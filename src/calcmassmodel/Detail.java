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

//DS
class Detail {
    
    private final String assortment;
    private final String type;
    private final String number;
    private final double length;
    private final double width;

    Detail(String assortment, String type, String number, double length, double width) {
        this.assortment = assortment;
        this.type = type;
        this.number = number;
        this.length = length;
        this.width = width;
    }

    public String getAssortmentName(){
        return assortment;
    }
    
    public String getTypeName(){
        return type;
    }
    
    public String getNumberName(){
        return number;
    }
    
    public double getDetailWidth(){
        return width;
    }
    
    public double getDetailLength(){
        return length;
    }
}
