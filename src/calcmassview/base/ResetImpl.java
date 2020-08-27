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
package calcmassview.base;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import javax.swing.JComponent;

@Reset(reset = false)
public class ResetImpl implements Serializable, Reset {

    private static final long serialVersionUID = 1L;
    
    private final ArrayList<JComponent> components;
    
    public ResetImpl(ArrayList<JComponent> components){
        this.components = components;
    }

    @Override
    public boolean reset() {
        components.stream()
                .filter((JComponent comp) -> comp.getClass().isAnnotationPresent(Reset.class))
                .forEach((JComponent element) -> ((Reset)element).reset());
        return true;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return getClass();
    }
    
}
