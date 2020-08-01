/*
 * Copyright 2019 Sergei Lyashko. Contacts: <9lLLLepuLLa@gmail.com>.
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
package calcmassview.base;

/**
 *
 * @author Korvin
 */
public class AreaBoxOFFState implements IDetailWidthState {
    
    private final BasePanel basePanel;
    
    public AreaBoxOFFState(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void haveWidth() {
        basePanel.getLengthField().activeField();
        basePanel.getWidthField().activeField();
        basePanel.getLengthField().difficultAreaStateOFF();
        basePanel.getWidthField().difficultAreaStateOFF();
    }

    @Override
    public void haveNotWidth() {
        basePanel.getLengthField().activeField();
        basePanel.getWidthField().deactiveField();
        basePanel.getLengthField().difficultAreaStateOFF();
        basePanel.getWidthField().difficultAreaStateOFF();
    }
}
