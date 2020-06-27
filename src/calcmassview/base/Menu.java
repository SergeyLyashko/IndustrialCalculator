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

import calcdatabase.DataBaseInterface;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 * �������� ���� ��� �����-���� ���� �� ��������
 * �� ���� ������
 * @author Sergei Lyashko
 */
public class Menu extends AbstractListModel<String> implements ComboBoxModel<String> {
    
    private final String assortmentHeader = "��� ����������";
    private final String typeHeader = "��� �������";
    private final String numberHeader = "� �������";
    
    private ArrayList<String> menu;
    private int selected;
    
    private String assortment;
    private String type;
    private String number;
    
    private final DataBaseInterface dataBase;
    
    /**
     * ����������� ����
     * @param dataBase ��������� ���� ������
     */
    public Menu(DataBaseInterface dataBase){
        this.dataBase = dataBase;
    }
    
    /**
     * ����������� �� ���������
     */
    public Menu(){
        dataBase = null;
    }
    
    /**
     * �������� ������ ���� ��� ������� ������ ����������� ����
     * @return 
     */
    public Menu createMenu(){
        dataBase.query(assortment, type, number);
        menu = dataBase.receiveMenuList();
        return this;
    }
    
    /**
     * �������� ������ ���� ����� �������� ������������ ����������
     * @param assortment ������������ ����������
     * @return 
     */
    public Menu createMenu(String assortment){
        this.assortment = assortment;
        dataBase.query(assortment, type, number);
        menu = dataBase.receiveMenuList();
        return this;
    }
    
    /**
     * �������� ������ ���� ������� �������� ����������� ���������� �
     * ���� �������
     * @param assortment ������������ ����������
     * @param type ������������ ���� �������
     * @return 
     */
    public Menu createMenu(String assortment, String type){
        this.assortment = assortment;
        this.type = type;
        dataBase.query(assortment, type, number);
        menu = dataBase.receiveMenuList();
        return this;
    }
    
    /**
     * �������� ���� ��� ������
     * @param menuBox �����-���� ����
     * @return 
     */
    public Menu createStartMenu(JComboBox<String> menuBox){
        menu = new ArrayList<>();
        if(menuBox.getClass().equals(AssortmentMenu.class)){
            menu.add(assortmentHeader);
        }
        if(menuBox.getClass().equals(TypesMenu.class)){
            menu.add(typeHeader);
        }
        if(menuBox.getClass().equals(NumbersMenu.class)){
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
