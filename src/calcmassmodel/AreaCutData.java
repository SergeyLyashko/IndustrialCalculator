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
package calcmassmodel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение площади сечений сортамента из БД
 * @author Sergei Lyashko
 */
// 
public class AreaCutData {     
    
    // Получение значения площади из (строки) из БД
    public static String getAreaCut(String detailName){
        File file = new File(AreaCutData.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        //File file = new File("./src/calcmassmodel/detailAreaCut.properties");
        Properties properties = new Properties();
        String s = null;
        try{
            try (FileReader reader = new FileReader(file)) {
                properties.load(reader);
                s = properties.getProperty(detailName);            
            }
        }catch(IOException e){
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        return s;
    }
}
