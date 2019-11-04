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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * работа с файлами properties
 * @author Sergei Lyashko
 */
public class MenuData {   
        
    //создание модели меню
    public static MenuBoxModel createMenuModelFromData(String menuName){
        MenuBoxModel menuModel = new MenuBoxModel(createMenu(menuName));
        return menuModel;
    }
    
    // Получение элемента меню (строки) из БД
    public static String getElementMenu(String keyString){
        File file = new File(MenuData.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        //File file = new File ("defaultMenu.properties");
        Properties properties = new Properties();
        String s = null;
        try{
            try (FileReader reader = new FileReader(file)) {
                properties.load(reader);
                s = properties.getProperty(keyString);            
            }
        }catch(IOException e){
            System.err.println("ОШИБКА: отсутствует файл с данными для создания меню!");
        }
        return s;
    }
    
    // создание списка из строки меню из БД для модели меню (MenuBoxModel)
    private static ArrayList<String> createMenu(String elementFromData){
        String str = getElementMenu(elementFromData);
        ArrayList<String> menu = new ArrayList<>();
        try{
            String[] s = str.split("\\s+");
            for(String point: s){
                if(point.isEmpty()) {
                    continue;
                }
                menu.add(point);
            }
            return menu;
        }catch(NullPointerException e){
            System.err.println("ОШИБКА: отстутствуют данные для создания меню!");
            menu.add("empty!");
            return menu;
        }        
    }
}
