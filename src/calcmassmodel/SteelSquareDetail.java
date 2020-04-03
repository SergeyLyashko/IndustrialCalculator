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
 * Сталь горячетканная квадратная ГОСТ 8240-97
 * @author Korvin
 */
class SteelSquareDetail extends AbstractDetail {

    public SteelSquareDetail(String profileAssortment, String profileType, String profileNumber, String length, String width) {
        super(profileAssortment, profileType, profileNumber, length, width);
        setLength(length);
        setValueFromDataBase(profileAssortment, profileType, profileNumber);
    }
        
    @Override
    public double getMass() {
        double dbValue = getValueFromDataBase();
        return DENSITY_STEEL * getLength() * dbValue * dbValue;
    }
}
