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
import calcdatabase.DataBase;
import java.util.Arrays;

/**
 * �������� ���� ��� �����-���� ���� �� ��������
 * �� ���� ������
 * @author Sergei Lyashko
 */
class Menu extends AbstractListModel<String> implements ComboBoxModel<String> {
    
    private final static String[] ASSORTMENT_WITH_WIDTH = {"����", "��������� ��������"};
    
    private final String assortmentHeader = "��� ����������";
    private final String typeHeader = "��� �������";
    private final String numberHeader = "� �������";
    
    private ArrayList<String> menu;
    private int selected;
    
    private String assortment;
    private String type;
    private String number;
    
    private final DataBase dataBase;
    
    /**
     * ����������� ����
     * @param dataBase ��������� ���� ������
     */
    public Menu(DataBase dataBase){
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
        if(dataBase != null){
            menu = dataBase.receiveMenu(assortment, type, number);
        }else{
            menu = new ArrayList<>();
            menu.add(assortmentHeader);
        }
        return this;
    }
    
    /**
     * �������� ������ ���� ����� �������� ������������ ����������
     * @param assortment ������������ ����������
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
     * �������� ������ ���� ������� �������� ����������� ���������� �
     * ���� �������
     * @param assortment ������������ ����������
     * @param type ������������ ���� �������
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
    
    /**
     * �������� �� ������� ������
     * @return
     */
    public boolean haveWidth(){
        //System.out.println("assortment: "+assortment);// TEST
        //System.out.println("type: "+type);// TEST
        return Arrays.stream(ASSORTMENT_WITH_WIDTH).anyMatch((String element) -> {
                return element.equals(assortment) || element.equals(type);
            });
    }
}
