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
package calcmassview;

import calcmassview.base.FieldsData;
import calcmasscontroller.ViewDataReceiveService;

/**
 *
 * @author Korvin
 */
class ViewDataServiceImpl implements ViewDataReceiveService {

    private final FieldsData data;

    public ViewDataServiceImpl(FieldsData data) {
        this.data = data;
    }

    @Override
    public String getAssortment() {
        return data.getAssortment();
    }

    @Override
    public String getType() {
        return data.getType();
    }

    @Override
    public String getNumber() {
        return data.getNumber();
    }

    @Override
    public double getFieldLengthValue() {
        return data.getLength();
    }

    @Override
    public double getFieldWidthValue() {
        return data.getWidth();
    }
    
}
