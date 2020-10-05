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
package calcmasscontroller;

import calcmassview.info.Info;
import calcmassview.settings.ColorTheme;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import javax.swing.JLabel;

/**
 *
 * @author Korvin
 */
@ColorTheme()
public class InfoImpl extends JLabel implements Serializable, Info, ColorTheme {
    
    private static final long serialVersionUID = 1L;
    
    private static final String TEXT =         
        "<html>"+
        " Управление.<br>"+
        " Навигация по меню осуществляется с помощью<br>"+
        " клавиш [Tab], [Shift] + [Tab] и стрелками &#8595; и &#8593; (либо с помощью мыши).<br>"+             
        " Поле ввода [длина]становится активно при выборе необходимого профиля в выпадающем меню<br>"+        
        " [№ профиля]<br>"+
        " Поле ввода [ширина] становится активно в том же самом случае, а также если выбраны детали, имеющие ширину (лист, пластина и т.п.).\n"+
        " Переключение между полями ввода значений осуществляется"+
        " с помощью <b>[Tab]</b>, нажатию <b>[Enter]</b> или с помощью мыши.<br>"+
        " Результат вычислений выводится по нажатию [Enter] "+
        " в поле [длина] и автоматически копируется в буфер обмена.<br>"+
        " Для вставки в стороннее приложение воспользуйтесь комбинацией [Ctrl]+[ V ]"+
        "<br>"+
        " Для расчетов используются следующие ГОСТы:<br>"+
        " &nbsp; &bull;<font size=-2> Прокат листовой горячетканный <a href=http://docs.cntd.ru/document/1200001025>ГОСТ 19903-74</a></font><br>"+
        " &nbsp; &bull;<font size=-2> Сталь прокатная угловая равнополочная <a href=http://docs.cntd.ru/document/1200001025>ГОСТ 8509-93</a></font><br>"+
        " &nbsp; &bull;<font size=-2> Сталь прокатная угловая неравнополочная <a href=http://docs.cntd.ru/document/1200001023>ГОСТ 8510-86</a></font><br>"+
        " &nbsp; &bull;<font size=-2> Швеллеры стальные горячекатаные <a href=http://docs.cntd.ru/document/1200019824>ГОСТ 8240-97</a></font><br>"+
        " &nbsp; &bull;<font size=-2> Двутавры стальные горячекатаные с параллельными гранями полок <a href=http://docs.cntd.ru/document/901711178>ГОСТ 26020-83</a></font><br>"+
        " &nbsp; &bull;<font size=-2> Сталь горячекатаная квадратная <a href=http://docs.cntd.ru/document/1200109199>ГОСТ 2591-51</a></font><br>"+
        " &nbsp; &bull;<font size=-2> Прокат стальной горячекатаный круглый <a href=http://docs.cntd.ru/document/1200004404>ГОСТ 2590-88</a></font><br>"+
        " &nbsp; &bull;<font size=-2> Листы стальные с ромбическим рифлением. <a href=http://docs.cntd.ru/document/1200005122/>ГОСТ 8568-77</a></font><br>"+        
        " &nbsp; &bull;<font size=-2> Пластины резиновые и резинотканевые <a href=http://docs.cntd.ru/document/1200005719>ГОСТ 7338-90</a></font><br>"+
        "<br>"+
        " <font size=-2>Copyright &#169; 2019 Sergei Lyashko.<br>"+
        " <font size=-2>Contacts: 9llllepulla@gmail.com";
    
    public InfoImpl(){
        super(TEXT);
    }

    @Override
    public JLabel receiveInfo() {
        return this;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
