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

//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/dnd/ChooseDropActionDemoProject/src/dnd/ChooseDropActionDemo.java

package calcmassview;

import calcmassview.viewpanelcomponent.MenuBoxModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.TransferHandler;

/**
 *
 * @author Korvin
 */
// окно выбора элементов меню выпадающих списков
public class CustomMenuFrame extends JFrame {

    // модель списка
    private static MenuBoxModel profileModel;
    private MenuBoxModel fromModel;   
    
    //private DefaultListModel fromModel = new DefaultListModel();
    //private DefaultListModel toModel = new DefaultListModel();
    
    // 
    private JList<String> profileList, fromList, toList;
    
    // панели    
    private final JPanel panel, panelLeft, profilePanelList, fromPanelList, toPanelList;
    
    public CustomMenuFrame(){
        super("Преобразование меню выпадающих списков");
        super.setResizable(false);
        super.setSize(300, 400);
        super.setLocationRelativeTo(this);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);       
        
        panel = new JPanel(new GridLayout(1, 2));
        panelLeft = new JPanel(new GridLayout(2, 1));               
        
        //панель списка профилей
        profilePanelList = new JPanel();       
        //панель полного списка номеров профилей
        fromPanelList = new JPanel();       
        fromPanelList.setBackground(Color.red);
        //панель собственного списка номеров профилей
        toPanelList = new JPanel();        
        toPanelList.setBackground(Color.blue);
        
        //список профилей
        profileList = new JList(profileModel);
        profileList.setFont(new Font("Serif", Font.BOLD, 15));
        profilePanelList.add(profileList);
        
        //fromList = new JList(fromModel);
        //fromPanelList.add(fromList);
        //fromList.setTransferHandler(new FromTransfer());
        //fromList.setPrototypeCellValue("????????????????????????????????????????");
        //fromList.setDragEnabled(true);
        //fromList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);       
        
        //toList = new JList(toModel);
        //toList.setTransferHandler(new ToTransfer(TransferHandler.MOVE));
        //toList.setDropMode(DropMode.INSERT);       
        
        panelLeft.add(profilePanelList);
        panelLeft.add(toPanelList);       
        panel.add(panelLeft);
        panel.add(fromPanelList);        
        super.add(panel);       
    }
    
    public static void setProfileModelList(MenuBoxModel model){
        CustomMenuFrame.profileModel = model;
    }   
    
    /*
    // класс трансвера из
    class FromTransfer extends TransferHandler {
        
        @Override
        public int getSourceActions(JComponent comp) {
            return COPY_OR_MOVE; // javax.swing.TransferHandler.COPY_OR_MOVE;
        }
        
        private int index = 0;
        @Override
        public Transferable createTransferable(JComponent comp) {
            index = fromList.getSelectedIndex();
            if (index < 0 || index >= fromModel.getSize()) {
                return null;
            } 
            return new StringSelection((String)fromList.getSelectedValue());
        }
    }
    
    class ToTransfer extends TransferHandler {
        
        private final int action;
         
        public ToTransfer(int action) {
            this.action = action;
        }
         
        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            // только drug-n-drops без копирования в буфер обмена
            if (!support.isDrop()) {
                return false;
            }
 
            // только импорт строк
            if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return false;
            }
 
            boolean actionSupported = (action & support.getSourceDropActions()) == action;
            if (actionSupported) {
                support.setDropAction(action);
                return true;
            }
 
            return false;
        }
    }
*/
}
