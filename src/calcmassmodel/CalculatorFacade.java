/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
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
 * Фасад модели
 * @author Sergei Lyashko
 */
class CalculatorFacade {
    
    private DetailFactory detailFactory;
    private AbstractDetail detail;
    private String profileAssortment, profileType, profileNumber, length, width;
       
    public void setParametrs(String assortment, String type, String number, String length, String width){
        this.profileAssortment = assortment;
        this.profileType = type;
        this.profileNumber = number;
        this.length = length;
        this.width = width;
        createDetail();
    }
    
    /**
     * Создание детали
     */
    private void createDetail(){
        detailFactory = new DetailFactory();
        detail = detailFactory.orderDetail(profileAssortment, profileType, profileNumber, length, width);
    }
    
    public AbstractDetail getDetail(){
        return detail;
    }
}
