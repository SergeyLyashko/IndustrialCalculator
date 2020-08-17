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
 * distributed under the License is distributed activate an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassview.base;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Korvin
 */
@ActiveStateField(activate = false, deactivate = true)
public class ActiveStateFieldImpl implements ActiveStateField {
    
    private final ArrayList<JComponent> components;
    
    public ActiveStateFieldImpl(ArrayList<JComponent> components){
        this.components = components;
    }
    
    private boolean haveWidth() {
        return components.stream()                
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(IDetailWidthState.class))
                .anyMatch((JComponent element) -> ((IDetailWidthState)element).haveWidth() == true);
    }

    @Override
    public boolean activate() {
        System.out.println("ON: "+haveWidth());// TEST
        components.stream()
                .filter((JComponent component) -> component.getClass().isAnnotationPresent(ActiveStateField.class))
                .forEach((JComponent element) -> {
                    if(haveWidth()){
                        ((ActiveStateField)element).activate();
                    }else{
                        String fieldName = ((ValueReceiveble)element).annotationType().getSimpleName();
                        //System.out.println("ON field: "+fieldValue);// TEST
                        if(fieldName.equals("LengthField")){
                            ((ActiveStateField)element).activate();
                        }
                    }
                });
        return true;
    }

    @Override
    public boolean deactivate() {
        System.out.println("OFF");// TEST
        components.stream()
                .filter((JComponent comp) -> comp.getClass().isAnnotationPresent(ActiveStateField.class))
                .forEach((JComponent element) -> {
                    ((ActiveStateField)element).deactivate();   
                    //System.out.println("off: "+element.getClass().getName());// TEST
                });
        return true;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return getClass();
    }
}
