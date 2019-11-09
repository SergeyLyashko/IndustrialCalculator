/*
 * Copyright 2019 Korvin.
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

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import javax.swing.JEditorPane;

/**
 *
 * @author Korvin
 */
public class InfoText  extends JEditorPane {
    
    private URL infoURL;
        
    public InfoText(){
        super();        
        //super.setText(loadText());
        loadText();        
        super.setEditable(false);
        //super.setForeground(Color.white);
        super.setBackground(Color.black);       
    }
    
    private void loadText(){
        String text =  "infoText.html";
        infoURL = getClass().getResource(text);
        displayURL(infoURL);
    }    
    /*
    private String loadText(){
        //String text =  "infoText.html";
        //infoURL = getClass().getResource(text);
        //displayURL(infoURL);
        String text =                 
        "<html>"+
        "Управление.<br>"+
        "Переключение между вкладками можно осуществлять<br>"+
        "с помощью клавиш <button>Tab</button> (или <button>Shift</button> + <button>Tab</button>) клавиатуры<br>"+
        "(либо с помощью мыши).<br>"+
        "Стрелками <button>&#8595;</button> и <button>&#8593;</button> (либо с помощью мыши) осуществляется<br>"+
        "выбор пунктов в меню выпадающих списков.<br>"+
        "Поле ввода длина становится активно при выборе<br>"+
        "необходимого профиля в третьем нижнем выпадающем меню.<br>"+
        "Поле ввода ширина становится активно в том же самом случае,<br>"+
        "а также если выбраны детали, имеющие ширину (лист, пластина и т.п.).<br>"+
        "Переключение между полями ввода значений осуществляется<br>"+
        "как во всем приложении или по нажатию <button>Enter</button><br>"+
        "Результат вычислений выводится в поле длина<br>"+
        "по нажатию <button>Enter</button> и автоматически копируется в буфер обмена<br>"+
        "для вставки в стороннее приложение по нажатию <button>Ctrl</button>+<button>V</button><br>"+
        "<br>"+
        "Для расчетов используются следующие ГОСТы:<br>"+
        "<ul><br>"+
        "<li>Сталь прокатная угловая равнополочная <a href=http://docs.cntd.ru/document/1200001025>ГОСТ 8509-93</a><br>"+
        "<li>Сталь прокатная угловая неравнополочная <a href=http://docs.cntd.ru/document/1200001023>ГОСТ 8510-86</a><br>"+
        "<li>Сталь прокатная швеллеры <a href=http://docs.cntd.ru/document/1200019824>ГОСТ 8240-97</a><br>"+
        "<li>Сталь горячекатаная квадратная <a href=http://docs.cntd.ru/document/1200109199>ГОСТ 2591-51</a><br>"+
        "<li>Прокат стальной горячекатаный круглый <a href=http://docs.cntd.ru/document/1200004404>ГОСТ 2590-88</a><br>"+
        "<li>Листы стальные с ромбическим рифлением. <a href=http://docs.cntd.ru/document/1200005122/>ГОСТ 8568-77</a><br>"+
        "<li>Пластины резиновые и резинотканевые <a href=http://docs.cntd.ru/document/1200005719>ГОСТ 7338-90</a><br>"+
        "</ul><br>"+
        "Copyright &#169; 2019 Sergei Lyashko.<br>"+
        "Contacts: <a href=mailto:slyashko@mail.ru>написать автору</a><br>";
        return text;
    }*/

    private void displayURL(URL url) {
        try{
            super.setPage(url);
        }catch(IOException e){
            System.err.println("Html файл для отображения Справки не найден "+ e);
        }
    }
}
