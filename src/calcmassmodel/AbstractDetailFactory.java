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
 * Паттерн Фабричный метод
 * @author Sergei Lyashko
 */
abstract class AbstractDetailFactory {
    
    /**
     * Заказ детали
     * @param profileAssortment
     * @param profileType
     * @param profileNumber
     * @param length
     * @param width
     * @return
     */
    public AbstractDetail orderDetail(String profileAssortment, String profileType, String profileNumber, String length, String width){
        AbstractDetail detail = createDetail(profileAssortment, profileType, profileNumber, length, width);
        detail.getMass();
        return detail;
    }
    
    /**
     * Фабричный метод создания детали
     * @param profileAssortment
     * @param profileType
     * @param profileNumber
     * @param length
     * @param width
     * @return
     */
    protected abstract AbstractDetail createDetail(String profileAssortment, String profileType, String profileNumber, String length, String width);    
}
