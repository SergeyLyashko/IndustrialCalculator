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
public abstract class Detail implements MassableDetail {
    
    private String assortment;
    private String type;
    private String number;
    private double length;
    private double width;
    private double area;
    
    void setLength(double length) {
        this.length = length;
    }

    void setArea(double area) {
        this.area = area;
    }
    
    double getArea(){
        return area;
    }
    
    double getLength(){
        return length;
    }
}
