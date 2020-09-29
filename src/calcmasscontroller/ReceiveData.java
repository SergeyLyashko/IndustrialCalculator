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
package calcmasscontroller;

import calcmassmodel.InputService;
import calcmassview.ViewDispatcher;

/**
 *
 * @author Korvin
 */
public class ReceiveData implements InputService {

    private final ViewDispatcher viewData;

    ReceiveData(ViewDispatcher viewData) {
        this.viewData = viewData;
    }

    @Override
    public String getAssortment() {
        return viewData.getViewData().getAssortment();
    }

    @Override
    public String getType() {
        return viewData.getViewData().getType();
    }

    @Override
    public String getNumber() {
        return viewData.getViewData().getNumber();
    }

    @Override
    public double getLength() {
        return viewData.getViewData().getLength();
    }

    @Override
    public double getWidth() {
        return viewData.getViewData().getWidth();
    }
}
