package components.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import components.common.Host;
import components.common.Visitor;
import components.common.AppComponent;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component("info")
class Info implements AppComponent, Host {

    private final JLabel jLabel;
    private Visitor colorVisitor;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorVisitor.addHost(this);
    }

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


    Info(){
        jLabel = new JLabel();
        jLabel.setText(TEXT);
        jLabel.setPreferredSize(new Dimension(250, 500));
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitLabel(this);
    }

    @Override
    public JComponent getComponentParent() {
        return jLabel;
    }
}
