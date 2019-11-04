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
package calcmassview;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * модель для панелей выпадающих меню
 * @author Sergei Lyashko
 */
public class MenuBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private ArrayList<String> menu;
    private int selected = 0;
    
    public MenuBoxModel(ArrayList<String> menu){
        this.menu = new ArrayList<>();
        this.menu = menu;
    }   

    public void add(String item) {
        menu.add(item);
        fireContentsChanged(this, 0, menu.size());
    }

    @Override
    public int getSize() {
        return menu.size();
    }

    @Override
    public String getElementAt(int index) {
        return menu.get(index);
    }   

    @Override
    public void setSelectedItem(Object item) {
        selected = menu.indexOf(item);
    }

    @Override
    public Object getSelectedItem() {
        return menu.get(selected);
    }
}
