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
        "����������.<br>"+
        "������������ ����� ��������� ����� ������������<br>"+
        "� ������� ������ <button>Tab</button> (��� <button>Shift</button> + <button>Tab</button>) ����������<br>"+
        "(���� � ������� ����).<br>"+
        "��������� <button>&#8595;</button> � <button>&#8593;</button> (���� � ������� ����) ��������������<br>"+
        "����� ������� � ���� ���������� �������.<br>"+
        "���� ����� ����� ���������� ������� ��� ������<br>"+
        "������������ ������� � ������� ������ ���������� ����.<br>"+
        "���� ����� ������ ���������� ������� � ��� �� ����� ������,<br>"+
        "� ����� ���� ������� ������, ������� ������ (����, �������� � �.�.).<br>"+
        "������������ ����� ������ ����� �������� ��������������<br>"+
        "��� �� ���� ���������� ��� �� ������� <button>Enter</button><br>"+
        "��������� ���������� ��������� � ���� �����<br>"+
        "�� ������� <button>Enter</button> � ������������� ���������� � ����� ������<br>"+
        "��� ������� � ��������� ���������� �� ������� <button>Ctrl</button>+<button>V</button><br>"+
        "<br>"+
        "��� �������� ������������ ��������� �����:<br>"+
        "<ul><br>"+
        "<li>����� ��������� ������� ������������� <a href=http://docs.cntd.ru/document/1200001025>���� 8509-93</a><br>"+
        "<li>����� ��������� ������� ��������������� <a href=http://docs.cntd.ru/document/1200001023>���� 8510-86</a><br>"+
        "<li>����� ��������� �������� <a href=http://docs.cntd.ru/document/1200019824>���� 8240-97</a><br>"+
        "<li>����� ������������� ���������� <a href=http://docs.cntd.ru/document/1200109199>���� 2591-51</a><br>"+
        "<li>������ �������� ������������� ������� <a href=http://docs.cntd.ru/document/1200004404>���� 2590-88</a><br>"+
        "<li>����� �������� � ����������� ���������. <a href=http://docs.cntd.ru/document/1200005122/>���� 8568-77</a><br>"+
        "<li>�������� ��������� � �������������� <a href=http://docs.cntd.ru/document/1200005719>���� 7338-90</a><br>"+
        "</ul><br>"+
        "Copyright &#169; 2019 Sergei Lyashko.<br>"+
        "Contacts: <a href=mailto:slyashko@mail.ru>�������� ������</a><br>";
        return text;
    }*/

    private void displayURL(URL url) {
        try{
            super.setPage(url);
        }catch(IOException e){
            System.err.println("Html ���� ��� ����������� ������� �� ������ "+ e);
        }
    }
}
