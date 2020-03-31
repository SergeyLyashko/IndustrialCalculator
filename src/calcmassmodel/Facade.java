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

import calcmassview.viewpanel.AbstractField;
import calcmassview.viewpanel.AbstractMenuBox;

/**
 * Фасад модели
 * реализует интерфейс ISubject делающий рассылку вычисления массы
 * детали в зависимости от посутпивших к нему данных
 * @author Sergei Lyashko
 */
public class Facade {
    
    private DetailFactory detailFactory;
    private AbstractDetail detail;
    private AbstractMenuBox baseMenuBox, typeProfileMenuBox, numberProfileMenuBox;
    private AbstractField lengthField, widthField;
    private String profileAssortment, profileType, profileNumber, length, width;
       
    public void addParametrs(AbstractMenuBox baseMenuBox, AbstractMenuBox typeProfileMenuBox, AbstractMenuBox numberProfileMenuBox,
            AbstractField lengthField, AbstractField widthField){
        this.baseMenuBox = baseMenuBox;
        this.typeProfileMenuBox = typeProfileMenuBox;
        this.numberProfileMenuBox = numberProfileMenuBox;
        this.lengthField = lengthField;
        this.widthField = widthField;
        setValueOfFields();
    }
    
    private void setValueOfFields(){
        this.profileAssortment = baseMenuBox.getStringValue();
        this.profileType = typeProfileMenuBox.getStringValue();
        this.profileNumber = numberProfileMenuBox.getStringValue();
        this.length = lengthField.getStringValue();
        this.width = widthField.getStringValue();
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
