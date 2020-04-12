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
package calcmassview.base;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;

/**
 * Политика обхода компонентов панели View фокусом
 * @author Sergei Lyashko
 */

public class MyFocusTraversalPolicy extends FocusTraversalPolicy {
    
    private final ArrayList<Component> order;
 
    public MyFocusTraversalPolicy(ArrayList<Component> order) {
        this.order = new ArrayList<>(order.size());
        this.order.addAll(order);
    }

    @Override
    public Component getComponentAfter(Container container, Component aComponent){
        int id = (order.indexOf(aComponent) + 1) % order.size();        
        return order.get(id);
    }

    @Override
    public Component getComponentBefore(Container container, Component aComponent){
        int id = order.indexOf(aComponent) - 1;
        if (id < 0){
            id = order.size() - 1;
        }
        return order.get(id);
    }

    @Override
    public Component getFirstComponent(Container container) {
        return order.get(0);
    }

    @Override
    public Component getLastComponent(Container container) {
        return order.get(order.size());
    }

    @Override
    public Component getDefaultComponent(Container container) {
        return order.get(0);
    }    
}