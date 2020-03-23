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
 * Фабрика для создания объектов деталей
 * @author Sergei Lyashko
 */
public class FactoryDetail {
    
    /**
     * Создание объекта(детали) интерфейса
     * @param profileAssortment
     * @param profileType
     * @param profileNumber
     * @param length
     * @param width
     */
    public void getCurrentDetail(String profileAssortment, String profileType, String profileNumber, String length, String width){
        Massable detail;
        switch(profileAssortment){
            case "Лист":
                    detail = selectedProfile(profileAssortment, profileType, profileNumber, length, width);
                    Facade.getInstance().setMass(detail.getMass());
                    break;
            case "Швеллер":
            case "Уголок":
            case "Двутавр":
                    detail = new AssortmentSteelDetail(profileAssortment, profileType, profileNumber, length);
                    Facade.getInstance().setMass(detail.getMass());
                    break;
            case "Другое":               
                    detail = selectedProfile(profileAssortment, profileType, profileNumber, length, width);
                    Facade.getInstance().setMass(detail.getMass());
                    break;
        }
    }
    
    private Massable selectedProfile(String profileAssortment, String profileType, String profileNumber, String length, String width){
        Massable detail = null;
        switch (profileType){
            case "рифленая(ромб)":
                            return new RiffledSteelPlate(profileAssortment, profileType, profileNumber, length, width);
            case "тонколистовая":
            case "толстолистовая":
                            return new SteelPlate(profileNumber, length, width);
            case "Круг":
                            return new CircleSteelDetail(profileNumber, length);                                    
            case "Квадрат":
                            return new SquareSteelDetail(profileNumber, length);                                    
            case "Резиновая пластина":
                            return new RubberSheet(profileNumber, length, width);
        }
        return detail;
    }
}
