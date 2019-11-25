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
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 *
 * @author Korvin
 */
public class InfoText extends JPanel {    
    
    private final JLabel htmlText;
        
    public InfoText(){
        super();
        super.setBackground(Color.black); 
        Theme.addTheme(this);
        htmlText = new JLabel(loadText());
        htmlText.setPreferredSize(new Dimension(280, 510));
        Theme.addTheme(htmlText);
        htmlText.setHorizontalAlignment(SwingConstants.CENTER);
        htmlText.setVerticalAlignment(SwingConstants.CENTER);
        htmlText.setForeground(Color.WHITE);
        
        JScrollPane scroller = new JScrollPane(htmlText);
        scroller.getViewport().setBackground(Color.BLACK);        
        Theme.addTheme(scroller.getViewport());
        scroller.setPreferredSize(new Dimension(345,132));
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBorder(null);        
        super.add(scroller);
    }  
    
    private String loadText(){        
        String text =         
        "<html>"+
        "����������.<br>"+
        "��������� �� ���� �������������� � �������<br>"+
        "������ [Tab], [Shift] + [Tab] � ��������� &#8595; � &#8593; (���� � ������� ����).<br>"+             
        "���� ����� [�����]���������� ������� ��� ������ ������������ ������� � ���������� ����<br>"+        
        "[� �������]<br>"+
        "���� ����� [������] ���������� ������� � ��� �� ����� ������, � ����� ���� ������� ������, ������� ������ (����, �������� � �.�.).\n"+
        "������������ ����� ������ ����� �������� ��������������"+
        "� ������� <b>[Tab]</b>, ������� <b>[Enter]</b> ��� � ������� ����.<br>"+
        "��������� ���������� ��������� �� ������� [Enter] "+
        "� ���� [�����] � ������������� ���������� � ����� ������.<br>"+
        "��� ������� � ��������� ���������� �������������� ����������� [Ctrl]+[ V ]"+
        "<br>"+
        "��� �������� ������������ ��������� �����:<br>"+               
        "&nbsp; &bull;<font size=-2> ����� ��������� ������� ������������� <a href=http://docs.cntd.ru/document/1200001025>���� 8509-93</a></font><br>"+
        "&nbsp; &bull;<font size=-2> ����� ��������� ������� ��������������� <a href=http://docs.cntd.ru/document/1200001023>���� 8510-86</a></font><br>"+
        "&nbsp; &bull;<font size=-2> ����� ��������� �������� <a href=http://docs.cntd.ru/document/1200019824>���� 8240-97</a></font><br>"+
        "&nbsp; &bull;<font size=-2> �������� �������� ������������� � ������������� ������� ����� <a href=http://docs.cntd.ru/document/901711178>���� ���� 26020-83</a></font><br>"+
        "&nbsp; &bull;<font size=-2> ����� ������������� ���������� <a href=http://docs.cntd.ru/document/1200109199>���� 2591-51</a></font><br>"+
        "&nbsp; &bull;<font size=-2> ������ �������� ������������� ������� <a href=http://docs.cntd.ru/document/1200004404>���� 2590-88</a></font><br>"+
        "&nbsp; &bull;<font size=-2> ����� �������� � ����������� ���������. <a href=http://docs.cntd.ru/document/1200005122/>���� 8568-77</a></font><br>"+        
        "&nbsp; &bull;<font size=-2> �������� ��������� � �������������� <a href=http://docs.cntd.ru/document/1200005719>���� 7338-90</a></font><br>"+
        "<br>"+
        "Copyright &#169; 2019 Sergei Lyashko.<br>"+
        "Contacts: <a href=mailto:slyashko@mail.ru>�������� ������</a>";
        return text;
    }   
}
