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

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import calcdatabase.IDataBase;

/**
 * Создание меню для комбо-бокс меню из профилей
 * из Базы Данных
 * @author Sergei Lyashko
 */
class Menu extends AbstractListModel<String> implements ComboBoxModel<String> {
    
    private final String assortmentHeader = "Тип сортамента";
    private final String typeHeader = "Тип профиля";
    private final String numberHeader = "№ профиля";
    
    private ArrayList<String> menu;
    private int selected;
    
    private String assortment;
    private String type;
    private String number;
    
    private final IDataBase dataBase;
    
    /**
     * Конструктор меню
     * @param dataBase интерфейс базы данных
     */
    public Menu(IDataBase dataBase){
        this.dataBase = dataBase;
    }
    
    /**
     * Конструктор по умолчанию
     */
    public Menu(){
        dataBase = null;
    }
    
    /**
     * создание модели меню для базовой панели выпадающего меню
     * @return 
     */
    public Menu createMenu(){        
        if(dataBase != null){
            menu = dataBase.receiveMenu(assortment, type, number);
        }else{
            menu = new ArrayList<>();
            menu.add(assortmentHeader);
        }
        return this;
    }
    
    /**
     * Создание модели меню типов профилей запрошенного сортамента
     * @param assortment наименование сортамента
     * @return 
     */
    public Menu createMenu(String assortment){
        this.assortment = assortment;        
        if(dataBase != null){
            menu  = dataBase.receiveMenu(assortment, type, number);
        }else{
            menu = new ArrayList<>();
            menu.add(typeHeader);
        }
        return this;
    }
    
    /**
     * Создание модели меню номеров профилей запрошенных сортамента и
     * типа профиля
     * @param assortment наименование сортамента
     * @param type наименование типа профиля
     * @return 
     */
    public Menu createMenu(String assortment, String type){
        this.assortment = assortment;
        this.type = type;        
        if(dataBase != null){
            menu = dataBase.receiveMenu(assortment, type, number);
        }else{
            menu = new ArrayList<>();
            menu.add(numberHeader);
        }
        return this;
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
