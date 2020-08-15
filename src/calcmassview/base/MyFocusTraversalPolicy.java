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

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * Политика обхода фокусом компонентов
 * @author Sergei Lyashko
 */
class MyFocusTraversalPolicy extends FocusTraversalPolicy {
    
    private final ArrayList<JComponent> thisOrder;
 
    public MyFocusTraversalPolicy(ArrayList<JComponent> order) {
        this.thisOrder = new ArrayList<>(order.size());
        order.stream().filter((Component comp) -> 
                comp.getClass().isAnnotationPresent(ValueReceiveble.class))
                .forEach(thisOrder::add);
        // TEST
        /*for(Component comp: thisOrder){
            System.out.println("focuse: "+comp.getClass().getCanonicalName());
        }*/
    }

    @Override
    public Component getComponentAfter(Container container, Component aComponent){
        int id = (thisOrder.indexOf(aComponent) + 1) % thisOrder.size();        
        return thisOrder.get(id);
    }

    @Override
    public Component getComponentBefore(Container container, Component aComponent){
        int id = thisOrder.indexOf(aComponent) - 1;
        if (id < 0){
            id = thisOrder.size() - 1;
        }
        return thisOrder.get(id);
    }

    @Override
    public Component getFirstComponent(Container container) {
        return thisOrder.get(0);
    }

    @Override
    public Component getLastComponent(Container container) {
        return thisOrder.get(thisOrder.size());
    }

    @Override
    public Component getDefaultComponent(Container container) {
        return thisOrder.get(0);
    }    
}