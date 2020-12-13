package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

class AppFrame implements Serializable {

    private static final long serialVersionUID = 1L;

    private final JTabbedPane jTabbedPane;

    AppFrame(AppPanel calculatorPanel, AppPanel settingsPanel, AppPanel infoPanel) {
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JFrame jFrame = new JFrame("Industrial calculator");
        jFrame.setSize(360, 220);
        jFrame.setLocationByPlatform(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("make save");
                System.exit(0);
            }
        });
        jFrame.setContentPane(jTabbedPane);
        addPanel("Калькулятор", calculatorPanel);
        addPanel("Настройки", settingsPanel);
        addPanel("Справка", infoPanel);
    }

    private void addPanel(String type, AppPanel panel){
        Container parentContainer = panel.getParent();
        jTabbedPane.add(type, parentContainer);
    }
}
