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

import calcmassmodel.InputDataService;

/**
 *
 * @author Korvin
 */
class InputServiceImpl implements InputDataService {

    private final ViewDataReceiveService view;

    InputServiceImpl(ViewDataReceiveService view) {
        this.view = view;
    }

    @Override
    public String getAssortment() {
        return view.getAssortment();
    }

    @Override
    public String getType() {
        return view.getType();
    }

    @Override
    public String getNumber() {
        return view.getNumber();
    }

    @Override
    public double getLength() {
        return view.getFieldLengthValue();
    }

    @Override
    public double getWidth() {
        return view.getFieldWidthValue();
    }
}
