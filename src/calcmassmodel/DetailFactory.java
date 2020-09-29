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

/**
 *
 * @author Korvin
 */
public abstract class DetailFactory {
    

    public Detail order(InputService inputService, AssortmentsAreaReceiver detailArea){
        
        String assortment = inputService.getAssortment();
        String type = inputService.getType();
        String number = inputService.getNumber();
        double length = inputService.getLength();
        double width = inputService.getWidth();
        
        double area = detailArea.getDetailArea(assortment, type, number);
        
        Detail detail = createDetail(assortment, type);
        detail.setLength(length);
        detail.setArea(area);
        return detail;
    }

    public abstract Detail createDetail(String assortment, String type);
    
}
