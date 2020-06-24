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
package calcmassview.info;

import calcmassview.settings.Theme;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * ������ ����������
 * @author Sergei Lyashko
 */
public class InfoPanel extends JPanel {

    private final String text =         
        "<html>"+
        " ����������.<br>"+
        " ��������� �� ���� �������������� � �������<br>"+
        " ������ [Tab], [Shift] + [Tab] � ��������� &#8595; � &#8593; (���� � ������� ����).<br>"+             
        " ���� ����� [�����]���������� ������� ��� ������ ������������ ������� � ���������� ����<br>"+        
        " [� �������]<br>"+
        " ���� ����� [������] ���������� ������� � ��� �� ����� ������, � ����� ���� ������� ������, ������� ������ (����, �������� � �.�.).\n"+
        " ������������ ����� ������ ����� �������� ��������������"+
        " � ������� <b>[Tab]</b>, ������� <b>[Enter]</b> ��� � ������� ����.<br>"+
        " ��������� ���������� ��������� �� ������� [Enter] "+
        " � ���� [�����] � ������������� ���������� � ����� ������.<br>"+
        " ��� ������� � ��������� ���������� �������������� ����������� [Ctrl]+[ V ]"+
        "<br>"+
        " ��� �������� ������������ ��������� �����:<br>"+
        " &nbsp; &bull;<font size=-2> ������ �������� ������������� <a href=http://docs.cntd.ru/document/1200001025>���� 19903-74</a></font><br>"+
        " &nbsp; &bull;<font size=-2> ����� ��������� ������� ������������� <a href=http://docs.cntd.ru/document/1200001025>���� 8509-93</a></font><br>"+
        " &nbsp; &bull;<font size=-2> ����� ��������� ������� ��������������� <a href=http://docs.cntd.ru/document/1200001023>���� 8510-86</a></font><br>"+
        " &nbsp; &bull;<font size=-2> ����� ��������� �������� <a href=http://docs.cntd.ru/document/1200019824>���� 8240-97</a></font><br>"+
        " &nbsp; &bull;<font size=-2> �������� �������� ������������� � ������������� ������� ����� <a href=http://docs.cntd.ru/document/901711178>���� ���� 26020-83</a></font><br>"+
        " &nbsp; &bull;<font size=-2> ����� ������������� ���������� <a href=http://docs.cntd.ru/document/1200109199>���� 2591-51</a></font><br>"+
        " &nbsp; &bull;<font size=-2> ������ �������� ������������� ������� <a href=http://docs.cntd.ru/document/1200004404>���� 2590-88</a></font><br>"+
        " &nbsp; &bull;<font size=-2> ����� �������� � ����������� ���������. <a href=http://docs.cntd.ru/document/1200005122/>���� 8568-77</a></font><br>"+        
        " &nbsp; &bull;<font size=-2> �������� ��������� � �������������� <a href=http://docs.cntd.ru/document/1200005719>���� 7338-90</a></font><br>"+
        "<br>"+
        " <font size=-2>Copyright &#169; 2019 Sergei Lyashko.<br>"+
        " <font size=-2>Contacts: 9llllepulla@gmail.com";
    
    public InfoPanel(Theme theme){
        super.setLayout(new BorderLayout());
        JLabel htmlText = new JLabel(text);
        JScrollPane scroller = new JScrollPane(htmlText);
        setPreference(htmlText, scroller, theme);
        super.add(scroller, BorderLayout.CENTER);
    }
    
    // ��������� ����������
    private void setPreference(JLabel htmlText, JScrollPane scroller, Theme theme){        
        theme.setColorTheme(this);
        setTextPreference(htmlText, theme);
        setScrollerPreference(scroller, theme);        
    }
    
    // ��������� ���������� ������
    private void setTextPreference(JLabel htmlText, Theme theme){
        htmlText.setPreferredSize(new Dimension(250, 510));
        theme.setColorTheme(htmlText);
    }
    
    // ��������� ���������� ������ ���������
    private void setScrollerPreference(JScrollPane scroller, Theme theme){
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        theme.setColorTheme(scroller.getViewport());
    }
}
